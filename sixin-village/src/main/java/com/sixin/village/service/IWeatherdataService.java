package com.sixin.village.service;

import com.sixin.village.domain.Weatherdata;
import java.util.List;

/**
 * 采集天气信息 服务层
 *
 * @author hfz
 * @date 2019-10-06
 */
public interface IWeatherdataService {

    /**
     * 天气采集信息公开列表
     *
     * @param userid 采集天气信息
     * @return 采集天气信息集合
     */
    public List<Weatherdata> selectWeatherdataList(String userid);

}

