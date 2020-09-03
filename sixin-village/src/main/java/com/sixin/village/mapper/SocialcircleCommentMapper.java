package com.sixin.village.mapper;

import com.sixin.village.domain.SocialcircleComment;

import java.util.List;

/**
 * @program: lyb_RongSys
 * @description: 暮云圈 评论 数据
 * @author: Mr.Liu
 * @create: 2019-01-18 09:32
 **/

public interface SocialcircleCommentMapper {

    /**
     * 根据暮云圈pcid 搜索所有评论
     * @param pcid
     * @return
     */
    List<SocialcircleComment> selectAllByPcid(Integer pcid);
}
