package com.sixin.broad.service;

import com.sixin.broad.domain.Tempgroup;

import java.util.List;

/**
 * Created by ASUS on 2019/7/29.
 * @author 陈霞
 */

public interface ITempgroupService {

    /**
     * 查询终端临时列表
     *
     * @return 终端临时列表
     */
    public List<Tempgroup> selectAllTempgroup(String tgname);

    /**
     * 查询终端列表 详细
     *
     *
     * @return 单条终端列表记录
     */
    public Tempgroup selectTempgroupById(String aid);

    /**
     * 更新终端临时列表
     *
     * @return 终端临时列表
     */
    public int updateTempgroup(Tempgroup tempgroup);

    /**
     * 删除终端临时列表
     *
     * @return 终端临时列表
     */
    public int deleteTempgroup(String createdtime);

    /**
     * 插入终端临时列表
     *
     * @return 终端临时列表
     */
    public int insertTempgroup(Tempgroup tempgroup);

    /**
     * 临时分组列表
     *
     * @return 终端临时列表
     */

    public List<Tempgroup> selectTempgroup();

    //新增tempgroup_ter
    public int insertTempgroupT(Tempgroup tempgroup);

    public int deleteTempgroupT(String tid);
}
