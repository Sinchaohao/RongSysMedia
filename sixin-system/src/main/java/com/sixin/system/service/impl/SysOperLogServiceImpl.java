package com.sixin.system.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.common.support.Convert;
import com.sixin.system.domain.SysOperLog;
import com.sixin.system.mapper.SysOperLogMapper;
import com.sixin.system.service.ISysOperLogService;

/**
 * 操作日志 服务层处理
 *
 * @author sixin
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService
{
    @Autowired
    private SysOperLogMapper operLogMapper;

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog)
    {
        operLogMapper.insertOperlog(operLog);
    }

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog)
    {
        return operLogMapper.selectOperLogList(operLog);
    }

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteOperLogByIds(String ids)
    {
        return operLogMapper.deleteOperLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLog selectOperLogById(Long operId)
    {
        return operLogMapper.selectOperLogById(operId);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog()
    {
        operLogMapper.cleanOperLog();
    }

    /**
     * 获取最近的操作记录传给前端
     *
     * @return 结果
     */
    public List<PageData> selectOperLogforIndex(){
        return operLogMapper.selectOperLogforIndex();
    }

    /**
     * 获取最近一个月操作次数最多的五个人名单
     *
     * @return 结果
     */
    public List<PageData> CountLogDescForMonth(){
        return operLogMapper.CountLogDescForMonth();
    }

    /**
     * 批量数据插入
     */
    @Override
    @DataSource(value = DataSourceType.MASTER)
    public int batchInsertSysOperLog(List<SysOperLog> list){
        return operLogMapper.batchInsertOperlog(list);
    }

    /**
     * 选择导出
     */
    @Override
    @DataSource(value = DataSourceType.MASTER)
    public List<SysOperLog> selectSysOperLogListByids(List<String> sfids){
        return operLogMapper.selectOperlogListByids(sfids);
    }
}
