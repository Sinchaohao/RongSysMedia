package com.sixin.iot.mapper;
import com.sixin.iot.domain.Flood;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Torrent;

import java.util.List;
import java.util.Map;

public interface FloodMapper {
    /**
     * 山洪灾害记录列表
     *
     * @return
     */
    List<Flood> selectFloodList(Flood flood);

    /**
     * 根据id批量删除数据
     * @param id
     */
    int deleteFloodByids(String[] id);
    /**
     * 添加山洪灾害信息
     * @param flood
     * @return
     */
    int insertFlood(Flood flood);
    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    Flood selectByid(String id);

    /**
     * 保存用户修改
     * @param flood
     * @return
     */
    int updateFlood(Flood flood);
    /**
     * 删除山洪灾害信息
     * @param id
     * @return
     */
    int deleteFloodByid(String id);

    List<Torrent> queryList(Torrent torrent);
    /**
     * 根据id批量选择数据
     * @param sfids
     * @return
     */
    public List<Flood> selectFloodByids(List<String> sfids);

    List<Ioterminal> queryList(Ioterminal ioterminal);
    public List<Ioterminal> selectimei(Ioterminal ioterminal);
}