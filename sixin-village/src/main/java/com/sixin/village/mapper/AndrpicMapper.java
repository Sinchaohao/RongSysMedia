package com.sixin.village.mapper;

import com.sixin.village.domain.Andrpic;
import com.sixin.village.domain.pubObjApi;

import java.util.List;
/**
 * 三级页面图片
 *
 * @author hfz
 * @date 2019-11-03
 */
public interface AndrpicMapper {
    /**
     * 查询三级页面图片信息
     *
     * @param aid 三级页面图片ID
     * @return 三级页面图片信息
     */
    public Andrpic selectAndrpicById(Integer aid);

    /**
     * 查询三级页面图片列表
     *
     * @param andrpic 三级页面图片信息
     * @return 三级页面图片集合
     */
    public List<Andrpic> selectAndrpicList(Andrpic andrpic);


    public List<Andrpic> selectAndrpicListById(pubObjApi andrpic);
}
