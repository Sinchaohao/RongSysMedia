package com.sixin.system.mapper;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.utils.PageData;
import com.sixin.system.domain.SysOperLog;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 操作日志 数据层
 *
 * @author sixin
 */
public interface SysOperLogMapper
{
    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    public void insertOperlog(SysOperLog operLog);

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    public List<SysOperLog> selectOperLogList(SysOperLog operLog);

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    public int deleteOperLogByIds(String[] ids);

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    public SysOperLog selectOperLogById(Long operId);

    /**
     * 清空操作日志
     */
    public void cleanOperLog();

    /**
     * 获取最近的操作记录传给前端
     *
     * @return 结果
     */
    @DataSource(DataSourceType.MASTER)
    public List<PageData> selectOperLogforIndex();

    /**
     * 获取最近一个月操作次数最多的五个人名单
     *
     * @return 结果
     */
    @DataSource(DataSourceType.MASTER)
    public List<PageData> CountLogDescForMonth();

    /**
     * 新增终端地域
     *
     * @param sys_oper_log 系统操作日志信息
     * @return 结果
     */
    @DataSource(DataSourceType.MASTER)
    public int batchInsertOperlog(List<SysOperLog> list);

    @DataSource(DataSourceType.MASTER)
    public List<SysOperLog> selectOperlogListByids(List<String> sfids);
}
