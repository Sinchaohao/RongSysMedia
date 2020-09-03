package com.sixin.web.controller.village;

import com.sixin.common.exception.base.BaseException;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.file.FileUploadUtils2;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.village.domain.Files2;
import com.sixin.village.domain.PlaysOrder;
import com.sixin.village.service.IFilesService;
import com.sixin.village.service.IPlayOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * 文件上传 信息操作处理
 *
 * @author yc
 * @date 2018-12-20
 */
@Api("文件管理")
@Controller
@RequestMapping("/village/file")
public class FilesController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(FilesController.class);
    private static String Save_Url = FileUploadUtils2.getDefaultBaseDir();
    private String prefix = "village/file";
    @Autowired
    private IFilesService filesService;

    @Autowired
    private IPlayOrderService playOrderService;

    @RequiresPermissions("village:file:view")
    @GetMapping()
    public String files() {
        return prefix + "/file";
    }

    /**
     * 查询文件上传列表
     */
    @RequiresPermissions("village:file:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Files2 files) {
        startPage();
        List<Files2> list = filesService.selectFilesList(files);
        return getDataTable(list);
    }

    /**
     * 查询文件上传列表(不带显示的)
     */
    @RequiresPermissions("village:file:list")
    @PostMapping("/listWithNoSave")
    @ResponseBody
    public TableDataInfo listWithNoSave(Files2 files) {
        startPage();
        List<Files2> list = filesService.selectFilesListNoSave(files);
        return getDataTable(list);
    }

    /**
     * 导出文件上传列表
     */
    @RequiresPermissions("village:file:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Files2 files) {
        List<Files2> list = filesService.selectFilesList(files);
        ExcelUtil<Files2> util = new ExcelUtil<Files2>(Files2.class);
        return util.exportExcel(list, "files");
    }

    /**
     * 新增文件上传
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        Files2 files = new Files2();
        mmap.put("files", files);
        return prefix + "/add";
    }

    /**
     * 新增保存文件上传
     */
    @RequiresPermissions("village:file:add")
    @Log(title = "文件上传", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult save(MultipartFile file, Files2 files) {
        int rtn = 0;
        Boolean isFile = false;
        try {
            files = dealFile(file, files);
            if (files.getUpdateFlag() == 1 && files.getId() > 0) {//修改
                if (file != null) {
                    isFile = true;
                }
                rtn = filesService.updateFiles(files, isFile);
            } else {//新增
                rtn = filesService.insertFiles(files);
            }
            if (file != null && !"2".equals(files.getType())) {
                File desc = FileUploadUtils2.getAbsoluteFile(Save_Url, files.getUrl());
                file.transferTo(desc);
            }
        } catch (Exception e) {
            log.error("保存失败，请检查后重试！", e);
            return error(e.getMessage());
        }
        return toAjax(rtn);
    }


    /**
     * 新增保存文件上传
     */
    @RequiresPermissions("village:file:add")
    @Log(title = "文件上传", businessType = BusinessType.INSERT)
    @PostMapping("/addFileToShow")
    @ResponseBody
    public AjaxResult addFileToShow(String list, Long channelId) {
        String[] ids = list.split(",");
        int rtn = 0;
        if (StringUtils.isEmpty(ids) || ids.length < 1) {
            throw new BaseException("文件不能为空！");
        }
        int order = 0;
        PlaysOrder lastModel = playOrderService.selectMaxLimit(channelId);
        if (lastModel != null) {//取到最大的排序
            order = lastModel.getOrderBy();
        }
        for (int i = 0; i < ids.length; i++) {
            order = order + 1;
            Files2 files = filesService.selectFilesById(Integer.valueOf(ids[i]));
            if (files == null) {
                throw new BaseException("文件已被删除！");
            }
            PlaysOrder playsOrder = getOrderModel(files, channelId);
            if (playsOrder != null ) {
                playsOrder.setOrderBy(order);
                rtn = playOrderService.insertOrder(playsOrder);
            }

        }
        return toAjax(rtn);
    }

    private PlaysOrder getOrderModel(Files2 files, Long channelId) {
        PlaysOrder playsOrder = new PlaysOrder();
        playsOrder.setChannelId(channelId);
        playsOrder.setContent(files.getContent());
        playsOrder.setFileName(files.getFileName());
        playsOrder.setUr(files.getUrl());
        playsOrder.setTypes(Integer.valueOf(files.getType()));
        playsOrder.setSuffix(files.getSuffix());
        return playsOrder;
    }


    /**
     * 修改文件View
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Files2 files = filesService.selectFilesById(id);
        mmap.put("files", files);
        String fileUrl = "/profile/" + files.getFileName() + "." + files.getSuffix();
        mmap.put("fileUrl", fileUrl);
        return prefix + "/add";
    }

    /**
     * 修改保存文件上传
     */
    @RequiresPermissions("village:file:edit")
    @Log(title = "文件上传", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MultipartFile file, Files2 files) {
        return toAjax(filesService.updateFiles(files, false));
    }

    /**
     * 删除文件上传
     */
    @RequiresPermissions("village:file:remove")
    @Log(title = "文件上传", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(filesService.deleteFilesByIds(ids));
    }

    @ApiOperation("获取列表")
    @RequestMapping("/listOrder")
    @ResponseBody
    public TableDataInfo getShow(PlaysOrder playOrder) {
        List<PlaysOrder> playOrderList = playOrderService.selectOrderList(playOrder);
        return getDataTable(playOrderList);
    }

    @RequestMapping("/listOrderPage")
    public String listOrder() {
        return prefix + "/orderShow";
    }

    /**
     * 检验文件名是否唯一
     *
     * @param files
     * @return
     */
    @PostMapping("/checkFileNameUnqiue")
    @ResponseBody
    public String checkFileNameUnqiue(Files2 files) {
        return filesService.checkFileNameUnqiue(files);
    }

    private Files2 dealFile(MultipartFile file, Files2 files) throws Exception {
        if (file == null || "2".equals(files.getType())) return files;

        String suffix = FileUploadUtils2.dealName(file.getOriginalFilename());
        if (StringUtils.isEmpty(suffix)) throw new Exception();

        String name = Save_Url + files.getFileName() + "." + suffix;
        files.setUrl(name);
        files.setSuffix(suffix);

        return files;
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        if (id != null && id > 0){
            model.addAttribute("id",id);
        }
        return prefix + "/lunbo";
    }
}
