package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.support.Convert;
import com.sixin.common.enums.DataSourceType;
import com.sixin.village.domain.Shishi;
import com.sixin.village.domain.Statistics;
import com.sixin.village.mapper.ShishiMapper;
import com.sixin.village.mapper.StatisticsMapper;
import com.sixin.village.service.IStatisticsService;
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
public class StatisticsServiceImpl implements IStatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;

    /**
     * 查询参数信息
     *
     * @param valueid 参数ID
     * @return 实时政事信息
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public Statistics selectStatisticsById(String valueid)
    {
        return statisticsMapper.selectStatisticsById(valueid);
    }

    /**
     * 查询参数列表
     *
     * @param statistics 参数信息
     * @return 参数集合
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Statistics> selectStatisticsList(Statistics statistics)
    {
        return statisticsMapper.selectStatisticsList(statistics);
    }

    /**
     * 新增参数设置
     *
     * @param statistics 参数信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public int insertStatistics(Statistics statistics)
    {
        return statisticsMapper.insertStatistics(statistics);
    }

    /**
     * 修改参数设置
     *
     * @param statistics 参数信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public int updateStatistics(Statistics statistics)
    {
        return statisticsMapper.updateStatistics(statistics);
    }

    /**
     * 删除参数设置
     *
     * @param ids 参数ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public int deleteStatisticsByIds(String ids)
    {
        return statisticsMapper.deleteStatisticsByIds(Convert.toStrArray(ids));
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Statistics> selectStatisticsByIds(List<String> id) {
        return statisticsMapper.selectStatisticsByIds(id);
    }

}
