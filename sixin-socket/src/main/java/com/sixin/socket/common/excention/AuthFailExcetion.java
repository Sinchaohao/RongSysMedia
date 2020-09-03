package com.sixin.socket.common.excention;

/**
 * @program: ruoyi
 * @description: 包头错误异常
 * @author: Mr.Liu
 * @create: 2020-03-21 12:56
 **/
public class AuthFailExcetion extends RuntimeException{
    static final long serialVersionUID = -2848938806368998894L;

    public AuthFailExcetion() {
    }

    public AuthFailExcetion(String message) {
        super(message);
    }

    public AuthFailExcetion(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthFailExcetion(Throwable cause) {
        super(cause);
    }

    public AuthFailExcetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
