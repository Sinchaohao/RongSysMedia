package com.sixin.iot.mapper;

import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Pump;
import com.sixin.iot.domain.Torrent;

import java.util.List;


public interface PumpMapper {
    /**
     * 水泵记录列表
     *
     * @return
     */
    List<Pump> selectPumpList(Pump pump);
    /**
     * 删除水泵信息
     * @param id
     * @return
     */
    public int deletePumpByid(Long id);
    /**
     * 根据id批量删除数据
     * @param id
     */
    public int deletePumpByids(String[] ids);
    /**
     * 添加水泵信息
     * @param pump
     * @return
     */
    int insertPump(Pump pump);
    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    Pump selectByid(Long id);
    /**
     * 根据id批量选择数据
     * @param id
     * @return
     */
    public List<Pump> selectPumpByids(List<String> sfids);
    List<Torrent> queryList(Torrent torrent);
    List<Iotype> selectList(Iotype iotype);


    /**
     * 保存用户修改
     * @param pump
     * @return
     */
    int updatePump(Pump pump);

}