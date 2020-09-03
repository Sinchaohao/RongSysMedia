package com.sixin.broad.mapper;

import com.sixin.broad.domain.Ledshow;
import com.sixin.village.domain.PersonApi;

import java.util.List;

/**
 * led显示 数据层
 * 
 * @author 张超
 * @date 2019-01-14
 */
public interface LedshowMapper 
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
     * 删除led显示
     * 
     * @param lsid led显示ID
     * @return 结果
     */
	public int deleteLedshowById(Integer lsid);
	
	/**
     * 批量删除led显示
     * 
     * @param lsids 需要删除的数据ID
     * @return 结果
     */
	public int deleteLedshowByIds(String[] lsids);

	/**
	 * 查询led显示列表
	 *
	 * @param ledshow led显示信息
	 * @return led显示集合
	 */
	public List<Ledshow> selectLedshowList1(PersonApi ledshow);
}