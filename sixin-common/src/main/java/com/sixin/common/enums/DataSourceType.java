package com.sixin.common.enums;
/**
 * @function:数据源配置
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:张超
 * @Description:
 * @Date:Created in 2020-01-27 17:45
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */

public enum DataSourceType
{
    /**
     * 主库，基础信息
     */
    MASTER,
    /**
     * 从库一，应用广播
     */
    SLAVE,
    /**
     * 从库二，用户管理
     */
    SXUSER,
    /**
     * 从库三，信息管理
     */
    SXINFOM,
    /**
     * 从库四，山洪灾害
     */
    SXRIVERVIS,
    /**
     * 从库五，村务管理
     */
    SXVILLAGE,
    /**
     * 从库六,村务宝典
     */
    SXBAODIAN,
    //    从库七 物联网数据
    SXIOT
}
