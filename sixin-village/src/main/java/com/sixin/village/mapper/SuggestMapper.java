package com.sixin.village.mapper;

import com.sixin.village.domain.Meeting;
import com.sixin.village.domain.Suggest;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * 我的建议 数据层
 * 
 * @author 张鸿权
 * @date 2019-08-15
 */
public interface SuggestMapper 
{
	/**
     * 查询我的建议信息
     * 
     * @param mid 我的建议ID
     * @return 我的建议信息
     */
	public Suggest selectSuggestById(Integer mid);
	
	/**
     * 查询我的建议列表
     * 
     * @param suggest 我的建议信息
     * @return 我的建议集合
     */
	public List<Suggest> selectSuggestList(Suggest suggest);
	
	/**
     * 新增我的建议
     * 
     * @param suggest 我的建议信息
     * @return 结果
     */
	public int insertSuggest(Suggest suggest);
	
	/**
     * 修改我的建议
     * 
     * @param suggest 我的建议信息
     * @return 结果
     */
	public int updateSuggest(Suggest suggest);
	
	/**
     * 删除我的建议
     * 
     * @param mid 我的建议ID
     * @return 结果
     */
	public int deleteSuggestById(Integer mid);
	
	/**
     * 批量删除我的建议
     * 
     * @param mids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSuggestByIds(String[] mids);

	/**
	 * @Author TanXY
	 * @Description 按需导出数据
	 * @Date 2020/4/18 9:58
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.Suggest>
	 */
	public List<Suggest>  selectSuggestByIds(List<String> id);

	public List<Suggest> selectSuggestListById(pubObjApi meet);
	
}