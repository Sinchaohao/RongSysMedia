package com.sixin.village.service;

import com.sixin.village.domain.Partyfupin;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * 扶贫工作 服务层
 * 
 * @author 张鸿权
 * @date 2019-08-25
 */
public interface IPartyfupinService 
{
	/**
     * 查询扶贫工作信息
     * 
     * @param mid 扶贫工作ID
     * @return 扶贫工作信息
     */
	public Partyfupin selectPartyfupinById(Integer mid);
	
	/**
     * 查询扶贫工作列表
     * 
     * @param partyfupin 扶贫工作信息
     * @return 扶贫工作集合
     */
	public List<Partyfupin> selectPartyfupinList(Partyfupin partyfupin);
	
	/**
     * 新增扶贫工作
     * 
     * @param partyfupin 扶贫工作信息
     * @return 结果
     */
	public int insertPartyfupin(Partyfupin partyfupin);
	
	/**
     * 修改扶贫工作
     * 
     * @param partyfupin 扶贫工作信息
     * @return 结果
     */
	public int updatePartyfupin(Partyfupin partyfupin);
		
	/**
     * 删除扶贫工作信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePartyfupinByIds(String ids);

	public List<Partyfupin> selectfupinListById(pubObjApi party);

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 19:33
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.Partyfupin>
	 */
	public List<Partyfupin> selectFupinByIds(List<String> id);
}
