package com.sixin.broad.service;

import com.sixin.broad.domain.BroadTime;

import java.util.List;

/**
 * 根据时间和地区对广播数目的统计
 * @author 周博
 * @date 2019-01-15
 */
public interface IBroadTimeService {

    public List<com.sixin.broad.domain.BroadTime> selectList(String deptId);

    public List<com.sixin.broad.domain.BroadTime> selectListByDate(String deptId);

    public  List<BroadTime> selectListByScategory(String deptId);
}
