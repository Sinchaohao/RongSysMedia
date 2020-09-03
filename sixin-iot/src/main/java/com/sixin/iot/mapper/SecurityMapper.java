package com.sixin.iot.mapper;

import com.sixin.iot.domain.*;

import java.util.List;

public interface SecurityMapper {
    /**
     * 安防记录列表
     *
     * @return
     */
    List<Security> selectSecurityList(Security security);


    List<Securitype> selectSecuritypeList(Securitype securitype);

    List<User> selectUserList(User user);

    List<Ioterminal> selectIoterminalList(Torrent torrent);
    /**
     * 根据id选择数据
     * @param id
     * @return
     *
     */
    Security selectSecurityByid(String id);

    /**
     * 添加安防信息
     * @param security
     * @return
     */
    int insertSecurity(Security security);

    /* 根据id删除数据
     * 根据id批量删除数据
     * @param id
     */
    int deleteSecurityByids(String[] ids);

    /**
     * 保存用户修改
     * @param security
     * @return
     */
    int updateSecurity(Security security);
    /**
     * 删除安防信息
     * @param id
     * @return

    int deleteSecurityByid(String id);*/

    public List<Security> selectSecurityByids(List<String> sfids);

    Ioterminal selectIoterminalBysno(String sno);

    int updateSecurity1(Security security);

    int updateSecurity2(Security security);
}