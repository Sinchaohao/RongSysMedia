package com.sixin.broad.mapper;

import com.sixin.broad.domain.Maincount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 终端维护记录统计 数据层
 * 
 * @author 张鸿权
 * @date 2019-06-09
 */
public interface MaincountMapper
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
     * @param Maincount 终端维护记录信息
     * @return 终端维护记录集合
     */
	public List<Maincount> selectMaincountList(Maincount Maincount);

	/**
     * 新增终端维护记录
     *
     * @return 结果
     */
	public int insertMaincount(@Param("tid") String tid, @Param("fault") String fault, @Param("mstaff") String mstaff, @Param("remark") String remark);
	
	/**
     * 修改终端维护记录
     * 
     * @param Maincount 终端维护记录信息
     * @return 结果
     */
	public int updateMaincount(Maincount Maincount);
	
	/**
     * 删除终端维护记录
     * 
     * @param tmid 终端维护记录ID
     * @return 结果
     */
	public int deleteMaincountById(Integer tmid);
	
	/**
     * 批量删除终端维护记录
     * 
     * @param tmids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMaincountByIds(String[] tmids);

}