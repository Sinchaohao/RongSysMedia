package com.sixin.iot.service;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Light;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.domain.User;

import java.util.List;

/**
 * @program: LightSys
 * @description: 路灯系统
 * @author: 饶泽敏
 * @create: 2019-12-25 14:40
 **/
public interface ILightService {
    /**
     * 查询路灯记录列表
     *
     * @return
     */
    public List<Light> selectLightList(Light light);

    /**
     * 根据nid批量删除数据
     * @param id
     */
    public int deleteLightByids(String nid);

    /**
     * 添加路灯公告数据
     * @param  Light
     * @return
     */
    public int insertLight(Light light);

    //public int insertLight1(Ioterminal ioterminal);
    /**
     * 根据nid选择数据
     * @param nid
     * @return
     */
    public Light selectBynid(String id);

    public Ioterminal selectIoterminalBycid(String cid);

    public List<Ioterminal> selectIoterminalList(Torrent torrent);


    public List<User> selectUserList(User user);

    public List<Light> selectLightByids(List<String> sfids);

    /**
     * 保存用户修改
     * @param Light
     * @return
     */
    public int updateLight(Light light);

    /**
     * 删除路灯信息
     * @param nid
     * @return
     */
    public int deleteLightByid(String nid);

    /**
     * 角色状态修改
     *
     * @param status 角色信息
     * @return 结果
     */
    public int changeStatus(Light status);


}
