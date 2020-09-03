package com.sixin.broad.service;

import com.sixin.broad.domain.BroadCount;

import java.util.List;

/**
 * 公共节目单
 *
 * @author 周博
 * @date 2019-03-20 */
public interface IBcountService {
    public List<BroadCount> select(BroadCount broadCount);
}
