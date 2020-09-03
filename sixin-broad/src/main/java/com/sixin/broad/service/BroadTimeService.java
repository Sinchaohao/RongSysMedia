package com.sixin.broad.service;

import com.sixin.broad.domain.BroadTime;

import java.util.List;

/**
 * 根据时间和地区对广播数目的统计
 * @author 周博
 * @date 2019-01-15
 */
public interface BroadTimeService {

    public List<BroadTime> selectList();

    public List<BroadTime> selectListByDate();

    public  List<BroadTime> selectListByScategory();
}
