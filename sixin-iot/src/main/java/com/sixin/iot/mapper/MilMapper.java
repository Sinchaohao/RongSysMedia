package com.sixin.iot.mapper;

import com.sixin.iot.domain.EnvirData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
@Mapper
public interface MilMapper {
    public List<EnvirData> selectMilList();
    public List<EnvirData> selectMilPm();
}
