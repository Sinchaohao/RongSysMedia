package com.sixin.iot.service;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Meteor;
import com.sixin.iot.domain.Torrent;

import java.util.List;

/**
 * @program: MeteorSys
 * @description: 气象系统
 * @author: 蔡文静
 * @create: 2019-12-25 15:00
 **/

public interface IMeteorService {
    /**
     * 查询气象记录列表
     *
     * @return
     */
    public List<Meteor> selectMeteorList(Meteor meteor);

    /**
     * 添加气象公告数据
     *
     */
    public int insertMeteor(Meteor meteor);
    /**
     * 根据weather_id选择数据
     *
     */
    public Meteor selectByid(Long id);
    public List<Meteor> selectMeteorByids(List<String> sfids);
    /**
     * 保存用户修改
     *
     */
    public int updateMeteor(Meteor meteor);
    /**
     * 删除气象信息
     *
     */
    public int deleteMeteorByid(Long id);
    public List<Torrent> queryList(Torrent torrent);
    public List<Iotype> selectList(Iotype iotype);
    /**
     * 根据weather_id批量删除数据
     *
     */
    public int deleteMeteorByids(String ids);

    public int changeStatus(Meteor meteor);
}
