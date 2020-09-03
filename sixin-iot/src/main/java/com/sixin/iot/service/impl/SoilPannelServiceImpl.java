package com.sixin.iot.service.impl;

import com.sixin.iot.domain.Environl;
import com.sixin.iot.mapper.SoilPannelMapper;
import com.sixin.iot.service.ISoilPannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoilPannelServiceImpl implements ISoilPannelService {
    @Autowired
    private SoilPannelMapper soilPannelMapper;

    @Override
    public List<Environl> selectSoilList(Environl environl){
        return soilPannelMapper.selectSoilList(environl);
    }
}
