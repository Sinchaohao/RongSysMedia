package com.sixin.broad.service;

import com.sixin.broad.domain.TerminalCount;

import java.util.List;

/**
 *
 * @author 陈霞
 * @data 2019/3/20.
 */
public interface ITerminalCountService {
    /**
     * @Description 统计故障终端，按时间分布
     */
    List<TerminalCount> getAllTerminal(String deptId);

}
