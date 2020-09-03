package com.sixin.broad.service.impl;

import com.sixin.broad.domain.Program;
import com.sixin.broad.mapper.ProgramMapper;
import com.sixin.broad.service.IProgramService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.PersonApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: RongSys-lyb
 * @description: 节目单管理 服务层实现
 * @author: Mr.Liu
 * @create: 2019-02-26 19:45
 **/
@Service
public class ProgramServiceImpl implements IProgramService {
    @Autowired
    private ProgramMapper programMapper;

    /**
     * 查询节目库数据
     * @param program 节目实体类
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Program> selectProList(Program program) {
        return programMapper.selectProList(program);
    }


    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public String getMaxFileidofYear(String year) {
        return programMapper.getMaxFileidofYear(year);
    }

    /**
     * 上传节目单
     * @param program 节目单
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int insertProgram(Program program) {
        return programMapper.insertProgram(program);
    }


    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int deleteProgram(String fid){
        return programMapper.deleteProgram(Convert.toStrArray(fid));
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Program> selectProgramListByfids(List<String> sfids) {
        return programMapper.selectProgramListByfids(sfids);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int setIsPublic(String fid){
        return programMapper.setIsPublic(fid);
    }
}
