package com.sixin.iot.mapper;

import com.sixin.iot.domain.Iotype;

import java.util.List;

/**
 * @Author TanXY
 * @Create 2020/4/17 - 13:22
 * @Description
 */
public interface IotypeMapper {
    /**
     * @Author TanXY
     * @Description
     * @Date 2020/4/17 13:23
     * @Param
     * @return java.util.List<com.sixin.iot.domain.Iotype>
     */
    public List<Iotype> selectIotypeList();
}
