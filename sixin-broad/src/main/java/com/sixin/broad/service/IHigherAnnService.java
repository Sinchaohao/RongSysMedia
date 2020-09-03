package com.sixin.broad.service;

import com.sixin.broad.domain.HigherAnn;

import java.util.List;

/**
 * Created by ASUS on 2019/8/19.
 *
 * @author cx
 *
 * 上级公告列表 业务层
 */
public interface IHigherAnnService {
    /**
     * 查询上级公告列表
     * @param higherAnn
     *
     * @return 上级公告列表集合
     */
    public List<HigherAnn> selectHigherAnnList(HigherAnn higherAnn);

    /**
     * 批量删除上级公告列表
     * @param mhid
     *
     * @return 被删除的上级公告列表的所在行序数
     */
    public int deleteHigherAnn(String mhid);

    /**
     * 修改上级公告列表
     * @param higherAnn
     *
     * @return 修改后的上级公告信息行
     */
    public HigherAnn updateHigherAnn(HigherAnn higherAnn);
}
