package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.Partyactive;
import com.sixin.village.mapper.PartyactiveMapper;
import com.sixin.village.service.IPartyactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 党员活动 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-08-25
 */
@Service
public class PartyactiveServiceImpl implements IPartyactiveService 
{
	@Autowired
	private PartyactiveMapper partyactiveMapper;

	/**
     * 查询党员活动信息
     * 
     * @param mid 党员活动ID
     * @return 党员活动信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Partyactive selectPartyactiveById(Integer mid)
	{
	    return partyactiveMapper.selectPartyactiveById(mid);
	}
	
	/**
     * 查询党员活动列表
     * 
     * @param partyactive 党员活动信息
     * @return 党员活动集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Partyactive> selectPartyactiveList(Partyactive partyactive)
	{
	    return partyactiveMapper.selectPartyactiveList(partyactive);
	}
	
    /**
     * 新增党员活动
     * 
     * @param partyactive 党员活动信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertPartyactive(Partyactive partyactive)
	{
	    return partyactiveMapper.insertPartyactive(partyactive);
	}
	
	/**
     * 修改党员活动
     * 
     * @param partyactive 党员活动信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updatePartyactive(Partyactive partyactive)
	{
	    return partyactiveMapper.updatePartyactive(partyactive);
	}

	/**
     * 删除党员活动对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deletePartyactiveByIds(String ids)
	{
		return partyactiveMapper.deletePartyactiveByIds(Convert.toStrArray(ids));
	}
	
}
