package com.sixin.village.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.village.mapper.GuideMapper;
import com.sixin.village.domain.Guide;
import com.sixin.village.service.IGuideService;
import com.sixin.common.support.Convert;

/**
 * 常用办事指南 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
@Service
public class GuideServiceImpl implements IGuideService 
{
	@Autowired
	private GuideMapper guideMapper;

	/**
     * 查询常用办事指南信息
     * 
     * @param guid 常用办事指南ID
     * @return 常用办事指南信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Guide selectGuideById(Integer guid)
	{
	    return guideMapper.selectGuideById(guid);
	}
	
	/**
     * 查询常用办事指南列表
     * 
     * @param guide 常用办事指南信息
     * @return 常用办事指南集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Guide> selectGuideList(Guide guide)
	{
	    return guideMapper.selectGuideList(guide);
	}
	
    /**
     * 新增常用办事指南
     * 
     * @param guide 常用办事指南信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertGuide(Guide guide)
	{
	    return guideMapper.insertGuide(guide);
	}
	
	/**
     * 修改常用办事指南
     * 
     * @param guide 常用办事指南信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateGuide(Guide guide)
	{
	    return guideMapper.updateGuide(guide);
	}

	/**
     * 删除常用办事指南对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deleteGuideByIds(String ids)
	{
		return guideMapper.deleteGuideByIds(Convert.toStrArray(ids));
	}
	
}
