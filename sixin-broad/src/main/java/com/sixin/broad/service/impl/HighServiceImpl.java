package com.sixin.broad.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.broad.mapper.HighMapper;
import com.sixin.broad.domain.High;
import com.sixin.broad.service.IHighService;
import com.sixin.common.support.Convert;

/**
 * 上级公告 服务层实现
 * 
 * @author 张超
 * @date 2019-01-14
 */
@Service
public class HighServiceImpl implements IHighService 
{
	@Autowired
	private HighMapper highMapper;

	/**
     * 查询上级公告信息
     * 
     * @param mhid 上级公告ID
     * @return 上级公告信息
     */
    @Override
	@DataSource(value = DataSourceType.SLAVE)
	public High selectHighById(Integer mhid)
	{
	    return highMapper.selectHighById(mhid);
	}
	
	/**
     * 查询上级公告列表
     * 
     * @param high 上级公告信息
     * @return 上级公告集合
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<High> selectHighList(High high)
	{
	    return highMapper.selectHighList(high);
	}
	
    /**
     * 新增上级公告
     * 
     * @param high 上级公告信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int insertHigh(High high)
	{
	    return highMapper.insertHigh(high);
	}
	
	/**
     * 修改上级公告
     * 
     * @param high 上级公告信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateHigh(High high)
	{
	    return highMapper.updateHigh(high);
	}

	/**
     * 删除上级公告对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteHighByIds(String ids)
	{
		return highMapper.deleteHighByIds(Convert.toStrArray(ids));
	}
	
}
