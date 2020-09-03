package com.sixin.village.service;

import com.sixin.village.domain.Villageplan;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * 美丽乡村 服务层
 * 
 * @author 张鸿权
 * @date 2019-08-20
 */
public interface IVillageplanService 
{
	/**
     * 查询美丽乡村信息
     * 
     * @param mid 美丽乡村ID
     * @return 美丽乡村信息
     */
	public Villageplan selectVillageplanById(Integer mid);
	
	/**
     * 查询美丽乡村列表
     * 
     * @param villageplan 美丽乡村信息
     * @return 美丽乡村集合
     */
	public List<Villageplan> selectVillageplanList(Villageplan villageplan);
	
	/**
     * 新增美丽乡村
     * 
     * @param villageplan 美丽乡村信息
     * @return 结果
     */
	public int insertVillageplan(Villageplan villageplan);
	
	/**
     * 修改美丽乡村
     * 
     * @param villageplan 美丽乡村信息
     * @return 结果
     */
	public int updateVillageplan(Villageplan villageplan);
		
	/**
     * 删除美丽乡村信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteVillageplanByIds(String ids);

	public List<Villageplan> selectBeaCountryListByid(pubObjApi beavill);

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 16:08
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.Villageplan>
	 */
	public List<Villageplan> selectBeaCountryListByids(List<String> id);
}
