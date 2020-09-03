package com.sixin.broad.mapper;

import com.sixin.broad.domain.ProChamanage;
import com.sixin.village.domain.PersonApi;

import java.util.List;

/**
 * 节目转播管理 数据层
 * 
 * @author 张超
 * @date 2019-03-02
 */
public interface ProChamanageMapper 
{
	/**
     * 查询节目转播管理信息
     * 
     * @param cid 节目转播管理ID
     * @return 节目转播管理信息
     */
	public ProChamanage selectProChamanageById(Integer cid);
	
	/**
     * 查询节目转播管理列表
     * 
     * @param proChamanage 节目转播管理信息
     * @return 节目转播管理集合
     */
	public List<ProChamanage> selectProChamanageList(ProChamanage proChamanage);

	/**
	 * 获取电台列表（村务宝典）
	 *
	 * @param proChamanage 节目转播管理信息
	 * @return 节目转播管理集合
	 */
	public List<ProChamanage> selectProChamanageList1(PersonApi proChamanage);
	
	/**
     * 新增节目转播管理
     * 
     * @param proChamanage 节目转播管理信息
     * @return 结果
     */
	public int insertProChamanage(ProChamanage proChamanage);
	
	/**
     * 修改节目转播管理
     * 
     * @param proChamanage 节目转播管理信息
     * @return 结果
     */
	public int updateProChamanage(ProChamanage proChamanage);
	
	/**
     * 删除节目转播管理
     * 
     * @param cid 节目转播管理ID
     * @return 结果
     */
	public int deleteProChamanageById(Integer cid);
	
	/**
     * 批量删除节目转播管理
     * 
     * @param cids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProChamanageByIds(String[] cids);
	
}