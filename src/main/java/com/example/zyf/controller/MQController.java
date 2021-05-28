/**
 * @projectName ZYF
 * @package com.example.zyf.controller
 * @className com.example.zyf.controller.MQController
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zyf.util.RabbitMQ.Producter;

/**
 * MQController
 * @description
 * @author zyf
 * @date 2020/12/31 10:06
 * @version 1.0
 */
@RestController
public class MQController {
    @Resource
    private Producter producter;
    @GetMapping("producer")
    public void producer(){
        producter.produce();
        producter.send2DirectTestQueue("DirectMessage");
        producter.send2TopicTestAQueue("TopicAMessage");
        producter.send2TopicTestBQueue("TopicBMessage");
    }

}
