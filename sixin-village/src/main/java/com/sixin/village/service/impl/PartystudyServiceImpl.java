package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.Partystudy;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.mapper.PartystudyMapper;
import com.sixin.village.service.IPartystudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 党员学习 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-08-25
 */
@Service
public class PartystudyServiceImpl implements IPartystudyService 
{
	@Autowired
	private PartystudyMapper partystudyMapper;

	/**
     * 查询党员学习信息
     * 
     * @param mid 党员学习ID
     * @return 党员学习信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Partystudy selectPartystudyById(Integer mid)
	{
	    return partystudyMapper.selectPartystudyById(mid);
	}
	
	/**
     * 查询党员学习列表
     * 
     * @param partystudy 党员学习信息
     * @return 党员学习集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Partystudy> selectPartystudyList(Partystudy partystudy)
	{
	    return partystudyMapper.selectPartystudyList(partystudy);
	}
	
    /**
     * 新增党员学习
     * 
     * @param partystudy 党员学习信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertPartystudy(Partystudy partystudy)
	{
	    return partystudyMapper.insertPartystudy(partystudy);
	}
	
	/**
     * 修改党员学习
     * 
     * @param partystudy 党员学习信息
     * @return 结果
     */
	@Override
	public int updatePartystudy(Partystudy partystudy)
	{
	    return partystudyMapper.updatePartystudy(partystudy);
	}

	/**
     * 删除党员学习对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deletePartystudyByIds(String ids)
	{
		return partystudyMapper.deletePartystudyByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Partystudy> selectPartystudyListById(pubObjApi partystudy)
	{
		return partystudyMapper.selectPartystudyListById(partystudy);
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Partystudy> selectPartystudyByIds(List<String> id) {
		return partystudyMapper.selectPartystudyByIds(id);
	}
}
