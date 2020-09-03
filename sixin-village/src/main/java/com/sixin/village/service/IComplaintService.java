package com.sixin.village.service;

import com.sixin.village.domain.Complaint;

import java.util.List;

/**
 * @program: lyb_RongSys
 * @description: 投诉咨询Service
 * @author: Mr.Liu
 * @create: 2019-01-14 15:37
 **/

public interface IComplaintService {

    /**
     * 查询投诉咨询记录列表
     *
     * @return
     */
    public List<Complaint> selectComplaintsList(Complaint complaintmessages);

    /**
     * 根据fbid删除数据
     * @param fbid
     */
    public int deleteComplaintsByids(String fbid);

    /**
     * 添加投诉咨询数据
     * @param complaint
     * @return
     */
    public int insertcomplaintsmessages(Complaint complaint);
    /**
     * 根据fbid选择数据
     * @param fbid
     * @return
     */
    public Complaint selectByfbid(Integer fbid);
    /**
     * 保存用户修改
     * @param complaint
     * @return
     */
    public int updateComplaintsmessages(Complaint complaint);

}
