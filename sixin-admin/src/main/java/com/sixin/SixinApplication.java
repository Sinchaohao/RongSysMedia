package com.sixin;

/**
 * @Systemname:湖南灾害应急管理系统
 * @Author:刘波
 * @Description:
 * @Date:Created in $[TIME] $[DATE]
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:综合训练系统V1.0
 * @Rewriting:刘波
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author sixin
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SixinApplication {
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(SixinApplication.class, args);

    }
}
