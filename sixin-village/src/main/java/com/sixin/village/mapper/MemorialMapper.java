package com.sixin.village.mapper;

import com.sixin.village.domain.Memorial;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * 备忘录 数据层
 * 
 * @author 张鸿权
 * @date 2019-08-15
 */
public interface MemorialMapper 
{
	/**
     * 查询备忘录信息
     * 
     * @param mid 备忘录ID
     * @return 备忘录信息
     */
	public Memorial selectMemorialById(Integer mid);
	
	/**
     * 查询备忘录列表
     * 
     * @param memorial 备忘录信息
     * @return 备忘录集合
     */
	public List<Memorial> selectMemorialList(Memorial memorial);
	
	/**
     * 新增备忘录
     * 
     * @param memorial 备忘录信息
     * @return 结果
     */
	public int insertMemorial(Memorial memorial);
	
	/**
     * 修改备忘录
     * 
     * @param memorial 备忘录信息
     * @return 结果
     */
	public int updateMemorial(Memorial memorial);
	
	/**
     * 删除备忘录
     * 
     * @param mid 备忘录ID
     * @return 结果
     */
	public int deleteMemorialById(Integer mid);
	
	/**
     * 批量删除备忘录
     * 
     * @param mids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemorialByIds(String[] mids);

	/**
	 * @Author TanXY
	 * @Description 按需导出数据
	 * @Date 2020/4/17 22:24
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.Memorial>
	 */
	public List<Memorial>  selectMemorialByIds(List<String> id);

	public List<Memorial> selectMemorialListById(pubObjApi memorial);
}