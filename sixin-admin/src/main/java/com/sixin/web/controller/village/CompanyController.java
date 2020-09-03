package com.sixin.web.controller.village;

import com.sixin.broad.domain.Area;
import com.sixin.broad.service.IAreaService;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.StringUtils;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysUserService;
import com.sixin.village.domain.Company;
import com.sixin.village.domain.Link;
import com.sixin.village.domain.VillagerInfo;
import com.sixin.village.service.ICompanyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 企业 信息操作处理
 *
 * @author 张鸿权
 * @date 2019-08-08
 */
@Controller
@RequestMapping("/village/company")
public class CompanyController extends BaseController {
    private String prefix = "village/company";

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IAreaService areaService;

    @Autowired
    private ISysUserService sysUserService;


    @RequiresPermissions("village:company:view")
    @GetMapping()
    public String company() {
        return prefix + "/company";
    }

    /**
     * 查询企业列表
     */
    @RequiresPermissions("village:company:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Company company) {
        startPage();
        List<Company> list = companyService.selectCompanyList(company);
        return getDataTable(list);
    }

    /**
     * @Author TanXY
     * @Description
     * @Date 2020/4/18 14:28
     * @Param [rows]
     * @return com.sixin.common.base.AjaxResult
     */
    @RequiresPermissions("village:company:export")
    @PostMapping("/exportByIds")
    @ResponseBody
    public AjaxResult export(@RequestParam("rows") List<String> rows) {
        List<Company> list = companyService.selectCompanyByIds(rows);
        ExcelUtil<Company> util = new ExcelUtil<>(Company.class);
        return util.exportExcel(list, "Company");
    }

    /**
     * @Author TanXY
     * @Description 导入数据
     * @Date 2020/4/17 21:54
     * @Param [file, updateSupport]
     * @return com.sixin.common.base.AjaxResult
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Company> util = new ExcelUtil<>(Company.class);
        List<Company> userList = util.importExcel(file.getInputStream());
        String message = importUser(userList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入用户数据
     *
     * @param userList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
     * @return 结果
     */
    public String importUser(List<Company> userList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
        for (Company user : userList) {
            try {
                companyService.insertCompany(user);
                successNum++;
                successMsg.append("<br/>" + successNum + "导入成功");
            } catch (Exception e) {
                String msg = "导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }

    /**
     * 加载区域列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData() {
        SysUser currentUser = ShiroUtils.getSysUser();//从session中获取当前登陆用户的userid
        Long userid = currentUser.getUserId();
        //int returnId = new Long(userid).intValue();
        int roleid = sysUserService.selectRoleid(userid);//通过所获取的userid去广播用户表中查询用户所属区域的Roleid
        if (roleid == 1) {
            List<Map<String, Object>> tree = areaService.selectAreaTree(new Area());
            return tree;
        } else {
            long aid;
            aid = Long.valueOf(sysUserService.selectAid(userid));//通过所获取的userid去广播用户表中查询用户所属区域的Aid
            Area update_area = new Area();
            update_area.setAid(aid);
            List<Map<String, Object>> tree = areaService.selectAreaTree(update_area);
            return tree;
        }
    }


    /**
     * 选择区域树
     */
    @GetMapping("/selectAidTree")
    public String selectAidTree() {
        return prefix + "/aidTree";
    }

    /**
     * 导出企业列表
     */
    @RequiresPermissions("village:company:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Company company) {
        List<Company> list = companyService.selectCompanyList(company);
        ExcelUtil<Company> util = new ExcelUtil<Company>(Company.class);
        return util.exportExcel(list, "company");
    }

    /**
     * 新增企业
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存企业
     */
    @RequiresPermissions("village:company:add")
    @Log(title = "企业", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Company company) {
        return toAjax(companyService.insertCompany(company));
    }

    /**
     * 修改企业
     */
    @GetMapping("/edit/{cid}")
    public String edit(@PathVariable("cid") Integer cid, ModelMap mmap) {
        Company company = companyService.selectCompanyById(cid);
        mmap.put("company", company);
        return prefix + "/edit";
    }

    /**
     * 修改保存企业
     */
    @RequiresPermissions("village:company:edit")
    @Log(title = "企业", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Company company) {
        return toAjax(companyService.updateCompany(company));
    }

    /**
     * 删除企业
     */
    @RequiresPermissions("village:company:remove")
    @Log(title = "企业", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(companyService.deleteCompanyByIds(ids));
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<Company> util = new ExcelUtil<Company>(Company.class);
        return util.importTemplateExcel("企业信息");
    }

    @Log(title = "企业", businessType = BusinessType.UPDATE)
    @RequiresPermissions("village:company:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Company company) {
		return toAjax(companyService.updateCompany(company));

    }

}
