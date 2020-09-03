package com.sixin.broad.mapper;

import com.sixin.broad.domain.LedList;

import java.util.List;

/**
 * @program: RongSys
 * @description: LedList 数据层
 * @开发人员： 王豪杰
 * @开发单位： 湖南农业大学物联网工程专业
 * @create: 2020-04-01 21:22
 */
public interface LedListMapper {

    /**
     * 查询led显示列表
     *
     * @param ledList led显示信息
     * @return led显示集合
     */
    public List<LedList> selectLedList(LedList ledList);

    /**
     * 删除led信息
     * @param id
     * @return
     */
    public int deleteLedListByid(Long id);

    /**
     * 根据id批量删除数据
     * @param ids
     */
    public int deleteLedListByids(String[] ids);
    /**
     * 添加led显示信息
     * @param ledList
     * @return
     */
    int insertLedList(LedList ledList);
    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    LedList selectLedListByid(Long id);

    /**
     * 根据id批量选择数据
     * @param ids
     * @return
     */
    public List<LedList> selectLedListByids(List<String> ids);



    /**
     * 保存用户修改
     * @param ledList
     * @return
     */
    int updateLedList(LedList ledList);
}
