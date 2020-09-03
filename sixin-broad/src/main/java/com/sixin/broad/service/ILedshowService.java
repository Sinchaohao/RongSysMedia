package com.sixin.broad.service;

import com.sixin.broad.domain.Ledshow;
import com.sixin.village.domain.PersonApi;

import java.util.List;

/**
 * led显示 服务层
 * 
 * @author 张超
 * @date 2019-01-14
 */
public interface ILedshowService 
{
	/**
     * 查询led显示信息
     * 
     * @param lsid led显示ID
     * @return led显示信息
     */
	public Ledshow selectLedshowById(Integer lsid);
	
	/**
     * 查询led显示列表
     * 
     * @param ledshow led显示信息
     * @return led显示集合
     */
	public List<Ledshow> selectLedshowList(Ledshow ledshow);
	
	/**
     * 新增led显示
     * 
     * @param ledshow led显示信息
     * @return 结果
     */
	public int insertLedshow(Ledshow ledshow);
	
	/**
     * 修改led显示
     * 
     * @param ledshow led显示信息
     * @return 结果
     */
	public int updateLedshow(Ledshow ledshow);
		
	/**
     * 删除led显示信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteLedshowByIds(String ids);

	/**
	 * 获取led显示屏播出记录列表
	 *
	 * @param ledshow led显示信息
	 * @return led显示集合
	 */
	public List<Ledshow> selectLedshowList1(PersonApi ledshow);
}
