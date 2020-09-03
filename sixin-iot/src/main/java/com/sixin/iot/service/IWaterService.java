package com.sixin.iot.service;


import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.domain.Water;

import java.util.List;

/**
 * @program: WaterSys
 * @description: 水质系统
 * @author: 蔡文静
 * @create: 2019-12-25 14:00
 **/
public interface IWaterService {
    /**
     * 查询水质记录列表
     *
     * @return
     */
    public List<Water> selectWaterList(Water water);

    /**
     * 根据wid批量删除数据
     * @param wid
     */
    public int deleteWaterByids(String ids);

    /**
     * 添加水质公告数据
     * @param  water
     * @return
     */
    public int insertWater(Water water);
    /**
     * 根据wid选择数据
     * @param wid
     * @return
     */
    public Water selectByid(Long id);
    /**
     * 保存用户修改
     * @param water
     * @return
     */
    public int updateWater(Water water);
    /**
     * 删除水质信息
     * @param wid
     * @return
     */
    public int deleteWaterByid(String wid);
    public List<Water> selectWaterByids(List<String> sfids);
    public int changeDelflag(Water water);

    public List<Torrent> queryList(Torrent torrent);
    public List<Iotype> selectList(Iotype iotype);
}
