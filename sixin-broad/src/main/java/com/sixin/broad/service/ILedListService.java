package com.sixin.broad.service;

import com.sixin.broad.domain.LedList;

import java.util.List;

/**
 * @program: RongSys
 * @description:
 * @开发人员： 王豪杰
 * @开发单位： 湖南农业大学物联网工程专业
 * @create: 2020-04-01 21:19
 */
public interface ILedListService {

    /**
     * 查询led显示列表
     *
     * @param ledList led显示信息
     * @return led显示集合
     */
    public List<LedList> selectLedList(LedList ledList);

    /**
     * 新增led显示
     *
     * @param ledList
     * @return
     */
    public int insertLedList(LedList ledList);

    /**
     * 根据id查询led
     *
     * @param id
     * @return
     */
    public LedList selectLedListByid(Long id);

    /**
     * 根据id批量查询led
     *
     * @param ids
     * @return
     */
    public List<LedList> selectLedListByids(List<String> ids);

    /**
     * 保存Led修改
     * @param ledList
     * @return
     */
    public int updateLedList(LedList ledList);

    /**
     * 删除led信息
     * @param id
     * @return
     */
    public int deleteLedListByid(Long id);

    /**
     * 批量删除led信息
     * @param ids
     * @return
     */
    public int deleteLedListByids(String ids);
}
