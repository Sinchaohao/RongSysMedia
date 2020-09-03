package com.sixin.village.service.impl;

import java.util.List;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.village.domain.Villageuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.village.mapper.WuserMapper;
import com.sixin.village.domain.Wuser;
import com.sixin.village.service.IWuserService;
import com.sixin.common.support.Convert;

/**
 * 用户基本 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
@Service
public class WuserServiceImpl implements IWuserService 
{
	@Autowired
	private WuserMapper wuserMapper;

	/**
     * 查询用户基本信息
     * 
     * @param uid 用户基本ID
     * @return 用户基本信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Wuser selectWuserById(Integer uid)
	{
	    return wuserMapper.selectWuserById(uid);
	}
	
	/**
     * 查询用户基本列表
     * 
     * @param wuser 用户基本信息
     * @return 用户基本集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Wuser> selectWuserList(Wuser wuser)
	{
	    return wuserMapper.selectWuserList(wuser);
	}
	
    /**
     * 新增微信用户基本
     * 
     * @param wuser 用户基本信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertWuser(Wuser wuser)
	{
	    return wuserMapper.insertWuser(wuser);
	}
	
	/**
     * 修改用户基本
     * 
     * @param wuser 用户基本信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateWuser(Wuser wuser)
	{
	    return wuserMapper.updateWuser(wuser);
	}

	/**
     * 删除用户基本对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deleteWuserByIds(String ids)
	{
		return wuserMapper.deleteWuserByIds(Convert.toStrArray(ids));
	}

	/**
	 * 新增平台用户基本
     *
	 * @param villageuser 用户基本信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertVillageuser(Villageuser villageuser)
	{
		return wuserMapper.insertVillageuser(villageuser);
	}
}
