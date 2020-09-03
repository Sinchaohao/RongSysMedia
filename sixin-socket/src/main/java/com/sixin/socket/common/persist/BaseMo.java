package com.sixin.socket.common.persist;

/**
 * @program: ruoyi
 * @description: 基础类
 * @author: Mr.Liu
 * @create: 2020-03-24 11:12
 **/
public interface BaseMo {
    /**
     * 插入环境数据
     * @param msg
     * @return
     */
    public boolean insert(Object msg);

    /**
     * 更新终端状态
     * @param msg
     * @return
     */
    public boolean update(Object msg);
}
