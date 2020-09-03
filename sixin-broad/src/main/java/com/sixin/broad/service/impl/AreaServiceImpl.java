package com.sixin.broad.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sixin.broad.domain.AreaGrouping;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.broad.mapper.AreaMapper;
import com.sixin.broad.domain.Area;
import com.sixin.broad.service.IAreaService;
import com.sixin.common.support.Convert;

/**
 * 终端地域 服务层实现
 *
 * @author 张超
 * @date 2019-01-17
 */
@Service
public class AreaServiceImpl implements IAreaService
{
	@Autowired
	private AreaMapper areaMapper;

	/**
     * 查询终端地域信息
     *
     * @param aid 终端地域ID
     * @return 终端地域信息
     */
    @Override
	@DataSource(value = DataSourceType.SLAVE)
	public Area selectAreaById(long aid)
	{
	    return areaMapper.selectAreaById(aid);
	}

	public Area selectAreaLikeId(long aid)
	{
		return areaMapper.selectAreaLikeId(aid);
	}

	/**
	    * 查询终端地区树
	    * @author 张超 teavamc
	    * @date 2019/2/14
	    * @param
	    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	    */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Map<String, Object>> selectAreaTree(Area area){
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		List<Area> areaList = areaMapper.chooseAreaList(area);
		trees = getTrees(areaList);
		return trees;
	}


	/**
	    * 根据List生产tree，前提是模型中包含id和父id关系
	    * @author 张超 teavamc
	    * @date 2019/2/14
	    * @param
	    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	    */
	public List<Map<String, Object>> getTrees(List<Area> areaList)
	{

		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		for (Area area : areaList)
		{
			Map<String, Object> areaMap = new HashMap<String, Object>();
			areaMap.put("id", area.getAid());
			areaMap.put("pId", area.getParentaid());
			areaMap.put("name", area.getAname());
			areaMap.put("title", area.getAname());
			trees.add(areaMap);
		}
		return trees;
	}

	/**
     * 查询终端地域列表
     *
     * @param area 终端地域信息
     * @return 终端地域集合
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Area> selectAreaList(Area area)
	{
	    return areaMapper.selectAreaList(area);
	}

	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Area> chooseAreaList(Area area) {
		return areaMapper.chooseAreaList(area);
	}

	/**
     * 新增终端地域
     *
     * @param area 终端地域信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int insertArea(Area area)
	{
	    return areaMapper.insertArea(area);
	}

	/**
     * 修改终端地域
     *
     * @param area 终端地域信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateArea(Area area)
	{
	    return areaMapper.updateArea(area);
	}

	/**
     * 删除终端地域对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteAreaByIds(long[] ids)
	{
		return areaMapper.deleteAreaByIds(ids);
	}

	/**
	 * 树
	 *
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public Area  selectAllArea(){return areaMapper.selectAllArea();}

	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Area>  selectAreaListByIds(List<Object> array)
	{
		return areaMapper.selectAreaListByIds(array);
	}
}
