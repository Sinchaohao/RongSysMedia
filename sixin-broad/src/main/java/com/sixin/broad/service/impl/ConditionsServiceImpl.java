package com.sixin.broad.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.broad.mapper.ConditionsMapper;
import com.sixin.broad.domain.Conditions;
import com.sixin.broad.service.IConditionsService;
import com.sixin.common.support.Convert;

/**
 * 设备报 服务层实现
 * 
 * @author 张超
 * @date 2019-01-15
 */
@Service
public class ConditionsServiceImpl implements IConditionsService 
{
	@Autowired
	private ConditionsMapper conditionsMapper;

	/**
     * 查询设备报信息
     * 
     * @param tid 设备报ID
     * @return 设备报信息
     */
    @Override
	@DataSource(value = DataSourceType.SLAVE)
	public Conditions selectConditionsById(String tid)
	{
	    return conditionsMapper.selectConditionsById(tid);
	}
	
	/**
     * 查询设备报列表
     * 
     * @param conditions 设备报信息
     * @return 设备报集合
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Conditions> selectConditionsList(Conditions conditions)
	{
	    return conditionsMapper.selectConditionsList(conditions);
	}
	
    /**
     * 新增设备报
     * 
     * @param conditions 设备报信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int insertConditions(Conditions conditions)
	{
	    return conditionsMapper.insertConditions(conditions);
	}
	
	/**
     * 修改设备报
     * 
     * @param conditions 设备报信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateConditions(Conditions conditions)
	{
	    return conditionsMapper.updateConditions(conditions);
	}

	/**
     * 删除设备报对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteConditionsByIds(String ids)
	{
		return conditionsMapper.deleteConditionsByIds(Convert.toStrArray(ids));
	}


	/**
	 * 随机选取100个数据
	 *
	 * @param
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Conditions> selectConditionsByRandom(){
			return conditionsMapper.selectConditionsByRandom();
	}
}
