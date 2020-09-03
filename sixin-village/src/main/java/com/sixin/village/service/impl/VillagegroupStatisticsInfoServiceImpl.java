package com.sixin.village.service.impl;

import java.util.List;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.village.mapper.VillagegroupStatisticsInfoMapper;
import com.sixin.village.domain.VillagegroupStatisticsInfo;
import com.sixin.village.service.IVillagegroupStatisticsInfoService;
import com.sixin.common.support.Convert;

/**
 * 村组统计 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
@Service
public class VillagegroupStatisticsInfoServiceImpl implements IVillagegroupStatisticsInfoService 
{
	@Autowired
	private VillagegroupStatisticsInfoMapper villagegroupStatisticsInfoMapper;

	/**
     * 查询村组统计信息
     * 
     * @param vgsid 村组统计ID
     * @return 村组统计信息
     */
    @Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public VillagegroupStatisticsInfo selectVillagegroupStatisticsInfoById(Integer vgsid)
	{
	    return villagegroupStatisticsInfoMapper.selectVillagegroupStatisticsInfoById(vgsid);
	}
	
	/**
     * 查询村组统计列表
     * 
     * @param villagegroupStatisticsInfo 村组统计信息
     * @return 村组统计集合
     */
	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public List<VillagegroupStatisticsInfo> selectVillagegroupStatisticsInfoList(VillagegroupStatisticsInfo villagegroupStatisticsInfo)
	{
	    return villagegroupStatisticsInfoMapper.selectVillagegroupStatisticsInfoList(villagegroupStatisticsInfo);
	}
	
    /**
     * 新增村组统计
     * 
     * @param villagegroupStatisticsInfo 村组统计信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public int insertVillagegroupStatisticsInfo(VillagegroupStatisticsInfo villagegroupStatisticsInfo)
	{
	    return villagegroupStatisticsInfoMapper.insertVillagegroupStatisticsInfo(villagegroupStatisticsInfo);
	}
	
	/**
     * 修改村组统计
     * 
     * @param villagegroupStatisticsInfo 村组统计信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public int updateVillagegroupStatisticsInfo(VillagegroupStatisticsInfo villagegroupStatisticsInfo)
	{
	    return villagegroupStatisticsInfoMapper.updateVillagegroupStatisticsInfo(villagegroupStatisticsInfo);
	}

	/**
     * 删除村组统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public int deleteVillagegroupStatisticsInfoByIds(String ids)
	{
		return villagegroupStatisticsInfoMapper.deleteVillagegroupStatisticsInfoByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public List<VillagegroupStatisticsInfo> selectVillagegroupStatisticsInfoByIds(List<String> id) {
		return villagegroupStatisticsInfoMapper.selectVillagegroupStatisticsInfoByIds(id);
	}

}
