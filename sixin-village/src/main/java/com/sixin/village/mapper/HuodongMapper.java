package com.sixin.village.mapper;

import com.sixin.village.domain.Huodong;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * @Description: 党员活动Mapper$
 * @Param: dyq_RongSys$
 * @return: $
 * @Author: Mr.DYQ
 * @Date: $
 */
public interface HuodongMapper {
       /**
     * 政策记录列表
     *
     * @return
     */
    List<Huodong> selectHuodongList(Huodong huodong);

    /**
     * 根据hdid删除数据
     * @param id
     */
    int deleteHuodongByids(String[] id);

    /**
     * 添加党员活动信息
     * @param huodong
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
     * 删除活动信息
     * @param hdid
     * @return
     */
    int deleteHuodongByIds(String hdid);

    List<Huodong> selectHuodongListById(pubObjApi beavill);
}
