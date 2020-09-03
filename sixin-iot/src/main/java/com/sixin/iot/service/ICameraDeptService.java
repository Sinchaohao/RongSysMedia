package com.sixin.iot.service;

import com.sixin.common.base.Ztree;
import com.sixin.iot.domain.CameraDept;
import com.sixin.system.domain.SysDept;
import com.sixin.system.domain.SysRole;

import java.util.List;
import java.util.Map;

/**
 * 部门管理 服务层
 * 
 * @author sixin
 */
public interface ICameraDeptService
{
    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<CameraDept> selectDeptList(CameraDept dept);

    public List<CameraDept> chooseDeptList(CameraDept dept);
    /**
     * 查询部门管理树
     * 
     * @param dept 部门信息
     * @return 所有部门信息
     */
    public List<Ztree> selectDeptTree(CameraDept dept);

    public List<Map<String, Object>> selectDeptTree2(CameraDept dept);
    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    public List<Ztree> roleDeptTreeData(SysRole role);

    /**
     * 查询部门人数
     * 
     * @param parentId 父部门ID
     * @return 结果
     */
    public int selectDeptCount(Long parentId);

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDeptExistUser(Long deptId);

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);

    /**
     * 新增保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int insertDept(CameraDept dept);

    /**
     * 修改保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int updateDept(CameraDept dept);

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    public CameraDept selectDeptById(Long deptId);
    public CameraDept selectDeptById2(Long deptId);
    /**
     * 校验部门名称是否唯一
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public String checkDeptNameUnique(CameraDept dept);
}
