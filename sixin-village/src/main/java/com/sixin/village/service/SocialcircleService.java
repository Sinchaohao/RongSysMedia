package com.sixin.village.service;

import com.sixin.village.domain.Socialcircle;

import java.util.List;

/**
 * @program: lyb_RongSys
 * @description: 暮云圈 留言 服务层
 * @author: Mr.Liu
 * @create: 2019-01-17 15:51
 **/

public interface SocialcircleService {
    /**
     * 获取暮云圈 留言
     * 最新数据
     * @param in 开始行 index 结束行
     * @return 暮云圈留言数据
     */
    public List<Socialcircle> selectSocialCircleList(int in,int index);

    /**
     * 获取暮云圈 留言
     * 热度数据
     * @param in 起始行 index 结束行
     * @return 暮云圈留言热度数据
     */
    public List<Socialcircle> selectSocialCircleList_Heat(int in,int index);
}
