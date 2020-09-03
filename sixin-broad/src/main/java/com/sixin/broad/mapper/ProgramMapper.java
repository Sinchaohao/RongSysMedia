package com.sixin.broad.mapper;

import com.sixin.broad.domain.Program;
import com.sixin.village.domain.PersonApi;

import java.util.List;

/**
 * @program: RongSys-lyb
 * @description: 节目上传 数据层
 * @author: Mr.Liu
 * @create: 2019-02-26 19:34
 **/

public interface ProgramMapper {

    /**
     * 查询节目库数据
     * @param program 节目实体类
     * @return 结果
     */
    public List<Program> selectProList(Program program);


    /**
     * 得到当年文件最大编号
     * @param year
     * @return
     */
    public String getMaxFileidofYear(String year);

    /**
     * 新增节目
     * @param program 节目名称
     * @return 结果
     */
    public int insertProgram(Program program);

    /**
     * 删除节目
     * @param fid 节目编号
     * @return 结果
     */
    public int deleteProgram(String[] fid);

    public List<Program> selectProgramListByfids(List<String> sfids);

    public int setIsPublic(String fid);
}
