package com.sixin.iot.mapper;

import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.LED;
import com.sixin.iot.domain.Torrent;

import java.util.List;

public interface LEDMapper {
    /**
     * led监测记录列表
     *
     * @return
     */
    public List<LED> selectLEDList(LED led);
    /**
     * 添加led监测信息
     * @param led
     * @return
     */
    public int insertLED(LED led);
    /**
     * 根据weather_id选择数据
     * @param weather_id
     * @return
     */
    public LED selectByid(Long id);
    /**
     * 根据id选择数据
     * @param weather_id
     * @return
     */
    public List<LED> selectLEDByids(List<String> sfids);
    /**
     * 保存用户修改
     * @param led
     * @return
     */
    public int updateLED(LED led);
    /**
     * 删除led监测信息
     * @param weather_id
     * @return
     */
    public int deleteLEDByid(Long id);
    /**
     * 根据weather_id批量删除数据
     * @param weather_ids
     */
    public int deleteLEDByids(String[] ids);
    List<Torrent> queryList(Torrent torrent);

}

