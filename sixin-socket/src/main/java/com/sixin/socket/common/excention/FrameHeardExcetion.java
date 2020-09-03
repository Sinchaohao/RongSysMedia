package com.sixin.socket.common.excention;

/**
 * @program: ruoyi
 * @description: 包头错误异常
 * @author: Mr.Liu
 * @create: 2020-03-21 12:56
 **/
public class FrameHeardExcetion extends RuntimeException{
    static final long serialVersionUID = -2848938806368998894L;

    public FrameHeardExcetion() {
    }

    public FrameHeardExcetion(String message) {
        super(message);
    }

    public FrameHeardExcetion(String message, Throwable cause) {
        super(message, cause);
    }

    public FrameHeardExcetion(Throwable cause) {
        super(cause);
    }

    public FrameHeardExcetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
