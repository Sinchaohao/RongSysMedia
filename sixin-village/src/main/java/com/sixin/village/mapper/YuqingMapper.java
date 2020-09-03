package com.sixin.village.mapper;

import com.sixin.village.domain.Yuqing;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 舆情上报 数据层
 *
 * @author 张鸿权
 * @date 2019-08-18
 */
public interface YuqingMapper
{
	/**
	 * 查询舆情上报信息
	 *
	 * @param yqid 舆情上报ID
	 * @return 舆情上报信息
	 */
	public Yuqing selectYuqingById(Integer yqid);

	/**
	 * 查询舆情上报列表
	 *
	 * @param yuqing 舆情上报信息
	 * @return 舆情上报集合
	 */
	public List<Yuqing> selectYuqingList(Yuqing yuqing);

	/**
	 * 新增舆情上报
	 *
	 * @param yuqing 舆情上报信息
	 * @return 结果
	 */
	public int insertYuqing(Yuqing yuqing);

	/**
	 * 修改舆情上报
	 *
	 * @param yuqing 舆情上报信息
	 * @return 结果
	 */
	public int updateYuqing(Yuqing yuqing);

	/**
	 * 删除舆情上报
	 *
	 * @param yqid 舆情上报ID
	 * @return 结果
	 */
	public int deleteYuqingById(Integer yqid);

	/**
	 * 批量删除舆情上报
	 *
	 * @param yqids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteYuqingByIds(String[] yqids);

	public int updateIsuseByYqid(@Param("tid") String yqid, @Param("remark")String remark);


	public List<Yuqing> selectYuqingByIds(List<String> id);
}