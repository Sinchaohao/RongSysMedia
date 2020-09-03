package com.sixin.broad.service;

import com.sixin.broad.domain.Maincount;

import java.util.List;

/**
 * 终端维护记录统计 服务层
 * 
 * @author 张鸿权
 * @date 2019-06-09
 */
public interface IMaincountService
{
	/**
     * 查询终端维护记录信息
     *
     * @param tmid 终端维护记录ID
     * @return 终端维护记录信息
     */
	public List<Maincount> selectMaincountById(String tmid);

	/**
     * 查询终端维护记录列表
     *
     * @param maincount 终端维护记录信息
     * @return 终端维护记录集合
     */
	public List<Maincount> selectMaincountList(Maincount maincount);

	/**
     * 新增终端维护记录
     *
     * @return 结果
     */
	public int insertMaincount(String tid, String fault, String mstaff, String remark);
	
	/**
     * 修改终端维护记录
     * 
     * @param maincount 终端维护记录信息
     * @return 结果
     */
	public int updateMaincount(Maincount maincount);
		
	/**
     * 删除终端维护记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMaincountByIds(String ids);

}
