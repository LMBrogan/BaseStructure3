package com.dailywear.base_structure;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@MapperScan("com.dailywear.base_structure.mapper")
@SpringBootApplication
public class BaseStructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseStructureApplication.class, args);
    }

    /**
     * mybatis-plus 分页插件  根据需要添加
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
