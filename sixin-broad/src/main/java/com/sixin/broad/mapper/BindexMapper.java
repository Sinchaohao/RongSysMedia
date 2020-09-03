package com.sixin.broad.mapper;

import com.sixin.broad.domain.Bindex;
import org.apache.ibatis.annotations.Param;

/**
 * @author 张超 teavamc
 * @Description:前台数据的封装模型
 * @ClassName BindexMapper
 * @date 2019/1/26 13:06
 **/
public interface BindexMapper {

    /**
     * 统计终端数，运行数，停止运行数
     * @author 张超 teavamc
     * @date 2019/1/26
     * @param []
     * @return com.sixin.broad.mapper.BindexMapper
     */
    public Bindex count(@Param("aid")String aid);

}
