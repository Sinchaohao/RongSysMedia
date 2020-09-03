package com.sixin.broad.mapper;

import com.sixin.broad.domain.Maintain;
import com.sixin.broad.domain.Maintainx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 终端维护记录 数据层
 * 
 * @author 张超
 * @date 2019-01-15
 */
public interface MaintainMapper 
{
	/**
     * 查询终端维护记录信息
     * 
     * @param tmid 终端维护记录ID
     * @return 终端维护记录信息
     */
	public Maintain selectMaintainById(Integer tmid);
	
	/**
     * 查询终端维护记录列表
     * 
     * @param maintain 终端维护记录信息
     * @return 终端维护记录集合
     */
	public List<Maintain> selectMaintainList(Maintain maintain);
	
	/**
     * 新增终端维护记录
     *
     * @return 结果
     */
	public int insertMaintain(Maintain maintain);
	
	/**
     * 修改终端维护记录
     * 
     * @param maintain 终端维护记录信息
     * @return 结果
     */
	public int updateMaintain(Maintain maintain);
	
	/**
     * 删除终端维护记录
     * 
     * @param tmid 终端维护记录ID
     * @return 结果
     */
	public int deleteMaintainById(Integer tmid);
	
	/**
     * 批量删除终端维护记录
     * 
     * @param tmids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMaintainByIds(String[] tmids);


	/**
	 * 连表查询设备维修记录和经纬度
	 * @author 张超 teavamc
	 * @date 2019/1/25
	 * @param []
	 * @return java.util.List<com.sixin.broad.domain.Maintainx>
	 */
	public List<Maintainx> selectlal();
}