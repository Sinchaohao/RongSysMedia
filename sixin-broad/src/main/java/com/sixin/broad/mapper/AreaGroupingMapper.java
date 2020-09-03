package com.sixin.broad.mapper;

import com.sixin.broad.domain.AreaGrouping;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by ASUS on 2019/8/2.
 * @author 陈霞
 * @return 分组列表管理
 */
public interface AreaGroupingMapper {

    @DataSource(DataSourceType.SLAVE)
    public AreaGrouping selectAreaGroupingById(String aid);
    /**
    * 查询分组列表，连表查询
    *
    * @return 终端分组列表
    */
    @DataSource(DataSourceType.SLAVE)
    public List<AreaGrouping> listAreaGrouping(AreaGrouping areaGrouping);

    /**
     * @author cx
     * @param aid
     *
     * @Description 分组管理批量删除
     */
    @DataSource(DataSourceType.SLAVE)
    public int deleteAreaGroupingByIds(String[] aid);

    /**
    * 更新分组列表
    *
    * @return 终端分组列表
    */
    @DataSource(DataSourceType.SLAVE)
    public int updateAreaGrouping(AreaGrouping areaGrouping);

    /**
    * 插入分组列表
    *
    * @return 终端分组列表
    */
    @DataSource(DataSourceType.SLAVE)
    public int insertAreaGrouping(AreaGrouping areaGrouping);

    /**
     * 查询分组列表
     *
     * @return 终端分组列表
     */
    @DataSource(DataSourceType.SLAVE)
    public AreaGrouping selectAreaGroupingByAid(String aid);

    /**
     * 新增终端地域
     *
     * @param area 终端地域信息
     * @return 结果
     */
    @DataSource(DataSourceType.SLAVE)
    public int batchInsertAreaGrouping(List<AreaGrouping> list);
}
