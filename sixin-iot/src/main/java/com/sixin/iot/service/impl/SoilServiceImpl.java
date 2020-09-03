package com.sixin.iot.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Soil;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.mapper.SoilMapper;
import com.sixin.iot.service.ISoilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: SoilSys
 * @description: 土壤系统
 * @author: 蔡文静
 * @create: 2019-12-25 13:40
 **/
@Service

public class SoilServiceImpl implements ISoilService {
    @Autowired
    private SoilMapper soilMapper;

    /**
     * 查询土壤记录列表
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Soil> selectSoilList(Soil soil) {
        return soilMapper.selectSoilList(soil);
    }

    /**
     * 根据id批量删除数据
     * @param id
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteSoilByids(String sid) {
        return soilMapper.deleteSoilByids(Convert.toStrArray(sid));
    }

    /**
     * 添加土壤公告数据
     * @param  Soil
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int insertSoil(Soil soil) {
        return soilMapper.insertSoil(soil);
    }

    /**
     * 根据sid选择数据
     * @param sid
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public Soil selectByid(Long id) {
        return soilMapper.selectByid(id);
    }




    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Soil> selectSoilByids(List<String> sfids)
    {return soilMapper.selectSoilByids(sfids);}



    /**
     * 删除土壤信息
     * @param Soil
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteSoilByid(String sid) {
        return soilMapper.deleteSoilByid(sid);
    }

    /**
     * 保存用户修改
     * @param Soil
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int updateSoil(Soil soil) {
        return soilMapper.updateSoil(soil);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int changeStatus(Soil delflag)
    {
        return soilMapper.updateSoil(delflag);
    }
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Torrent> queryList(Torrent torrent) {return soilMapper.queryList(torrent);}

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Iotype> selectList(Iotype iotype) {return soilMapper.selectList(iotype);}
}
