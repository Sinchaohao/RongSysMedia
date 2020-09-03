package com.sixin.village.service;

import com.sixin.village.domain.Education;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * @Description: 党员在线学习Service$
 * @Param: dyq_RongSys$
 * @return: $
 * @Author: Mr.DYQ
 * @Date: $
 */

public interface IEducationService {
    /**
     * 查询政策记录列表
     *
     * @return
     */
    List<Education> selectEducationList(Education education);

    /**
     * 根据shiid删除数据
     * @param id
     */
    int deleteEducationByids(String id);

    /**
     * 添加政策公告数据
     * @param  education
     * @return
     */
    int inserteducation(Education education);
    /**
     * 根据eid选择数据
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
