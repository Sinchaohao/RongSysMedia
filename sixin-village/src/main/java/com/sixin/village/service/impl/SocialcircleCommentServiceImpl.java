package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.village.domain.SocialcircleComment;
import com.sixin.village.mapper.SocialcircleCommentMapper;
import com.sixin.village.service.SocialcircleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: lyb_RongSys
 * @description: 暮云圈 评论 服务层实现
 * @author: Mr.Liu
 * @create: 2019-01-18 09:35
 **/
@Service
public class SocialcircleCommentServiceImpl implements SocialcircleCommentService {
    @Autowired
    private SocialcircleCommentMapper mapper;

    /**
     * 根据暮云圈pcid 搜索所有评论
     * @param pcid 暮云圈pcid号
     * @return  暮云圈数据
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<SocialcircleComment> selectAllByPcid(Integer pcid) {
        return mapper.selectAllByPcid(pcid);
    }
}
