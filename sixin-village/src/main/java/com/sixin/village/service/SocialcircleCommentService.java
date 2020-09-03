package com.sixin.village.service;

import com.sixin.village.domain.SocialcircleComment;

import java.util.List;

/**
 * @program: lyb_RongSys
 * @description: 暮云圈 评论 服务层
 * @author: Mr.Liu
 * @create: 2019-01-18 09:35
 **/

public interface SocialcircleCommentService {
    /**
     * 根据暮云圈pcid 搜索所有评论
     * @param pcid 暮云圈pcid号
     * @return  暮云圈数据
     */
    public List<SocialcircleComment> selectAllByPcid(Integer pcid);
}
