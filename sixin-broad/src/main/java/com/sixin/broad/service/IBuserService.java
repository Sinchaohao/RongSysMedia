package com.sixin.broad.service;

import com.sixin.broad.domain.Buser;

import java.util.List;

/**
 * 根据用户对广播数目的统计
 * @author 周博
 * @date 2019-03-21
 */

public interface IBuserService {

    public List<Buser> selectProgramsByUser();

    public List<Buser> selectProgramsByLenth(String deptId);

    public List<Buser> selectProgramsIfPublic(String deptId);

    public List<Buser> selectProgramsPtp(String deptId);
}
