package com.sixin.village.mapper;

import com.sixin.village.domain.Villagefamily;
import java.util.List;

/**
 * @program: lyb_RongSys
 * @description: 小村家事数据层
 * @author: Mr.Liu
 * @create: 2019-01-15 14:59
 **/

public interface VillagefamilyMapper {

    /**
     * 获取小村家事数据
     * @return
     */
    List<Villagefamily> selectVillageFamilyList(Villagefamily villagefamily);

    /**
     * 新增小村家事 数据
     * @param villagefamily
     * @return
     */
    int insertVillageFamily(Villagefamily villagefamily);

    /**
     * 修改小村家事数据
     * @param jsid
     * @return
     */
    Villagefamily selectByfbid(Integer jsid);

    /**
     * 保存小村家事修改
     * @param villagefamily
     * @return
     */
    int updateVillageFamily(Villagefamily villagefamily);

    /**
     * 根据 id 批量删除
     * @param id
     * @return
     */
    int deleteVillageFamilyByids(String[] id);
}
