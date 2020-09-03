package com.sixin.iot.service;

import com.sixin.iot.domain.Environl;

import java.util.List;

/**
 * @开发人员：周宇滔
 * @开发单位：湖南农业大学物联网工程专业
 */
public interface IMetService {
    /**
     * 查询下雨信息
     */
    public List<Environl> selectMet(Environl environl);
    /**
     * 查询气象信息
     *
     * @param id 气象信息ID
     * @return 气象信息
     */
    public Environl selectMetDataById(Long id);
    /**
     * 新增气象信息
     *
     * @param environl 气象信息
     * @return 结果
     */
    public int insertMetData(Environl environl);

    /**
     * 修改气象信息
     *
     * @param environl 气象信息
     * @return 结果
     */
    public int updateMetData(Environl environl);

    /**
     * 删除气象信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMetDataByIds(String ids);
}
