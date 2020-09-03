package com.sixin.iot.mapper;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.iot.domain.CameraDept;
import com.sixin.system.domain.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 摄像头管理 数据层
 * 
 * @author sixin
 */
public interface CameraDeptMapper
{
    /**
     * 查询部门人数
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @DataSource(DataSourceType.SXINFOM)
    public int selectDeptCount(CameraDept dept);

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    @DataSource(DataSourceType.SXINFOM)
    public int checkDeptExistUser(Long deptId);

    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @DataSource(DataSourceType.SXINFOM)
    public List<CameraDept> selectDeptList(CameraDept dept);
    @DataSource(DataSourceType.SXINFOM)
    public List<CameraDept> chooseDeptList(CameraDept dept);
    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    @DataSource(DataSourceType.SXINFOM)
    public int deleteDeptById(Long deptId);

    /**
     * 新增部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @DataSource(DataSourceType.SXINFOM)
    public int insertDept(CameraDept dept);

    /**
     * 修改部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @DataSource(DataSourceType.SXINFOM)
    public int updateDept(CameraDept dept);

    /**
     * 修改子元素关系
     * 
     * @param depts 子元素
     * @return 结果
     */
    @DataSource(DataSourceType.SXINFOM)
    public int updateDeptChildren(@Param("depts") List<CameraDept> depts);

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    @DataSource(DataSourceType.SXINFOM)
    public CameraDept selectDeptById(Long deptId);

    @DataSource(DataSourceType.SXINFOM)
    public CameraDept selectDeptById2(Long deptId);
    /**
     * 校验部门名称是否唯一
     * 
     * @param deptName 部门名称
     * @param parentId 父部门ID
     * @return 结果
     */
    @DataSource(DataSourceType.SXINFOM)
    public CameraDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    /**
     * 根据角色ID查询部门
     *
     * @param roleId 角色ID
     * @return 部门列表
     */
    @DataSource(DataSourceType.SXINFOM)
    public List<String> selectRoleDeptTree(Long roleId);

    /**
     * 修改所在部门的父级部门状态
     * 
     * @param dept 部门
     */
    @DataSource(DataSourceType.SXINFOM)
    public void updateDeptStatus(CameraDept dept);

    /**
     * 根据ID查询所有子部门
     * @param deptId 部门ID
     * @return 部门列表
     */
    @DataSource(DataSourceType.SXINFOM)
    public List<CameraDept> selectChildrenDeptById(Long deptId);
}
