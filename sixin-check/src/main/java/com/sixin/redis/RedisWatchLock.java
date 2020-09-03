package com.sixin.redis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

/**
 * redis CAS乐观锁
 *
 * @author 周博
 * @date 2019-5-12
 */
public class RedisWatchLock {
    public static final String redisHost = "127.0.0.1";
    public static final String redisPassword = "123456";
    public static final String redisSocketSet = "chat";
    public static final String redisNumber = "chatNumber";

    private static final int port = 6379;

    private static JedisPoolConfig config;

    public static JedisPool pool;

    private static ExecutorService service;

    private static int ThLeng = 10;

    private static CountDownLatch latch;

    private static AtomicInteger Countor = new AtomicInteger(0);

    static {
        //利用Redis连接池，保证多个线程利用多个连接，充分模拟并发性
        config = new JedisPoolConfig();
        config.setMaxIdle(10);
        config.setMaxWaitMillis(1000);
        config.setMaxTotal(30);
        pool = new JedisPool(config, redisHost, port);
        //利用ExecutorService 管理线程
        service = Executors.newFixedThreadPool(10);
        //CountDownLatch保证主线程在全部线程结束之后退出
        latch = new CountDownLatch(ThLeng);
    }

    public static class TestThread implements Runnable {
        private Jedis jedis;
        private JedisPool pool;
        private String text;

        public TestThread(String text) {
            this.pool = RedisWatchLock.pool;
            jedis = pool.getResource();
            this.text = text;
            jedis.auth(redisPassword);
        }

        @Override
        public void run() {
            try {
                actomicAdd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void actomicAdd() {
            boolean flag = true;
            while (flag) {
                String number = jedis.get(redisNumber);
                int n = Integer.valueOf(number);
                jedis.zadd(redisSocketSet, n, text);
                jedis.set(redisNumber, String.valueOf(++n));
                Set<String> set = jedis.zrange(redisSocketSet, n, n);
                String textInSet = "";
                for (String i : set)
                    textInSet += i;
                if (!textInSet.equals(text)) {
                    continue;
                } else {
                    //如果达到期望值那么结束while循环
                    flag = false;
                }
            }
            Countor.incrementAndGet();
        }

    }

}