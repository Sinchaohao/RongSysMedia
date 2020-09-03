package com.sixin.iot.mapper;
import com.sixin.iot.domain.Fire;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Torrent;

import java.util.List;

public interface FireMapper {
    /**
     * 火灾记录列表
     *
     * @return
     */
    List<Fire> selectFireList(Fire fire);
    /**
     * 根据id批量选择数据
     * @param sfids
     * @return
     */
    public List<Fire> selectFireByids(List<String> sfids);
    /**
     * 根据fid批量删除数据
     * @param fid
     */
    int deleteFireByids(String[] fid);
    /**
     * 添加火灾探头终端信息
     * @param fire
     * @return
     */
    int insertFire(Fire fire);
    List<Torrent> queryList(Torrent torrent);
    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    Fire selectByid(String id);

    /**
     * 保存用户修改
     * @param fire
     * @return
     */
    int updateFire(Fire fire);
    /**
     * 删除火灾探头终端信息
     * @param fid
     * @return
     */
    int deleteFireByid(String fid);
    List<Ioterminal> queryList(Ioterminal ioterminal);
    public List<Ioterminal> selectimei(Ioterminal ioterminal);
}