package com.sixin.village.service;

import com.sixin.village.domain.PlaysOrder;

import java.util.List;

/**
 * 播放顺序 服务层
 * 
 * @author yc
 * @date 2018-12-25
 */
public interface IPlayOrderService
{
	/**
     * 查询播放顺序信息
     * 
     * @param id 播放顺序ID
     * @return 播放顺序信息
     */
	public PlaysOrder selectOrderById(Long id);
	
	/**
     * 查询播放顺序列表
     * 
     * @param order 播放顺序信息
     * @return 播放顺序集合
     */
	public List<PlaysOrder> selectOrderList(PlaysOrder order);
	
	/**
     * 新增播放顺序
     * 
     * @param order 播放顺序信息
     * @return 结果
     */
	public int insertOrder(PlaysOrder order);
	
	/**
     * 修改播放顺序
     * 
     * @param order 播放顺序信息
     * @return 结果
     */
	public int updateOrder(PlaysOrder order);
		
	/**
     * 删除播放顺序信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOrderByIds(String ids);

    int updateOrderLimit(Long id, int rtn, int order);

    PlaysOrder getByid(Long id);

    List<PlaysOrder> selectByChannelId(Long channelId);

    PlaysOrder selectMaxLimit(Long channelId);
}
