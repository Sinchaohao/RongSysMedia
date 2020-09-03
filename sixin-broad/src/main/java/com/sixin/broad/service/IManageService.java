package com.sixin.broad.service;

import com.sixin.broad.domain.Manage;

import java.util.List;

/**
 * @author cx
 * @Time 2019/8/20 15:58
 * @Description 管理参数 服务层接口
 */
public interface IManageService {

    /**
     * @author cx
     * @param manage
     *
     * @Description 查询管理参数列表
     */
    public List<Manage> selectManagelist(Manage manage);

    /**
     * @author cx
     * @param manage
     *
     * @Description 更新参数列表
     */
    public int updateManage(Manage manage);

    /**
     * @author cx
     * @param manage
     *
     * @Description 插入参数列表
     */
    public int insertManage(Manage manage);
}
