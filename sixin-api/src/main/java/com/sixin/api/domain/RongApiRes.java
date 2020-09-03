package com.sixin.api.domain;

import com.sixin.common.base.AjaxResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 融媒体Api接口的response模型
 *
 * @author 张超
 */
public class RongApiRes extends HashMap<String, Object>{

    private static final long serialVersionUID = 1L;


    /**
     * code:0 成功，code:1 失败，code:500 失败
     */
    private int code;
    /**
     * 状态信息
     */
    private String msg;
    /**
     * 时间戳
     */
    private String time;

    /**
     * 初始化一个新创建的 Message 对象，并初始化时间戳
     */
    public RongApiRes(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        this.put("time",date);
    }


    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static RongApiRes error()
    {
        return error(1, "接口调用失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @return 错误消息
     */
    public static RongApiRes error(String msg)
    {
        return error(500, msg);
    }

    /**
     * 返回错误消息
     *
     * @param code 错误码
     * @param msg 内容
     * @return 错误消息
     */
    public static RongApiRes error(int code, String msg)
    {
        RongApiRes json = new RongApiRes();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @param msg 内容
     * @return 成功消息
     */
    public static RongApiRes success(String msg)
    {
        RongApiRes json = new RongApiRes();
        json.put("msg", msg);
        json.put("code", 0);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static RongApiRes success()
    {
        return RongApiRes.success("接口调用成功");
    }

    /**
     * 返回成功消息
     *
     * @param key 键值
     * @param value 内容
     * @return 成功消息
     */
    @Override
    public RongApiRes put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }

}
