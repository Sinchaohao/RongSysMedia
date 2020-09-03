package com.sixin.web.controller.broad;

import com.sixin.broad.domain.HigherAnn;
import com.sixin.broad.service.IHigherAnnService;
import com.sixin.common.page.TableDataInfo;
import com.sixin.framework.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ASUS on 2019/8/19.
 *
 * 上级公告 控制层
 * @author cx
 *
 */
@Controller
@RequestMapping("/broad/higherann")
public class HigherAnnController extends BaseController {


    private String prefix = "broad/higherann";

    @Autowired
    private IHigherAnnService iHigherAnnService;

    @GetMapping()
    private String high(){return prefix + "/higherann";}

    /**
     * 查询上级公告列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(HigherAnn higherAnn){
        startPage();
        List<HigherAnn> list = iHigherAnnService.selectHigherAnnList(higherAnn);
        return getDataTable(list);
    }

}
