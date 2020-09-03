package com.sixin.api.controller.broad;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.broad.domain.Tersga;
import com.sixin.broad.service.IMaintainService;
import com.sixin.broad.service.IManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author 张超 teavamc
 * @Description:应急广播设备的统一RESTful API出入口
 * @ClassName Device
 * @date 2019/1/25 19:36
 **/
@RestController
@RequestMapping("/api/device")
@CrossOrigin
@Api(value = "应急广播模块 - 终端设备API类")
public class Device {

    @Autowired
    private IMaintainService maintainService;
    @Autowired
    private IManagementService managementService;

    /**
        * 返回按照创建时间倒序的十条带有经纬度的维修设备记录
        * @author 张超 teavamc
        * @date 2019/1/25
        * @param []
        * @return com.sixin.api.domain.RongApiRes
        */
    @CrossOrigin
    @GetMapping("/mlal")
    @ApiOperation(value = "按照创建时间倒序的十条带有经纬度的维修设备记录")
    public RongApiRes maintianx(){
        return RongApiService.get_list(maintainService.selectlal());
    }

    /**
        * 按照终端地址进行运行状态的分组统计
        * @author 张超 teavamc
        * @date 2019/1/25
        * @param []
        * @return com.sixin.api.domain.RongApiRes
        */
    @CrossOrigin
    @GetMapping("/tersga")
    @ApiOperation(value = "按照终端地址进行运行状态的分组统计：总数，运行数，停止数")
    public RongApiRes tersgabyarea(){
        return RongApiService.get_list(managementService.selecttersga());
    }

    /**
     * 回传目前设备挂载总数,运行数，停止数
     * @author 张超 teavamc
     * @date 2019/1/25
     * @param []
     * @return com.sixin.api.domain.RongApiRes
     */
    @CrossOrigin
    @GetMapping("/sumters")
    @ApiOperation(value = "回传目前设备挂载总数,运行数，停止数")
    public RongApiRes sumters(){
        return RongApiService.get_bean(managementService.sumters());
    }

    /**
        * 按照终端地址进行运行状态的分组统计,包括分组地区、挂在数量、运行数量、停止数量、维修数量
        * @author 张超 teavamc
        * @date 2019/1/25
        * @return com.sixin.api.domain.RongApiRes
        */
    @CrossOrigin
    @GetMapping("/sumterm")
    @ApiOperation(value = "按照终端地址进行运行状态的分组统计,包括分组地区、挂在数量、运行数量、停止数量、维修数量")
    public RongApiRes sumterm(){
        List pre = managementService.sumterm();
        RongApiRes test = RongApiService.get_list(pre);
        return test;
    }


    /**
        * (排序后）按照终端地址进行运行状态的分组统计,包括分组地区、挂在数量、运行数量、停止数量、维修数量
        * @author 张超 teavamc
        * @date 2019/2/13
        * @param []
        * @return com.sixin.api.domain.RongApiRes
        */
    @CrossOrigin
    @GetMapping("/sumtermSort")
    @ApiOperation(value = "(排序后）按照终端地址进行运行状态的分组统计,包括分组地区、挂在数量、运行数量、停止数量、维修数量")
    public RongApiRes sumtermSort(){
        List<Tersga> pre = managementService.sumterm();
        sumtermQuickSort(pre,0,pre.size()-1);
        return RongApiService.get_list(pre);
    }


    /**
        * 对List<Tersga>数据结构按照Tersga的Sum熟悉进行排序
        * @author 张超 teavamc
        * @date 2019/2/13
        * @param [list, start, end]
        * @return void
        */
    public void sumtermQuickSort(List<Tersga> list,int start,int end){
//        1、确定起始下标，默认Index=0处为基准值
//        2、若左右下标未相遇，右侧下标向左走，若遇到小于基准值的则停止；左侧下标向右走，若遇到大于基准值的则停止
//        3、交换左右下标的值，确保第一次左右下标相遇时，左侧值均小于等于基准值，右侧值均大于等于基准值
//        4、左右下标若相遇，交换基准值与其位置的值，完成第一次相遇，再使用分治递归处理左右两部分
        if (start < end){
            int left = start;
            int right = end;
            while (left != right){
                while (list.get(right).getSum() >= list.get(start).getSum() && right > left){
                    right--;
                }
                while (list.get(left).getSum() <= list.get(start).getSum() && right > left){
                    left++;
                }
                if (right > left){
                    Collections.swap(list,left,right);
                }
            }
            Collections.swap(list,left,start);
            sumtermQuickSort(list,start,left-1);
            sumtermQuickSort(list,right+1,end);
        }
    }

}
