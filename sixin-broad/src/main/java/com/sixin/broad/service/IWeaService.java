package com.sixin.broad.service;

import com.sixin.broad.domain.Wea;
import java.util.List;

/**
 * 天气公告 服务层
 * 
 * @author 张超
 * @date 2019-01-14
 */
public interface IWeaService 
{
	/**
     * 查询天气公告信息
     * 
     * @param wid 天气公告ID
     * @return 天气公告信息
     */
	public Wea selectWeaById(Integer wid);
	
	/**
     * 查询天气公告列表
     * 
     * @param wea 天气公告信息
     * @return 天气公告集合
     */
	public List<Wea> selectWeaList(Wea wea);
	
	/**
     * 新增天气公告
     * 
     * @param wea 天气公告信息
     * @return 结果
     */
	public int insertWea(Wea wea);
	
	/**
     * 修改天气公告
     * 
     * @param wea 天气公告信息
     * @return 结果
     */
	public int updateWea(Wea wea);
		
	/**
     * 删除天气公告信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWeaByIds(String ids);
	
}
