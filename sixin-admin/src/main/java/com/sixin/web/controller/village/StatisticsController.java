package com.sixin.web.controller.village;

import com.sixin.common.annotation.Log;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.enums.BusinessType;
import com.sixin.village.domain.Statistics;
import com.sixin.village.domain.Worklog;
import com.sixin.village.service.IStatisticsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
@Controller
@RequestMapping("/village/statistics")
public class StatisticsController extends BaseController {
    private String prefix= "village/statistics";

    @Autowired
    private IStatisticsService statisticsService;

    @RequiresPermissions("village:statistics:view")
    @GetMapping()
    public String statistics(){return prefix+"/statistics";}

    /**
     * 查询参数列表
     */
    @RequiresPermissions("village:statistics:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Statistics statistics)
    {
        startPage();
        List<Statistics> list = statisticsService.selectStatisticsList(statistics);
        return getDataTable(list);
    }

    /**
     * @Author TanXY
     * @Description 全部导出
     * @Date 2020/4/18 14:48
     * @Param [worklog]
     * @return com.sixin.common.base.AjaxResult
     */
    @RequiresPermissions("village:worklog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Statistics statistics)
    {
        List<Statistics> list = statisticsService.selectStatisticsList(statistics);
        ExcelUtil<Statistics> util = new ExcelUtil<>(Statistics.class);
        return util.exportExcel(list, "Statistics");
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<Worklog> util = new ExcelUtil<Worklog>(Worklog.class);
        return util.importTemplateExcel("参数设置");
    }

    /**
     * @Author TanXY
     * @Description 按需导出
     * @Date 2020/4/18 14:51
     * @Param [rows]
     * @return com.sixin.common.base.AjaxResult
     */
    @RequiresPermissions("village:worklog:export")
    @PostMapping("/exportByIds")
    @ResponseBody
    public AjaxResult export(@RequestParam("rows") List<String> rows) {//@RequestParam("rows") List<String> rows  VillagerInfo villagerInfo
        //List<VillagerInfo> list = villagerInfoService.selectVillagerInfoList(villagerInfo);
        List<Statistics> list = statisticsService.selectStatisticsByIds(rows);
        ExcelUtil<Statistics> util = new ExcelUtil<>(Statistics.class);
        return util.exportExcel(list, "Statistics");
    }

    /**
     * @Author TanXY
     * @Description
     * @Date 2020/4/18 14:51
     * @Param [file, updateSupport]
     * @return com.sixin.common.base.AjaxResult
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Statistics> util = new ExcelUtil<>(Statistics.class);
        List<Statistics> userList = util.importExcel(file.getInputStream());
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
    public String importUser(List<Statistics> userList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得userlist，再通过遍历userlist去将每一行数据插入数据库中*/
        for (Statistics user : userList) {
            try {
                statisticsService.insertStatistics(user);
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
     * 新增参数设置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存参数设置
     */
    @RequiresPermissions("village:statistics:add")
    @Log(title = "参数设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Statistics statistics)
    {
        return toAjax(statisticsService.insertStatistics(statistics));
    }

    /**
     * 修改参数设置
     */
    @GetMapping("/edit/{valueid}")
    public String edit(@PathVariable("valueid") String valueid, ModelMap mmap)
    {
        Statistics statistics = statisticsService.selectStatisticsById(valueid);
        mmap.put("statistics", statistics);
        return prefix + "/edit";
    }

    /**
     * 修改保存参数设置
     */
    @RequiresPermissions("village:statistics:edit")
    @Log(title = "参数设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Statistics statistics)
    {
        return toAjax(statisticsService.updateStatistics(statistics));
    }

    /**
     * 删除参数设置
     */
    @RequiresPermissions("village:statistics:remove")
    @Log(title = "参数设置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(statisticsService.deleteStatisticsByIds(ids));
    }

}
