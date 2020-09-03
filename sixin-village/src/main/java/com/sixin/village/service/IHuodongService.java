package com.sixin.village.service;

import com.sixin.village.domain.Huodong;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * @Description: 党员活动Service$
 * @Param: dyq_RongSys$
 * @return: $
 * @Author: Mr.DYQ
 * @Date: $
 */

public interface IHuodongService {
    /**
     * 查询党员活动记录列表
     *
     * @return
     */
    List<Huodong> selectHuodongList(Huodong huodong);

    /**
     * 根据hdid删除数据
     * @param id
     */
    int deleteHuodongByids(String id);

    /**
     * 添加公告信息数据
     * @param  huodong
     * @return
     */
    int inserthuodong(Huodong huodong);
    /**
     * 根据hdid选择数据
     * @param hdid
     * @return
     */
    Huodong selectByhdid(Integer hdid);
    /**
     * 保存用户修改
     * @param huodong
     * @return
     */
    int updateHuodong(Huodong huodong);
    /**
     * 删除公告信息
     * @param hdid
     * @return
     */
    int deleteHuodongByIds(String hdid);

    List<Huodong> selectHuodongListById(pubObjApi beavill);
}
