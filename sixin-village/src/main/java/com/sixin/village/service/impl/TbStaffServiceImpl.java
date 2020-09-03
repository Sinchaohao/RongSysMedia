package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.TbStaff;
import com.sixin.village.mapper.TbStaffMapper;
import com.sixin.village.service.ITbStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作员 服务层实现
 *
 * @author zx
 * @date 2019-11-03
 */
@Service
public class TbStaffServiceImpl implements ITbStaffService
{
	@Autowired
	private TbStaffMapper tbStaffMapper;

	/**
	 * 查询工作员信息
	 *
	 * @param stid 工作员ID
	 * @return 工作员信息
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public TbStaff selectTbStaffById(String stid)
	{
		return tbStaffMapper.selectTbStaffById(stid);
	}

	/**
	 * 查询工作员列表
	 *
	 * @param tbStaff 工作员信息
	 * @return 工作员集合
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<TbStaff> selectTbStaffList(TbStaff tbStaff)
	{
		return tbStaffMapper.selectTbStaffList(tbStaff);
	}

	/**
	 * 新增工作员
	 *
	 * @param tbStaff 工作员信息
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertTbStaff(TbStaff tbStaff)
	{
		return tbStaffMapper.insertTbStaff(tbStaff);
	}

	/**
	 * 修改工作员
	 *
	 * @param tbStaff 工作员信息
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateTbStaff(TbStaff tbStaff)
	{
		return tbStaffMapper.updateTbStaff(tbStaff);
	}

	/**
	 * 删除工作员对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deleteTbStaffByIds(String ids)
	{
		return tbStaffMapper.deleteTbStaffByIds(Convert.toStrArray(ids));
	}


	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<TbStaff> selectTbStaffByIds(List<String> stid){
		return  tbStaffMapper.selectTbStaffByIds(stid);
	}

}
