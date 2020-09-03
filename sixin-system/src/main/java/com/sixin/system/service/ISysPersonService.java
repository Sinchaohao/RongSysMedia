package com.sixin.system.service;

import com.sixin.system.domain.SysPerson;

import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
public interface ISysPersonService {
    /**
     * 查询授权号码集合
     *
     * @param phone 授权号码对象
     * @return 授权号码集合
     */
    public List<SysPerson> selectPersonlist(SysPerson person);
    /**
     * 新增保存授权号码
     *
     * @param phone 授权号码对象
     * @return 结果
     */
    public int insertPerson(SysPerson person);
    /**
     * 删除特殊人员
     *
     * @param ids 特殊人员编号
     * @return 结果
     */
    public int deletePersonById(String ids);
    /**
     * 按编号查询
     *
     * @param id 特殊人员编号
     * @return 结果
     */
    public SysPerson selectPersonById(Long id);
    /**
     * 修改特殊人员
     *
     * @param person 特殊人员对象
     * @return 结果
     */
    public int updatePerson(SysPerson person);
}
