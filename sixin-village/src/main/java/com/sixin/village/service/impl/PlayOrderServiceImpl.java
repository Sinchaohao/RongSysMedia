package com.sixin.village.service.impl;

import com.sixin.common.exception.base.BaseException;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.PlaysOrder;
import com.sixin.village.mapper.PlayOrderMapper;
import com.sixin.village.service.IPlayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 播放顺序 服务层实现
 * 
 * @author yc
 * @date 2018-12-25
 */
@Service
public class PlayOrderServiceImpl implements IPlayOrderService
{
	@Autowired
	private PlayOrderMapper orderMapper;

	/**
     * 查询播放顺序信息
     * 
     * @param id 播放顺序ID
     * @return 播放顺序信息
     */
    @Override
	public PlaysOrder selectOrderById(Long id)
	{
	    return orderMapper.selectOrderById(id);
	}
	
	/**
     * 查询播放顺序列表
     * 
     * @param order 播放顺序信息
     * @return 播放顺序集合
     */
	@Override
	public List<PlaysOrder> selectOrderList(PlaysOrder order)
	{
	    return orderMapper.selectOrderList(order);
	}
	
    /**
     * 新增播放顺序
     * 
     * @param order 播放顺序信息
     * @return 结果
     */
	@Override
	public int insertOrder(PlaysOrder order)
	{
	    return orderMapper.insertOrder(order);
	}
	
	/**
     * 修改播放顺序
     * 
     * @param order 播放顺序信息
     * @return 结果
     */
	@Override
	public int updateOrder(PlaysOrder order)
	{
	    return orderMapper.updateOrder(order);
	}

	/**
     * 删除播放顺序对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOrderByIds(String ids)
	{
		return orderMapper.deleteOrderByIds(Convert.toStrArray(ids));
	}

	@Override
	public int updateOrderLimit(Long id, int rtn,int orderby) {
		return orderMapper.updateOrderLimit(id, rtn,orderby);
	}

	@Override
	public PlaysOrder getByid(Long id) {
		return orderMapper.getById(id);
	}

	@Override
	public List<PlaysOrder> selectByChannelId(Long channelId) {
		if (channelId == null) {
			throw new BaseException("保存异常，请稍后重试！");
		}
		PlaysOrder order = new PlaysOrder();
		order.setChannelId(channelId);
		return orderMapper.selectOrderList(order);
	}

	@Override
	public PlaysOrder selectMaxLimit(Long channelId) {
		return orderMapper.selectMaxLimit(channelId);
	}

}
