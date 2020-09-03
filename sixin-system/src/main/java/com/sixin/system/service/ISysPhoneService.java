package com.sixin.system.service;

import com.sixin.system.domain.SysPhone;

import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description: 授权号码 服务层
 * @Date:
 */
public interface ISysPhoneService {
    /**
     * 查询授权号码集合
     *
     * @param phone 授权号码对象
     * @return 授权号码集合
     */
    public List<SysPhone> selectPhonelist(SysPhone phone);
    /**
     * 新增保存授权号码
     *
     * @param phone 授权号码对象
     * @return 结果
     */
    public int insertPhone(SysPhone phone);
    /**
     * 删除授权号码
     *
     * @param ids 删除授权号码编号
     * @return 结果
     */
    public int deletePhone(String ids);
    /**
     * 按编号查询
     *
     * @param phone 授权号码编号
     * @return 结果
     */
    public SysPhone selectPhoneById(Long pid);
    /**
     * 修改保存授权编号
     *
     * @param phone 授权号码对象
     * @return 结果
     */
    public int updatePhone(SysPhone phone);
}
