package com.sixin.iot.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.support.Convert;
import com.sixin.common.utils.StringUtils;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Meteor;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.mapper.MeteorMapper;
import com.sixin.iot.service.IMeteorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: SoilSys
 * @description: 气象系统
 * @author: 蔡文静
 * @create: 2019-12-25 13:40
 **/
@Service
public class MeteorServiceImpl implements IMeteorService {
    @Autowired
    private MeteorMapper meteorMapper;


    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Meteor> selectMeteorList(Meteor meteor) {
        return meteorMapper.selectMeteorList(meteor);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int insertMeteor(Meteor meteor) {
        return meteorMapper.insertMeteor(meteor);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public Meteor selectByid(Long id) {
        return meteorMapper.selectByid(id);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Meteor> selectMeteorByids(List<String> sfids) {
        return meteorMapper.selectMeteorByids(sfids);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int updateMeteor(Meteor meteor) {
        return meteorMapper.updateMeteor(meteor);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteMeteorByid(Long id) {
        return meteorMapper.deleteMeteorByid(id);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteMeteorByids(String ids) {
        return meteorMapper.deleteMeteorByids(Convert.toStrArray(ids));
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int changeStatus(Meteor meteor) {
        return meteorMapper.updateMeteor(meteor);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Torrent> queryList(Torrent torrent) {return meteorMapper.queryList(torrent);}
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Iotype> selectList(Iotype iotype) {return meteorMapper.selectList(iotype);}
}
