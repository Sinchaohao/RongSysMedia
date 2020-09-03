package com.sixin.generator.config;
/**
 * @function:读取代码生成相关配置
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:刘波
 * @Description:
 * @Date:Created in 2020-01-20 18：29
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "gen")
@PropertySource(value = { "classpath:generator.yml" })
public class GenConfig
{
    /** 作者 */
    public static String author;

    /** 生成包路径 */
    public static String packageName;

    /** 自动去除表前缀，默认是false */
    public static boolean autoRemovePre;

    /** 表前缀(类名不会包含表前缀) */
    public static String tablePrefix;

    public static String getAuthor()
    {
        return author;
    }

    @Value("${author}")
    public void setAuthor(String author)
    {
        GenConfig.author = author;
    }

    public static String getPackageName()
    {
        return packageName;
    }

    @Value("${packageName}")
    public void setPackageName(String packageName)
    {
        GenConfig.packageName = packageName;
    }

    public static boolean getAutoRemovePre()
    {
        return autoRemovePre;
    }

    @Value("${autoRemovePre}")
    public void setAutoRemovePre(boolean autoRemovePre)
    {
        GenConfig.autoRemovePre = autoRemovePre;
    }

    public static String getTablePrefix()
    {
        return tablePrefix;
    }

    @Value("${tablePrefix}")
    public void setTablePrefix(String tablePrefix)
    {
        GenConfig.tablePrefix = tablePrefix;
    }
}
