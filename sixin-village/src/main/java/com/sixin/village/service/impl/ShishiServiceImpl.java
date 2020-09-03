package com.sixin.village.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.village.mapper.ShishiMapper;
import com.sixin.village.domain.Shishi;
import com.sixin.village.service.IShishiService;
import com.sixin.common.support.Convert;

/**
 * 实时政事 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
@Service
public class ShishiServiceImpl implements IShishiService 
{
	@Autowired
	private ShishiMapper shishiMapper;

	/**
     * 查询实时政事信息
     * 
     * @param shiid 实时政事ID
     * @return 实时政事信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Shishi selectShishiById(Integer shiid)
	{
	    return shishiMapper.selectShishiById(shiid);
	}
	
	/**
     * 查询实时政事列表
     * 
     * @param shishi 实时政事信息
     * @return 实时政事集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Shishi> selectShishiList(Shishi shishi)
	{
	    return shishiMapper.selectShishiList(shishi);
	}
	
    /**
     * 新增实时政事
     * 
     * @param shishi 实时政事信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertShishi(Shishi shishi)
	{
	    return shishiMapper.insertShishi(shishi);
	}
	
	/**
     * 修改实时政事
     * 
     * @param shishi 实时政事信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateShishi(Shishi shishi)
	{
	    return shishiMapper.updateShishi(shishi);
	}

	/**
     * 删除实时政事对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deleteShishiByIds(String ids)
	{
		return shishiMapper.deleteShishiByIds(Convert.toStrArray(ids));
	}
	
}
