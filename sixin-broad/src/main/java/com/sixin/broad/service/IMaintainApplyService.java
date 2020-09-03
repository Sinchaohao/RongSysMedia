package com.sixin.broad.service;

import com.sixin.broad.domain.MaintainApply;

import java.util.List;

/**
 * Created by ASUS on 2019/7/12.
 * @author 陈霞
 */
public interface IMaintainApplyService {
    /**
     * 查询终端维护记录信息
     *
     * @param maid 申请维护记录ID
     * @return 申请维护记录信息
     */
    public MaintainApply selectMaintainApplyById(String maid);

    /**
     * 查询申请维护记录信息
     *
     *
     * @return 所有申请维护记录信息
     */
    public List<MaintainApply> selectMaintainApplyList(MaintainApply maintainApply);
    /**
     * 查询申请维护记录信息
     *
     *
     * @return 申请维护记录信息
     */
    public List<MaintainApply> selectAllMaintainApply();
    /**
     * 删除申请维护记录信息
     *
     *
     * @return 申请维护记录信息
     */

    public int deleteMaintainApplyById(String maid);
    /**
     * 插入申请维护记录信息
     *
     *
     * @return 申请维护记录信息
     */
    public int insertMaintainApply(MaintainApply maintainApply);
    /**
     * 更新申请维护记录信息
     *
     *
     * @return 申请维护记录信息
     */
    public int updateMaintainApply(MaintainApply maintainApply);
}
