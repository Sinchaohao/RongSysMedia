package com.sixin.rivervis.mapper;
/**
 * @function:环境监测 数据层
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:张超
 * @Description:
 * @Date:Created in 2020-01-27 17:43
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */
import com.sixin.rivervis.domain.EnvData;
import com.sixin.rivervis.domain.Riverbytl;
import java.util.List;

public interface EnvDataMapper 
{
	/**
     * 查询环境监测信息
     * 
     * @param id 环境监测ID
     * @return 环境监测信息
     */
	public EnvData selectEnvDataById(Integer id);
	
	/**
     * 查询环境监测列表
     * 
     * @param envData 环境监测信息
     * @return 环境监测集合
     */
	public List<EnvData> selectEnvDataList(EnvData envData);

	/**
	 * 查询环境监测列表 前20条数据
	 *
	 * @param envData 环境监测信息
	 * @return 环境监测集合
	 */
	public List<EnvData> selectEnvDataListLimit();

	/**
	    * 统计山洪数据总数
	    * @author 张超 teavamc
	    * @date 2019/3/10
	    * @param []
	    * @return int
	    */
	public int countall();

	/**
     * 新增环境监测
     * 
     * @param envData 环境监测信息
     * @return 结果
     */
	public int insertEnvData(EnvData envData);
	
	/**
     * 修改环境监测
     * 
     * @param envData 环境监测信息
     * @return 结果
     */
	public int updateEnvData(EnvData envData);
	
	/**
     * 删除环境监测
     * 
     * @param id 环境监测ID
     * @return 结果
     */
	public int deleteEnvDataById(Integer id);
	
	/**
     * 批量删除环境监测
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteEnvDataByIds(String[] ids);

	/**
	 * 根据时间范围和查询条数统计环境数据
	 *
	 * @param envData 环境监测信息
	 * @return 环境监测集合
	 */
	public List<EnvData> selectEnvDataListbytl(Riverbytl riverbytl);
}