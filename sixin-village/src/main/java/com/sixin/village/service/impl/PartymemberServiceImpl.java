package com.sixin.village.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.village.domain.Pedulevel;
import com.sixin.village.domain.Pmcount;
import com.sixin.village.domain.pubObjApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.village.mapper.PartymemberMapper;
import com.sixin.village.domain.Partymember;
import com.sixin.village.service.IPartymemberService;
import com.sixin.common.support.Convert;

/**
 * 暮云党员 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-01-20
 */
@Service
public class PartymemberServiceImpl implements IPartymemberService
{
	@Autowired
	private PartymemberMapper partymemberMapper;

	/**
     * 查询暮云党员信息
     * 
     * @param pid 暮云党员ID
     * @return 暮云党员信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Partymember selectPartymemberById(Integer pid)
	{
	    return partymemberMapper.selectPartymemberById(pid);
	}
	
	/**
     * 查询暮云党员列表
     * 
     * @param partymember 暮云党员信息
     * @return 暮云党员集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Partymember> selectPartymemberList(Partymember partymember)
	{
	    return partymemberMapper.selectPartymemberList(partymember);
	}
	
    /**
     * 新增暮云党员
     * 
     * @param partymember 暮云党员信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertPartymember(Partymember partymember)
	{
	    return partymemberMapper.insertPartymember(partymember);
	}
	
	/**
     * 修改暮云党员
     * 
     * @param partymember 暮云党员信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updatePartymember(Partymember partymember)
	{
	    return partymemberMapper.updatePartymember(partymember);
	}

	/**
     * 删除暮云党员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deletePartymemberByIds(String ids)
	{
		return partymemberMapper.deletePartymemberByIds(Convert.toStrArray(ids));
	}

	/**
	 * 按照地区统计党员信息
	 *
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Pmcount> countbygroup(String deptId){
		return partymemberMapper.countbygroup(deptId);
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Partymember> selectGrouplist(pubObjApi group)
	{
		return partymemberMapper.selectGrouplist(group);
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Pedulevel> countbyedulevel(){
		return partymemberMapper.countbyedulevel();
	}


	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Partymember> selectPartymemberListBytype(pubObjApi group)
	{
		return partymemberMapper.selectPartymemberListBytype(group);
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Partymember> selectPartymemberByIds(List<String> id) {
		return partymemberMapper.selectPartymemberByIds(id);
	}
}
