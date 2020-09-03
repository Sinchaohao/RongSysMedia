package com.sixin.iot.mapper;

import com.sixin.iot.domain.Blowdown;
import com.sixin.iot.domain.Torrent;

import java.util.List;

public interface BlowdownMapper {
    /**
     * 气象监测记录列表
     *
     * @return
     */
    public List<Blowdown> selectBlowdownList(Blowdown blowdown);
    /**
     * 添加气象监测信息
     * @param blowdown
     * @return
     */
    public int insertBlowdown(Blowdown blowdown);
    /**
     * 根据weather_id选择数据

     * @return
     */
    public Blowdown selectByid(Long id);
    /**
     * 根据id选择数据

     * @return
     */
    public List<Blowdown> selectBlowdownByids(List<String> sfids);
    /**
     * 保存用户修改
     * @param blowdown
     * @return
     */
    public int updateBlowdown(Blowdown blowdown);
    /**
     * 删除气象监测信息

     * @return
     */
    public int deleteBlowdownByid(Long id);
    /**
     * 根据weather_id批量删除数据
     * @param weather_ids
     */
    public int deleteBlowdownByids(String[] ids);
    List<Torrent> queryList(Torrent torrent);
}