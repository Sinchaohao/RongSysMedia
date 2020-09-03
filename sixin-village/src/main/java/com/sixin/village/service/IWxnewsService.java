package com.sixin.village.service;

import com.sixin.village.domain.Wxnews;

import java.util.List;

/**
 * 微信公众号新闻
 *
 * @author hfz
 * @date 2020-1-8
 */
public interface IWxnewsService {
    /**
     * 获取微信公众号新闻列表
     *
     * @param wxnews 获取列表集合
     * @return  微信公众号相关信息
     */
    public List<Wxnews> selectWxnewslist(Wxnews wxnews);
    /**
     * 微信公众号新闻发布
     *
     * @param wxnews 获取列表集合
     * @return  微信公众号相关信息
     */
    public List<Wxnews> selectWxnewsByAll(Wxnews wxnews);
}
