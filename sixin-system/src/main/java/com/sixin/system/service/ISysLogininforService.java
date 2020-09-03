package com.sixin.system.service;

import java.util.List;

import com.sixin.common.utils.PageData;
import com.sixin.system.domain.SysLogininfor;
import com.sixin.system.domain.SysOperLog;

/**
 * 系统访问日志情况信息 服务层
 *
 * @author sixin
 */
public interface ISysLogininforService
{
    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    public void insertLogininfor(SysLogininfor logininfor);

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    public int deleteLogininforByIds(String ids);

    /**
     * 清空系统登录日志
     */
    public void cleanLogininfor();

    /**
     * 获取最近的8条访客记录传给前端
     *
     * @return 结果
     */
    public List<PageData> selectLoginLogforIndex();

    /**
     * 获取一个月内访问次数最多的五个人
     *
     * @return 结果
     */
    public List<PageData> CountLoLogDescMonth();

    /**
     * 获取前五条热门登陆地点
     *
     * @return 结果
     */
    public List<PageData> CountHotLocal();

    /**
     * 获取所有登陆地点
     *
     * @return 结果
     */
    public List<PageData> CountLocal();

    /**
     * 获取最近创建的三个用户
     *
     * @return 结果
     */
    public List<PageData> recentCreatUser();

    /**
     * 批量数据插入
     */
    public int batchInsertSysLogininfor(List<SysLogininfor> list);

    /**
     * 选择导出
     */
    public List<SysLogininfor> selectSysLogininforListByids(List<String> sfids);
}
