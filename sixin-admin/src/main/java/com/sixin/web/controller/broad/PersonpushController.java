package com.sixin.web.controller.broad;

import com.sixin.broad.domain.BroadMessage;
import com.sixin.broad.domain.InfoVo;
import com.sixin.broad.service.IMessageService;
import com.sixin.common.utils.PageData;
import com.sixin.framework.web.base.BaseController;
import com.sixin.system.domain.UserMessage;
import com.sixin.system.domain.UserMessageContent;
import com.sixin.system.service.IUserMessageContentService;
import com.sixin.system.service.IUserMessageService;
import lombok.Data;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/broad/personpush")
public class PersonpushController extends BaseController {
    private String prefix = "broad/personpush";
    @Autowired
    private IMessageService messageService;
    @Autowired
    private IUserMessageService userMessageService;
    @Autowired
    private IUserMessageContentService userMessageContentService;
    @RequiresPermissions("broad:personpush:view")
    @GetMapping()
    public String personpush(){
        return prefix + "/personpush";
    }
    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData() {
        List<Map<String, Object>> tree = messageService.selectMessageListUser((new BroadMessage()));
        return tree;
    }
    /**
     * 发送短信
     *
     * @param
     * @return
     */

    @PostMapping(value = "/sendsms")
    @ResponseBody
    public Object addProBroad(@RequestParam(value = "terids") String[] terids,
                              @RequestParam(value = "phones") String[] phones,
                              @RequestParam(value = "content") String[] content,
                              @RequestParam(value = "names") String[] names,
                              @RequestParam(value = "isDev") String[] isDevs) throws Exception {
        String profix = "user_aid_";
        int ln = profix.length();
        List<UserMessage> infoVoList = new ArrayList<>();
        Map<String,String> email = new HashMap<>();
        UserMessageContent content1 = new UserMessageContent();
        content1.setContent(content[0].trim());
        userMessageContentService.insertUserMessageContent(content1);
        for (int i=0;i<isDevs.length;i++){
            if("1".equals(isDevs[i])){
                System.out.print("\n电话: "+phones[i]);
                System.out.print("\t用户编号: "+terids[i].substring(ln));
                System.out.print("\t用户名称: "+names[i]);
                System.out.println("\t发送内容: "+content[0]);
                UserMessage userMessage = new UserMessage();
                userMessage.setNotifier(getUserId());
                userMessage.setReceiver(Long.valueOf(terids[i].substring(ln).trim()));
                userMessage.setOuterid(content1.getId());
                userMessage.setType((long)0);
                if(content[0].length()>10){
                    userMessage.setOuterTitle(content[0].substring(0,10)+"...");
                }else {
                    userMessage.setOuterTitle(content[0]);
                }
                userMessage.setNotifierName(names[i]);
                infoVoList.add(userMessage);
                email.put(phones[i].trim(),content[0]);
            }
        }
        Map<String,Object> map = new HashMap<>();
        if(infoVoList.size()>0){
            // TODO 发送给用户  首先数据库保存一份
            userMessageService.insertUserMessageList(infoVoList);
            // TODO 短信通知给用户 ,
            //... email
            map.put("result", "success");
        }else {
            map.put("result", "无用户");
        }
        return map;
    }
}
