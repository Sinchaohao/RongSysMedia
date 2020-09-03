package com.sixin.web.controller.broad;

import com.sixin.broad.domain.Manage;
import com.sixin.broad.service.IManageService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.page.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author cx
 * @Time 2019/8/20 16:24
 * @Description 管理参数 控制层
 */
@Controller
@RequestMapping("/broad/manage")
public class ManageController extends BaseController {

    private String prefix = "broad/manage";

    @Autowired
    private IManageService iManageService;

    @GetMapping()
    @RequiresPermissions("broad:manage:view")
    public String manage()
    {
        return prefix + "/manage";
    }

    @PostMapping("/list")
    @RequiresPermissions("broad:manage:list")
    @ResponseBody
    public TableDataInfo list(Manage manage)
    {
        startPage();
        List<Manage> list = iManageService.selectManagelist(manage);
        return getDataTable(list);
    }
}
