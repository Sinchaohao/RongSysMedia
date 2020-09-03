package com.sixin.streamsocket.listener;

import com.sixin.broad.utils.bConst;
import com.sixin.broadserver.global.GlobalInfo;
import com.sixin.broadserver.server.MinaCastThread;
import com.sixin.streamsocket.server.StreamServer;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.util.Locale;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 张超 teavamc
 * @Description: 线程监听器
 * @ClassName StreamSocketListener
 * @date 2019/2/17 19:47
 **/
// SpringBoot 监听器
@WebListener
public class StreamSocketListener implements ServletContextListener {

    /**
     *  获取活跃的 cpu数量
     */
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private final static BlockingQueue<Runnable> mWorkQueue;
    private final static long KEEP_ALIVE_TIME = 3L;
    private final static TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private static ThreadFactory mThreadFactory;
    static {
        mWorkQueue = new LinkedBlockingQueue<Runnable>();
        //默认的工厂方法将新创建的线程命名为：pool-[虚拟机中线程池编号]-thread-[线程编号]
        //mThreadFactory= Executors.defaultThreadFactory();
        mThreadFactory = new StreamSocketListener.IOThreadFactory();
        //        System.out.println("NUMBER_OF_CORES:"+NUMBER_OF_CORES);
    }
    // 流媒体线程
    private StreamSocketThread socketThread;

    /**
     * 监听环境关闭
     * 服务器关闭时候执行
     *
     * @param e
     * @return void
     * @author 张超 teavamc
     * @date 2019/2/17
     */
    @Override
    public void contextDestroyed(ServletContextEvent e) {
        if (socketThread != null && !socketThread.isInterrupted()) {
            socketThread.interrupt();
        }
        //关闭线程池
        GlobalInfo.getExecutorService().shutdown();

        if (GlobalInfo.getCommandThread().getAcceptor() != null){
            GlobalInfo.getCommandThread().getAcceptor().unbind();
            GlobalInfo.getCommandThread().getAcceptor().setCloseOnDeactivation(true);
            GlobalInfo.getCommandThread().getAcceptor().dispose();
        }
        if (GlobalInfo.getIOTThread().getAcceptor() != null){
            GlobalInfo.getIOTThread().getAcceptor().unbind();
            GlobalInfo.getIOTThread().getAcceptor().setCloseOnDeactivation(true);
            GlobalInfo.getIOTThread().getAcceptor().dispose();
        }

    }

    /**
     * 监听环境初始化
     *
     * @param e
     * @return void
     * @author 张超 teavamc
     * @date 2019/2/17
     */
    @Override
    public void contextInitialized(ServletContextEvent e) {
        System.out.println("Server contextInitialized over");
        if (socketThread == null) {
            // 创建S treamSocketThread
            socketThread = new StreamSocketThread(null);
            // 开启线程
            socketThread.start();
        }
        //初始化线程池
        GlobalInfo.setExecutorService(new ThreadPoolExecutor((int) (NUMBER_OF_CORES)+2,
                NUMBER_OF_CORES * 2+4, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT,
                mWorkQueue,mThreadFactory));//创建一个处理IO的线程池
        //初始化组播全局信息
        MinaCastThread CommandThread = new MinaCastThread(bConst.CommdPort,bConst.COMMANDTYPE,1,2,10);
        CommandThread.run();
        GlobalInfo.setCommandThread(CommandThread);
        MinaCastThread IOTThread = new MinaCastThread(bConst.IOTPort,bConst.IOTTYPE,1,2,15);
        IOTThread.run();
        GlobalInfo.setIOTThread(IOTThread);
        System.out.println("MinaServer is started");
    }

    private static class IOThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumberAtomicInteger = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            Thread thread=  new Thread(r,String.format(Locale.CHINA,"%s%d","IOThreadFactory",threadNumberAtomicInteger.getAndIncrement()));
            /* thread.setDaemon(true);//是否是守护线程
            thread.setPriority(Thread.NORM_PRIORITY);//设置优先级 1~10 有3个常量 默认 Thread.MIN_PRIORITY*/
            return thread;
        }
    }
}

/**
 * 流媒体线程
 *
 * @author 张超 teavamc
 * @date 2019/2/17
 */
class StreamSocketThread extends Thread {

    Integer count = 0;
    private StreamServer serverSocket;

    /**
     * 描述
     *
     * @param serverSocket
     * @return
     * @author 张超 teavamc
     * @date 2019/2/17
     */
    public StreamSocketThread(StreamServer serverSocket) {
        if (serverSocket == null) {
            serverSocket = new StreamServer();
        }
        this.serverSocket = serverSocket;
    }

    public void run() {
        // 绑定线程池
        serverSocket.bind();
    }
}
