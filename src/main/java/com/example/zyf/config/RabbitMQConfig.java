/**
 * @projectName ZYF
 * @package com.example.zyf.config
 * @className com.example.zyf.config.RabbitMQConfig
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

/**
 * RabbitMQConfig
 * @description
 * @author zyf
 * @date 2021/1/4 16:26
 * @version 1.0
 */
public class RabbitMQConfig {
    //队列名
    public static final String FANOUT_QUEUE_NAME = "test_fanout_queue";
    public static final String FANOUT_QUEUE_NAME1 = "test_fanout_queue1";
    public static final String TEST_FANOUT_EXCHANGE = "testFanoutExchange";

    public static final String DIRECT_QUEUE_NAME = "test_direct_queue";
    public static final String TEST_DIRECT_EXCHANGE = "testDirectExchange";
    public static final String DIRECT_ROUTINGKEY = "test";

    public static final String TOPIC_QUEUE_NAME = "test_topic_queue";
    public static final String TEST_TOPIC_EXCHANGE = "testTopicExchange";
    public static final String TOPIC_ROUTINGKEY = "test.*";

    //创建队列
    public Queue createFanoutQueue() {
        return new Queue(FANOUT_QUEUE_NAME);
    }

    //创建队列
    public Queue createFanoutQueue1() {
        return new Queue(FANOUT_QUEUE_NAME1);
    }

    //创建队列
    public Queue createDirectQueue() {
        return new Queue(DIRECT_QUEUE_NAME);
    }

    //创建队列
    public Queue createTopicQueue() {
        return new Queue(TOPIC_QUEUE_NAME);
    }
    //创建交换机
    public FanoutExchange defFanoutExchange() {
        return new FanoutExchange(TEST_FANOUT_EXCHANGE);
    }

    //队列与交换机进行绑定
    @Bean
    Binding bindingFanout() {
        return BindingBuilder.bind(createFanoutQueue()).
                to(defFanoutExchange());
    }

    //队列与交换机进行绑定
    @Bean
    Binding bindingFanout1() {
        return BindingBuilder.bind(createFanoutQueue1()).
                to(defFanoutExchange());
    }

    DirectExchange directExchange(){
        return new DirectExchange(TEST_DIRECT_EXCHANGE);
    }

    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(createDirectQueue()).
                to(directExchange()).
                with(DIRECT_ROUTINGKEY);
    }

    TopicExchange defTopicExchange(){
        return new TopicExchange(TEST_TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingTopic() {
        return BindingBuilder.bind(createTopicQueue()).
                to(defTopicExchange()).
                with(TOPIC_ROUTINGKEY);
    }
}
