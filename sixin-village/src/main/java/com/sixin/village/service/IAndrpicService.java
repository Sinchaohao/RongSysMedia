package com.sixin.village.service;

import com.sixin.village.domain.Andrpic;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * 三级图片 服务层
 *
 * @author hfz
 * @date 2019-11-3
 */
public interface IAndrpicService {
    /**
     * 查询三级图片信息
     *
     * @param aid 三级图片ID
     * @return 三级图片信息
     */
    public Andrpic selectAndrpicById(Integer aid);

    /**
     * 查询三级图片列表
     *
     * @param andrpic 三级图片信息
     * @return 三级图片集合
     */
    public List<Andrpic> selectAndrpicList(Andrpic andrpic);

    public List<Andrpic> selectAndrpicListById(pubObjApi andrpic);
}
