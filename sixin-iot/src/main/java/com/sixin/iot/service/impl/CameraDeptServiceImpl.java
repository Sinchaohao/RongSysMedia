package com.sixin.iot.service.impl;

import com.sixin.common.annotation.DataScope;
import com.sixin.common.base.Ztree;
import com.sixin.common.constant.UserConstants;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import com.sixin.iot.domain.CameraDept;
import com.sixin.iot.mapper.CameraDeptMapper;
import com.sixin.iot.service.ICameraDeptService;
import com.sixin.system.domain.SysDept;
import com.sixin.system.domain.SysRole;
import com.sixin.system.mapper.SysDeptMapper;
import com.sixin.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门管理 服务实现
 * 
 * @author sixin
 */
@Service
public class CameraDeptServiceImpl implements ICameraDeptService
{
    @Autowired
    private CameraDeptMapper deptMapper;

    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<CameraDept> selectDeptList(CameraDept dept)
    {
        return deptMapper.selectDeptList(dept);
    }

    /**
     * 查询部门管理树
     * 
     * @param dept 部门信息
     * @return 所有部门信息
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<Ztree> selectDeptTree(CameraDept dept)
    {
        List<CameraDept> deptList = deptMapper.selectDeptList(dept);
        return initZtree(deptList);
    }

    @Override
    public List<Map<String, Object>> selectDeptTree2(CameraDept dept){
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<CameraDept> deptList = deptMapper.chooseDeptList(dept);
        /*System.out.println("输出deptlist："+deptList);*/
        trees = getTrees(deptList);
        return trees;
    }

    @Override
    public List<CameraDept> chooseDeptList(CameraDept dept) {
        return deptMapper.chooseDeptList(dept);
    }




    /**
     * 根据角色ID查询部门（数据权限）
     *
     * @param role 角色对象
     * @return 部门列表（数据权限）
     */
    @Override
    public List<Ztree> roleDeptTreeData(SysRole role)
    {
        Long roleId = role.getRoleId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<CameraDept> deptList = selectDeptList(new CameraDept());
        if (StringUtils.isNotNull(roleId))
        {
            List<String> roleDeptList = deptMapper.selectRoleDeptTree(roleId);
            ztrees = initZtree(deptList, roleDeptList);
        }
        else
        {
            ztrees = initZtree(deptList);
        }
        return ztrees;
    }

    /**
     * 对象转部门树
     *
     * @param deptList 部门列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<CameraDept> deptList)
    {
        return initZtree(deptList, null);
    }
    public List<Map<String, Object>> getTrees2(List<CameraDept> broadMessages)
    {

        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (CameraDept broadMessage : broadMessages)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", broadMessage.getDeptId());
            map.put("aid", broadMessage.getDeptId());
            map.put("pId", broadMessage.getParentId());
            map.put("name", broadMessage.getDeptName());
            map.put("title", broadMessage.getUrl());
            map.put("phone", broadMessage.getUrl());
            map.put("isDev", "N".equals(broadMessage.getIsCamera())?0:1);
            trees.add(map);
        }
        return trees;
    }
    public List<Map<String, Object>> getTrees(List<CameraDept> deptList)
    {

        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (CameraDept dept : deptList)
        {
            Map<String, Object> areaMap = new HashMap<String, Object>();
            areaMap.put("id", dept.getDeptId());
            areaMap.put("pId", dept.getParentId());
            areaMap.put("name", dept.getDeptName());
            areaMap.put("title", dept.getDeptName());
            trees.add(areaMap);
        }
        return trees;
    }
    /**
     * 对象转部门树
     *
     * @param deptList 部门列表
     * @param roleDeptList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<CameraDept> deptList, List<String> roleDeptList)
    {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (CameraDept dept : deptList)
        {
            if("Y".equals(dept.getIsCamera())){
                if (!UserConstants.DEPT_NORMAL.equals(dept.getStatus()))
                {
                    Ztree ztree = new Ztree();
                    ztree.setId(dept.getDeptId());
                    ztree.setpId(dept.getParentId());
                    ztree.setName(dept.getDeptName());
                    ztree.setTitle(dept.getUrl());
                    if("Y".equals(dept.getIsCamera())){
                        ztree.setNocheck(true);
                    }else {
                        ztree.setNocheck(false);
                    }
                    if (isCheck)
                    {
                        ztree.setChecked(roleDeptList.contains(dept.getDeptId() + dept.getDeptName()));
                    }
                    ztrees.add(ztree);
                }
            }else {
                if (UserConstants.DEPT_NORMAL.equals(dept.getStatus()))
                {
                    Ztree ztree = new Ztree();
                    ztree.setId(dept.getDeptId());
                    ztree.setpId(dept.getParentId());
                    ztree.setName(dept.getDeptName());
                    ztree.setTitle(dept.getUrl());
                    if("Y".equals(dept.getIsCamera())){
                        ztree.setNocheck(true);
                    }else {
                        ztree.setNocheck(false);
                    }
                    if (isCheck)
                    {
                        ztree.setChecked(roleDeptList.contains(dept.getDeptId() + dept.getDeptName()));
                    }
                    ztrees.add(ztree);
                }
            }
        }
        return ztrees;
    }

    /**
     * 查询部门人数
     * 
     * @param parentId 部门ID
     * @return 结果
     */
    @Override
    public int selectDeptCount(Long parentId)
    {
        CameraDept dept = new CameraDept();
        dept.setParentId(parentId);
        return deptMapper.selectDeptCount(dept);
    }

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(Long deptId)
    {
        int result = deptMapper.checkDeptExistUser(deptId);
        return result > 0 ? true : false;
    }

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public int deleteDeptById(Long deptId)
    {
        return deptMapper.deleteDeptById(deptId);
    }

    /**
     * 新增保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int insertDept(CameraDept dept)
    {
        CameraDept info = deptMapper.selectDeptById(dept.getParentId());
        // 如果父节点不为"正常"状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus()))
        {
            throw new BusinessException("部门停用，不允许新增");
        }
        dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
        return deptMapper.insertDept(dept);
    }

    /**
     * 修改保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    //@Transactional
    //加事务的话，会导致多数据源切换失败
    public int updateDept(CameraDept dept)
    {
        CameraDept newParentDept = deptMapper.selectDeptById(dept.getParentId());
        CameraDept oldDept = selectDeptById(dept.getDeptId());
        if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept))
        {
            String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getDeptId();
            String oldAncestors = oldDept.getAncestors();
            dept.setAncestors(newAncestors);
            updateDeptChildren(dept.getDeptId(), newAncestors, oldAncestors);
        }
        int result = deptMapper.updateDept(dept);
        if (UserConstants.DEPT_NORMAL.equals(dept.getStatus()))
        {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatus(dept);
        }
        return result;
    }

    /**
     * 修改该部门的父级部门状态
     * 
     * @param dept 当前部门
     */
    private void updateParentDeptStatus(CameraDept dept)
    {
        String updateBy = dept.getUpdateBy();
        dept = deptMapper.selectDeptById(dept.getDeptId());
        dept.setUpdateBy(updateBy);
        deptMapper.updateDeptStatus(dept);
    }

    /**
     * 修改子元素关系
     * 
     * @param deptId 被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors)
    {
        List<CameraDept> children = deptMapper.selectChildrenDeptById(deptId);
        for (CameraDept child : children)
        {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            deptMapper.updateDeptChildren(children);
        }
    }

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public CameraDept selectDeptById(Long deptId)
    {
        return deptMapper.selectDeptById(deptId);
    }

    @Override
    public CameraDept selectDeptById2(Long deptId)
    {
        return deptMapper.selectDeptById2(deptId);
    }
    /**
     * 校验部门名称是否唯一
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public String checkDeptNameUnique(CameraDept dept)
    {
        Long deptId = StringUtils.isNull(dept.getDeptId()) ? -1L : dept.getDeptId();
        CameraDept info = deptMapper.checkDeptNameUnique(dept.getDeptName(), dept.getParentId());
        if (StringUtils.isNotNull(info) && info.getDeptId().longValue() != deptId.longValue())
        {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }
}
