package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.Villageplan;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.mapper.VillageplanMapper;
import com.sixin.village.service.IVillageplanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 美丽乡村 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-08-20
 */
@Service
public class VillageplanServiceImpl implements IVillageplanService 
{
	@Autowired
	private VillageplanMapper villageplanMapper;

	/**
     * 查询美丽乡村信息
     * 
     * @param mid 美丽乡村ID
     * @return 美丽乡村信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Villageplan selectVillageplanById(Integer mid)
	{
	    return villageplanMapper.selectVillageplanById(mid);
	}
	
	/**
     * 查询美丽乡村列表
     * 
     * @param villageplan 美丽乡村信息
     * @return 美丽乡村集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Villageplan> selectVillageplanList(Villageplan villageplan)
	{
	    return villageplanMapper.selectVillageplanList(villageplan);
	}
	
    /**
     * 新增美丽乡村
     * 
     * @param villageplan 美丽乡村信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertVillageplan(Villageplan villageplan)
	{
	    return villageplanMapper.insertVillageplan(villageplan);
	}
	
	/**
     * 修改美丽乡村
     * 
     * @param villageplan 美丽乡村信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateVillageplan(Villageplan villageplan)
	{
	    return villageplanMapper.updateVillageplan(villageplan);
	}

	/**
     * 删除美丽乡村对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deleteVillageplanByIds(String ids)
	{
		return villageplanMapper.deleteVillageplanByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Villageplan> selectBeaCountryListByid(pubObjApi beavill)
	{
		return villageplanMapper.selectBeaCountryListByid(beavill);
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Villageplan> selectBeaCountryListByids(List<String> id) {
		return villageplanMapper.selectBeaCountryListByids(id);
	}
}
