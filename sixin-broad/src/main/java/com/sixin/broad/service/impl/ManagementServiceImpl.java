package com.sixin.broad.service.impl;

import java.util.List;


import com.sixin.broad.domain.*;
import com.sixin.broad.mapper.BindexMapper;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.broad.mapper.ManagementMapper;
import com.sixin.broad.service.IManagementService;
import com.sixin.common.support.Convert;

/**
 * 终端管理 服务层实现
 *
 * @author 张超
 * @date 2019-01-15
 */
@Service
public class ManagementServiceImpl implements IManagementService
{
	@Autowired
	private ManagementMapper managementMapper;
	@Autowired
	private BindexMapper bindexMapper;

	/**
	 * 查询终端管理信息
	 *
	 * @param tid 终端管理ID
	 * @return 终端管理信息
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public Management selectManagementById(String tid)
	{
		return managementMapper.selectManagementById(tid);
	}

	/**
	 * 查询终端管理列表
	 *
	 * @param management 终端管理信息
	 * @return 终端管理集合
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List selectManagementList(Management management)
	{
		return managementMapper.selectManagementList(management);
	}

	/**
	 * 新增终端管理
	 *
	 * @param management 终端管理信息
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int insertManagement(Management management)
	{
		return managementMapper.insertManagement(management);
	}

	/**
	 * 修改终端管理
	 *
	 * @param management 终端管理信息
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateManagement(Management management)
	{
		return managementMapper.updateManagement(management);
	}

	/**
	 * 删除终端管理对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteManagementByIds(String ids)
	{
		return managementMapper.deleteManagementByIds(Convert.toStrArray(ids));
	}

	/**
	 * 随机选取100个数据
	 *
	 * @param
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Termap> selectManagementByRandom(){
		return managementMapper.selectManagementByRandom();
	}

	/**
	 * 所有数据
	 *
	 * @param
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Termap> selectManagementAll(){
		return managementMapper.selectManagementAll();
	};

	/**
	 * 按照终端地址进行运行状态的分组统计
	 * @author 张超 teavamc
	 * @date 2019/1/25
	 * @param []
	 * @return java.util.List<com.sixin.broad.domain.Tersga>
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Tersga> selecttersga(){
		return managementMapper.selecttersga();
	}

	/**
	 * 回传目前设备挂载总数,运行数，停止数
	 * @author 张超 teavamc
	 * @date 2019/1/25
	 * @param []
	 * @return com.sixin.broad.domain.Tersga
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public Tersga sumters(){
		return managementMapper.sumters();
	}

	/**
	 * 按照终端地址进行运行状态的分组统计,包括分组地区、挂在数量、运行数量、停止数量
	 * @author 张超 teavamc
	 * @date 2019/1/25
	 * @param []
	 * @return java.util.List<com.sixin.broad.domain.Tersga>
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Tersga> sumterm(){
		return managementMapper.sumterm();
	}

	/**
	 * 统计终端数，运行数，停止运行数
	 * @author 张超 teavamc
	 * @date 2019/1/26
	 * @param []
	 * @return com.sixin.broad.mapper.BindexMapper
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public Bindex count(String aid){
		return bindexMapper.count(aid);
	}
}
