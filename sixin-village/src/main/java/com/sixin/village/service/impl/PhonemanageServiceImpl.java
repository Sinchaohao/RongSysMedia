package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.village.domain.Phonemanage;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.mapper.PhonemanageMapper;
import com.sixin.village.service.IPhonemanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 授权号码表管理 服务层实现
 *
 * @author 薛莎莎
 * @date 2019-12-6
 */
@Service
public class PhonemanageServiceImpl implements IPhonemanageService {
    @Autowired
    private PhonemanageMapper phonemanageMapper;

    /**
     * 查询授权号码表管理信息
     *
     * @param pid 授权号码表管理信息ID
     * @return 授权号码表管理信息
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public Phonemanage selectPhonemanageByPId(Integer pid) {
        return phonemanageMapper.selectPhonemanageByPId(pid);
    }
    /**
     * 查询授权号码表管理信息列表
     *
     * @param phonemanage 授权号码表管理信息
     * @return 授权号码表管理信息集合
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Phonemanage> selectPhonemanageList(Phonemanage phonemanage) {
        return phonemanageMapper.selectPhonemanageList(phonemanage);
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Phonemanage> selectPhonemanageListByPId(pubObjApi phonemanage) {
        return phonemanageMapper.selectPhonemanageListByPId(phonemanage);
    }
}
