package com.sixin.broad.service.impl;

import com.sixin.broad.domain.HigherAnn;
import com.sixin.broad.mapper.HigherAnnMapper;
import com.sixin.broad.service.IHigherAnnService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/8/19.
 *
 * @author cx
 *
 * 上级公告列表 业务层实现
 */
@Service
public class HigherAnnServiceImpl implements IHigherAnnService {

    @Autowired
    private HigherAnnMapper higherAnnMapper;

    /**
     * 查询上级公告列表
     * @param higherAnn
     *
     * @return 上级公告列表集合
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<HigherAnn> selectHigherAnnList(HigherAnn higherAnn){return higherAnnMapper.selectHigherAnnList(higherAnn);}

    /**
     * 批量删除上级公告列表
     * @param mhid
     *
     * @return 被删除的上级公告列表的所在行序数
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int deleteHigherAnn(String mhid){return higherAnnMapper.deleteHigherAnn(Convert.toStrArray(mhid));}

    /**
     * 修改上级公告列表
     * @param higherAnn
     *
     * @return 修改后的上级公告信息行
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public HigherAnn updateHigherAnn(HigherAnn higherAnn){return higherAnnMapper.updateHigherAnn(higherAnn);}
}
