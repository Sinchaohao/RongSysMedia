package com.sixin.api.controller.village;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.village.domain.Partymember;
import com.sixin.village.domain.PersonApi;
import com.sixin.village.domain.VillagerInfo;
import com.sixin.village.service.IPersonalDetailService;
import com.sixin.village.service.IProjectService;
import com.sixin.village.service.IVareaService;
import com.sixin.village.service.IVillagerInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@CrossOrigin
@Api(value = "个人信息API类")
public class Person {
    @Autowired
    private IPersonalDetailService personalDetailService;
    @Autowired
    private IVillagerInfoService villagerInfoService;
    @Autowired
    private IVareaService vareaService;

    @GetMapping("/all")
    @CrossOrigin
    @ApiOperation(value = "查询个人详细信息")
    public RongApiRes searchPerson(Integer pdid)
    {
        return RongApiService.get_bean(personalDetailService.selectPersonalDetailById(pdid));
    }

    @PostMapping("/login")
    @CrossOrigin
    @ApiOperation(value = "查询登录用户")//@RequestParam用来接受前台传回的数据
    public RongApiRes getLogin(@RequestParam("idcard") String idcard,@RequestParam("password") String password){
        return RongApiService.get_bean(villagerInfoService.findByIdcardAndPassword(idcard, password));
        // 调用service层的方法并返回获取的数据
    }

    @GetMapping("/perAllInfo")
    @CrossOrigin
    @ApiOperation(value = "查询个人详细信息")
    public RongApiRes selectPersonAll(PersonApi person)
    {
        person.setPageIndex((person.getPageIndex()-1)*person.getPageSize());
        List<VillagerInfo> res;
        List<String> allaid = vareaService.listNextAid(person.getAid());
        if (allaid.isEmpty()){
            allaid.add(person.getAid());
            person.setListaid(allaid);
            res = villagerInfoService.selectPersonAllByApi(person);
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
            person.setListaid(allaid);
            res = villagerInfoService.selectPersonAllByApi(person);
        }
        return RongApiService.get_list(res);
    }

    @GetMapping("/cadreinfo")
    @CrossOrigin
    @ApiOperation(value = "村工作人员列表")
    public RongApiRes selectbycadre(PersonApi person){
        person.setPageIndex((person.getPageIndex()-1)*person.getPageSize());
        List<VillagerInfo> res;
        List<String> allaid = vareaService.listNextAid(person.getAid());
        if (allaid.isEmpty()){
            allaid.add(person.getAid());
            person.setListaid(allaid);
            res = villagerInfoService.selectbycadre(person);
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
            person.setListaid(allaid);
            res = villagerInfoService.selectbycadre(person);
        }
        return RongApiService.get_list(res);
    }

}
