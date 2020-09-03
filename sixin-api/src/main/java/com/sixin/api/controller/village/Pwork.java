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

/**
 * 村务模块中党员值班功能的接口
 *
 * @author 张超
 */
@RestController
@RequestMapping("/api/pwork")
@CrossOrigin
@Api(value = "村务模块 - 党员值班API类")
public class Pwork extends BaseController {

    @Autowired
    private IPartyworkService partyworkService;
    @Autowired
    private IWorklogService worklogService;
    @Autowired
    private IMemorialService memorialService;
    @Autowired
    private IMeetingService meetingService;
    @Autowired
    private ISuggestService suggestService;
    @Autowired
    private IFileinfoService fileinfoService;
    /**
        * 返回最近五条党员值班信息
        * @author 张超 teavamc
        * @date 2019/1/25
        * @param []
        * @return com.sixin.api.domain.RongApiRes
        */
    @GetMapping("/five")
    @CrossOrigin
    @ApiOperation(value = "返回最近五条党员值班信息")
    public RongApiRes searchfive(){
        return RongApiService.get_list(partyworkService.selectPartyworkListlimit5());
    }

    @GetMapping("/ListWorkLog")
    @CrossOrigin
    @ApiOperation(value = "工作记录列表")
    public RongApiRes selectWorkLog(pubObjApi worklog){
        worklog.setPageIndex((worklog.getPageIndex()-1)*worklog.getPageSize());
        return RongApiService.get_list(worklogService.selectWorklogListByid(worklog));
    }

    @PostMapping("/insertWorkLog")
    @CrossOrigin
    @ApiOperation(value = "新增工作记录")
    public AjaxResult insertWorkLog(Worklog worklog,@RequestParam(value = "files", required = false) MultipartFile[] files,
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
        worklog.setWpic(address);//给project实体的“文件地址”赋值
        return toAjax(worklogService.insertWorklog(worklog));
    }

    @GetMapping("/ListMemorial")
    @CrossOrigin
    @ApiOperation(value = "备忘录列表")
    public RongApiRes selectMemorial(pubObjApi memorial){
        memorial.setPageIndex((memorial.getPageIndex()-1)*memorial.getPageSize());
        return RongApiService.get_list(memorialService.selectMemorialListById(memorial));
    }

    @PostMapping("/insertMemorial")
    @CrossOrigin
    @ApiOperation(value = "新增备忘录")
    public AjaxResult insertMemorial(Memorial memorial,@RequestParam(value = "files", required = false) MultipartFile[] files,
                                     @RequestParam(value = "filename", required = false) String[] fnames,
                                     @RequestParam(value = "flenth" ,required = false)String[] flenth, //时长
                                     @RequestParam(value = "fsize",required = false) String[] fsize  )
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
        memorial.setMpic(address);//给project实体的“文件地址”赋值
        return toAjax(memorialService.insertMemorial(memorial));
    }

    @GetMapping("/ListMeeting")
    @CrossOrigin
    @ApiOperation(value = "会议记录列表")
    public RongApiRes selectMeeting(pubObjApi meet){
        meet.setPageIndex((meet.getPageIndex()-1)*meet.getPageSize());
        return RongApiService.get_list(meetingService.selectMeetingListById(meet));
    }

    @PostMapping("/insertMeeting")
    @CrossOrigin
    @ApiOperation(value = "新增会议记录")
    public AjaxResult insertMeeting(Meeting meeting,@RequestParam(value = "files", required = false) MultipartFile[] files,
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
        meeting.setMpic(address);//给project实体的“文件地址”赋值
        return toAjax(meetingService.insertMeeting(meeting));
    }



    @PostMapping("/insertSuggest")
    @CrossOrigin
    @ApiOperation(value = "新增我的建议")
    public AjaxResult insertSuggest(Suggest suggest,@RequestParam(value = "files", required = false) MultipartFile[] files,
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
        suggest.setMpic(address);//给project实体的“文件地址”赋值
        return toAjax(suggestService.insertSuggest(suggest));
    }

    @GetMapping("/ListSuggest")
    @CrossOrigin
    @ApiOperation(value = "我的建议列表")
    public RongApiRes selectSuggest(pubObjApi meet){
        meet.setPageIndex((meet.getPageIndex()-1)*meet.getPageSize());
        return RongApiService.get_list(suggestService.selectSuggestListById(meet));
    }

    @GetMapping("/ListMyFile")
    @CrossOrigin
    @ApiOperation(value = "我的文件夹列表")
    public RongApiRes selectMyFile(pubObjApi myfile){
        myfile.setPageIndex((myfile.getPageIndex()-1)*myfile.getPageSize());
        return RongApiService.get_list(fileinfoService.selectMyFileListById(myfile));
    }


}
