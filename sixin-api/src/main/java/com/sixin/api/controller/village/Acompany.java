package com.sixin.api.controller.village;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.village.domain.Company;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.service.ICompanyService;
import com.sixin.village.service.IVareaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@CrossOrigin
@Api(value = "企业信息")
public class Acompany extends BaseController {
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IVareaService vareaService;

    @GetMapping("/companylist")
    @CrossOrigin
    @ApiOperation(value = "企业信息列表")
    public RongApiRes selectCompanyList(pubObjApi company)
    {
        company.setPageIndex((company.getPageIndex()-1)*company.getPageSize());
        List<Company> res;
        List<String> allaid = vareaService.listNextAid(company.getAid());
        if (allaid.isEmpty()){
            allaid.add(company.getAid());
            company.setListaid(allaid);
            res = companyService.selectCompanyListById(company);
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
            company.setListaid(allaid);
            res = companyService.selectCompanyListById(company);
        }
        return RongApiService.get_list(res);
    }

    @PostMapping("/insertcompany")
    @CrossOrigin
    @ApiOperation(value = "返回所有项目")
    public AjaxResult insertCompany(Company company)
    {
        return toAjax(companyService.insertCompany(company));
    }
}
