package com.sixin.api.controller.village;


import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.common.utils.PageData;
import com.sixin.village.domain.*;

import com.sixin.village.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 村务模块的数据统计接口
 *
 * @author 张超
 */

@RestController
@RequestMapping("/api/count")
@CrossOrigin
@Api(value = "村务模块 - 数据统计API类")
public class Vcount {

    @Autowired
    private IPartymemberService partymemberService;
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IForestlandInfoService forestlandInfoService;
    @Autowired
    private IPlowlandInfoService plowlandInfoService;
    @Autowired
    private IAssetAssessmentService assetAssessmentService;
    @Autowired
    private IVillagegroupStatisticsInfoService villagegroupStatisticsInfoService;
    @Autowired
    private IVareaService vareaService;
    @Autowired
    private IVillagerInfoService villagerInfoService;
    /**
        * 党员统计数据api
        * @author 张超 teavamc
        * @date 2019/1/25
        * @param []
        * @return com.sixin.api.domain.RongApiRes
        */
    @PostMapping("/pm")
    @CrossOrigin
    @ApiOperation(value = "按照地区统计党员数据：地区、总数、男性、女性")
    public RongApiRes countpm(String deptId){
        return RongApiService.get_list(partymemberService.countbygroup(deptId));
    }

    /**
        * （快排）按照地区统计党员数据：地区、总数、男性、女性
        * @author 张超 teavamc
        * @date 2019/2/13
        * @param []
        * @return com.sixin.api.domain.RongApiRes
        */
    @PostMapping("/pmSort")
    @CrossOrigin
    @ApiOperation(value = "（快排）按照地区统计党员数据：地区、总数、男性、女性")
    public RongApiRes pmSort(String aid){
        List<Pmcount> pre = partymemberService.countbygroup(aid);
        pmQuickSort(pre,0,pre.size()-1);
        return RongApiService.get_list(pre);
    }

    /**
        * 对List<Pmcount>数据，比较psum属性进行快排
        * @author 张超 teavamc
        * @date 2019/2/13
        * @param [list, start, end]
        * @return void
        */
    public void pmQuickSort(List<Pmcount> list,int start,int end){
        if (start < end){
            int left = start;
            int right = end;
            while (left != right){
                while (list.get(right).getPsum() >= list.get(start).getPsum() && right > left){
                    right--;
                }
                while (list.get(left).getPsum() <= list.get(start).getPsum() && right > left){
                    left++;
                }
                if (right > left){
                    Collections.swap(list,left,right);
                }
            }
            Collections.swap(list,left,start);
            pmQuickSort(list,start,left-1);
            pmQuickSort(list,right+1,end);
        }
    }


    /**
        * 村民统计数据接api
        * @author 张超 teavamc
        * @date 2019/1/25
        * @param []
        * @return com.sixin.api.domain.RongApiRes
        */
    @PostMapping("/m")
    @CrossOrigin
    @ApiOperation(value = "按照地区统计村民数据：地区、总数、男性、女性")
    public RongApiRes countm(String aid){
        return RongApiService.get_list(memberService.countbygroup(aid));
    }

    @GetMapping("/pmBySex")
    @CrossOrigin
    @ApiOperation(value = "性别比例分析")
    public RongApiRes countpmBySex(PersonApi person){
        Mcount res;
        List<String> allaid = vareaService.listNextAid(person.getAid());
        if (allaid.isEmpty()){
            allaid.add(person.getAid());
            person.setListaid(allaid);
            res = villagerInfoService.countpmBySex(person);
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
            res = villagerInfoService.countpmBySex(person);
        }
        return RongApiService.get_bean(res);
    }
    /**
        * （快排）按照地区统计村民数据：地区、总数、男性、女性
        * @author 张超 teavamc
        * @date 2019/2/13
        * @param []
        * @return com.sixin.api.domain.RongApiRes
        */
    @PostMapping("/mSort")
    @CrossOrigin
    @ApiOperation(value = "（快排）按照地区统计村民数据：地区、总数、男性、女性")
    public RongApiRes mSort(String aid){
        List<Mcount> pre = memberService.countbygroup(aid);
        mQuickSort(pre,0,pre.size()-1);
        return RongApiService.get_list(pre);
    }

    /**
        * 对List<Mcount>数据中的msum属性进行快排
        * @author 张超 teavamc
        * @date 2019/2/13
        * @param [list, start, end]
        * @return void
        */
    public void mQuickSort(List<Mcount> list,int start,int end){
        if (start < end){
            int left = start;
            int right = end;
            while (left != right){
                while (list.get(right).getMsum() >= list.get(start).getMsum() && right > left){
                    right--;
                }
                while (list.get(left).getMsum() <= list.get(start).getMsum() && right > left){
                    left++;
                }
                if (right > left){
                    Collections.swap(list,left,right);
                }
            }
            Collections.swap(list,left,start);
            mQuickSort(list,start,left-1);
            mQuickSort(list,right+1,end);
        }
    }

    /**
        * 统计村民和党员总数/男/女
        * @author 张超 teavamc
        * @date 2019/1/30
        * @param []
        * @return com.sixin.api.domain.RongApiRes
        */
    @GetMapping("/p_m")
    @CrossOrigin
    @ApiOperation(value = "统计村民和党员总数/男/女")
    public RongApiRes countp_m(){
        return RongApiService.get_list(memberService.countpm());
    }

    @GetMapping("/g_cPM")
    @CrossOrigin
    @ApiOperation(value = "按照地区类型返回村民和党员的总数/男性/女性")
    public RongApiRes groupByAreaCountPM(){
        return RongApiService.get_list(memberService.groupAreaCountPM());
    }


