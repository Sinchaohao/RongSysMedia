package com.sixin.iot.service;

import com.sixin.iot.domain.Environl;


import java.util.List;

public interface ISoilPannelService {
    /**
     * 查询土壤信息
     */
    public List<Environl> selectSoilList(Environl environl);
}
