package com.sixin.broad.mapper;

import com.sixin.broad.domain.Traffic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流量 数据层
 *
 * @author 张超
 * @date 2019-01-15
 */
public interface TrafficMapper
{
	/**
     * 查询流量信息
     *
     * @param ttid 流量ID
     * @return 流量信息
     */
	public Traffic selectTrafficById(Integer ttid);

	/**
     * 查询流量列表
     *
     * @param traffic 流量信息
     * @return 流量集合
     */
	public List<Traffic> selectTrafficList(Traffic traffic);

	/**
     * 新增流量
     *
     * @param traffic 流量信息
     * @return 结果
     */
	public int insertTraffic(Traffic traffic);

	/**
     * 修改流量
     *
     * @param traffic 流量信息
     * @return 结果
     */
	public int updateTraffic(Traffic traffic);

	/**
	 * 修改流量限制
	 *
	 * @param traffic 流量信息
	 * @return 结果
	 */
	public int updateTrafficSet(Traffic traffic);
	/**
     * 删除流量
     *
     * @param ttid 流量ID
     * @return 结果
     */
	public int deleteTrafficById(Integer ttid);

	/**
     * 批量删除流量
     *
     * @param ttids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTrafficByIds(String[] ttids);

	/**
	 *  批量设置流量限制
	 *
	 * @param ids 需要修改终端
	 * @return 结果
	 */
	public int updateRdsByIds(@Param("ids") String[] ids, @Param("number")String number);

	/**
	 *  批量设置限终端启用
	 *
	 * @param ids 需要修改终端
	 * @return 结果
	 */
	public int updateFmfrequencyByIds(@Param("ids") String[] ids, @Param("number")String number);
}