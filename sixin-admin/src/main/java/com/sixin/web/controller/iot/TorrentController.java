package com.sixin.web.controller.iot;

import com.sixin.common.annotation.Log;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.service.ITorrentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @开发人员: 蔡文静
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
@Controller
@RequestMapping("/iot/torrent")
public class TorrentController extends BaseController
{
    private String prefix = "iot/torrent";

    @Autowired
    private ITorrentService ITorrentService;

    @RequiresPermissions("iot:torrent:view")
    @GetMapping()
    public String torrent()
    {
        return prefix + "/torrent";
    }

    /**
     * 查询终端运转列表
     */
    @RequiresPermissions("iot:torrent:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Torrent torrent)
    {
        startPage();
        List<Torrent> list = ITorrentService.selectTorrentList(torrent);
        return getDataTable(list);
    }
    @GetMapping("/add")
    public String add(ModelMap mmap, Iotype iotype){
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        mmap.put("iotypes", ITorrentService.selectList(iotype));
        return prefix+"/add";
    }

    /**
     * 新增Torrent信息
     */
    @RequiresPermissions("iot:torrent:add")
    @Log(title = "新增Torrent信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Torrent torrent)
    {
        return toAjax(ITorrentService.insertTorrent(torrent));
    }


    /**
     * 修改保存Torrent信息
     */
    @RequiresPermissions("iot:torrent:edit")
    @Log(title = "修改保存Torrent信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Torrent torrent)
    {
        return toAjax(ITorrentService.updateTorrent(torrent));
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap,Iotype iotype)
    {
        Torrent torrent = ITorrentService.selectByid(id);
        mmap.put("iotypes", ITorrentService.selectList(iotype));
        mmap.put("torrent", torrent);
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        return prefix + "/edit";
    }

    /**
     * 导出
     */
    @Log(title = "终端导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:torrent:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Torrent torrent)
    {
        List<Torrent> list = ITorrentService.selectTorrentList(torrent);
        ExcelUtil<Torrent> util = new ExcelUtil<Torrent>(Torrent.class);
        return util.exportExcel(list, "终端监测数据");
    }

    /** @author qwerty
     * @description 导出数据
     *
     * @param sfids
     * @return
     */
    @Log(title = "Torrent", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:torrent:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult export(@RequestParam("sjids") List<String> sfids)
    {
        List<Torrent> list = ITorrentService.selectTorrentByids(sfids);
        ExcelUtil<Torrent> util = new ExcelUtil<Torrent>(Torrent.class);
        return util.exportExcel(list, "Torrent");
    }


    /**
     * 删除Torrent信息
     */
    @PostMapping( "/remove")
    @Log(title = "删除Torrent信息", businessType = BusinessType.DELETE)
    @RequiresPermissions("iot:torrent:remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ITorrentService.deleteTorrentByids(ids));
    }


    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("iot:torrent:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Torrent torrent)
    {
        return toAjax(ITorrentService.changeStatus(torrent));
    }


    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Torrent> util = new ExcelUtil<Torrent>(Torrent.class);
        List<Torrent> torrentList = util.importExcel(file.getInputStream());
        String message = importTorrent(torrentList, updateSupport);
        return AjaxResult.success(message);
    }
    /**
     * 导入用户数据
     *
     * @param TorrentList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
     * @return 结果
     */
    public String importTorrent(List<Torrent> torrentList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(torrentList) || torrentList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得Torrentlist，再通过遍历Torrentlist去将每一行数据插入数据库中*/
        for (Torrent torrent : torrentList) {
            try {
                ITorrentService.insertTorrent(torrent);
                successNum++;
                successMsg.append("<br/>" + successNum + "用户 " + torrent.getId() + " 导入成功");
            } catch (Exception e) {
                String msg = torrent.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }


}