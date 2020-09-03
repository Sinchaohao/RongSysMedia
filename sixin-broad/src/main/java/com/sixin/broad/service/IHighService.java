package com.sixin.broad.service;

import com.sixin.broad.domain.High;
import java.util.List;

/**
 * 上级公告 服务层
 * 
 * @author 张超
 * @date 2019-01-14
 */
public interface IHighService 
{
	/**
     * 查询上级公告信息
     * 
     * @param mhid 上级公告ID
     * @return 上级公告信息
     */
	public High selectHighById(Integer mhid);
	
	/**
     * 查询上级公告列表
     * 
     * @param high 上级公告信息
     * @return 上级公告集合
     */
	public List<High> selectHighList(High high);
	
	/**
     * 新增上级公告
     * 
     * @param high 上级公告信息
     * @return 结果
     */
	public int insertHigh(High high);
	
	/**
     * 修改上级公告
     * 
     * @param high 上级公告信息
     * @return 结果
     */
	public int updateHigh(High high);
		
	/**
     * 删除上级公告信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHighByIds(String ids);
	
}
