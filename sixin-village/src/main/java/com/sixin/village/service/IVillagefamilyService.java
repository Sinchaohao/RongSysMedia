package com.sixin.village.service;

import com.sixin.village.domain.Villagefamily;

import java.util.List;

/**
 * @program: lyb_RongSys
 * @description: 小村家事 服务层
 * @author: Mr.Liu
 * @create: 2019-01-15 15:00
 **/

public interface IVillagefamilyService {

    /**
     * 获取小村家事数据 List定义
     * @return
     */
    public List<Villagefamily> selectvillagefamilylist(Villagefamily villagefamily);


    /**
     * 新增小村家事 数据 常量定义
     * @param villagefamily
     * @return
     */
    public int insertvillagefamily(Villagefamily villagefamily);
    /**
     * 修改小村家事数据 Object定义
     * @param jsid
     * @return
     */
    public Villagefamily selectByfbid(Integer jsid);
    /**
     * 保存小村家事修改
     * @param villagefamily
     * @return
     */
    public int updatevillagefamily(Villagefamily villagefamily);
    /**
     * 根据 jsid 删除
     * @param jsid
     * @return
     */
    public int deletevillagefamilybyids(String jsid);

}
