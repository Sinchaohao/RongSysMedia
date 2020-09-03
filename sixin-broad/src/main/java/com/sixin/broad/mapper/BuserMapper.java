package com.sixin.broad.mapper;

import com.sixin.broad.domain.Buser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 根据用户对广播数目的统计
 * @author 周博
 * @date 2019-03-21
 */
@Repository
public interface BuserMapper {

    public List<Buser> selectProgramsByUser();

    public List<Buser> selectProgramsByLenth(@Param("deptId")Long deptId);

    public List<Buser> selectProgramsIfPublic(@Param("deptId")Long deptId);

    public List<Buser> selectProgramsPtp(@Param("deptId")Long deptId);
}
