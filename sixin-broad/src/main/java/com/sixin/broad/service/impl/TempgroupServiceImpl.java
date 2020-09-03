package com.sixin.broad.service.impl;

import com.sixin.broad.domain.Tempgroup;
import com.sixin.broad.mapper.TempgroupMapper;
import com.sixin.broad.service.ITempgroupService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/7/29.
 * @author cx
 */
@Service
public class TempgroupServiceImpl implements ITempgroupService{

    @Autowired
    private TempgroupMapper tempgroupMapper;
    /**
     * 查询终端临时列表
     *
     * @return 终端临时列表
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Tempgroup> selectAllTempgroup(String tgname){
        return tempgroupMapper.selectAllTempgroup(tgname);
    }

    /**
     * 查询终端列表 详细
     *
     *
     * @return 单条终端列表记录
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public Tempgroup selectTempgroupById(String aid)
    {
        return tempgroupMapper.selectTempgroupById(aid);
    }

    /**
     * 更新终端临时列表
     *
     * @return 终端临时列表
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int updateTempgroup(Tempgroup tempgroup)
    {
        return tempgroupMapper.updateTempgroup(tempgroup);
    }

    /**
     * 删除终端临时列表
     *
     * @return 终端临时列表
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int deleteTempgroup(String tgid){
        return tempgroupMapper.deleteTempgroup(tgid);
    }

    /**
     * 插入终端临时列表
     *
     * @return 终端临时列表
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int insertTempgroup(Tempgroup tempgroup)
    {
        return tempgroupMapper.insertTempgroup(tempgroup);
    }

    /**
     * 临时分组列表
     *
     * @return 终端临时列表
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Tempgroup> selectTempgroup(){return tempgroupMapper.selectTempgroup();}

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int insertTempgroupT(Tempgroup tempgroup){
        return tempgroupMapper.insertTempgroupT(tempgroup);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int deleteTempgroupT(String tid){
        return tempgroupMapper.deleteTempgroupT(tid);
    }

}
