package com.sixin.village.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.village.domain.pubObjApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.village.mapper.ProjectMapper;
import com.sixin.village.domain.Project;
import com.sixin.village.service.IProjectService;
import com.sixin.common.support.Convert;

/**
 * 重大项目 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-01-19
 */
@Service
public class ProjectServiceImpl implements IProjectService 
{
	@Autowired
	private ProjectMapper projectMapper;

	/**
     * 查询重大项目信息
     * 
     * @param proid 重大项目ID
     * @return 重大项目信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Project selectProjectById(Integer proid)
	{
	    return projectMapper.selectProjectById(proid);
	}
	
	/**
     * 查询重大项目列表
     * 
     * @param project 重大项目信息
     * @return 重大项目集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Project> selectProjectList(Project project)
	{
	    return projectMapper.selectProjectList(project);
	}
	
    /**
     * 新增重大项目
     * 
     * @param project 重大项目信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertProject(Project project)
	{
	    return projectMapper.insertProject(project);
	}
	
	/**
     * 修改重大项目
     * 
     * @param project 重大项目信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateProject(Project project)
	{
	    return projectMapper.updateProject(project);
	}

	/**
     * 删除重大项目对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deleteProjectByIds(String ids)
	{
		return projectMapper.deleteProjectByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Project> selectProjectByIds(List<String> id) {
		return projectMapper.selectProjectByIds(id);
	}


	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Project> selectProjectListForapp(pubObjApi project)
	{
		return projectMapper.selectProjectListForapp(project);
	}
}
