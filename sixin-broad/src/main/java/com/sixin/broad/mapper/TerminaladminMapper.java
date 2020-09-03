package com.sixin.broad.mapper;

import com.sixin.broad.domain.Terminaladmin;

import java.util.List;

/**
 * 授权号码管理 数据层
 *
 * @date 2020-02-21
 */
public interface TerminaladminMapper {
    /**
     * 查询授权号码管理信息
     *
     * @param pid 授权号码管理ID
     * @return 授权号码管理信息
     */
    public Terminaladmin selectTerminaladminById(Long pid);

    /**
     * 查询授权号码管理列表
     *
     * @param terminaladmin 授权号码管理信息
     * @return 授权号码管理集合
     */
    public List<Terminaladmin> selectTerminaladminList(Terminaladmin terminaladmin);

    /**
     * 新增授权号码管理
     *
     * @param terminaladmin 授权号码管理信息
     * @return 结果
     */
    public int insertTerminaladmin(Terminaladmin terminaladmin);

    /**
     * 修改授权号码管理
     *
     * @param terminaladmin 授权号码管理信息
     * @return 结果
     */
    public int updateTerminaladmin(Terminaladmin terminaladmin);

    /**
     * 删除授权号码管理
     *
     * @param pid 授权号码管理ID
     * @return 结果
     */
    public int deleteTerminaladminById(Long pid);

    /**
     * 批量删除授权号码管理
     *
     * @param pids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTerminaladminByIds(String[] pids);

}