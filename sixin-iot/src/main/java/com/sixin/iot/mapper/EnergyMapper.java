package com.sixin.iot.mapper;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.iot.domain.Energy;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Torrent;

import java.util.List;

public interface EnergyMapper {
    /**
     * 能源管理记录列表
     *
     * @return
     */
    List<Energy> selectEnergyList(Energy energy);

    List<Torrent> queryList(Torrent torrent);

    List<Ioterminal> selectIoterminalList(Ioterminal ioterminal);
    /**
     * 根据id批量删除数据
     * @param id
     */
    int deleteEnergyByids(String[] id);
    /**
     * 清空数据
     */
    @DataSource(DataSourceType.SXINFOM)
    int deleteAllEnergy();
    /**
     * 添加能源管理记录信息
     * @param energy
     * @return
     */
    int insertEnergy(Energy energy);
    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    Energy selectByid(String id);


    public List<Energy> selectEnergyListByids(List<String> sfids);


    //Energy selectBybvo(String bvoltage);

    /**
     * 保存用户修改
     * @param energy
     * @return
     */
    int updateEnergy(Energy energy);
    /**
     * 删除能源管理记录信息
     * @param id
     * @return
     */
    int deleteEnergyByid(String id);


    public List<Energy> selectEnergyByids(List<String> sfids);

    int updateEnergy1(Energy energy);
}