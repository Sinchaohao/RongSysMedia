package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.Link;
import com.sixin.village.mapper.LinkMapper;
import com.sixin.village.service.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 链接管理 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-08-18
 */
@Service
public class LinkServiceImpl implements ILinkService 
{
	@Autowired
	private LinkMapper linkMapper;

	/**
     * 查询链接管理信息
     * 
     * @param mid 链接管理ID
     * @return 链接管理信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Link selectLinkById(Integer mid)
	{
	    return linkMapper.selectLinkById(mid);
	}
	
	/**
     * 查询链接管理列表
     * 
     * @param link 链接管理信息
     * @return 链接管理集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Link> selectLinkList(Link link)
	{
	    return linkMapper.selectLinkList(link);
	}
	
    /**
     * 新增链接管理
     * 
     * @param link 链接管理信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertLink(Link link)
	{
	    return linkMapper.insertLink(link);
	}
	
	/**
     * 修改链接管理
     * 
     * @param link 链接管理信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateLink(Link link)
	{
	    return linkMapper.updateLink(link);
	}

	/**
     * 删除链接管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deleteLinkByIds(String ids)
	{
		return linkMapper.deleteLinkByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Link> selectLinkByIds(List<String> id) {
		return linkMapper.selectLinkByIds(id);
	}

}
