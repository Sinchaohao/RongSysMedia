package com.sixin.iot.service;

/**
 * @function:定义安防类型表对应操作接口
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:刘波
 * @Description:
 * @Date:Created in 2020-02-16 16:56
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */

import com.sixin.iot.domain.Securitype;

import java.util.List;

public interface ISecuritypeService {
    //查询所有安全类型记录
    public List<Securitype>  selectAllSecuritype();

    //查询所有有效安全类型记录
    public List<Securitype> selectSecuritypeByDelflag(char delflag);

    //根据id查询安防类型记录
    public Securitype selectSecuritypeById(int id);

    //根据name模糊提取相应记录
    public List<Securitype> selectSecurityByName(String name);

    //添加记录
    public int insertSecuritype(Securitype securitype);

    //更新记录
    public int updateSecurity(Securitype securitype);

    //根据id删除记录
    public int deleteSecuritypeById(int id);

    //清除系统全部记录
    public int deleteSecuritype();
}
