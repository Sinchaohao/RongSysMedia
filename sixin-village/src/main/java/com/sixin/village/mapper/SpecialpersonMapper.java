package com.sixin.village.mapper;

import com.sixin.village.domain.Specialperson;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * 特殊人员监控表sys_specialperson管理 数据层
 *
 * @author 薛莎莎
 * @date 2019-12-15
 */
public interface SpecialpersonMapper {
    /**
     * 查询特殊人员监控表信息
     *
     * @param id 特殊人员监控表ID
     * @return 特殊人员监控表信息
     */
    public Specialperson selectSpecialpersonById(Integer id);
    /**
     * 查询特殊人员监控表信息列表
     *
     * @param specialperson 特殊人员监控表
     * @return 特殊人员监控表信息集合
     */
    public List<Specialperson> selectSpecialpersonList(Specialperson specialperson);

    public List<Specialperson> selectSpecialpersonListById(pubObjApi specialperson);
}
