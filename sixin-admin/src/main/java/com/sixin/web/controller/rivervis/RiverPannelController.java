package com.sixin.web.controller.rivervis;

import com.sixin.framework.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 张超 teavamc
 * @Description:TODO
 * @ClassName RiverPannelController
 * @date 2019/3/27 16:41
 **/
@Controller
@RequestMapping("/river/pannel")
public class RiverPannelController extends BaseController {
    private String prefix = "rivervis/pannel";

    @GetMapping()
    public String pannel() {
        return prefix + "/pannel";
    }
}
