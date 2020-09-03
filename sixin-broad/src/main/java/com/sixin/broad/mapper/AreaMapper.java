package com.sixin.broad.mapper;

import com.sixin.broad.domain.Area;
import com.sixin.broad.domain.AreaGrouping;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 终端地域 数据层
 * 
 * @author 张超
 * @date 2019-01-17
 */
public interface AreaMapper 
{
	/**
     * 查询终端地域信息
     * 
     * @param aid 终端地域ID
     * @return 终端地域信息
     */
	@DataSource(DataSourceType.SLAVE)
	public Area selectAreaById(long aid);

	@DataSource(DataSourceType.SLAVE)
	public Area selectAreaLikeId(long aid);
	/**
     * 查询终端地域列表
     * 
     * @param area 终端地域信息
     * @return 终端地域集合
     */
	@DataSource(DataSourceType.SLAVE)
	public List<Area> selectAreaList(Area area);

	/**
	 * 通过aid查询终端地域列表
	 *
	 * @param area 终端地域信息
	 * @return 终端地域集合
	 */
	@DataSource(DataSourceType.SLAVE)
	public List<Area> chooseAreaList(Area area);
	/**
     * 新增终端地域
     * 
     * @param area 终端地域信息
     * @return 结果
     */
	@DataSource(DataSourceType.SLAVE)
	public int insertArea(Area area);

	/**
     * 修改终端地域
     * 
     * @param area 终端地域信息
     * @return 结果
     */
	@DataSource(DataSourceType.SLAVE)
	public int updateArea(Area area);
	
	/**
     * 删除终端地域
     * 
     * @param aid 终端地域ID
     * @return 结果
     */
	@DataSource(DataSourceType.SLAVE)
	public int deleteAreaById(long aid);
	
	/**
     * 批量删除终端地域
     * 
     * @param aids 需要删除的数据ID
     * @return 结果
     */
	@DataSource(DataSourceType.SLAVE)
	public int deleteAreaByIds(long[] aids);

	/**
	 * 树
	 */
	@DataSource(DataSourceType.SLAVE)
	public Area selectAllArea();

	@DataSource(DataSourceType.SLAVE)
	public List<Area> selectAreaListByIds(@Param("array") List<Object> array);
}