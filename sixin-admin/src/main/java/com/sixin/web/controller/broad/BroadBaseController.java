package com.sixin.web.controller.broad;

import com.sixin.framework.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BroadBaseController extends BaseController {

    @GetMapping("/broad/todo")
    public String todo()
    {
        return "broad/todo";
    }

}
