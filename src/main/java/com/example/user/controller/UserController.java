package com.example.user.controller;

import com.example.user.pojo.UserPo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class UserController {
    //日志
    private Logger log = (Logger) Logger.getLogger(String.valueOf(this.getClass()));

//    服务发现客户端
    @Autowired
    private DiscoveryClient discoveryClient=null;

//    获取用户信息
    @GetMapping("/user/{id}")
    public UserPo getUserPo(@PathVariable("id") Long id){
        ServiceInstance serviceInstance=discoveryClient.getInstances("USER").get(0);
        log.info("["+serviceInstance.getServiceId()+"]:"+serviceInstance.getHost()+":"+serviceInstance.getPort());
        UserPo userPo=new UserPo();
        userPo.setId(id);
        int level= (int) (id%3+1);
        userPo.setLevel(level);
        userPo.setUsername("username_"+id);
        userPo.setNote("note_"+id);
        return userPo;
    }

}
