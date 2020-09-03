package com.sixin.broad.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.broad.mapper.BroaduserMapper;
import com.sixin.broad.domain.Broaduser;
import com.sixin.broad.service.IBroaduserService;
import com.sixin.common.support.Convert;

/**
 * 广播用户 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-01-18
 */
@Service
public class BroaduserServiceImpl implements IBroaduserService 
{
	@Autowired
	private BroaduserMapper broaduserMapper;

	/**
     * 查询广播用户信息
     * 
     * @param userid 广播用户ID
     * @return 广播用户信息
     */
    @Override
	@DataSource(value = DataSourceType.SLAVE)
	public Broaduser selectBroaduserById(String userid)
	{
	    return broaduserMapper.selectBroaduserById(userid);
	}
	
	/**
     * 查询广播用户列表
     * 
     * @param broaduser 广播用户信息
     * @return 广播用户集合
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Broaduser> selectBroaduserList(Broaduser broaduser)
	{
	    return broaduserMapper.selectBroaduserList(broaduser);
	}

	/**
     * 修改广播用户
     * 
     * @param broaduser 广播用户信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateBroaduser(Broaduser broaduser)
	{
	    return broaduserMapper.updateBroaduser(broaduser);
	}

	/**
     * 删除广播用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteBroaduserByIds(String ids)
	{
		return broaduserMapper.deleteBroaduserByIds(Convert.toStrArray(ids));
	}

	/**
	 * 新增广播用户信息
	 *
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public void insertBroaduser(Broaduser broaduser1)
	{
		 broaduserMapper.insertBroaduser(broaduser1);
	}
}
