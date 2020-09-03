package com.sixin.village.service;

import com.sixin.village.domain.TbHuodong;
import java.util.List;

/**
 * 党员活动 服务层
 * 
 * @author 张鸿权
 * @date 2019-11-16
 */
public interface ITbHuodongService 
{
	/**
     * 查询党员活动信息
     * 
     * @param hdid 党员活动ID
     * @return 党员活动信息
     */
	public TbHuodong selectTbHuodongById(Integer hdid);
	
	/**
     * 查询党员活动列表
     * 
     * @param tbHuodong 党员活动信息
     * @return 党员活动集合
     */
	public List<TbHuodong> selectTbHuodongList(TbHuodong tbHuodong);
	
	/**
     * 新增党员活动
     * 
     * @param tbHuodong 党员活动信息
     * @return 结果
     */
	public int insertTbHuodong(TbHuodong tbHuodong);
	
	/**
     * 修改党员活动
     * 
     * @param tbHuodong 党员活动信息
     * @return 结果
     */
	public int updateTbHuodong(TbHuodong tbHuodong);
		
	/**
     * 删除党员活动信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTbHuodongByIds(String ids);

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 18:20
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.TbHuodong>
	 */
	public List<TbHuodong> selectTbHuodongByIds(List<String> id);
	
}
