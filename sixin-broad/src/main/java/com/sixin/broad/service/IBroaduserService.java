package com.sixin.broad.service;

import com.sixin.broad.domain.Broaduser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 广播用户 服务层
 * 
 * @author 张鸿权
 * @date 2019-01-18
 */
public interface IBroaduserService 
{
	/**
     * 查询广播用户信息
     * 
     * @param userid 广播用户ID
     * @return 广播用户信息
     */
	public Broaduser selectBroaduserById(String userid);
	
	/**
     * 查询广播用户列表
     * 
     * @param broaduser 广播用户信息
     * @return 广播用户集合
     */
	public List<Broaduser> selectBroaduserList(Broaduser broaduser);

	/**
     * 修改广播用户
     * 
     * @param broaduser 广播用户信息
     * @return 结果
     */
	public int updateBroaduser(Broaduser broaduser);
		
	/**
     * 删除广播用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBroaduserByIds(String ids);

	/**
	 *
	 * @param user_id
	 * @param username
	 * @param uname
	 * @param uphone
	 * @return
	 */
	public void insertBroaduser(Broaduser broaduser1);

}
