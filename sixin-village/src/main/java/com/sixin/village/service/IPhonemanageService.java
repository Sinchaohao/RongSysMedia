package com.sixin.village.service;

import com.sixin.village.domain.Phonemanage;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * 授权号码表管理 服务层
 *
 * @author 薛莎莎
 * @date 2019-12-6
 */
public interface IPhonemanageService {
    /**
     * 查询授权号码表管理
     *
     * @param pid
     * @return 授权号码表管理
     */
    public Phonemanage selectPhonemanageByPId(Integer pid);
    /**
     * 查询授权号码表管理列表
     *
     * @param phonemanage 授权号码表管理
     * @return 授权号码表管理集合
     */
    public List<Phonemanage> selectPhonemanageList(Phonemanage phonemanage);


    public List<Phonemanage> selectPhonemanageListByPId(pubObjApi phonemanage);
}