    @GetMapping("/g_fLand")
    @CrossOrigin
    @ApiOperation(value = "按照地区类型返回林地列表")
    public RongApiRes selectForestlandInfoList(ForestlandInfo forestlandInfo){
        return RongApiService.get_list(forestlandInfoService.selectForestlandInfoList(forestlandInfo));
    }

    @GetMapping("/g_pLand")
    @CrossOrigin
    @ApiOperation(value = "按照地区类型返回耕地列表")
    public RongApiRes selectPlowlandInfoList(PlowlandInfo plowlandInfo){
        return RongApiService.get_list(plowlandInfoService.selectPlowlandInfoList(plowlandInfo));
    }

    @GetMapping("/g_asset")
    @CrossOrigin
    @ApiOperation(value = "按照地区类型返回资产清查")
    public RongApiRes selectAssetAssessmentList(AssetAssessment assetAssessment){
        return RongApiService.get_list(assetAssessmentService.selectAssetAssessmentList(assetAssessment));
    }

    @GetMapping("/g_viiGroup")
    @CrossOrigin
    @ApiOperation(value = "按照地区类型返回村组列表")
    public RongApiRes selectVillagegroupStatisticsInfoList(VillagegroupStatisticsInfo villagegroupStatisticsInfo){
        return RongApiService.get_list(villagegroupStatisticsInfoService.selectVillagegroupStatisticsInfoList(villagegroupStatisticsInfo));
    }

    @GetMapping("/g_edulevel")
    @CrossOrigin
    @ApiOperation(value = "统计村民和党员总数/男/女")
    public RongApiRes countbyedulevel(){
        return RongApiService.get_list(partymemberService.countbyedulevel());
    }
//    报错：类转换错误
//    java.lang.ClassCastException: java.util.HashMap cannot be cast to com.sixin.common.utils.PageData
//    不知为何PageData实现了Map接口，却无法使用override的get(Object key)和getString(Object key)方法
//    本功能的排序暂搁置


//    @GetMapping("/g_cPMSort")
//    @CrossOrigin
//    @ApiOperation(value = "（排序后）按照地区类型返回村民和党员的总数/男性/女性")
//    public RongApiRes groupByAreaCountPMSort(){
//        List<PageData> pre = memberService.groupAreaCountPM();
//        //PageData中的基准key
//        Object key = "msum";
////        gcQuickSort(pre,key,0,pre.size()-1);
//        return RongApiService.get_list(pre);
//    }
//
//    public void gcQuickSort(List<PageData> list,String key,int start,int end){
//        if (start < end){
//            int left = start;
//            int right = end;
//            while (left != right){
//                while (Integer.valueOf((list.get(right)).getString(key)) >= Integer.valueOf((list.get(start)).getString(key)) && left < right){
//                    right--;
//                }
//                while (Integer.valueOf((list.get(left)).getString(key)) <= Integer.valueOf((list.get(start)).getString(key)) && left < right){
//                    left++;
//                }
//                Collections.swap(list,left,right);
//            }
//            Collections.swap(list,left,start);
//            gcQuickSort(list,key,start,left-1);
//            gcQuickSort(list,key,right+1,end);
//        }
//    }

    @GetMapping("/newPre")
    @CrossOrigin
    @ApiOperation(value = "新增人口分析")
    public RongApiRes countbyedulevel(pubObjApi pre){
        Mcount res;
        List<String> allaid = vareaService.listNextAid(pre.getAid());
        if (allaid.isEmpty()){
            allaid.add(pre.getAid());
            pre.setListaid(allaid);
            res = villagerInfoService.countNewPre(pre);
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
            pre.setListaid(allaid);
            res = villagerInfoService.countNewPre(pre);
        }
        return RongApiService.get_bean(res);
    }



    @GetMapping("/lowincome")
    @CrossOrigin
    @ApiOperation(value = "低保户分析")
    public RongApiRes countbylowincome(pubObjApi pre){
        Mcount res;
        List<String> allaid = vareaService.listNextAid(pre.getAid());
        if (allaid.isEmpty()){
            allaid.add(pre.getAid());
            pre.setListaid(allaid);
            res = villagerInfoService.countbylowincome(pre);
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
            pre.setListaid(allaid);
            res = villagerInfoService.countbylowincome(pre);
        }
        return RongApiService.get_bean(res);
    }

    @GetMapping("/demob")
    @CrossOrigin
    @ApiOperation(value = "复原军人比例分析")
    public RongApiRes countbydemob(pubObjApi pre){
        Mcount res;
        List<String> allaid = vareaService.listNextAid(pre.getAid());
        if (allaid.isEmpty()){
            allaid.add(pre.getAid());
            pre.setListaid(allaid);
            res = villagerInfoService.countbydemob(pre);
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
            pre.setListaid(allaid);
            res = villagerInfoService.countbydemob(pre);
        }
        return RongApiService.get_bean(res);
    }

    @GetMapping("/agePart")
    @CrossOrigin
    @ApiOperation(value = "年龄段分析")
    public RongApiRes countbyagePart(PersonApi pre){
        Mcount res;
        List<String> allaid = vareaService.listNextAid(pre.getAid());
        if (allaid.isEmpty()){
            allaid.add(pre.getAid());
            pre.setListaid(allaid);
            res = villagerInfoService.countbyagePart(pre);
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
            pre.setListaid(allaid);
            res = villagerInfoService.countbyagePart(pre);
        }
        return RongApiService.get_bean(res);
    }
}
