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

    //返回首页
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
