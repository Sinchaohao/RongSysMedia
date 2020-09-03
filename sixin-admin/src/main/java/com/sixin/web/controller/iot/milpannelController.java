package com.sixin.web.controller.iot;

import com.sixin.framework.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/iot/milpannel")
public class milpannelController extends BaseController {
    private String prefix ="iot/milpannel";

    @GetMapping()
    public String milpannel() {
        return prefix + "/bMilpannel";
    }
}
