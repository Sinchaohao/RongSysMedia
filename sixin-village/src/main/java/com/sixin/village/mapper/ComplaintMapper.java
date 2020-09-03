package com.sixin.village.mapper;

import com.sixin.village.domain.Complaint;

import java.util.List;

/**
 * @program: lyb_RongSys
 * @description: 投诉咨询mapper
 * @author: Mr.Liu
 * @create: 2019-01-14 15:34
 **/

public interface ComplaintMapper {
    /**
     * 查询投诉咨询记录列表
     *
     * @return
     */
    List<Complaint> selectComplaintsList(Complaint complaintmessages);

    /**
     * 根据fbid删除数据
     * @param fbid
     */
    int deleteComplaintsByids(String[] fbid);

    /**
     * 添加投诉咨询
     * @param complaint
     * @return
     */
    int insertcomplaintsmessages(Complaint complaint);

    /**
     * 根据fbid选择数据
     * @param fbid
     * @return
     */
    Complaint selectByfbid(Integer fbid);

    /**
     * 保存用户修改
     * @param complaint
     * @return
     */
    int updateComplaintsmessages(Complaint complaint);

}
