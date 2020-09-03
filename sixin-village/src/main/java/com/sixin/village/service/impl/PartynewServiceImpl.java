package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.Partynew;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.mapper.PartynewMapper;
import com.sixin.village.service.IPartynewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 党员新闻 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-08-25
 */
@Service
public class PartynewServiceImpl implements IPartynewService 
{
	@Autowired
	private PartynewMapper partynewMapper;

	/**
     * 查询党员新闻信息
     * 
     * @param mid 党员新闻ID
     * @return 党员新闻信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Partynew selectPartynewById(Integer mid)
	{
	    return partynewMapper.selectPartynewById(mid);
	}
	
	/**
     * 查询党员新闻列表
     * 
     * @param partynew 党员新闻信息
     * @return 党员新闻集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Partynew> selectPartynewList(Partynew partynew)
	{
	    return partynewMapper.selectPartynewList(partynew);
	}
	
    /**
     * 新增党员新闻
     * 
     * @param partynew 党员新闻信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertPartynew(Partynew partynew)
	{
	    return partynewMapper.insertPartynew(partynew);
	}
	
	/**
     * 修改党员新闻
     * 
     * @param partynew 党员新闻信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updatePartynew(Partynew partynew)
	{
	    return partynewMapper.updatePartynew(partynew);
	}

	/**
     * 删除党员新闻对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deletePartynewByIds(String ids)
	{
		return partynewMapper.deletePartynewByIds(Convert.toStrArray(ids));
	}


	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Partynew> selectpartynewListById(pubObjApi party)
	{
		return partynewMapper.selectpartynewListById(party);
	}

	@Override
	@DataSource(DataSourceType.SXVILLAGE)
	public List<Partynew> selectPartynewByIds(List<String> id) {
		return partynewMapper.selectPartynewByIds(id);
	}
}
