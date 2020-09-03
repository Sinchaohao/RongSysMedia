package com.sixin.iot.service.impl;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.iot.domain.Flood;
import com.sixin.iot.domain.Floodbytl;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.mapper.FloodMapper;
import com.sixin.iot.service.IFloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program:  FloodSys
 * @description: 山洪系统
 * @author: 饶泽敏
 * @create: 2019-12-25 15:00
 **/
@Service
public class FloodServiceImpl implements IFloodService {
    @Autowired
    private FloodMapper floodMapper;

    /**
     * 查询山洪记录列表
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Flood> selectFloodList(Flood flood) {
        return floodMapper.selectFloodList(flood);
    }

    /**
     * 根据id批量删除数据
     * @param id
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteFloodByids(String id) {
        return floodMapper.deleteFloodByids(Convert.toStrArray(id));
    }

    /**
     * 添加山洪公告数据
     * @param  Flood
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int insertFlood(Flood flood) {
        return floodMapper.insertFlood(flood);
    }

    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public Flood selectByid(String id) {
        return floodMapper.selectByid(id);
    }

    /**
     * 删除山洪信息
     * @param Flood
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteFloodByid(String id) {
        return floodMapper.deleteFloodByid(id);
    }

    /**
     * 保存用户修改
     * @param Flood
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int updateFlood(Flood flood) {
        return floodMapper.updateFlood(flood);
    }




    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Torrent> queryList(Torrent torrent) {return floodMapper.queryList(torrent);}
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Flood> selectFloodByids(List<String> sfids) {
        return floodMapper.selectFloodByids(sfids);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Ioterminal> queryList(Ioterminal ioterminal){
        return floodMapper.queryList(ioterminal);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Ioterminal> selectimei(Ioterminal ioterminal){
        return floodMapper.selectimei(ioterminal);
    }
    /**
     * 设备状态修改
     *
     * @param flood 设备信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int changeStatus(Flood flood)
    {
        return floodMapper.updateFlood(flood);
    }
}
