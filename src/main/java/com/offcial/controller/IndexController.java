package com.offcial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页
 *
 * @author Zza
 * @date 2017-11-29 15:36
 */
@Controller
@RequestMapping("/")
public class IndexController {

    //首页 xmlns:th="http://www.thymeleaf.org"
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    //往期微信推送
    @RequestMapping("/WeChatHistory")
    public String WeChatHistory() {
        return "WeChatHistory";
    }

    //web
    @RequestMapping("/web")
    public String web() {
        return "web";
    }

}
