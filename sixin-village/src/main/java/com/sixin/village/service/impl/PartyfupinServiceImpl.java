package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.Partyfupin;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.mapper.PartyfupinMapper;
import com.sixin.village.service.IPartyfupinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 扶贫工作 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-08-25
 */
@Service
public class PartyfupinServiceImpl implements IPartyfupinService 
{
	@Autowired
	private PartyfupinMapper partyfupinMapper;

	/**
     * 查询扶贫工作信息
     * 
     * @param mid 扶贫工作ID
     * @return 扶贫工作信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Partyfupin selectPartyfupinById(Integer mid)
	{
	    return partyfupinMapper.selectPartyfupinById(mid);
	}
	
	/**
     * 查询扶贫工作列表
     * 
     * @param partyfupin 扶贫工作信息
     * @return 扶贫工作集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Partyfupin> selectPartyfupinList(Partyfupin partyfupin)
	{
	    return partyfupinMapper.selectPartyfupinList(partyfupin);
	}
	
    /**
     * 新增扶贫工作
     * 
     * @param partyfupin 扶贫工作信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertPartyfupin(Partyfupin partyfupin)
	{
	    return partyfupinMapper.insertPartyfupin(partyfupin);
	}
	
	/**
     * 修改扶贫工作
     * 
     * @param partyfupin 扶贫工作信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updatePartyfupin(Partyfupin partyfupin)
	{
	    return partyfupinMapper.updatePartyfupin(partyfupin);
	}

	/**
     * 删除扶贫工作对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deletePartyfupinByIds(String ids)
	{
		return partyfupinMapper.deletePartyfupinByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Partyfupin> selectfupinListById(pubObjApi party)
	{
		return partyfupinMapper.selectfupinListById(party);
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Partyfupin> selectFupinByIds(List<String> id) {
		return partyfupinMapper.selectFupinByIds(id);
	}
}
