package com.sixin.iot.mapper;

import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Meteor;
import com.sixin.iot.domain.Torrent;

import java.util.List;

public interface MeteorMapper {
    /**
     * 气象监测记录列表
     *
     * @return
     */
    public List<Meteor> selectMeteorList(Meteor meteor);
    /**
     * 添加气象监测信息
     * @param meteor
     * @return
     */
    public int insertMeteor(Meteor meteor);
    /**
     * 根据weather_id选择数据
     * @param weather_id
     * @return
     */
    public Meteor selectByid(Long id);
    /**
     * 根据id选择数据
     * @param weather_id
     * @return
     */
    public List<Meteor> selectMeteorByids(List<String> sfids);
    /**
     * 保存用户修改
     * @param meteor
     * @return
     */
    public int updateMeteor(Meteor meteor);
    /**
     * 删除气象监测信息
     * @param weather_id
     * @return
     */
    public int deleteMeteorByid(Long id);
    /**
     * 根据weather_id批量删除数据
     * @param weather_ids
     */
    public int deleteMeteorByids(String[] ids);
    List<Torrent> queryList(Torrent torrent);
    List<Iotype> selectList(Iotype iotype);
}