package com.sixin.iot.service.impl;

/**
 * @function:定义安防类型表对应操作接口实现
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:刘波
 * @Description:
 * @Date:Created in 2020-02-17 17:28
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */

import com.sixin.common.support.Convert;
import com.sixin.iot.domain.SecurityData;
import com.sixin.iot.domain.Securitype;
import com.sixin.iot.mapper.SecurityDataMapper;
import com.sixin.iot.service.ISecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityDataServiceImpl implements ISecurityDataService {

    @Autowired
    private SecurityDataMapper securityDataMapper;

    //提取全部防控表信息
    public List<SecurityData> selectAllSecurityData(SecurityData securityData){
        return securityDataMapper.getAllSecurityData(securityData);
    }

    //提取全部有效防控分类表信息
    public List<Securitype> selectSecuritypeByDelflag(char delflag){
        return securityDataMapper.getSecuritypeByDelflag(delflag);
    }

    //提取全部有效防控表信息
    public List<SecurityData> selectSecurityDataByDelflag(char delflag){
        return securityDataMapper.getSecurityDataByDelflag(delflag);
    }

    //根据id提取有效防控表信息
    public SecurityData selectSecurityDataById(int id){
        return securityDataMapper.getSecurityDataById(id);
    }

    //根据指定查询条件进行查询
    public List<SecurityData> selectSecurityDataList(SecurityData securityData){
        return securityDataMapper.getSecurityDataList(securityData);
    }

    //根据dept_id选择防控表信息
    public List<SecurityData> selectSecurityDataByDeptid(long deptid){
        return securityDataMapper.getSecurityDataByDeptid(deptid);
    }

    //添加防控表信息
    public int insertSecurityData(SecurityData securityData){
        return securityDataMapper.insertSecurityData(securityData);
    }

    //更新防控表信息
    public int updateSecurityData(SecurityData securityData){
        return securityDataMapper.updateSecurityData(securityData);
    }

    //根据ID删除记录
    public int deleteSecurityDataById(long id){
        return securityDataMapper.deleteSecurityDataById(id);
    }

    //删除所有记录，将delflag改为无效
    public int deleteSecurityData(){
        return securityDataMapper.deleteSecurityData();
    }

    //按安装时间统计
    public List<SecurityData> InstallByTimeList(String deptId){
        return securityDataMapper.InstallByTimeList(Convert.toLong(deptId));
    }

    //按结束时间统计
    public List<SecurityData>EndByTime(String deptId){
        return securityDataMapper.EndByTime(Convert.toLong(deptId));
    }

    //统计安防设备状态
    public List<SecurityData>isdelflag(String deptId){
        return securityDataMapper.isdelflag(Convert.toLong(deptId));
    }
}
