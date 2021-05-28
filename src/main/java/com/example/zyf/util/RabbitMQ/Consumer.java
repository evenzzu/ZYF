/**
 * @projectName ZYF
 * @package com.example.zyf.util.RabbitMQ
 * @className com.example.zyf.util.RabbitMQ.Consumer
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.util.RabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.zyf.config.RabbitMQConfig;

/**
 * Consumer
 * @description
 * @author zyf
 * @date 2020/12/31 10:00
 * @version 1.0
 */
@Component
public class Consumer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @RabbitHandler
//    @RabbitListener(queuesToDeclare = @Queue("zyf") )
//    public void consumer(String message){
//        System.out.println("消费者消费消息---->"+message);
//    }

//    @RabbitListener(bindings = {
//            @QueueBinding(
//                    exchange = @Exchange(value = "direct"),
//                    value = @Queue(value = "${spring.rabbitmq.receive-queue}", durable = "true"),
//                    key = "${spring.rabbitmq.routingkey}"
//            )
//    })
//    public void process(String message){
//        System.out.println("消费者消费消息：" + message);
//
//    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = RabbitMQConfig.FANOUT_QUEUE_NAME,durable = "true"),
    exchange = @Exchange(value = RabbitMQConfig.TEST_FANOUT_EXCHANGE,type = "fanout"))})
    @RabbitHandler
    public void processFanoutMsg(Message message){
        System.out.println(new String(message.getBody()));
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = RabbitMQConfig.FANOUT_QUEUE_NAME1,durable = "true"),
            exchange = @Exchange(value = RabbitMQConfig.TEST_FANOUT_EXCHANGE,type = "fanout"))})
    @RabbitHandler
    public void processFanoutMsg1(Message message){
        System.out.println(new String(message.getBody())+1);
    }

    @RabbitHandler
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = RabbitMQConfig.DIRECT_QUEUE_NAME,durable = "true"),
    exchange = @Exchange(value = RabbitMQConfig.TEST_DIRECT_EXCHANGE,type = "direct"),key = RabbitMQConfig.DIRECT_ROUTINGKEY)
    })
    public void processDirectMsg(Message message){
        System.out.println(new String(message.getBody()));
    }

    @RabbitHandler
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = RabbitMQConfig.DIRECT_QUEUE_NAME,durable = "true"),
            exchange = @Exchange(value = RabbitMQConfig.TEST_DIRECT_EXCHANGE,type = "direct"),key = "123")
    })
    public void processDirectMsg1(Message message){
        System.out.println(new String(message.getBody()));
    }


    @RabbitHandler
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = RabbitMQConfig.TOPIC_QUEUE_NAME,durable = "true"),
    exchange = @Exchange(value = RabbitMQConfig.TEST_TOPIC_EXCHANGE,type = "topic"),key = RabbitMQConfig.TOPIC_ROUTINGKEY)})
    public void processTopicMes(Message message){
        System.out.println(new String(message.getBody())+"topic");
    }

//    @RabbitHandler
//    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = RabbitMQConfig.TOPIC_QUEUE_NAME,durable = "true"),
//    exchange = @Exchange(value = RabbitMQConfig.TEST_TOPIC_EXCHANGE,type = "topic"),key = RabbitMQConfig.TOPIC_ROUTINGKEY))
//    public void processTopicMes1(Message message){
//        System.out.println(new String(message.getBody())+"topic1");
//    }
}
