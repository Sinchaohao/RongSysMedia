package com.sixin.village.mapper;

import com.sixin.village.domain.Partymember;
import com.sixin.village.domain.Pedulevel;
import com.sixin.village.domain.Pmcount;
import com.sixin.village.domain.pubObjApi;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 暮云党员 数据层
 * 
 * @author 张鸿权
 * @date 2019-01-20
 */
public interface PartymemberMapper 
{
	/**
     * 查询暮云党员信息
     * 
     * @param pid 暮云党员ID
     * @return 暮云党员信息
     */
	public Partymember selectPartymemberById(Integer pid);
	
	/**
     * 查询暮云党员列表
     * 
     * @param partymember 暮云党员信息
     * @return 暮云党员集合
     */
	public List<Partymember> selectPartymemberList(Partymember partymember);
	
	/**
     * 新增暮云党员
     * 
     * @param partymember 暮云党员信息
     * @return 结果
     */
	public int insertPartymember(Partymember partymember);
	
	/**
     * 修改暮云党员
     * 
     * @param partymember 暮云党员信息
     * @return 结果
     */
	public int updatePartymember(Partymember partymember);
	
	/**
     * 删除暮云党员
     * 
     * @param pid 暮云党员ID
     * @return 结果
     */
	public int deletePartymemberById(Integer pid);
	
	/**
     * 批量删除暮云党员
     * 
     * @param pids 需要删除的数据ID
     * @return 结果
     */
	public int deletePartymemberByIds(String[] pids);

	/**
	 * 按照地区统计党员信息
	 *
	 * @return 结果
	 */
	public List<Pmcount> countbygroup(@Param("aid")String deptId);

	public List<Partymember> selectGrouplist(pubObjApi group);

	public List<Pedulevel> countbyedulevel();

	public List<Partymember> selectPartymemberListBytype(pubObjApi group);

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 18:57
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.Partymember>
	 */
	public List<Partymember> selectPartymemberByIds(List<String> id);
}