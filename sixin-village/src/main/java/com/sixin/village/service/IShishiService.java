package com.sixin.village.service;

import com.sixin.village.domain.Shishi;
import java.util.List;

/**
 * 实时政事 服务层
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
public interface IShishiService 
{
	/**
     * 查询实时政事信息
     * 
     * @param shiid 实时政事ID
     * @return 实时政事信息
     */
	public Shishi selectShishiById(Integer shiid);
	
	/**
     * 查询实时政事列表
     * 
     * @param shishi 实时政事信息
     * @return 实时政事集合
     */
	public List<Shishi> selectShishiList(Shishi shishi);
	
	/**
     * 新增实时政事
     * 
     * @param shishi 实时政事信息
     * @return 结果
     */
	public int insertShishi(Shishi shishi);
	
	/**
     * 修改实时政事
     * 
     * @param shishi 实时政事信息
     * @return 结果
     */
	public int updateShishi(Shishi shishi);
		
	/**
     * 删除实时政事信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteShishiByIds(String ids);
	
}
