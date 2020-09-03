package com.sixin.iot.service.impl;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.support.Convert;
import com.sixin.common.utils.StringUtils;
import com.sixin.iot.domain.Energy;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.mapper.EnergyMapper;
import com.sixin.iot.service.IEnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @program:  EnergySys
 * @description: 能源管理系统
 * @author: 蔡文静
 * @create: 2019-12-25 14:20
 **/
@Service
public class EnergyServiceImpl implements IEnergyService {
    @Autowired
    private EnergyMapper energyMapper;

    /**
     * 查询能源管理记录列表
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Energy> selectEnergyList(Energy energy) {
        return energyMapper.selectEnergyList(energy);
    }
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Torrent> queryList(Torrent torrent) {return energyMapper.queryList(torrent);}

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Ioterminal> selectIoterminalList(Ioterminal ioterminal) {
        return energyMapper.selectIoterminalList(ioterminal);
    }
    /**
     * 根据id批量删除数据
     * @param id
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteEnergyByids(String id) {
        return energyMapper.deleteEnergyByids(Convert.toStrArray(id));
    }

    @Override
    public int deleteAllEnergy() {
        return energyMapper.deleteAllEnergy();
    }

    /**
     * 添加能源管理公告数据
     * @param  Energy
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int insertEnergy(Energy energy) {
        return energyMapper.insertEnergy(energy);
    }

    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public Energy selectByid(String id) {
        return energyMapper.selectByid(id);
    }


    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Energy> selectEnergyListByids(List<String> sfids){
        return energyMapper.selectEnergyListByids(sfids);
    }
    /**@Override
     @DataSource(value = DataSourceType.SXINFOM)
     public Energy selectBybvo(String bvoltage) {
     return energyMapper.selectBybvo(bvoltage);
     }*/

    /**
     * 删除能源管理信息
     * @param Energy
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteEnergyByid(String id) {
        return energyMapper.deleteEnergyByid(id);
    }

    /**
     * 保存用户修改
     * @param Energy
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int updateEnergy(Energy energy) {
        return energyMapper.updateEnergy(energy);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public void checkUserAllowed(Energy energy)
    {
        if (StringUtils.isNotNull(energy.getId()) )
        {
            throw new BusinessException("不允许操作超级管理员用户");
        }
    }

    /**
     * 用户状态修改
     *
     * @param user 用户信息
     * @return 结果
     */

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int changeStatus(Energy energy)
    {
        return energyMapper.updateEnergy(energy);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Energy> selectEnergyByids(List<String> sfids) {
        return energyMapper.selectEnergyByids(sfids);
    }
}
