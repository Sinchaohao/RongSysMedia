package com.sixin.api.controller.village;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.village.domain.Comment;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.service.IPolicyinfoService;
import com.sixin.village.service.IPoliticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin
@Api(value = "村务模块 - 公告功能API类")
public class Vcomment extends BaseController {

    @Autowired
    private IPolicyinfoService policyinfoService;
    @Autowired
    private IPoliticsService politicsService;

    @GetMapping("/infoAll")
    @CrossOrigin
    @ApiOperation(value = "返回最近五条公告评论信息")
    public RongApiRes searchinfofive(pubObjApi info){
        info.setPageIndex((info.getPageIndex()-1)*info.getPageSize());
        List<Comment> commentlist =policyinfoService.selectinfocommentListlimit(info);
        // 遍历存储回复的评论
        for(Comment comment : commentlist){
            comment.setRecomment(policyinfoService.selectinforecommentList(comment.getCoid()));
        }
        return RongApiService.get_list(commentlist);
    }

    @GetMapping("/infoAllA")
    @CrossOrigin
    @ApiOperation(value = "返回最近五条政策评论信息")
    public RongApiRes searchcommentfive(pubObjApi comm){
        comm.setPageIndex((comm.getPageIndex()-1)*comm.getPageSize());
        List<Comment> commentlist = politicsService.selectpoliticsListlimit(comm);
        // 遍历存储回复的评论
        for(Comment comment : commentlist){
            comment.setRecomment(politicsService.selectpoliticsrecommentList(comment.getCoid()));
        }
        return RongApiService.get_list(commentlist);
    }

    @PostMapping("/insertInfoCM")
    @CrossOrigin
    @ApiOperation(value = "新增公告评论")
    public AjaxResult insertInfoCM(Comment comment){

        System.out.println(comment.getPcid());
        return toAjax(policyinfoService.insertInfoCM(comment));
    }

    @PostMapping("/insertPoliticsCM")
    @CrossOrigin
    @ApiOperation(value = "新增政策评论")
    public AjaxResult insertPoliticsCM(Comment comment){

        System.out.println(comment.getPcid());
        return toAjax(politicsService.insertPoliticsCM(comment));
    }
}
