package com.sixin.village.mapper;

import com.sixin.village.domain.HouseInfo;
import java.util.List;	

/**
 * 房屋 数据层
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
public interface HouseInfoMapper 
{
	/**
     * 查询房屋信息
     * 
     * @param hid 房屋ID
     * @return 房屋信息
     */
	public HouseInfo selectHouseInfoById(Integer hid);
	
	/**
     * 查询房屋列表
     * 
     * @param houseInfo 房屋信息
     * @return 房屋集合
     */
	public List<HouseInfo> selectHouseInfoList(HouseInfo houseInfo);
	
	/**
     * 新增房屋
     * 
     * @param houseInfo 房屋信息
     * @return 结果
     */
	public int insertHouseInfo(HouseInfo houseInfo);
	
	/**
     * 修改房屋
     * 
     * @param houseInfo 房屋信息
     * @return 结果
     */
	public int updateHouseInfo(HouseInfo houseInfo);
	
	/**
     * 删除房屋
     * 
     * @param hid 房屋ID
     * @return 结果
     */
	public int deleteHouseInfoById(Integer hid);
	
	/**
     * 批量删除房屋
     * 
     * @param hids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHouseInfoByIds(String[] hids);

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 21:57
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.ForestlandInfo>
	 */
	public List<HouseInfo> selectHouseInfoByIds(List<String> id);
	
}