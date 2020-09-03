package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.TbHuodong;
import com.sixin.village.mapper.TbHuodongMapper;
import com.sixin.village.service.ITbHuodongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 党员活动 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-11-16
 */
@Service
public class TbHuodongServiceImpl implements ITbHuodongService 
{
	@Autowired
	private TbHuodongMapper tbHuodongMapper;

	/**
     * 查询党员活动信息
     * 
     * @param hdid 党员活动ID
     * @return 党员活动信息
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public TbHuodong selectTbHuodongById(Integer hdid)
	{
	    return tbHuodongMapper.selectTbHuodongById(hdid);
	}
	
	/**
     * 查询党员活动列表
     * 
     * @param tbHuodong 党员活动信息
     * @return 党员活动集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<TbHuodong> selectTbHuodongList(TbHuodong tbHuodong)
	{
	    return tbHuodongMapper.selectTbHuodongList(tbHuodong);
	}
	
    /**
     * 新增党员活动
     * 
     * @param tbHuodong 党员活动信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertTbHuodong(TbHuodong tbHuodong)
	{
	    return tbHuodongMapper.insertTbHuodong(tbHuodong);
	}
	
	/**
     * 修改党员活动
     * 
     * @param tbHuodong 党员活动信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateTbHuodong(TbHuodong tbHuodong)
	{
	    return tbHuodongMapper.updateTbHuodong(tbHuodong);
	}

	/**
     * 删除党员活动对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deleteTbHuodongByIds(String ids)
	{
		return tbHuodongMapper.deleteTbHuodongByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<TbHuodong> selectTbHuodongByIds(List<String> id) {
		return tbHuodongMapper.selectTbHuodongByIds(id);
	}

}
