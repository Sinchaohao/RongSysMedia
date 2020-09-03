package com.sixin.village.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.village.mapper.PartyscoreMapper;
import com.sixin.village.domain.Partyscore;
import com.sixin.village.service.IPartyscoreService;
import com.sixin.common.support.Convert;

/**
 * 党员自评 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-01-20
 */
@Service
public class PartyscoreServiceImpl implements IPartyscoreService 
{
	@Autowired
	private PartyscoreMapper partyscoreMapper;

	/**
     * 查询党员自评信息
     * 
     * @param sfid 党员自评ID
     * @return 党员自评信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Partyscore selectPartyscoreById(Integer sfid)
	{
	    return partyscoreMapper.selectPartyscoreById(sfid);
	}
	
	/**
     * 查询党员自评列表
     * 
     * @param partyscore 党员自评信息
     * @return 党员自评集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Partyscore> selectPartyscoreList(Partyscore partyscore)
	{
	    return partyscoreMapper.selectPartyscoreList(partyscore);
	}
	
    /**
     * 新增党员自评
     * 
     * @param partyscore 党员自评信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertPartyscore(Partyscore partyscore)
	{
	    return partyscoreMapper.insertPartyscore(partyscore);
	}
	
	/**
     * 修改党员自评
     * 
     * @param partyscore 党员自评信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updatePartyscore(Partyscore partyscore)
	{
	    return partyscoreMapper.updatePartyscore(partyscore);
	}

	/**
     * 删除党员自评对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deletePartyscoreByIds(String ids)
	{
		return partyscoreMapper.deletePartyscoreByIds(Convert.toStrArray(ids));
	}
	
}
