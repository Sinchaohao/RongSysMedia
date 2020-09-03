package com.sixin.common.utils;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @program: FFPS
 * @description: Redis工具类
 * @author: Mr.Liu
 * @create: 2019-05-11 21:57
 **/
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name="redisTemplate")
    private ValueOperations<String, String> valueOperations;
//    @Resource(name="redisTemplate")
//    private HashOperations<String, String, Object> hashOperations;
//    @Resource(name="redisTemplate")
//    private ListOperations<String, Object> listOperations;
//    @Resource(name="redisTemplate")
//    private SetOperations<String, Object> setOperations;
//    @Resource(name="redisTemplate")
//    private ZSetOperations<String, Object> zSetOperations;

    /**  默认过期时长，单位：秒 */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**  不设置过期时长 */
    public final static long NOT_EXPIRE = -1;

    /**
     * 设置访问次数的
     * @param key
     */
    public void addOne(String key){
            //val做+1操作
            stringRedisTemplate.boundValueOps(key).increment(1);
        System.out.println("1123232321312--------"+stringRedisTemplate.opsForValue().get(key));
    }
    public void set2(String key, int value, long expire){
        valueOperations.set(key, toJson(value));
        if(expire != NOT_EXPIRE){
            stringRedisTemplate.opsForValue().set(key, String.valueOf(value),expire,TimeUnit.SECONDS);
        }
    }

    public boolean checkval(String key,int num){
        if(stringRedisTemplate.opsForValue().get(key)!=null){
            if(num>=Integer.valueOf(Objects.requireNonNull(stringRedisTemplate.opsForValue().get(key)))){
                return true;
            }else {
                return false;
            }

        }else {
            return true;
        }
    }
    public String get2(String key, long expire) {
        return stringRedisTemplate.opsForValue().get(key);
    }



    public void set(String key, Object value, long expire){
        try{
            valueOperations.set(key, toJson(value));
            if(expire != NOT_EXPIRE){
                redisTemplate.expire(key, expire, TimeUnit.SECONDS);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void set(String key, Object value){
        set(key, value, DEFAULT_EXPIRE);
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public String get(String key,String value) {
        return stringRedisTemplate.opsForValue().get(key);
    }
    /**
     * 删除key
     *
     * @param key
     * @return
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }
    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public Boolean exists(String key) {

        try {
            if(get(key)!=null|| get(key).equals("")){
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    /**
     * Object转成JSON数据
     */
    public String toJson(Object object){
        if(object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String){
            return String.valueOf(object);
        }
        return JSON.toJSONString(object);
    }

    /**
     * JSON数据，转成Object
     */
    public  <T> T fromJson(String json, Class<T> clazz){
        return JSON.parseObject(json, clazz);
    }

    /**
     *
     * 下面是比较牛逼的方法，存储的 K 为String
     * 如需其他请自行更改
     *
     ***/

    private static String LOCK_PREFIX = "redisLock";
    private static int LOCK_EXPIRE = 5;
    /**
     * 最终加强分布式锁
     * @param key key值
     * @return 是否获取到
     */
    public boolean addLock(String key) {
        String lock = LOCK_PREFIX + key;
        // 利用lambda表达式
        return (Boolean) redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                long expireAt = System.currentTimeMillis() + LOCK_EXPIRE + 1;
                Boolean acquire = redisConnection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
                if (acquire) {
                    return true;
                } else {
                    byte[] value = redisConnection.get(lock.getBytes());
                    if (Objects.nonNull(value) && value.length > 0) {
                        long expireTime = Long.parseLong(new String(value));
                        if (expireTime < System.currentTimeMillis()) {
                            // 如果锁已经过期
                            byte[] oldValue = redisConnection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE + 1).getBytes());
                            // 防止死锁
                            return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                        }
                    }
                }
                return false;
            }
        });
    }
    /**
     * 删除锁
     *
     * @param key
     */
    public void deleteLock(String key) {
        redisTemplate.delete(key);
    }
    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String ... key){
        if(key!=null&&key.length>0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key,long time){
        try {
            if(time>0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }
    /**
     * 递增
     * @param key 键
     * @return
     */
    public long incr(String key, long delta){
        if(delta<0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }
    /**
     * 递减
     * @param key 键
     * @return
     */
    public long decr(String key, long delta){
        if(delta<0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }
    /**
     * 特殊方法 1
     * hash 一个map集合进去
     */
    public boolean hashSetAll(String k, Map<String,Object> map){
        try {
            redisTemplate.opsForHash().putAll(k, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * HashSet 并设置时间
     * @param key 键
     * @param map 对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hashSetAllTime(String key, Map<String,Object> map, long time){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if(time>0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 特殊方法 2
     * 修改hash 中的某个属性的值
     */
    public boolean hashUpdate(String k, Object h1,Object h2){
        if(redisTemplate.opsForHash().get(k,h1)!=null){
            redisTemplate.opsForHash().put(k,h1,h2);
            return true;
        }else {
            System.err.println("不存在这个属性");
            return false;
        }
    }

    /**
     * HashGet 获取一个属性的值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hashGet(String key,Object item){
        return redisTemplate.opsForHash().get(key, item);
    }
    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object,Object> hashGetAll(String key){
        return redisTemplate.opsForHash().entries(key);
    }
    /**
     * 删除hash表中的值
     * @param key 键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hashdel(String key, Object... item){
        redisTemplate.opsForHash().delete(key,item);
    }
    /**
     * 判断hash表中是否有该项的值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hashHasKey(String key, String item){
        return redisTemplate.opsForHash().hasKey(key, item);
    }
    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @param key 键
     * @param item 项
     * @param by 要增加几(大于0)
     * @return
     */
    public double hashIncr(String key, String item,double by){
        return redisTemplate.opsForHash().increment(key, item, by);
    }
    /**
     * hash递减
     * @param key 键
     * @param item 项
     * @param by 要减少记(小于0)
     * @return
     */
    public double hashDecr(String key, String item,double by){
        return redisTemplate.opsForHash().increment(key, item,-by);
    }

    /**
     * SET类型相关操作
     **/
    /**
     * 根据key获取Set中的所有值
     * @param key 键
     * @return
     */
    public Set<Object> setGet(String key){
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 根据value从一个set中查询,是否存在
     * @param key 键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean setHasKey(String key,Object value){
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 将数据放入set缓存
     * @param key 键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long setSet(String key, Object...values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * 将set数据放入缓存 并添加过期时间
     * @param key 键
     * @param time 时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long setSetAndTime(String key,long time,Object...values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if(time>0) expire(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * 获取set缓存的长度
     * @param key 键
     * @return
     */
    public long setGetSetSize(String key){
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * 移除值为value的
     * @param key 键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object ...values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * LIST类型相关操作 底层是双端链表
     */

    /**
     * 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束  0 到 -1代表所有值
     * @return
     */
    public List<Object> listGet(String key, long start, long end){
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获取list缓存的长度
     * @param key 键
     * @return
     */
    public long listGetListSize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * 通过索引 获取list中的值
     * @param key 键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object listGetByIndex(String key,long index){
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 将从list的右边放入数据 ，一般采用这个方法
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean listRightSet(String key, Object value) {
        try {
            //从双端链表的右边push进去
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 将从list的左边弹出数据
     * @param key 键
     * @return
     */
    public Object listLiftPop(String key) {
        try {

            return redisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 将从list的右边放入数据 并设置过期时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean listRightSetAddTime(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 直接将整个list 存到list中
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean listRightSetAll(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 直接将整个list 存到list中 并设置过期时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean listSetAllAddTime(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean listUpdateIndex(String key, long index,Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long listRemove(String key,long count,Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
