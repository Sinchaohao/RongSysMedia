package com.sixin.iot.service;
import com.sixin.iot.domain.Flood;
import com.sixin.iot.domain.Floodbytl;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Torrent;

import java.util.List;

/**
 * @program: FloodSys
 * @description: 山洪系统
 * @author: 饶泽敏
 * @create: 2019-12-25 17:00
 **/
public interface IFloodService {
    /**
     * 查询山洪记录列表
     *
     * @return
     */
    public List<Flood> selectFloodList(Flood flood);

    /**
     * 根据id批量删除数据
     * @param id
     */
    public int deleteFloodByids(String id);

    /**
     * 添加山洪公告数据
     * @param  Flood
     * @return
     */
    public int insertFlood(Flood flood);
    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    public Flood selectByid(String id);
    /**
     * 保存用户修改
     * @param Flood
     * @return
     */
    public int updateFlood(Flood flood);
    public List<Torrent> queryList(Torrent torrent);
    /**
     * 删除山洪信息
     * @param id
     * @return
     */
    public int deleteFloodByid(String id);

    public List<Flood>  selectFloodByids(List<String> sfids);

    public List<Ioterminal> queryList(Ioterminal ioterminal);
    public List<Ioterminal> selectimei(Ioterminal ioterminal);
    /**
     * 修改终端状态
     *
     * @param flood
     * @return 结果
     */
    public int changeStatus(Flood flood);
}
