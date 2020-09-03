package com.sixin.broad.service.impl;

import com.sixin.broad.domain.TerminalCount;
import com.sixin.broad.mapper.TerminalCountMapper;
import com.sixin.broad.service.ITerminalCountService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/3/20.
 */
@Service
public class TerminalCountService implements ITerminalCountService {
    @Autowired
    private TerminalCountMapper terminalCountMapper;
    /**
     *
     * 统计终端维护量
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<TerminalCount> getAllTerminal(String deptId){
        return terminalCountMapper.getAllTerminal(Convert.toLong(deptId));
    }


}
