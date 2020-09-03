package com.sixin.broad.service.impl;

import com.sixin.broad.domain.BroadCount;
import com.sixin.broad.mapper.BcountMapper;
import com.sixin.broad.service.IBcountService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公共节目单
 *
 * @author 周博
 * @date 2019-03-22
 */
@Service
public class BcountServiceImpl implements IBcountService {
    @Autowired
    private BcountMapper bcountMapper;

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<BroadCount> select(BroadCount broadCount) {
        return bcountMapper.select(broadCount);
    }
}
