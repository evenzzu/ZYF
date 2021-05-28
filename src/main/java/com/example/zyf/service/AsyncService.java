/**
 * @projectName ZYF
 * @package com.example.zyf.service
 * @className com.example.zyf.service.AsyncService
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.service;

import com.alibaba.fastjson.support.odps.udf.CodecCheck;
import com.example.zyf.model.User;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * AsyncService
 *
 * @description
 * @author zyf
 * @date 2021/1/15 19:26
 * @version 1.0
 */
@Service
public class AsyncService {
    //  @Autowired
    JavaMailSenderImpl javaMailSender;
    private final WebClient webClient;

    // 使用 WebClient 调用 REST 服务
    public AsyncService(WebClient.Builder web) {
        this.webClient = web.baseUrl("http://example.org").build();
    }

    public Mono<User> someRestCall(String name) {
        return this.webClient.get().uri("/producer", name).retrieve().bodyToMono(User.class);
    }

    @Async  // 异步注解
    public void test() {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": ");
            try {
                System.out.println("数据处理中");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
            System.out.println("数据处理完成");
        }
    }

        @Scheduled(cron = "${sj}")
        @PostConstruct
        public void hello () {
            System.out.println("hello ...");
        }

        @Test
        public void test01 () throws Exception {

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("今天放假");
            simpleMailMessage.setText("明天周六放假");
            simpleMailMessage.setTo("819960954@qq.com");
            simpleMailMessage.setFrom("evenzzu@163.com");
            javaMailSender.send(simpleMailMessage);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSentDate(new Date());
            mimeMessageHelper.addAttachment("1.jpg", new File(""));
        }

    }
