package com.sixin.iot.service;

import com.sixin.iot.domain.*;

import java.util.List;

/**
 * @program: SecuritySys
 * @description: 安防系统
 * @author: 饶泽敏
 * @create: 2019-12-24 15:40
 **/


public interface ISecurityService {
    /**
     * 查询安防记录列表
     *
     * @return
     */
    public List<Security> selectSecurityList(Security security);

    public List<Securitype> selectSecuritypeList(Securitype securitype);
    /**
     * 根据infoid选择数据
     * @param id
     * @return
     */
    public List<User> selectUserList(User user);

    public List<Ioterminal> selectIoterminalList(Torrent torrent);
    public Security selectSecurityByid(String id);

    /**
     * 添加安防公告数据
     * @param  security
     * @return
     */
    public int insertSecurity(Security security);

    /**
     * 根据id批量删除数据
     * @param ids
     */
    public int deleteSecurityByids(String ids);

    /**
     * 保存用户修改
     * @param security
     * @return
     */
    public int updateSecurity(Security security);
    /**
     * 删除安防信息
     * @param id
     * @return*/


    public List<Security>  selectSecurityByids(List<String> sfids);

    public Ioterminal selectIoterminalBysno(String sno);
    public int changeStatus(Security security);
}
