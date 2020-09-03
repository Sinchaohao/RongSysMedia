package com.sixin.village.mapper;

import com.sixin.village.domain.Energyshow;

import java.util.List;

/**
 * 能源管理终端采集信息表 数据层
 *
 * @author 薛莎莎
 * @date 2019-11-23
 */
public interface EnergyshowMapper {
    /**
     * 查询能源管理终端采集信息
     *
     * @param id
     * @return 能源管理终端采集信息
     */
    public Energyshow selectEnergyshowById(Integer id);
    /**
     * 查询能源管理终端采集信息列表
     *
     * @param energyshow 能源管理终端采集信息
     * @return 能源管理终端采集信息集合
     */
    public List<Energyshow> selectEnergyshowList(Energyshow energyshow);


    public List<Energyshow> selectEnergyshowListByAll(Energyshow energyshow);
}
