package cn.x.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.x.study.mapper")
public class ShiroStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroStudyApplication.class, args);
    }

}
