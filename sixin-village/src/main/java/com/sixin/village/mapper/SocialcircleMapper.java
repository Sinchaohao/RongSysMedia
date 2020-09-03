package com.sixin.village.mapper;

import com.sixin.village.domain.Socialcircle;
import org.apache.ibatis.annotations.Param;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @program: lyb_RongSys
 * @description: 暮云圈 留言 数据
 * @author: Mr.Liu
 * @create: 2019-01-17 15:49
 **/
public interface SocialcircleMapper {

    /**
     * 获取暮云圈 留言
     * @param index 结束行
     * @return
     */
    public List<Socialcircle> selectSocialCircleList(@Param("in") int in , @Param("index") int index);
    /**
     * 获取暮云圈 留言
     * @param  index 结束行
     * @return 热度榜
     */
    public List<Socialcircle> selectSocialCircleList_Heat(@Param("in") int in , @Param("index") int index);
}
