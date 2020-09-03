package com.sixin.iot.mapper;

/**
 * @function:定义安防表对应操作接口
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:刘波
 * @Description:
 * @Date:Created in 2020-02-15 18:14
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */
import com.sixin.iot.domain.SecurityData;
import com.sixin.iot.domain.Securitype;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecurityDataMapper {
    //提取全部防控表信息
    public List<SecurityData> getAllSecurityData(SecurityData securityData);

    //提取全部有效防控分类表信息
    public List<Securitype> getSecuritypeByDelflag(char delflag);

    //提取全部有效防控表信息
    public List<SecurityData> getSecurityDataByDelflag(char delflag);

    //根据id提取有效防控表信息
    public SecurityData getSecurityDataById(int id);

    //根据指定查询条件进行查询
    public List<SecurityData> getSecurityDataList(SecurityData securityData);

    //根据dept_id选择防控表信息
    public List<SecurityData> getSecurityDataByDeptid(long deptid);

    //添加防控表信息
    public int insertSecurityData(SecurityData securityData);

    //更新防控表信息
    public int updateSecurityData(SecurityData securityData);

    //根据ID删除记录
    public int deleteSecurityDataById(long id);

    //删除所有记录，将remark改为无效
    public int deleteSecurityData();

    //按安装时间统计
    public List<SecurityData> InstallByTimeList(@Param("deptId")Long deptId);

    //按安装时间统计
    public List<SecurityData> EndByTime(@Param("deptId")Long deptId);

    //统计安防设备状态
    public List<SecurityData> isdelflag(@Param("deptId")Long deptId);
}
