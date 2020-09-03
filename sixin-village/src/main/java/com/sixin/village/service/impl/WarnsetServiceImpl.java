package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.support.Convert;
import com.sixin.common.enums.DataSourceType;
import com.sixin.village.domain.Warnset;
import com.sixin.village.mapper.WarnsetMapper;
import com.sixin.village.service.IWarnsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
@Service
public class WarnsetServiceImpl implements IWarnsetService {
    @Autowired
    private WarnsetMapper warnsetMapper;

    /**
     * 查询报警提示信息
     *
     * @param id 参数ID
     * @return 报警提示信息
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public Warnset selectWarnsetById(Long id)
    {
        return warnsetMapper.selectWarnsetById(id);
    }

    /**
     * 查询报警提示列表
     *
     * @param warnset 报警提示对象
     * @return 报警提示集合
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Warnset> selectWarnsetList(Warnset warnset)
    {
        return warnsetMapper.selectWarnsetList(warnset);
    }

    /**
     * 新增报警提示
     *
     * @param warnset 报警提示对象
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public int insertWarnset(Warnset warnset)
    {
        return warnsetMapper.insertWarnset(warnset);
    }

    /**
     * 修改报警提示
     *
     * @param warnset 报警提示对象
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public int updateWarnset(Warnset warnset)
    {
        return warnsetMapper.updateWarnset(warnset);
    }

    /**
     * 批量删除报警提示
     *
     * @param ids 需要删除的参数ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public int deleteWarnsetByIds(String ids)
    {
        return warnsetMapper.deleteWarnsetByIds(Convert.toStrArray(ids));
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Warnset> selectWarnsetByIds(List<String> id) {
        return warnsetMapper.selectWarnsetByIds(id);
    }
}
