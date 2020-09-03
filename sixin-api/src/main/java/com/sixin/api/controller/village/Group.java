package com.sixin.api.controller.village;


import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;


import com.sixin.village.domain.Partymember;
import com.sixin.village.domain.PersonApi;
import com.sixin.village.domain.VillagerInfo;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.service.IPartymemberService;
import com.sixin.village.service.IVareaService;
import com.sixin.village.service.IVillagerInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/group")
@CrossOrigin
@Api(value = "党建模块 - 党员小组API类")
public class Group {
    @Autowired
    private IVillagerInfoService villagerInfoService;
    @Autowired
    private IVareaService vareaService;

    @GetMapping("/all")
    @CrossOrigin
    @ApiOperation(value = "党员信息")
    public RongApiRes searchAll(PersonApi group)
    {
        group.setPageIndex((group.getPageIndex()-1)*group.getPageSize());
        return RongApiService.get_list(villagerInfoService.selectvillagerInfoListBytype(group));
    }

    @GetMapping("/grouplist")
    @CrossOrigin
    @ApiOperation(value = "党员小组")
    public RongApiRes selectGrouplist(PersonApi group)
    {
        group.setPageIndex((group.getPageIndex()-1)*group.getPageSize());
        List<VillagerInfo> res;
        List<String> allaid = vareaService.listNextAid(group.getAid());
        if (allaid.isEmpty()){
            allaid.add(group.getAid());
            group.setListaid(allaid);
            res = villagerInfoService.selectGrouplist(group);
        }else {
            //获得所有的子 aid 放入 list
            List<String> temp;
            temp = vareaService.listNextAid(allaid.get(0));
            for (int i = 1; i < allaid.size(); i++){
                List<String> l = vareaService.listNextAid(allaid.get(i));
                if (!l.isEmpty()){
                    temp.addAll(l);
                }
            }
            allaid.addAll(temp);
            // 遍历所有的 aid 信息然后装入结果
            group.setListaid(allaid);
            res =villagerInfoService.selectGrouplist(group);
        }
        return RongApiService.get_list(res);
    }


}
