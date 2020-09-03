package com.sixin.iot.service;

/**
 * @program: RongSys
 * @description:
 * @author: Mr.Liu
 * @create: 2020-04-15 21:44
 **/

public interface CameraRecorder {
    public void recorder(String url);
    public int stop(String url);
}
