package com.sixin.iot.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.LED;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.mapper.LEDMapper;
import com.sixin.iot.service.ILEDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LEDServiceImpl implements ILEDService {
    @Autowired
    private LEDMapper ledMapper;


    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<LED> selectLEDList(LED led) {
        return ledMapper.selectLEDList(led);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int insertLED(LED led) {
        return ledMapper.insertLED(led);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public LED selectByid(Long id) {
        return ledMapper.selectByid(id);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<LED> selectLEDByids(List<String> sfids) {
        return ledMapper.selectLEDByids(sfids);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int updateLED(LED led) {
        return ledMapper.updateLED(led);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteLEDByid(Long id) {
        return ledMapper.deleteLEDByid(id);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteLEDByids(String ids) {
        return ledMapper.deleteLEDByids(Convert.toStrArray(ids));
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int changeStatus(LED led) {
        return ledMapper.updateLED(led);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Torrent> queryList(Torrent torrent) {return ledMapper.queryList(torrent);}

}