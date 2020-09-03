package com.sixin.iot.service;
import com.sixin.iot.domain.Blowdown;
import com.sixin.iot.domain.Torrent;

import java.util.List;


public interface IBlowdownService {
    /**
     * 查询气象记录列表
     *
     * @return
     */
    public List<Blowdown> selectBlowdownList(Blowdown blowdown);

    /**
     * 添加气象公告数据
     *
     */
    public int insertBlowdown(Blowdown blowdown);
    /**
     * 根据weather_id选择数据
     *
     */
    public Blowdown selectByid(Long id);
    public List<Blowdown> selectBlowdownByids(List<String> sfids);
    public List<Torrent> queryList(Torrent torrent);
    /**
     * 保存用户修改
     *
     */
    public int updateBlowdown(Blowdown blowdown);
    /**
     * 删除气象信息
     *
     */
    public int deleteBlowdownByid(Long id);

    /**
     * 根据weather_id批量删除数据
     *
     */
    public int deleteBlowdownByids(String ids);

    public int changeStatus(Blowdown blowdown);
}
