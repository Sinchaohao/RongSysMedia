package com.sixin.web.controller.broad;

import com.sixin.broad.domain.ProApplyUser;
import com.sixin.broad.domain.ProListen;
import com.sixin.broad.service.IProListenService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author cx
 * @Description: 节目审听
 * @ClassName ProListenController
 *
 **/
@Controller
@RequestMapping(value="/broad/prolisten")
public class ProListenController extends BaseController {
    private String prefix = "broad/prolisten";

    @Autowired
    private IProListenService iProListenService;

    @GetMapping()
    @RequiresPermissions("broad:prolisten:view")
    public String proreApply()
    {
        return prefix + "/prolisten";
    }


    @RequiresPermissions("broad:prolisten:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProListen proListen){
        startPage();
        proListen.setUserid(getUserId().toString());
        List<ProListen> list = iProListenService.selectProListenList(proListen);
        return getDataTable(list);
    }

    @GetMapping("/play")
    @RequiresPermissions("broad:prolisten:view")
    public String proreApplyplay() {
        return prefix + "/play";
    }


    @GetMapping("/pass/{paid}")
    @RequiresPermissions("broad:prolisten:view")
    @ResponseBody
    public AjaxResult checkpass(@PathVariable("paid") String paid) {
        return toAjax(iProListenService.checkpass(paid));
    }

    @GetMapping("/nopassreason")
    @RequiresPermissions("broad:prolisten:view")
    public String nopassreason() {
        return prefix + "/nopassreason";
    }

    @PostMapping("/nopassreason")
    @RequiresPermissions("broad:prolisten:view")
    @ResponseBody
    public AjaxResult nopassreason(ProListen proListen) {
        return toAjax(iProListenService.updateProListen(proListen));
    }
}
