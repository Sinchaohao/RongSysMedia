package com.sixin.village.mapper;

import com.sixin.village.domain.Education;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * @Description: 政策Mapper$
 * @Param: dyq_RongSys$
 * @return: $
 * @Author: Mr.DYQ
 * @Date: $
 */
public interface EducationMapper {
       /**
     * 政策记录列表
     *
     * @return
     */
    List<Education> selectEducationList(Education education);

    /**
     * 根据shiid删除数据
     * @param id
     */
    int deleteEducationByids(String[] id);

    /**
     * 添加公告信息
     * @param education
     * @return
     */
    int inserteducation(Education education);

    /**
     * 根据shiid选择数据
     * @param eid
     * @return
     */
    Education selectByeid(Integer eid);

    /**
     * 保存用户修改
     * @param education
     * @return
     */
    int updateEducation(Education education);

    /**
     * 删除政策信息
     * @param eid
     * @return
     */
    int deleteEducationByIds(String eid);


    List<Education> selectEducationListById(pubObjApi party);
}
