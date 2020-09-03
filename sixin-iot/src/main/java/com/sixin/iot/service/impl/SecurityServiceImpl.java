package com.sixin.iot.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;

import com.sixin.iot.domain.*;
import com.sixin.iot.mapper.SecurityMapper;
import com.sixin.iot.service.ISecurityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixin.common.support.Convert;import com.sixin.common.utils.StringUtils;
import java.util.List;

/**
 * @program: SecuritySys
 * @description: 安防惯例
 * @author: 饶泽敏
 * @create: 2019-12-24 15:40
 **/
@Service
public class SecurityServiceImpl implements ISecurityService {
    @Autowired
    private SecurityMapper securityMapper;

    /**
     * 查询安防记录列表
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Security> selectSecurityList(Security security) {
        return securityMapper.selectSecurityList(security);
    }


    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Securitype> selectSecuritypeList(Securitype securitype) {
        return securityMapper.selectSecuritypeList(securitype);
    }


    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<User> selectUserList(User user) {
        return securityMapper.selectUserList(user);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Ioterminal> selectIoterminalList(Torrent torrent) {
        return securityMapper.selectIoterminalList(torrent);
    }

    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public Security selectSecurityByid(String id) {
        return securityMapper.selectSecurityByid(id);
    }

    /**
     * 添加安防公告数据
     * @param  Security
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int insertSecurity(Security security) {
        return securityMapper.insertSecurity(security);
    }


    /**
     * 根据id批量删除数据
     * @param id

     @Override
     @DataSource(value = DataSourceType.SXINFOM)
     public int deleteSecurityByid(String id) {
     return securityMapper.deleteSecurityByids(Convert.toStrArray(id));
     }*/


    /**
     * 根据id删除安防信息
     * @param ids
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteSecurityByids(String ids) {
        return securityMapper.deleteSecurityByids(Convert.toStrArray(ids));
    }

    /**
     * 保存用户修改
     * @param Security
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int updateSecurity(Security security) {
//        securityMapper.updateSecurity1(security);
//        Ioterminal ioterminal= securityMapper.selectIoterminalBysno(security.getSno());
//        BeanUtils.copyProperties(ioterminal,security);
//        security.setDept_id(ioterminal.getDeptid());
//        security.setInstalltime(ioterminal.getStartdate());
//        security.setEndtime(ioterminal.getExdate());
//        securityMapper.updateSecurity2(security);
        return securityMapper.updateSecurity(security);
    }

    /**
     * 用户状态修改
     *
     * @param user 用户信息
     * @return 结果
     */

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int changeStatus(Security security)
    {
        return securityMapper.updateSecurity(security);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Security> selectSecurityByids(List<String> sfids) {
        return securityMapper.selectSecurityByids(sfids);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public Ioterminal selectIoterminalBysno(String sno) {
        return securityMapper.selectIoterminalBysno(sno);
    }

}
