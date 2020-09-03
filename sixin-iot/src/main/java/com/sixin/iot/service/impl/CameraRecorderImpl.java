package com.sixin.iot.service.impl;

import ch.qos.logback.core.util.ExecutorServiceUtil;
import com.sixin.iot.service.CameraRecorder;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: RongSys
 * @description:
 * @author: Mr.Liu
 * @create: 2020-04-15 21:45
 **/
@Service
public class CameraRecorderImpl implements CameraRecorder {
    @Value("${sixin.profile}")
    private String path ;
    private static final Logger logger = LoggerFactory.getLogger(CameraRecorderImpl.class);
    ExecutorService executors = Executors.newCachedThreadPool();
    Map<String,ThreadRecorder> map = new ConcurrentHashMap<>();
    @Override
    public void recorder(String url) {

        Thread thread = new Thread( new Runnable() {
            ThreadRecorder threadRecorder = new ThreadRecorder(url,path+"/recorde"+System.currentTimeMillis()+".mp4");
            @Override
            public void run() {
                try {
                    map.put(url,threadRecorder);
                    System.out.println(threadRecorder.toString());
                    threadRecorder.frameRecord(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },url);
        executors.submit(thread);

    }

    @Override
    public int stop(String url) {
        ThreadRecorder t = map.get(url);
        if(t != null){
            t.tag = false;
        }
        return 1;
    }

    class ThreadRecorder{
        public volatile  Boolean tag = true;
        private String url;
        private String outputFile;
        public ThreadRecorder(String url,String outputFile) {
            this.url = url;
            this.outputFile = outputFile;
        }

        @Override
        public String toString() {
            return "ThreadRecorder{" +
                    "tag=" + tag +
                    ", url='" + url + '\'' +
                    ", outputFile='" + outputFile + '\'' +
                    '}';
        }

        /**
         * 按帧录制视频
         *
         * @param inputFile-该地址可以是网络直播/录播地址，也可以是远程/本地文件路径
         * @param outputFile
         *            -该地址只能是文件地址，如果使用该方法推送流媒体服务器会报错，原因是没有设置编码格式
         * @throws FrameGrabber.Exception
         * @throws FrameRecorder.Exception
         * @throws org.bytedeco.javacv.FrameRecorder.Exception
         */
        public  void frameRecord(int audioChannel)
                throws Exception, org.bytedeco.javacv.FrameRecorder.Exception {

            //isStart 该变量建议设置为全局控制变量，用于控制录制结束
            // 获取视频源
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(url);
            // 流媒体输出地址，分辨率（长，高），是否录制音频（0:不录制/1:录制）
            FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputFile, 1280, 720, audioChannel);
            // 开始取视频源
            logger.info("开始录制视频==>>流地址：{}",url);
            recordByFrame(grabber, recorder);
        }

        private  void recordByFrame(FFmpegFrameGrabber grabber, FFmpegFrameRecorder recorder)
                throws Exception, org.bytedeco.javacv.FrameRecorder.Exception {
            try {//建议在线程中使用该方法
                grabber.start();
                recorder.start();
                Frame frame = null;
                while (tag && (frame = grabber.grabFrame()) != null) {
                    recorder.record(frame);
                }
                recorder.stop();
                grabber.stop();
            } finally {
                if (grabber != null) {
                    grabber.stop();
                }
                if(recorder != null){
                    recorder.stop();
                }
                logger.info("停止录制");
            }
        }
    }
}
