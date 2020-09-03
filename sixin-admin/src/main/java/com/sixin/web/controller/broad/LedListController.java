package com.sixin.web.controller.broad;

import com.sixin.broad.domain.LedList;
import com.sixin.broad.service.ILedListService;
import com.sixin.common.annotation.Log;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.iot.domain.Torrent;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: RongSys
 * @description:
 * @开发人员：王豪杰
 * @开发单位：湖南农业大学物联网工程专业
 * @create: 2020-04-01 21:44
 */
@Controller
@RequestMapping("/broad/ledlist")
public class LedListController extends BaseController {

    private String prefix = "broad/ledlist";

    @Autowired
    private ILedListService ledListService;

    @Autowired
    private com.sixin.iot.service.ITorrentService ITorrentService;

    @RequiresPermissions("broad:ledlist:view")
    @GetMapping()
    public String ledlist()
    {
        return prefix + "/ledlist";
    }

    /**
     * 查询led显示列表
     */
    @RequiresPermissions("broad:ledlist:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LedList ledList)
    {
        startPage();
        List<LedList> list = ledListService.selectLedList(ledList);
        return getDataTable(list);
    }


    @GetMapping("/add")
    public String add(ModelMap mmap){
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);

        Torrent torrent=new Torrent();
        mmap.put("imei", torrent);

        torrent.setCtype("LED");
        mmap.put("imeiList", ITorrentService.selectTorrentList(torrent));

        return prefix+"/add";
    }

    /**
     * 新增Led信息
     */
    @RequiresPermissions("broad:ledlist:add")
    @Log(title = "新增led信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addLedList(LedList ledList)
    {
        return toAjax(ledListService.insertLedList(ledList));
    }

    @Log(title = "LED管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("broad:ledlist:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(LedList ledList)
    {
        return toAjax(ledListService.updateLedList(ledList));
    }

    /**
     * 修改Led信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        LedList ledList = ledListService.selectLedListByid(id);
        mmap.put("ledlist", ledList);
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        return prefix + "/edit";
    }

    /**
     * 导出
     */
    @Log(title = "led数据导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("broad:ledlist:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LedList ledList)
    {
        List<LedList> ledLists = ledListService.selectLedList(ledList);
        ExcelUtil<LedList> util = new ExcelUtil<LedList>(LedList.class);
        return util.exportExcel(ledLists, "水泵监测数据");
    }

    /** @author qwerty
     * @description 导出数据
     *
     * @param ids
     * @return
     */
    @Log(title = "LedList", businessType = BusinessType.EXPORT)
    @RequiresPermissions("broad:ledlist:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult export(@RequestParam("sjids") List<String> ids)
    {
        List<LedList> ledLists = ledListService.selectLedListByids(ids);
        ExcelUtil<LedList> util = new ExcelUtil<LedList>(LedList.class);
        return util.exportExcel(ledLists, "LedList");
    }


    /**
     * 修改保存LedList信息
     */
    @RequiresPermissions("broad:ledlist:edit")
    @Log(title = "修改保存ledlist信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editLedlist(LedList ledList)
    {
        System.out.println(ledList.toString());
        return toAjax(ledListService.updateLedList(ledList));
    }
    /**
     * 删除LedList信息
     */
    @PostMapping( "/remove")
    @Log(title = "删除ledlist信息", businessType = BusinessType.DELETE)
    @RequiresPermissions("broad:ledlist:remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ledListService.deleteLedListByids(ids));
    }


    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<LedList> util = new ExcelUtil<LedList>(LedList.class);
        List<LedList> ledLists = util.importExcel(file.getInputStream());
        String message = importLedList(ledLists, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入用户数据
     *
     * @param ledLists        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
     * @return 结果
     */
    public String importLedList(List<LedList> ledLists, Boolean isUpdateSupport) {
        if (StringUtils.isNull(ledLists) || ledLists.size() == 0) {
            throw new BusinessException("导入数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得pumplist，再通过遍历pumplist去将每一行数据插入数据库中*/
        for (LedList ledList : ledLists) {
            try {
                ledListService.insertLedList(ledList);
                successNum++;
                successMsg.append("<br/>" + successNum + "用户 " + ledList.getId() + " 导入成功");
            } catch (Exception e) {
                String msg = ledList.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<LedList> util = new ExcelUtil<LedList>(LedList.class);
        return util.importTemplateExcel("用户数据");
    }
}
