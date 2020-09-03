package com.sixin.iot.mapper;

import com.sixin.iot.domain.Environl;

import java.util.List;

public interface SoilPannelMapper {
    public List<Environl> selectSoilList(Environl environl);
}
