package com.sixin.iot.mapper;

import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Light;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.domain.User;

import java.util.List;

public interface LightMapper {
    /**
     * 路灯记录列表
     *
     * @return
     */
    List<Light> selectLightList(Light light);

    List<Ioterminal> selectIoterminalList(Torrent torrent);

    List<User> selectUserList(User user);
    /**
     * 根据nid批量删除数据
     * @param nid
     */
    int deleteLightByids(String[] nid);
    /**
     * 添加路灯信息
     * @param light
     * @return
     */
    int insertLight(Light light);

    int updateLight1(Light light);
    /**
     * 根据nid选择数据
     * @param nid
     * @return
     */
    Light selectByid(String nid);

    Ioterminal selectIoterminalBycid(String cid);
    //  public List<Light> selectLightListByids(List<String> sfids);


    /**
     * 保存用户修改
     * @param light
     * @return
     */
    int updateLight(Light light);

    int updateLight2(Light light);
    /**
     * 删除路灯信息
     * @param nid
     * @return
     */
    int deleteLightByid(String nid);

    public List<Light> selectLightByids(List<String> sfids);
}