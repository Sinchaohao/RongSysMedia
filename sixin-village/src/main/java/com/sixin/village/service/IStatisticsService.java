package com.sixin.village.service;

import com.sixin.village.domain.Shishi;
import com.sixin.village.domain.Statistics;
import com.sixin.village.domain.Worklog;

import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
public interface IStatisticsService {
    /**
     * 查询参数信息
     *
     * @param valueid 参数ID
     * @return 实时政事信息
     */
    public Statistics selectStatisticsById(String valueid);

    /**
     * 查询参数列表
     *
     * @param statistics 参数信息
     * @return 参数集合
     */
    public List<Statistics> selectStatisticsList(Statistics statistics);

    /**
     * 新增参数设置
     *
     * @param statistics 参数信息
     * @return 结果
     */
    public int insertStatistics(Statistics statistics);

    /**
     * 修改参数设置
     *
     * @param statistics 参数信息
     * @return 结果
     */
    public int updateStatistics(Statistics statistics);

    /**
     * 批量删除参数设置
     *
     * @param ids 需要删除的参数ID
     * @return 结果
     */
    public int deleteStatisticsByIds(String ids);


    /**
     * @Author TanXY
     * @Description 按需导出数据
     * @Date 2020/4/18 14:53
     * @Param [id]
     * @return java.util.List<com.sixin.village.domain.Statistics>
     */
    public List<Statistics>  selectStatisticsByIds(List<String> id);
}
