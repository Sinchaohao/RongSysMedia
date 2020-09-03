package com.sixin.broad.mapper;

import com.sixin.broad.domain.BroadCount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公共节目单
 *
 * @author 周博
 * @date 2019-03-22
 */
@Mapper
public interface BcountMapper {

    public List<BroadCount> select(BroadCount broadCount);
}
