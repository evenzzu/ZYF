/**
 * @projectName ZYF
 * @package com.example.zyf.util.RabbitMQ
 * @className com.example.zyf.util.RabbitMQ.Producter
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.util.RabbitMQ;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.zyf.config.RabbitMQConfig;

/**
 * Producter
 * @description 生产者
 * @author zyf
 * @date 2020/12/31 9:54
 * @version 1.0
 */
@Component
public class Producter {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void produce(){
//        Producter.class.d

        String message = new Date() + "北京";
        System.out.println("生产者 生产消息---->"+message);
//        rabbitTemplate.convertAndSend("routing",message);
//        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(RabbitMQConfig.TEST_FANOUT_EXCHANGE,"",message);
//        }
    }
    public void send2DirectTestQueue(String massage){
        rabbitTemplate.convertAndSend(RabbitMQConfig.TEST_DIRECT_EXCHANGE,
                RabbitMQConfig.DIRECT_ROUTINGKEY, massage);
    }

    public void send2TopicTestAQueue(String massage){
        rabbitTemplate.convertAndSend(RabbitMQConfig.TEST_TOPIC_EXCHANGE,
                "test.aaa", massage);
    }

    public void send2TopicTestBQueue(String massage){
        rabbitTemplate.convertAndSend(RabbitMQConfig.TEST_TOPIC_EXCHANGE,
                "test.bbb", massage);
    }
}
