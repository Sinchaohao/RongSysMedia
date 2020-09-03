package com.sixin.web.controller.village;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/layui")
public class LayuiController {

    @RequestMapping("/test")
    public String demo() {
        return "test";
    }

}