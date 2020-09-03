package com.sixin.broad.service.impl;

import com.sixin.broad.domain.Terminaladmin;
import com.sixin.broad.mapper.TerminaladminMapper;
import com.sixin.broad.service.ITerminaladminService;
import com.sixin.common.support.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 授权号码管理 服务层实现
 *
 * @date 2020-02-21
 */
@Service
public class TerminaladminServiceImpl implements ITerminaladminService {
    @Autowired
    private TerminaladminMapper terminaladminMapper;

    /**
     * 查询授权号码管理信息
     *
     * @param pid 授权号码管理ID
     * @return 授权号码管理信息
     */
    @Override
    public Terminaladmin selectTerminaladminById(Long pid) {
        return terminaladminMapper.selectTerminaladminById(pid);
    }

    /**
     * 查询授权号码管理列表
     *
     * @param terminaladmin 授权号码管理信息
     * @return 授权号码管理集合
     */
    @Override
    public List<Terminaladmin> selectTerminaladminList(Terminaladmin terminaladmin) {
        return terminaladminMapper.selectTerminaladminList(terminaladmin);
    }

    /**
     * 新增授权号码管理
     *
     * @param terminaladmin 授权号码管理信息
     * @return 结果
     */
    @Override
    public int insertTerminaladmin(Terminaladmin terminaladmin) {
        return terminaladminMapper.insertTerminaladmin(terminaladmin);
    }

    /**
     * 修改授权号码管理
     *
     * @param terminaladmin 授权号码管理信息
     * @return 结果
     */
    @Override
    public int updateTerminaladmin(Terminaladmin terminaladmin) {
        return terminaladminMapper.updateTerminaladmin(terminaladmin);
    }

    /**
     * 删除授权号码管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTerminaladminByIds(String ids) {
        return terminaladminMapper.deleteTerminaladminByIds(Convert.toStrArray(ids));
    }

}
