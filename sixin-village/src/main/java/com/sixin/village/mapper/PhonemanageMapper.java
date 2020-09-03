package com.sixin.village.mapper;

import com.sixin.village.domain.Phonemanage;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * 授权号码表管理 数据层
 *
 * @author 薛莎莎
 * @date 2019-12-6
 */
public interface PhonemanageMapper {
    /**
     * 查询授权号码表管理信息
     *
     * @param pid
     * @return 授权号码表管理信息
     */
    public Phonemanage selectPhonemanageByPId(Integer pid);
    /**
     * 查询授权号码表管理列表
     *
     * @param phonemanage 授权号码表管理信息
     * @return 授权号码表管理集合
     */
    public List<Phonemanage> selectPhonemanageList(Phonemanage phonemanage);


    public List<Phonemanage> selectPhonemanageListByPId(pubObjApi phonemanage);
}
