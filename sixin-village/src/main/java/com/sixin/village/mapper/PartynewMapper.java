package com.sixin.village.mapper;

import com.sixin.village.domain.Partynew;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * 党员新闻 数据层
 * 
 * @author 张鸿权
 * @date 2019-08-25
 */
public interface PartynewMapper 
{
	/**
     * 查询党员新闻信息
     * 
     * @param mid 党员新闻ID
     * @return 党员新闻信息
     */
	public Partynew selectPartynewById(Integer mid);
	
	/**
     * 查询党员新闻列表
     * 
     * @param partynew 党员新闻信息
     * @return 党员新闻集合
     */
	public List<Partynew> selectPartynewList(Partynew partynew);
	
	/**
     * 新增党员新闻
     * 
     * @param partynew 党员新闻信息
     * @return 结果
     */
	public int insertPartynew(Partynew partynew);
	
	/**
     * 修改党员新闻
     * 
     * @param partynew 党员新闻信息
     * @return 结果
     */
	public int updatePartynew(Partynew partynew);
	
	/**
     * 删除党员新闻
     * 
     * @param mid 党员新闻ID
     * @return 结果
     */
	public int deletePartynewById(Integer mid);
	
	/**
     * 批量删除党员新闻
     * 
     * @param mids 需要删除的数据ID
     * @return 结果
     */
	public int deletePartynewByIds(String[] mids);

	public List<Partynew> selectpartynewListById(pubObjApi party);

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 16:35
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.Partynew>
	 */
	public List<Partynew> selectPartynewByIds(List<String> id);
}