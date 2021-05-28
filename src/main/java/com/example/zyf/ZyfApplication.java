package com.example.zyf;

import com.example.zyf.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@EnableAsync // 异步注解
@EnableScheduling // 定时任务
//@ComponentScan({"com.example.zyf.service","com.example.zyf.mapper","com.example.zyf.controller","com.example.zyf.util"})
//@MapperScan("com.example.zyf.mapper")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ZyfApplication {
    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        ConfigurableApplicationContext run = SpringApplication.run(ZyfApplication.class, args);
        User user = run.getBean("user", User.class);
        System.out.println(user);
        log.info("\r\n***********************************************************"
                + "\r\n*******             服务启动成功              **********"
                + "\r\n***********************************************************");
    }

}
