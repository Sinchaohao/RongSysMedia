package com.sixin.iot.service;

import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Pump;
import com.sixin.iot.domain.Torrent;

import java.util.List;

/**
 * @program: PumpSys
 * @description: 水泵系统
 * @author: 蔡文静
 * @create: 2019-12-25 15:00
 **/

public interface IPumpService {
    /**
     * 查询水泵记录列表
     *
     * @return
     */
    public List<Pump> selectPumpList(Pump pump);
    public List<Torrent> queryList(Torrent torrent);
    public List<Iotype> selectList(Iotype iotype);
    /**
     * 添加水泵公告数据
     * @param  Pump
     * @return
     */
    public int insertPump(Pump pump);
    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    public Pump selectByid(Long id);

    /**
     * 根据id批量删除数据
     * @param id
     */
    public List<Pump> selectPumpByids(List<String> sfids);
    /**
     * 保存用户修改
     * @param Pump
     * @return
     */
    public int updatePump(Pump pump);
    /**
     * 删除水泵信息
     * @param id
     * @return
     */
    public int deletePumpByid(Long id);
    public int deletePumpByids(String ids);
    public int changeStatus(Pump pump);

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */

}
