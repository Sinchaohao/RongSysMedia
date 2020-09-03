package com.sixin.broad.mapper;

import com.sixin.broad.domain.TerminalCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ASUS on 2019/3/20.
 * @author cx
 */
public interface TerminalCountMapper {

    /**
     * @Description 返回终端数量
     */
    List<TerminalCount> getAllTerminal(@Param("deptId")Long deptId);


}
