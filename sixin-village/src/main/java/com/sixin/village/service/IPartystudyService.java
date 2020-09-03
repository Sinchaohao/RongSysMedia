package com.sixin.village.service;

import com.sixin.village.domain.Partystudy;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * 党员学习 服务层
 * 
 * @author 张鸿权
 * @date 2019-08-25
 */
public interface IPartystudyService 
{
	/**
     * 查询党员学习信息
     * 
     * @param mid 党员学习ID
     * @return 党员学习信息
     */
	public Partystudy selectPartystudyById(Integer mid);
	
	/**
     * 查询党员学习列表
     * 
     * @param partystudy 党员学习信息
     * @return 党员学习集合
     */
	public List<Partystudy> selectPartystudyList(Partystudy partystudy);
	
	/**
     * 新增党员学习
     * 
     * @param partystudy 党员学习信息
     * @return 结果
     */
	public int insertPartystudy(Partystudy partystudy);
	
	/**
     * 修改党员学习
     * 
     * @param partystudy 党员学习信息
     * @return 结果
     */
	public int updatePartystudy(Partystudy partystudy);
		
	/**
     * 删除党员学习信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePartystudyByIds(String ids);

	public List<Partystudy> selectPartystudyListById(pubObjApi partystudy);

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 16:59
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.Partystudy>
	 */
	public List<Partystudy> selectPartystudyByIds(List<String> id);
	
}
