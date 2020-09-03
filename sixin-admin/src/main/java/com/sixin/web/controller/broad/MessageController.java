package com.sixin.web.controller.broad;

import com.sixin.broad.domain.BroadMessage;
import com.sixin.broad.domain.MessageExample;
import com.sixin.broad.service.IMessageService;
import com.sixin.broad.service.IMsmUsersService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信播出端
 *
 * @author 周博
 * @date 2019-03-28
 */

@Controller
@RequestMapping("/broad/message")
public class MessageController extends BaseController {
    private String prefix = "broad/message";
    @Autowired
    private IMessageService messageService;
    @Autowired
    private IMsmUsersService usersService;

    @GetMapping()
    public String sms() {
        return prefix + "/message";
    }

    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData() {
        List<Map<String, Object>> tree = messageService.selectMessageList((new BroadMessage()));
        return tree;
    }

    @GetMapping("/example")
    @ResponseBody
    public List<MessageExample> selectMessageExampleList() {
        return messageService.selectMessageExampleList();
    }

    /**
     * 去发送短信
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/toSendSms")
    public List<Map<String, Object>> toSendSms() throws Exception {
        return null;
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
                              @RequestParam(value = "smstype") String[] smstype,
                              @RequestParam(value = "content") String[] content,
                              @RequestParam(value = "names") String[] names) throws Exception {
        System.out.print("\nphones: ");
        for (String i : phones)
            System.out.print(i + " ");

        System.out.print("\nterids: ");
        for (String i : terids)
            System.out.print(i + " ");

        System.out.print("\nnames: ");
        for (String i : names)
            System.out.print(i + " ");

        System.out.print("\nsmstype: ");
        for (String i : smstype)
            System.out.print(i + " ");

        System.out.print("\ncontent: ");
        for (String i : content)
            System.out.print(i + " ");
        System.out.println();

        Map<String, String> map = new HashMap<String, String>();

        List<String> phonelist = new ArrayList<String>();
        List<PageData> pdlist = new ArrayList<PageData>();

        /*for (int i = 0; i < terids.length; i++) {
            if (phones.length > i && (phones[i] != null || !"".equals(phones[i]))) {
                PageData tmpd = new PageData();
                phonelist.add(phones[i]);
                tmpd.put("sendtime", DateUtil.getTime());
                tmpd.put("scontent", content);
                tmpd.put("remark", smstype);
                tmpd.put("issend", 0);
                tmpd.put("tid", terids[i]);
                tmpd.put("smobile", phones[i]);
                pdlist.add(tmpd);
            }
        }
        //添加发送短信表
        messageService.addSendMessage(pdlist);
//		SendSms.sendSmsAll(phonelist, content);*/

        map.put("result", "success");
        return map;
    }

}
