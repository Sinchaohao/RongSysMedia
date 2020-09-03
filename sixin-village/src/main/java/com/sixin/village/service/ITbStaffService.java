package com.sixin.village.service;

import com.sixin.village.domain.TbStaff;
import java.util.List;

/**
 * 工作员 服务层
 *
 * @author 张鸿权
 * @date 2019-11-03
 */
public interface ITbStaffService
{
	/**
	 * 查询工作员信息
	 *
	 * @param stid 工作员ID
	 * @return 工作员信息
	 */
	public TbStaff selectTbStaffById(String stid);

	/**
	 * 查询工作员列表
	 *
	 * @param tbStaff 工作员信息
	 * @return 工作员集合
	 */
	public List<TbStaff> selectTbStaffList(TbStaff tbStaff);

	/**
	 * 新增工作员
	 *
	 * @param tbStaff 工作员信息
	 * @return 结果
	 */
	public int insertTbStaff(TbStaff tbStaff);

	/**
	 * 修改工作员
	 *
	 * @param tbStaff 工作员信息
	 * @return 结果
	 */
	public int updateTbStaff(TbStaff tbStaff);

	/**
	 * 删除工作员信息
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTbStaffByIds(String ids);

	public List<TbStaff> selectTbStaffByIds(List<String> stid);

}
