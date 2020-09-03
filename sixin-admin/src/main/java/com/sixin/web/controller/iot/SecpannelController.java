package com.sixin.web.controller.iot;/* *
 * @Description:
 * @param $params$
 * @Return: $returns$
 * @开发人员：余新伟
 * @开发单位：湖南农业大学物联网工程专业
 * @Date: 2019/12/19 20:38
 * @开发版本：综合练习V0.1
 */


import com.sixin.common.page.TableDataInfo;
import com.sixin.framework.web.base.BaseController;
import com.sixin.iot.domain.Security;
import com.sixin.iot.domain.SecurityData;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.service.ISecurityDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/iot/secpannel")
public class SecpannelController extends BaseController {
    private String prefix = "iot/secpannel";

    @Autowired
    //private ISecService secService;
    private ISecurityDataService securityDataService;


    @GetMapping()
    //public String pannel(){return prefix + "/bSecpannel";}
    public String pannel(){return prefix + "/securitypannel";}

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo List(SecurityData securityData){
        startPage();
        //List<Security> list = secService.selectSecList();
        List<SecurityData> list=securityDataService.selectAllSecurityData(securityData);
        return getDataTable(list);
    }
}
