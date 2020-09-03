package com.sixin.village.mapper;

import com.sixin.village.domain.VillagegroupStatisticsInfo;
import java.util.List;	

/**
 * 村组统计 数据层
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
public interface VillagegroupStatisticsInfoMapper 
{
	/**
     * 查询村组统计信息
     * 
     * @param vgsid 村组统计ID
     * @return 村组统计信息
     */
	public VillagegroupStatisticsInfo selectVillagegroupStatisticsInfoById(Integer vgsid);
	
	/**
     * 查询村组统计列表
     * 
     * @param villagegroupStatisticsInfo 村组统计信息
     * @return 村组统计集合
     */
	public List<VillagegroupStatisticsInfo> selectVillagegroupStatisticsInfoList(VillagegroupStatisticsInfo villagegroupStatisticsInfo);
	
	/**
     * 新增村组统计
     * 
     * @param villagegroupStatisticsInfo 村组统计信息
     * @return 结果
     */
	public int insertVillagegroupStatisticsInfo(VillagegroupStatisticsInfo villagegroupStatisticsInfo);
	
	/**
     * 修改村组统计
     * 
     * @param villagegroupStatisticsInfo 村组统计信息
     * @return 结果
     */
	public int updateVillagegroupStatisticsInfo(VillagegroupStatisticsInfo villagegroupStatisticsInfo);
	
	/**
     * 删除村组统计
     * 
     * @param vgsid 村组统计ID
     * @return 结果
     */
	public int deleteVillagegroupStatisticsInfoById(Integer vgsid);
	
	/**
     * 批量删除村组统计
     * 
     * @param vgsids 需要删除的数据ID
     * @return 结果
     */
	public int deleteVillagegroupStatisticsInfoByIds(String[] vgsids);

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 23:13
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.VillagegroupStatisticsInfo>
	 */
	public List<VillagegroupStatisticsInfo> selectVillagegroupStatisticsInfoByIds(List<String> id);
	
}