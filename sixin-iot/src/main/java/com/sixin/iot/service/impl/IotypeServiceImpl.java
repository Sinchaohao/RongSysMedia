package com.sixin.iot.service.impl;

import com.sixin.iot.domain.Iotype;
import com.sixin.iot.mapper.IotypeMapper;
import com.sixin.iot.service.IIotypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author TanXY
 * @Create 2020/4/17 - 13:26
 * @Description
 */
@Service
public class IotypeServiceImpl implements IIotypeService {

    @Autowired
    private IotypeMapper iotTypeMapper;

    /**
     * @Author TanXY
     * @Description
     * @Date 2020/4/17 13:27
     * @Param
     * @return java.util.List<com.sixin.iot.domain.Iotype>
     */
    @Override
    public List<Iotype> selectIotypeList(){
        return iotTypeMapper.selectIotypeList();
    }
}
