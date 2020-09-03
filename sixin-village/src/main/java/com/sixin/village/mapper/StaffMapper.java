package com.sixin.village.mapper;

/**
 * 工作员信息表 数据层
 *
 * @author 薛莎莎
 * @date 2019-11-25
 */

import com.sixin.village.domain.Staff;
import com.sixin.village.domain.pubObjApi;

import java.util.List;
public interface StaffMapper {
    /**
     * 查询工作员信息
     *
     * @param stid
     * @return 工作员信息
     */
    public Staff selectStaffById(String stid);

    /**
     * 查询工作员信息列表
     *
     * @param staff 工作员信息
     * @return 工作员信息集合
     */
    public List<Staff> selectStaffList(Staff staff);

    public List<Staff> selectStaffListById(pubObjApi staff);

}
