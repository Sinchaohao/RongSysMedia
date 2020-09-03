package com.sixin.web.controller.village;

import com.sixin.framework.web.base.BaseController;
import com.sixin.village.domain.Socialcircle;
import com.sixin.village.domain.SocialcircleComment;
import com.sixin.village.service.SocialcircleCommentService;
import com.sixin.village.service.SocialcircleService;
import com.sixin.village.util.FilterText;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: lyb_RongSys
 * @description: 暮云圈 信息操作处理
 * @author: Mr.Liu
 * @create: 2019-01-17 13:00
 **/
@Controller
@RequestMapping("/village/SocialCircle")
public class SocialcircleController extends BaseController {

    private String prefix = "village/socialCircle";

    @Autowired
    private SocialcircleService socialCircleService;
    @Autowired
    private SocialcircleCommentService socialCircleCommentService;

    @RequiresPermissions("village:socialCircle:view")
    @RequestMapping()
    public String list(ModelMap mmap){
        int index = 4;
        List<Socialcircle> list = socialCircleService.selectSocialCircleList(0,index);
        List<Socialcircle> list1 = socialCircleService.selectSocialCircleList_Heat(0,index);

        for (int i =0 ;i<index;i++){
            list.get(i).setChild(socialCircleCommentService.selectAllByPcid(list.get(i).getPcid()));
            list1.get(i).setChild(socialCircleCommentService.selectAllByPcid(list1.get(i).getPcid()));
        }
        mmap.put("socialCircleOne",list);
        mmap.put("index",index);
        mmap.put("socialCircleTwo",list1);
        return prefix+"/socialCircle";
    }

    @RequestMapping("/addindex")
    @ResponseBody
    public Map<String,Object> Ajax_list(ModelMap mmp, @PathParam("index") int index){
        int in = index + 1;
        List<Socialcircle> list = socialCircleService.selectSocialCircleList(in,2);
        List<Socialcircle> list1 = socialCircleService.selectSocialCircleList_Heat(in,2);
        for (int i =0 ;i<2;i++){
            list.get(i).setChild(socialCircleCommentService.selectAllByPcid(list.get(i).getPcid()));
            list1.get(i).setChild(socialCircleCommentService.selectAllByPcid(list1.get(i).getPcid()));
        }
        System.out.println(in);
        mmp.put("index",in);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("socialCircleOne2",list);
        map.put("socialCircleTwo2",list1);
        map.put("index",in);
        return map;
    }

    //@RequiresPermissions("village:socialCircle:list")
    @PostMapping("/list")
    @ResponseBody
    public Map<String, Object> List(String pcid){
        Map<String,Object> map = new HashMap<String, Object>();
        String[] list = pcid.split(",");
        for(int i=0;i<list.length;i++){
            List<SocialcircleComment> scc = socialCircleCommentService.selectAllByPcid(Integer.valueOf(list[i]));
            map.put(list[i],scc);
        }


        return map;
    }
}
