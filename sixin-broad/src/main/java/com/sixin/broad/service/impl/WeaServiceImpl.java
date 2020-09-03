package com.sixin.broad.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.broad.mapper.WeaMapper;
import com.sixin.broad.domain.Wea;
import com.sixin.broad.service.IWeaService;
import com.sixin.common.support.Convert;

/**
 * 天气公告 服务层实现
 * 
 * @author 张超
 * @date 2019-01-14
 */
@Service
public class WeaServiceImpl implements IWeaService 
{
	@Autowired
	private WeaMapper weaMapper;

	/**
     * 查询天气公告信息
     * 
     * @param wid 天气公告ID
     * @return 天气公告信息
     */
    @Override
	@DataSource(value = DataSourceType.SLAVE)
	public Wea selectWeaById(Integer wid)
	{
	    return weaMapper.selectWeaById(wid);
	}
	
	/**
     * 查询天气公告列表
     * 
     * @param wea 天气公告信息
     * @return 天气公告集合
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Wea> selectWeaList(Wea wea)
	{
	    return weaMapper.selectWeaList(wea);
	}
	
    /**
     * 新增天气公告
     * 
     * @param wea 天气公告信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int insertWea(Wea wea)
	{
	    return weaMapper.insertWea(wea);
	}
	
	/**
     * 修改天气公告
     * 
     * @param wea 天气公告信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateWea(Wea wea)
	{
	    return weaMapper.updateWea(wea);
	}

	/**
     * 删除天气公告对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteWeaByIds(String ids)
	{
		return weaMapper.deleteWeaByIds(Convert.toStrArray(ids));
	}


	
}
