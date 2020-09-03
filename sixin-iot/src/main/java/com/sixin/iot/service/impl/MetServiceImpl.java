package com.sixin.iot.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.iot.domain.Environl;
import com.sixin.iot.mapper.MetMapper;
import com.sixin.iot.service.IMetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @开发人员：周宇滔
 * @开发单位：湖南农业大学物联网工程专业
 */
@Service
public class MetServiceImpl implements IMetService {
    @Autowired
    private MetMapper metMapper;

    @Override
    public List<Environl> selectMet(Environl environl){
        return metMapper.selectMet(environl);
    }

    /**
     * 查询气象信息
     *
     * @param id 气象信息ID
     * @return 气象信息
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public Environl selectMetDataById(Long id)
    {
        return metMapper.selectMetDataById(id);
    }
    /**
     * 新增气象信息
     *
     * @param environment 气象信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int insertMetData(Environl environl)
    {
        return metMapper.insertMetData(environl);
    }

    /**
     * 修改气象信息
     *
     * @param environment 气象信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int updateMetData(Environl environl)
    {
        return metMapper.updateMetData(environl);
    }

    /**
     * 删除气象信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteMetDataByIds(String ids)
    {
        return metMapper.deleteMetDataByIds(Convert.toStrArray(ids));
    }

}
