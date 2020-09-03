//package com.sixin.api.util;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//注释原因，因目前使用Springboot中的@CrossOrigin进行跨域操作，打开/api路径，本配置因实现效果相同，暂不使用
//时间：2019-01-26
// 张超

//@Configuration
//public class ApiConfiguration {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                System.out.println("如果***跨域端口开放***执行了这里显示就会看到这句话\n端口的开放为/api/**");
//                registry.addMapping("/api/**")
//                        .allowedOrigins("*")
//                        .allowCredentials(true)
//                        .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
//                        .maxAge(3600);
//                System.out.println("如果***跨域端口开放***执行完毕了这里显示就会看到这句话");
//            }
//        };
//    }
//}