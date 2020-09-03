package com.sixin.framework.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.util.Utils;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.utils.spring.SpringUtils;
import com.sixin.framework.config.properties.DruidProperties;
import com.sixin.framework.datasource.DynamicDataSource;

/**
 * druid 配置多数据源
 * 
 * @author sixin
 */
@Configuration
public class DruidConfig
{
    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource(DruidProperties druidProperties)
    {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
    public DataSource slaveDataSource(DruidProperties druidProperties)
    {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }
    @Bean
    @ConfigurationProperties("spring.datasource.druid.sxuser")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.sxuser", name = "enabled", havingValue = "true")
    public DataSource sx_userDataSource()
    {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.sxinfom")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.sxinfom", name = "enabled", havingValue = "true")
    public DataSource sx_infomDataSource()
    {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.sxrivervis")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.sxrivervis", name = "enabled", havingValue = "true")
    public DataSource sx_rivervisDataSource()
    {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.sxvillage")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.sxvillage", name = "enabled", havingValue = "true")
    public DataSource sx_villageDataSource()
    {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.sxbaodian")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.sxbaodian", name = "enabled", havingValue = "true")
    public DataSource sx_baodianDataSource()
    {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource, DataSource slaveDataSource,
                                        DataSource sx_userDataSource, DataSource sx_infomDataSource,
                                        DataSource sx_rivervisDataSource, DataSource sx_villageDataSource,
                                        DataSource sx_baodianDataSource)
    {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
//        setDataSource(targetDataSources, DataSourceType.SLAVE.name(), "slaveDataSource");
        targetDataSources.put(DataSourceType.SLAVE.name(), slaveDataSource);
        targetDataSources.put(DataSourceType.SXUSER.name(), sx_userDataSource);
        targetDataSources.put(DataSourceType.SXINFOM.name(), sx_infomDataSource);
        targetDataSources.put(DataSourceType.SXRIVERVIS.name(), sx_rivervisDataSource);
        targetDataSources.put(DataSourceType.SXVILLAGE.name(), sx_villageDataSource);
        targetDataSources.put(DataSourceType.SXBAODIAN.name(), sx_baodianDataSource);
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }

    /**
     * 设置数据源
     * 
     * @param targetDataSources 备选数据源集合
     * @param sourceName 数据源名称
     * @param beanName bean名称
     */
    public void setDataSource(Map<Object, Object> targetDataSources, String sourceName, String beanName)
    {
        try
        {
            DataSource dataSource = SpringUtils.getBean(beanName);
            targetDataSources.put(sourceName, dataSource);
        }
        catch (Exception e)
        {
        }
    }

    /**
     * 去除监控页面底部的广告
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    @ConditionalOnProperty(name = "spring.datasource.druid.statViewServlet.enabled", havingValue = "true")
    public FilterRegistrationBean removeDruidFilterRegistrationBean(DruidStatProperties properties)
    {
        // 获取web监控页面的参数
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
        // 提取common.js的配置路径
        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
        final String filePath = "support/http/resources/js/common.js";
        // 创建filter进行过滤
        Filter filter = new Filter()
        {
            @Override
            public void init(javax.servlet.FilterConfig filterConfig) throws ServletException
            {
            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException
            {
                chain.doFilter(request, response);
                // 重置缓冲区，响应头不会被重置
                response.resetBuffer();
                // 获取common.js
                String text = Utils.readFromResource(filePath);
                // 正则替换banner, 除去底部的广告信息
                text = text.replaceAll("<a.*?banner\"></a><br/>", "");
                text = text.replaceAll("powered.*?shrek.wang</a>", "");
                response.getWriter().write(text);
            }

            @Override
            public void destroy()
            {
            }
        };
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(commonJsPattern);
        return registrationBean;
    }
}
