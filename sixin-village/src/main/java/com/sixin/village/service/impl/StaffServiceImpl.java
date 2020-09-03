package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.village.domain.Staff;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.mapper.StaffMapper;
import com.sixin.village.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作员信息表 服务层实现
 *
 * @author 薛莎莎
 * @date 2019-11-25
 */
@Service
public class StaffServiceImpl implements IStaffService{
    @Autowired
    public StaffMapper staffMapper;

    /**
     * 查询工作员信息
     *
     * @param stid 工作员信息ID
     * @return 工作员信息
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public Staff selectStaffById(String stid) {
        return staffMapper.selectStaffById(stid);
    }

    /**
     * 查询工作员信息列表
     *
     * @param staff 工作员信息
     * @return 工作员信息列表
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Staff> selectStaffList(Staff staff) {
        return staffMapper.selectStaffList(staff);
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Staff> selectStaffListById(pubObjApi staff) {
        return staffMapper.selectStaffListById(staff);
    }
}
