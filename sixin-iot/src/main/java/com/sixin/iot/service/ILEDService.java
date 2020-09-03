package com.sixin.iot.service;

import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.LED;
import com.sixin.iot.domain.Torrent;

import java.util.List;
/**
 * @program: MeteorSys
 * @description: 气象系统
 * @author: 蔡文静
 * @create: 2019-12-25 15:00
 **/

public interface ILEDService {

    /**
     * 查询LED记录列表
     *
     * @return
     */
    public List<LED> selectLEDList(LED led);

    /**
     * 添加LED公告数据
     *
     */
    public int insertLED(LED led);
    /**
     * 根据weather_id选择数据
     *
     */
    public LED selectByid(Long id);
    public List<LED> selectLEDByids(List<String> sfids);
    /**
     * 保存用户修改
     *
     */
    public int updateLED(LED led);
    /**
     * 删除LED信息
     *
     */
    public int deleteLEDByid(Long id);
    public List<Torrent> queryList(Torrent torrent);
    /**
     * 根据weather_id批量删除数据
     *
     */
    public int deleteLEDByids(String ids);

    public int changeStatus(LED led);
}
