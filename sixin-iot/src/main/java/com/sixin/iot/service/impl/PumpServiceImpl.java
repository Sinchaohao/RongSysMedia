package com.sixin.iot.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Pump;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.mapper.PumpMapper;
import com.sixin.iot.service.IPumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program:  PumpSys
 * @description: 水泵系统
 * @author: 蔡文静
 * @create: 2019-12-25 14:40
 **/
@Service
public class PumpServiceImpl implements IPumpService {
    @Autowired
    private PumpMapper pumpMapper;

    /**
     * 查询水泵记录列表
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Pump> selectPumpList(Pump pump) {
        return pumpMapper.selectPumpList(pump);
    }
    /**
     * 根据id批量删除数据
     * @param id
     */

    // @Override
    // @DataSource(value = DataSourceType.SXINFOM)
    // public int deletePumpByids(String id) {
    //     return pumpMapper.deletePumpByids(Convert.toStrArray(id));
    //}

    /**
     * 添加水泵公告数据
     * @param  Pump
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int insertPump(Pump pump) {
        return pumpMapper.insertPump(pump);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Torrent> queryList(Torrent torrent) {return pumpMapper.queryList(torrent);}

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Iotype> selectList(Iotype iotype) {return pumpMapper.selectList(iotype);}
    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public Pump selectByid(Long id) {
        return pumpMapper.selectByid(id);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Pump> selectPumpByids(List<String> sfids){
        return pumpMapper.selectPumpByids(sfids);
    }
    /**
     * 删除水泵信息
     * @param Pump
     * @return
     */
    //  @Override
    // @DataSource(value = DataSourceType.SXINFOM)
    // public int deletePumpByid(String id) {
    //    return pumpMapper.deletePumpByid(id);
    // }

    /**
     * 保存用户修改
     * @param Pump
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int updatePump(Pump pump) {
        return pumpMapper.updatePump(pump);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deletePumpByid(Long id) {
        return pumpMapper.deletePumpByid(id);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deletePumpByids(String ids) {
        return pumpMapper.deletePumpByids(Convert.toStrArray(ids));
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int changeStatus(Pump pump) {
        return pumpMapper.updatePump(pump);
    }



}
