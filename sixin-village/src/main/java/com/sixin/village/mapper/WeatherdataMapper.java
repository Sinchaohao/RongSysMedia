package com.sixin.village.mapper;

import com.sixin.village.domain.Weatherdata;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采集天气信息 数据层
 *
 * @author hfz
 * @date 2019-10-06
 */
public interface WeatherdataMapper {

    /**
     * 查询采集天气信息
     *
     * @param userid 采集天气信息
     * @return 采集天气信息集合
     */
    public List<Weatherdata> selectWeatherdataList(@Param("userid") String userid);


}

