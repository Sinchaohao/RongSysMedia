package com.sixin.village.mapper;

import com.sixin.village.domain.PlaysOrder;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 播放顺序 数据层
 * 
 * @author yc
 * @date 2018-12-25
 */
public interface PlayOrderMapper
{
	/**
     * 查询播放顺序信息
     * 
     * @param id 播放顺序ID
     * @return 播放顺序信息
     */
	public PlaysOrder selectOrderById(Long id);


	@Select("select * from play_order where id = #{id}")
	PlaysOrder getById(Long id);
	
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
     * 删除播放顺序
     * 
     * @param id 播放顺序ID
     * @return 结果
     */
	public int deleteOrderById(Long id);
	
	/**
     * 批量删除播放顺序
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOrderByIds(String[] ids);

	@Update("update plays_order a set a.order_by =a.order_by-#{rtn} where a.channel_id=#{id} and a.order_by > #{orderBy}")
    int updateOrderLimit(Long id, int rtn, int orderBy);

	@Select("select a.id as id, a.channel_id as channelId, a.file_name as fileName,a.order_by as orderBy from plays_order a where a.channel_id=#{channelId} order by order_by desc limit 1")
    PlaysOrder selectMaxLimit(Long channelId);
}