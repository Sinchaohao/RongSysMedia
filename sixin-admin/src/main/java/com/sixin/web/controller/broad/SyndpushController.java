package com.sixin.web.controller.broad;

import com.sixin.broad.domain.BroadMessage;
import com.sixin.broad.domain.InfoVo;
import com.sixin.broad.service.IMessageService;
import com.sixin.common.utils.PageData;
import com.sixin.framework.web.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/broad/syndpush")
public class SyndpushController extends BaseController {
    private String prefix = "broad/syndpush";

    @Autowired
    private IMessageService messageService;

    @RequiresPermissions("broad:syndpush:view")
    @GetMapping()
    public String syndpush(){
        return prefix+"/syndpush";
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
        String profix = "dev_aid_";
        int ln = profix.length();
        List<InfoVo> infoVoList = new ArrayList<>();
        for (int i=0;i<isDevs.length;i++){
            if("1".equals(isDevs[i])){
                System.out.print("\n电话: "+phones[i]);
                System.out.print("\t终端IMEI: "+terids[i].substring(ln));
                System.out.print("\t终端名称: "+names[i]);
                System.out.print("\t内容: "+content[0]);
                InfoVo infoVo = new InfoVo(terids[i].substring(ln),names[i],phones[i],content[0]);
                infoVoList.add(infoVo);
            }
        }
        // TODO 发送给终端

        Map<String, String> map = new HashMap<String, String>();
        map.put("result", "success");
        return map;
    }
}
