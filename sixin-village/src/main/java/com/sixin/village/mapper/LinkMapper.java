package com.sixin.village.mapper;

import com.sixin.village.domain.Link;
import com.sixin.village.domain.Memorial;

import java.util.List;

/**
 * 链接管理 数据层
 * 
 * @author 张鸿权
 * @date 2019-08-18
 */
public interface LinkMapper 
{
	/**
     * 查询链接管理信息
     * 
     * @param mid 链接管理ID
     * @return 链接管理信息
     */
	public Link selectLinkById(Integer mid);
	
	/**
     * 查询链接管理列表
     * 
     * @param link 链接管理信息
     * @return 链接管理集合
     */
	public List<Link> selectLinkList(Link link);
	
	/**
     * 新增链接管理
     * 
     * @param link 链接管理信息
     * @return 结果
     */
	public int insertLink(Link link);
	
	/**
     * 修改链接管理
     * 
     * @param link 链接管理信息
     * @return 结果
     */
	public int updateLink(Link link);
	
	/**
     * 删除链接管理
     * 
     * @param mid 链接管理ID
     * @return 结果
     */
	public int deleteLinkById(Integer mid);
	
	/**
     * 批量删除链接管理
     * 
     * @param mids 需要删除的数据ID
     * @return 结果
     */
	public int deleteLinkByIds(String[] mids);

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 14:32
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.Link>
	 */
	public List<Link>  selectLinkByIds(List<String> id);
	
}