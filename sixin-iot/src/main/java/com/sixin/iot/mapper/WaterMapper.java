package com.sixin.iot.mapper;


import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.domain.Water;

import java.util.List;

public interface WaterMapper {
    /**
     * 水质记录列表
     *
     * @return
     */
    List<Water> selectWaterList(Water water);

    List<Torrent> queryList(Torrent torrent);
    List<Iotype> selectList(Iotype iotype);
    /**
     * 根据wid批量删除数据
     * @param wid
     */
    int deleteWaterByids(String[] ids);
    /**
     * 添加水质信息
     * @param water
     * @return
     */
    int insertWater(Water water);
    /**
     * 根据wid选择数据
     * @param wid
     * @return
     */
    Water selectByid(Long id);

    /**
     * 保存用户修改
     * @param water
     * @return
     */
    int updateWater(Water water);
    /**
     * 删除水质信息
     * @param wid
     * @return
     */
    int deleteWaterByid(String wid);

    public  List<Water> selectWaterByids(List<String> sfids);
}
