package com.sixin.api.controller.village;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.broad.domain.LedList;
import com.sixin.broad.domain.Organization;
import com.sixin.broad.domain.ProSinmanage;
import com.sixin.broad.domain.Sendmessages;
import com.sixin.broad.service.*;
import com.sixin.village.domain.CwProList;
import com.sixin.village.domain.PersonApi;
import com.sixin.village.domain.Wxnews;
import com.sixin.village.service.IWxnewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 村务宝典接口 控制层
 * 信息发布
 * @author hfz
 * @date 2019-11-29
 */
@RestController
@RequestMapping("/api/infopublish")
@CrossOrigin
@Api(value = "村务宝典 - 信息发布API类")
public class Infopublish {
    @Autowired
    private IOrganizationService organizationService;
    @Autowired
    private IProSinmanageService proSinmanageService;
    @Autowired
    private IProgramService programService;
    @Autowired
    private IProChamanageService proChamanageService;
    @Autowired
    private IProListService proListService;
    @Autowired
    private ISendmessagesService sendmessagesService;
    @Autowired
    private ILedshowService ledshowService;
    @Autowired
    private ILedListService ledlistService;
    @Autowired
    private IWxnewsService wxnewsService;

    @GetMapping("/terminalslist")
    @CrossOrigin
    @ApiOperation(value = "根据aid分类的应急广播终端列表")
    public RongApiRes searchterminalslist(String aid)
    {
        return RongApiService.get_list(organizationService.selectByaid(aid));
    }

    @GetMapping("/terminalslistbytid")
    @CrossOrigin
    @ApiOperation(value = "流媒体直播---选择直播终端")
    public RongApiRes searchterminalslistbytid(String tids , String userid)
    {
        return RongApiService.get_list(organizationService.selecttidBytwo(tids,userid));
    }

    @GetMapping("/prosinmanagelist")
    @CrossOrigin
    @ApiOperation(value = "查询节目单播出列表")
    public RongApiRes searchprosinmanagelist(ProSinmanage proSinmanage)
    {
        return RongApiService.get_list(proSinmanageService.selectProSinmanageList1(proSinmanage));
    }

//    @GetMapping("/programslist")
//    @CrossOrigin
//    @ApiOperation(value = "获取广播MP3文件列表")
//    public RongApiRes selectprogramslist(Program program) {
//        program.setPageIndex((program.getPageIndex()-1)*program.getPageSize());
//        return RongApiService.get_list(programService.selectProList(program));
//    }

    @GetMapping("/prochamanagelist")
    @CrossOrigin
    @ApiOperation(value = "获取电台列表")
    public RongApiRes selectprochamanagelist(PersonApi proChamanage) {
        proChamanage.setPageIndex((proChamanage.getPageIndex()-1)*proChamanage.getPageSize());
        return RongApiService.get_list(proChamanageService.selectProChamanageList1(proChamanage));
    }

    @GetMapping("/prolist")
    @CrossOrigin
    @ApiOperation(value = "编辑节目单播出")
    public RongApiRes selectprolistlist(CwProList proList)
    {
        return RongApiService.get_list(proListService.selectProListByAll(proList));
    }

    @GetMapping("/sendmessageslist")
    @CrossOrigin
    @ApiOperation(value = "获取短信转语音记录列表")
    public RongApiRes selectsendmessageslist(PersonApi sendmessages) {
        sendmessages.setPageIndex((sendmessages.getPageIndex()-1)*sendmessages.getPageSize());
        return RongApiService.get_list(sendmessagesService.selectSendmessagesList1(sendmessages));
    }

    @GetMapping("/sendmessageslistBytid")
    @CrossOrigin
    @ApiOperation(value = "获取短信转语音播出列表")
    public RongApiRes selectsendmessageslistBytid(Sendmessages sendmessages) {
        return RongApiService.get_list(sendmessagesService.selectSendmessagesBytid(sendmessages));
    }

    @GetMapping("/terminalsledlist")
    @CrossOrigin
    @ApiOperation(value = "获取led终端列表")
    public RongApiRes selectterminalsledlist(Organization organization)
    {
        return RongApiService.get_list(organizationService.selectByLedUserid(organization));
    }

    @GetMapping("/ledshowlist")
    @CrossOrigin
    @ApiOperation(value = "获取led显示屏播出记录列表")
    public RongApiRes selectledshowlist(PersonApi ledshow)
    {
        return RongApiService.get_list(ledshowService.selectLedshowList1(ledshow));
    }

    @GetMapping("/ledlist")
    @CrossOrigin
    @ApiOperation(value = "多参数获取led显示屏播出记录")
    public RongApiRes selectLedList(LedList ledlist)
    {
        return RongApiService.get_list(ledlistService.selectLedList(ledlist));
    }

    @GetMapping("/emergencyprogram")
    @CrossOrigin
    @ApiOperation(value = "紧急节目播出单查询")
    public RongApiRes selectemergencyprogramist(Long userid)
    {
        return RongApiService.get_list(proSinmanageService.selectProSinmanageListForWarning(userid));
    }

    @GetMapping("/wxnews")
    @CrossOrigin
    @ApiOperation(value = "获取微信公众号新闻列表")
    public RongApiRes selectwxnewslist(Wxnews wxnews)
    {
        return RongApiService.get_list(wxnewsService.selectWxnewslist(wxnews));
    }

    @GetMapping("/wxnewsall")
    @CrossOrigin
    @ApiOperation(value = "微信公众号新闻发布列表")
    public RongApiRes selectwxnewsByAll(Wxnews wxnews)
    {
        return RongApiService.get_list(wxnewsService.selectWxnewsByAll(wxnews));
    }
}
