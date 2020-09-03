package com.sixin.web.controller.broad;

import com.sixin.broad.domain.BroadCount;
import com.sixin.broad.service.IBcountService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 公共节目单
 *
 * @author 周博
 * @date 2019-03-20
 */
@Controller
@RequestMapping("/broad/pannel")
public class BroadPannelController extends BaseController {
    private String prefix = "broad/pannel";

    @Autowired
    private IBcountService bcountService;

    @GetMapping()
    public String pannel() {
        return prefix + "/bPannel";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BroadCount broadCount) {
        startPage();
        List<BroadCount> list = bcountService.select(broadCount);
        return getDataTable(list);
    }
}
