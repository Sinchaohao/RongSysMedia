package com.sixin.broad.service.impl;

import com.sixin.broad.domain.Manage;
import com.sixin.broad.mapper.ManageMapper;
import com.sixin.broad.service.IManageService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cx
 * @Time 2019/8/20 15:59
 * @Description 管理参数 业务层实现
 */
@Service
public class ManageServiceImpl implements IManageService {

    @Autowired
    private ManageMapper manageMapper;
    /**
     * @author cx
     * @param manage
     *
     * @Description 查询管理参数列表
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Manage> selectManagelist(Manage manage){return manageMapper.selectManagelist(manage);}

    /**
     * @author cx
     * @param manage
     *
     * @Description 更新参数列表
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int updateManage(Manage manage){return manageMapper.updateManage(manage);}

    /**
     * @author cx
     * @param manage
     *
     * @Description 插入参数列表
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int insertManage(Manage manage){return manageMapper.insertManage(manage);}
}
