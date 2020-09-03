package com.sixin.api.controller.village;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.utils.DateUtil;
import com.sixin.village.domain.*;
import com.sixin.village.service.IHouseMemberInfoService;
import com.sixin.village.service.ILinkService;
import com.sixin.village.service.IVareaService;
import com.sixin.village.service.IVillageplanService;
import com.sixin.village.util.bFileUtil1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/beaCountry")
@CrossOrigin
@Api(value = "村务模块 - 美丽乡村API类")
public class BeaCountry extends BaseController {
    @Autowired
    private IVillageplanService villageplanService;
    @Autowired
    private ILinkService linkService;
    @Autowired
    private IVareaService vareaService;

    @GetMapping("/ListBeaCountry")
    @CrossOrigin
    @ApiOperation(value = "美丽乡村列表")
    public RongApiRes selectBeaCountry(pubObjApi beavill){
        beavill.setPageIndex((beavill.getPageIndex()-1)*beavill.getPageSize());
        List<Villageplan> res;
        List<String> allaid = vareaService.listNextAid(beavill.getAid());
        if (allaid.isEmpty()){
            allaid.add(beavill.getAid());
            beavill.setListaid(allaid);
            res = villageplanService.selectBeaCountryListByid(beavill);
        }else {
            //获得所有的子 aid 放入 list
            List<String> temp;
            temp = vareaService.listNextAid(allaid.get(0));
            for(int i = 1; i < allaid.size(); i++){
                List<String> l = vareaService.listNextAid(allaid.get(i));
                if (!l.isEmpty()){
                    temp.addAll(l);
                }
            }
            allaid.addAll(temp);
            // 遍历所有的 aid 信息然后装入结果
            beavill.setListaid(allaid);
            res = villageplanService.selectBeaCountryListByid(beavill);
        }
        return RongApiService.get_list(res);
    }

    @PostMapping("/insertBeaCountry")
    @CrossOrigin
    @ApiOperation(value = "新增美丽乡村")
    public AjaxResult insertBeaCountry(Villageplan villageplan,@RequestParam(value = "files", required = false) MultipartFile[] files,
                                       @RequestParam(value = "filename", required = false) String[] fnames,
                                       @RequestParam(value = "flenth" ,required = false)String[] flenth, //时长
                                       @RequestParam(value = "fsize",required = false) String[] fsize )
    {
        String year = DateUtil.getYear();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        System.out.println(dateFormat.format(date));
        String maxfileid = dateFormat.format(date); //获取文件上传时的时间参数字符串作为文件名
        String address="";
        //判断file数组不能为空并且长度大于0
        if (files != null && files.length > 0) {
            //循环获取file数组中得文件
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                try{
                    //保存图片
                    Files g = bFileUtil1.uplodeFile(maxfileid, file, fnames[i], flenth[i], fsize[i], year);
                    address +=g.getAddress()+";";

                } catch (Exception e) {
                    //return "上传图片失败";
                    System.out.println("失败");
                    return toAjax(0);
                }

            }
        }
        villageplan.setMpic(address);//给project实体的“文件地址”赋值
        return toAjax(villageplanService.insertVillageplan(villageplan));
    }
    @GetMapping("/Link")
    @CrossOrigin
    @ApiOperation(value = "链接列表")
    public RongApiRes selectLinkList(Link link){
        return RongApiService.get_list(linkService.selectLinkList(link));
    }
}
