package com.sixin.iot.service.impl;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.iot.domain.Fire;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.mapper.FireMapper;
import com.sixin.iot.service.IFireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program:  FireSys
 * @description: 火灾系统
 * @author: 高彭
 * @create: 2019-12-25 17:20
 **/
@Service
public class FireServiceImpl implements IFireService {
    @Autowired
    private FireMapper fireMapper;

    /**
     * 查询火灾记录列表
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Fire> selectFireList(Fire fire) {
        return fireMapper.selectFireList(fire);
    }

    /**
     * 根据id批量删除数据
     * @param fid
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteFireByids(String fid) {
        return fireMapper.deleteFireByids(Convert.toStrArray(fid));
    }

    /**
     * 添加火灾公告数据
     * @param  fire
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int insertFire(Fire fire) {
        return fireMapper.insertFire(fire);
    }
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Torrent> queryList(Torrent torrent) {return fireMapper.queryList(torrent);}

    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public Fire selectByid(String id) {
        return fireMapper.selectByid(id);
    }

    /**
     * 删除火灾信息
     * @param fid
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteFireByIds(String fid) {
        return fireMapper.deleteFireByid(fid);
    }

    /**
     * 保存用户修改
     * @param fire
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int updateFire(Fire fire) {
        return fireMapper.updateFire(fire);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Fire> selectFireListByids(List<String> sfids){
        return fireMapper.selectFireByids(sfids);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Ioterminal> queryList(Ioterminal ioterminal){
        return fireMapper.queryList(ioterminal);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Ioterminal> selectimei(Ioterminal ioterminal){
        return fireMapper.selectimei(ioterminal);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int changeStatus(Fire fire) {
        return fireMapper.updateFire(fire);
    }


}
