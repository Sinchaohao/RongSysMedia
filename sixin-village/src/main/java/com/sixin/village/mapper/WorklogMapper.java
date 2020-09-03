package com.sixin.village.mapper;

import com.sixin.village.domain.VillagerInfo;
import com.sixin.village.domain.Worklog;
import com.sixin.village.domain.pubObjApi;
import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.HashMap;
import java.util.List;

/**
 * 工作记录 数据层
 * 
 * @author 张鸿权
 * @date 2019-07-23
 */
public interface WorklogMapper 
{
	/**
     * 查询工作记录信息
     * 
     * @param wid 工作记录ID
     * @return 工作记录信息
     */
	public Worklog selectWorklogById(Integer wid);

	public  List<HashMap> selectWorkLogByProId(Integer proid);
	public int selectWorkLogNumByProId(Integer proid);
	/**
     * 查询工作记录列表
     * 
     * @param worklog 工作记录信息
     * @return 工作记录集合
     */
	public List<Worklog> selectWorklogList(Worklog worklog);
	
	/**
     * 新增工作记录
     * 
     * @param worklog 工作记录信息
     * @return 结果
     */
	public int insertWorklog(Worklog worklog);
	
	/**
     * 修改工作记录
     * 
     * @param worklog 工作记录信息
     * @return 结果
     */
	public int updateWorklog(Worklog worklog);
	
	/**
     * 删除工作记录
     * 
     * @param wid 工作记录ID
     * @return 结果
     */
	public int deleteWorklogById(Integer wid);
	
	/**
     * 批量删除工作记录
     * 
     * @param wids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWorklogByIds(String[] wids);

	/**
	 * @Author TanXY
	 * @Description 按需导出数据
	 * @Date 2020/4/17 21:40
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.VillagerInfo>
	 */
	public List<Worklog> selectWorklogByIds(List<String> id);

	public List<Worklog> selectWorklogListByid(pubObjApi worklog);
}