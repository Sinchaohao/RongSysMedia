package com.sixin.system.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.common.support.Convert;
import com.sixin.system.domain.SysLogininfor;
import com.sixin.system.mapper.SysLogininforMapper;
import com.sixin.system.service.ISysLogininforService;

/**
 * 系统访问日志情况信息 服务层处理
 *
 * @author sixin
 */
@Service
public class SysLogininforServiceImpl implements ISysLogininforService
{

    @Autowired
    private SysLogininforMapper logininforMapper;

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(SysLogininfor logininfor)
    {
        logininforMapper.insertLogininfor(logininfor);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor)
    {
        return logininforMapper.selectLogininforList(logininfor);
    }

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteLogininforByIds(String ids)
    {
        return logininforMapper.deleteLogininforByIds(Convert.toStrArray(ids));
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLogininfor()
    {
        logininforMapper.cleanLogininfor();
    }

    /**
     * 获取最近的8条访客记录传给前端
     *
     * @return 结果
     */
    @Override
    public List<PageData> selectLoginLogforIndex()
    {
        return logininforMapper.selectLoginLogforIndex();
    }

    /**
     * 获取一个月内访问次数最多的五个人
     *
     * @return 结果
     */
    public List<PageData> CountLoLogDescMonth(){
        return logininforMapper.CountLoLogDescMonth();
    }


    /**
     * 获取前五条热门登陆地点
     *
     * @return 结果
     */
    public List<PageData> CountHotLocal(){
        return logininforMapper.CountHotLocal();
    };

    /**
     * 获取所有登陆地点
     *
     * @return 结果
     */
    public List<PageData> CountLocal(){
        return logininforMapper.CountLocal();
    };

    /**
     * 获取最近创建的三个用户
     *
     * @return 结果
     */
    public List<PageData> recentCreatUser()
    {
        return logininforMapper.recentCreatUser();
    }

    /**
     * 批量数据插入
     */
    @Override
    @DataSource(value = DataSourceType.MASTER)
    public int batchInsertSysLogininfor(List<SysLogininfor> list){
        return logininforMapper.batchInsertSysLogininfor(list);
    }

    /**
     * 选择导出
     */
    @Override
    @DataSource(value = DataSourceType.MASTER)
    public List<SysLogininfor> selectSysLogininforListByids(List<String> sfids){
        return logininforMapper.selectSysLogininforListByids(sfids);
    }
}
