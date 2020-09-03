package com.sixin.iot.mapper;


import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Soil;
import com.sixin.iot.domain.Torrent;

import java.util.List;

public interface SoilMapper {
    /**
     * 路灯记录列表
     *
     * @return
     */
    List<Soil> selectSoilList(Soil soil);

    /**
     * 根据sid批量删除数据
     * @param sid
     */
    int deleteSoilByids(String[] sid);
    /**
     * 添加路灯信息
     * @param soil
     * @return
     */
    int insertSoil(Soil soil);
    /**
     * 根据sid选择数据
     * @param sid
     * @return
     */
    Soil selectByid(Long id);
    /**
     * 保存用户修改
     * @param soil
     * @return
     */
    int updateSoil(Soil soil);
    public int changeDelflag(Soil soil);
    /**
     * 删除路灯信息
     * @param sid
     * @return
     */
    int deleteSoilByid(String sid);
    public  List<Soil> selectSoilByids(List<String> sfids);
    List<Torrent> queryList(Torrent torrent);
    List<Iotype> selectList(Iotype iotype);

}