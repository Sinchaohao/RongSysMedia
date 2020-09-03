package com.sixin.iot.service.impl;

/**
 * @function:定义安防类型表对应操作接口实现
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:刘波
 * @Description:
 * @Date:Created in 2020-02-16 17:28
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.iot.domain.Securitype;
import com.sixin.iot.mapper.SecuritypeMapper;
import com.sixin.iot.service.ISecuritypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecuritypeServiceImpl implements ISecuritypeService {
    @Autowired
    private SecuritypeMapper securitypeMapper;

    //查询所有安全类型记录
    @Override
    @DataSource(value = DataSourceType.SXIOT)
    public List<Securitype>  selectAllSecuritype(){
        return securitypeMapper.getAllSecuritype();
    }

    //查询所有有效安全类型记录
    @Override
    @DataSource(value = DataSourceType.SXIOT)
    public List<Securitype> selectSecuritypeByDelflag(char delflag){
        return securitypeMapper.getSecuritypeByDelflag(delflag);
    }

    //根据id查询安防类型记录
    @Override
    @DataSource(value = DataSourceType.SXIOT)
    public Securitype selectSecuritypeById(int id){
        return securitypeMapper.getSecuritypeById(id);
    }

    //根据name模糊提取相应记录
    @Override
    @DataSource(value = DataSourceType.SXIOT)
    public List<Securitype> selectSecurityByName(String name){
        return securitypeMapper.getSecurityByName(name);
    }

    //添加记录
    @Override
    @DataSource(value = DataSourceType.SXIOT)
    public int insertSecuritype(Securitype securitype){
        return securitypeMapper.insertSecuritype(securitype);
    }

    //更新记录
    @Override
    @DataSource(value = DataSourceType.SXIOT)
    public int updateSecurity(Securitype securitype){
        return securitypeMapper.updateSecurity(securitype);
    }

    //根据id删除记录
    public int deleteSecuritypeById(int id){
        return securitypeMapper.deleteSecuritypeById(id);
    }

    //清除系统全部记录
    @Override
    @DataSource(value = DataSourceType.SXIOT)
    public int deleteSecuritype(){
        return securitypeMapper.deleteSecuritype();
    }
}
