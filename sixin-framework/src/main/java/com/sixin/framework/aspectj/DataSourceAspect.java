package com.sixin.framework.aspectj;
/**
 * @function:多数据源处理
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:刘波
 * @Description:
 * @Date:Created in 2020-01-20 18：27
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */
import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.config.datasource.DynamicDataSourceContextHolder;
import com.sixin.common.utils.StringUtils;

@Aspect
@Order(1)
@Component
public class DataSourceAspect
{
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(com.sixin.common.annotation.DataSource)"
            + "|| @within(com.sixin.common.annotation.DataSource)")
    public void dsPointCut()
    {

    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable
    {
        DataSource dataSource = getDataSource(point);

        if (StringUtils.isNotNull(dataSource))
        {
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
        }

        try
        {
            return point.proceed();
        }
        finally
        {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

    /**
     * 获取需要切换的数据源
     */
    public DataSource getDataSource(ProceedingJoinPoint point)
    {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends Object> targetClass = point.getTarget().getClass();
        DataSource targetDataSource = targetClass.getAnnotation(DataSource.class);
        if (StringUtils.isNotNull(targetDataSource))
        {
            return targetDataSource;
        }
        else
        {
            Method method = signature.getMethod();
            DataSource dataSource = method.getAnnotation(DataSource.class);
            return dataSource;
        }
    }
}
