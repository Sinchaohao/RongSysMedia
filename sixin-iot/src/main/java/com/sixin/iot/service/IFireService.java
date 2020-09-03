package com.sixin.iot.service;
import com.sixin.iot.domain.Fire;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Torrent;

import java.util.List;

/**
 * @program: FireSys
 * @description: 火灾系统
 * @author: 高彭
 * @create: 2019-12-25 17:20
 **/
public interface IFireService {
    /**
     * 查询火灾记录列表
     *
     * @return
     */
    public List<Fire> selectFireList(Fire fire);

    /**
     * 根据fid批量删除数据
     * @param fid
     */
    public int deleteFireByids(String fid);

    /**
     * 添加火灾公告数据
     * @param  fire
     * @return
     */
    public int insertFire(Fire fire);
    public List<Torrent> queryList(Torrent torrent);
    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    public Fire selectByid(String id);
    /**
     * 保存用户修改
     * @param fire
     * @return
     */
    public int updateFire(Fire fire);
    /**
     * 删除火灾信息
     * @param fid
     * @return
     */
    public int deleteFireByIds(String fid);
    public List<Fire> selectFireListByids(List<String> sfids);
    public List<Ioterminal> queryList(Ioterminal ioterminal);
    public List<Ioterminal> selectimei(Ioterminal ioterminal);
    /**
     * 修改终端状态
     * @param id
     * @return
     */
    public int changeStatus(Fire fire);

}
