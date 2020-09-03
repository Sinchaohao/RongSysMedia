package com.sixin.iot.service;
import com.sixin.iot.domain.Energy;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Torrent;

import java.util.List;

/**
 * @program: EnergySys
 * @description: 能源管理系统
 * @author: 饶泽敏
 * @create: 2019-12-25 14:20
 **/
public interface IEnergyService {
    /**
     * 查询能源管理记录列表
     *
     * @return
     */
    public List<Energy> selectEnergyList(Energy energy);
    public List<Torrent> queryList(Torrent torrent);

    public List<Ioterminal> selectIoterminalList(Ioterminal ioterminal);
    /**
     * 根据id批量删除数据
     * @param id
     */
    public int deleteEnergyByids(String id);

    /**
     * 清空数据
     */
    public int deleteAllEnergy();
    /**
     * 添加能源管理公告数据
     * @param  Energy
     * @return
     */
    public int insertEnergy(Energy energy);
    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    public Energy selectByid(String id);

    public List<Energy> selectEnergyListByids(List<String> sfids);

    // public Energy selectBybvo(String bcoltage);
    /**
     * 保存用户修改
     * @param Energy
     * @return
     */
    public int updateEnergy(Energy energy);
    /**
     * 删除能源管理信息
     * @param id
     * @return
     */
    public int deleteEnergyByid(String id);

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    public void checkUserAllowed(Energy energy);

    /**
     * 用户状态修改
     *
     * @param user 用户信息
     * @return 结果
     */
    public int changeStatus(Energy energy);

    public List<Energy>  selectEnergyByids(List<String> sfids);


}
