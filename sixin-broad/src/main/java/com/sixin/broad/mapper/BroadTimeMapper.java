package com.sixin.broad.mapper;

import com.sixin.broad.domain.BroadTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 根据时间和地区对广播数目的统计
 * @author 周博
 * @date 2019-01-15
 */
@Mapper
public interface BroadTimeMapper {

    public List<BroadTime> selectList(@Param("deptId")Long deptId);

    public List<BroadTime> selectListByDate(@Param("deptId")Long deptId);

    public  List<BroadTime> selectListByScategory(@Param("deptId")Long deptId);
}
