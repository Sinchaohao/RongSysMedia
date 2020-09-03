package com.sixin.web.controller.broad;

import com.sixin.framework.util.ShiroUtils;
import com.sixin.system.domain.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sixin.framework.web.base.BaseController;
import javax.servlet.http.HttpSession;


/**
 * 聊天室
 *
 * @author 周博
 * @date 2019-04-27
 */
@Controller
@RequestMapping("/broad/chat")
public class ChatController extends BaseController {
    private String prefix = "broad/chat";

    @GetMapping()
    public String chat(HttpSession session) {
        SysUser user = ShiroUtils.getSysUser(); //从session中获取当前登陆用户的userid
        session.setAttribute("userName", user.getUserName());
        return prefix + "/chat";
    }
}
