package com.sixin.iot.service;

import com.sixin.iot.domain.Iotype;

import java.util.List;

/**
 * @Author TanXY
 * @Create 2020/4/17 - 13:24
 * @Description
 */
public interface IIotypeService {
    /**
     * @Author TanXY
     * @Description
     * @Date 2020/4/17 13:25
     * @Param
     * @return java.util.List<com.sixin.iot.domain.Iotype>
     */
    public List<Iotype> selectIotypeList();
}
