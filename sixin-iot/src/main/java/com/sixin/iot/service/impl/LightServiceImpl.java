package com.sixin.iot.service.impl;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Light;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.domain.User;
import com.sixin.iot.mapper.LightMapper;
import com.sixin.iot.service.ILightService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @program:  LightSys
 * @description: 路灯系统
 * @author: 饶泽敏
 * @create: 2019-12-25 14:40
 **/
@Service
public class LightServiceImpl implements ILightService {
    @Autowired
    private LightMapper lightMapper;

    /**
     * 查询路灯记录列表
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Light> selectLightList(Light light) {
        return lightMapper.selectLightList(light);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Ioterminal> selectIoterminalList(Torrent torrent) {
        return lightMapper.selectIoterminalList(torrent);
    }


    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<User> selectUserList(User user) {
        return lightMapper.selectUserList(user);
    }

    /**
     * 根据id批量删除数据
     * @param id
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteLightByids(String nid) {
        return lightMapper.deleteLightByids(Convert.toStrArray(nid));
    }

    /**
     * 添加路灯公告数据
     * @param  Light
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int insertLight(Light light) {

        return lightMapper.insertLight(light);
    }
/**
 @Override
 @DataSource(value = DataSourceType.SXINFOM)
 public int insertLight1(Ioterminal ioterminal) {
 return lightMapper.insertLight1(ioterminal);
 }
 */
    /**
     * 根据nid选择数据
     * @param nid
     * @return
     */

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public Light selectBynid(String nid) {
        return lightMapper.selectByid(nid);
    }


    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public Ioterminal selectIoterminalBycid(String cid) {
        return lightMapper.selectIoterminalBycid(cid);
    }

    // @Override
    //@DataSource(value = DataSourceType.SXINFOM)
    //public List<Light> selectLightListByids(List<String> sfids){return lightMapper.selectLightListByids(sfids);}

    /**
     * 删除路灯信息
     * @param Light
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteLightByid(String nid) {
        return lightMapper.deleteLightByid(nid);
    }

    /**
     * 保存用户修改
     * @param Light
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int updateLight(Light light) {
        lightMapper.updateLight1(light);
        Ioterminal ioterminal= lightMapper.selectIoterminalBycid(light.getCid());
        BeanUtils.copyProperties(ioterminal,light);
        lightMapper.updateLight2(light);
        return lightMapper.updateLight(light);
    }

    /**
     * 角色状态修改
     *
     * @param status 角色信息
     * @return 结果
     */
    @Override
    public int changeStatus(Light status)
    {
        return lightMapper.updateLight(status);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Light> selectLightByids(List<String> sfids){
        return lightMapper.selectLightByids(sfids);
    }
}
