package com.sixin.iot.mapper;

import com.sixin.iot.domain.EngPannel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EngPannelMapper {
    /*
          功能：能源数据分页
          @Param : engPannel
     */
    public List<EngPannel> selectEnergyList(EngPannel engPannel);
    /*
          功能：控制台图表能源数据查询
          @Param : deptId
     */
    public List<EngPannel> selectEnergyListOnControl(@Param("addrid")String addrid);
}
