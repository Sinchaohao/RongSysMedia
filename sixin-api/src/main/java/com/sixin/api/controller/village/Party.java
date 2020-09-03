package com.sixin.api.controller.village;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.utils.DateUtil;
import com.sixin.village.domain.*;
import com.sixin.village.service.*;
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
@RequestMapping("/api/party")
@CrossOrigin
@Api(value = "党建模块 - 党员活动API类")
public class Party extends BaseController {
    @Autowired
    private IHuodongService huodongService;
    @Autowired
    private IPartystudyService partystudyService;
    @Autowired
    private IPartyaffairsService partyaffairsService;
    @Autowired
    private IShishiService shishiService;
    @Autowired
    private IPartynewService partynewService;
    @Autowired
    private IPartyfupinService partyfupinService;
    @Autowired
    private IVareaService vareaService;
    /**
     * 返回所有党员活动信息
     * @author 施景程 teavamc
     * @date 2019/6/8
     * @param []
     * @return com.sixin.api.domain.RongApiRes
     */
    @GetMapping("/hdAll")
    @CrossOrigin
    @ApiOperation(value = "返回所有党员活动信息")
    public RongApiRes searchAll(Huodong huodong)
    {
        return RongApiService.get_list(huodongService.selectHuodongList(huodong));
    }
    @PostMapping("/insertHd")
    @CrossOrigin
    @ApiOperation(value = "新增党员活动信息")
    public AjaxResult insertHd(Huodong huodong,@RequestParam(value = "files", required = false) MultipartFile[] files,
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
        huodong.setHdpic(address);//给project实体的“文件地址”赋值
        return toAjax(huodongService.inserthuodong(huodong));
    }

    @GetMapping("/eduAll")
    @CrossOrigin
    @ApiOperation(value = "返回所有党员学习信息")
    public RongApiRes searchEduAll(Partystudy partystudy)
    {
        return RongApiService.get_list(partystudyService.selectPartystudyList(partystudy));
    }

    @GetMapping("/affairAll")
    @CrossOrigin
    @ApiOperation(value = "返回所有党务公开信息")
    public RongApiRes searchAffairAll(Partyaffairs partyaffairs)
    {
        return RongApiService.get_list(partyaffairsService.selectPartyaffairsList(partyaffairs));
    }

    @GetMapping("/shishiAll")
    @CrossOrigin
    @ApiOperation(value = "返回所有党务公开信息")
    public RongApiRes selectShishiList(Shishi shishi)
    {
        return RongApiService.get_list(shishiService.selectShishiList(shishi));
    }

    @GetMapping("/listAll")
    @CrossOrigin
    @ApiOperation(value = "党建各栏目列表")
    public RongApiRes selectPartyAllById(pubObjApi party)
    {
        party.setPageIndex((party.getPageIndex()-1)*party.getPageSize());
        List<String> allaid = vareaService.listNextAid(party.getAid());
        if (allaid.isEmpty()){
            allaid.add(party.getAid());
            party.setListaid(allaid);
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
            party.setListaid(allaid);

        }
        switch(party.getVtype()){
            case "0":
                return RongApiService.get_list(partynewService.selectpartynewListById(party));
            case "1":
                return RongApiService.get_list(huodongService.selectHuodongListById(party));
            case "2":
                return RongApiService.get_list(partystudyService.selectPartystudyListById(party));
            case "3":
                return RongApiService.get_list(partyfupinService.selectfupinListById(party));
            default:
                return null;
        }
    }

    @PostMapping("/insertPNew")
    @CrossOrigin
    @ApiOperation(value = "新增党员新闻")
    public AjaxResult insertPartyNew(Partynew partynew,@RequestParam(value = "files", required = false) MultipartFile[] files,
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
        partynew.setMpic(address);//给project实体的“文件地址”赋值
        return toAjax(partynewService.insertPartynew(partynew));
    }

    @PostMapping("/insertEducation")
    @CrossOrigin
    @ApiOperation(value = "新增党员学习")
    public AjaxResult insertEducation(Partystudy partystudy,@RequestParam(value = "files", required = false) MultipartFile[] files,
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
        partystudy.setMpic(address);//给project实体的“文件地址”赋值
        return toAjax(partystudyService.insertPartystudy(partystudy));
    }

    @PostMapping("/insertFuPin")
    @CrossOrigin
    @ApiOperation(value = "新增扶贫工作")
    public AjaxResult insertPartyfupin(Partyfupin partyfupin,@RequestParam(value = "files", required = false) MultipartFile[] files,
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
        partyfupin.setMpic(address);//给project实体的“文件地址”赋值
        return toAjax(partyfupinService.insertPartyfupin(partyfupin));
    }


}
