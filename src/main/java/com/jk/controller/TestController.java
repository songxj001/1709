package com.jk.controller;

import com.jk.model.UserBean;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@RestController
public class TestController {

    @RequestMapping("login")
    public void test(HttpServletRequest request) throws IOException, InterruptedException {
        HttpSession session = request.getSession();
        UserBean userBean = new UserBean();
        userBean.setName("张三");
        userBean.setPassword("admin");
        String sessionid = session.getId();
        session.setAttribute(sessionid,userBean);
        System.out.println("存储的sessionid="+sessionid);
    }

    @Value("${server.port}")
    private String port;
    @GetMapping("getLogin")
    public String getLogin(HttpServletRequest request) throws IOException, InterruptedException {

        HttpSession session = request.getSession(false);
        String sessionId = session.getId();
        System.out.println("取出时用的sessionid="+sessionId);
        Object attribute = session.getAttribute(sessionId);
        System.out.println(attribute);
        if(attribute == null){
            return "false="+port;
        }else{
            return "true="+port;
        }
    }
}
