package com.sixin.web.controller.iot;

import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Torrent;
import com.sixin.village.util.bFileUtil1;
import com.sixin.village.util.bConstant1;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.DateUtil;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.annotation.Log;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.iot.domain.LED;
import com.sixin.iot.service.ILEDService;
import com.sixin.streamsocket.client.NettyClient;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

/**
 * 终端运转 信息操作处理
 *
 * @author 张超
 * @date 2019-03-03
 */
@Controller
@RequestMapping ("/iot/led")
public class LEDController extends BaseController
{

    private String prefix = "iot/led";
    @Autowired
    private ILEDService ILEDService;

    @RequiresPermissions("iot:ledinfo:view")
    @GetMapping()
    public String LED(){
        return prefix+"/led";
    }

    /**
     * LED列表
     * @param led
     * @return
     */
    @RequiresPermissions("iot:ledinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LED led){
        startPage();
        List<LED> list = ILEDService.selectLEDList(led);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add(ModelMap mmap, Torrent torrent){

        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        mmap.put("imeis", ILEDService.queryList(torrent));
        return prefix+"/add";
    }
    /**
     * 导出LED列表
     */

    /**
     * 导出终端运转列表
     */
    @Log(title = "LED", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:ledinfo:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult exportLEDByIds(@RequestParam("sjids") List<String> sfids)
    {
        List<LED> list = ILEDService.selectLEDByids(sfids);
        ExcelUtil<LED> util = new ExcelUtil<LED>(LED.class);
        return util.exportExcel(list, "LED");
    }


    /**
     * 新增LED信息
     */
    @RequiresPermissions("iot:ledinfo:add")
    @Log(title = "LED信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LED led)
    {
        return toAjax(ILEDService.insertLED(led));
    }
    /**
     * 修改LED信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap, Torrent torrent)
    {
        LED led = ILEDService.selectByid(id);
        mmap.put("led", led);
        mmap.put("imeis", ILEDService.queryList(torrent));
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);

        return prefix + "/edit";
    }
    @GetMapping("/selectIotTree")
    public String selectOrganizationTree()
    {
        return prefix + "/listIotTree";
    }

    @PostMapping("/listIot")
    @ResponseBody
    public TableDataInfo listProBroad(Torrent torrent)
    {
        startPage() ;
        List<Torrent> list = ILEDService.queryList(torrent);
        return getDataTable(list);
    }

    /**
     * 修改保存LED信息
     */
    @RequiresPermissions("iot:ledinfo:edit")
    @Log(title = "LED信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LED led)
    {
        return toAjax(ILEDService.updateLED(led));
    }
    /**
     * 删除LED信息
     */
    @RequiresPermissions("iot:ledinfo:remove")
    @Log(title = "删除LED信息", businessType = BusinessType.DELETE)

    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ILEDService.deleteLEDByids(ids));
    }



    @Log(title = "LED管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("iot:led:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(LED led)
    {
        return toAjax(ILEDService.changeStatus(led));
    }
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<LED> util = new ExcelUtil<LED>(LED.class);
        List<LED> ledList = util.importExcel(file.getInputStream());
        String message = importLED(ledList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导出
     */
    @Log(title = "终端导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:metoerinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LED led)
    {
        List<LED> list = ILEDService.selectLEDList(led);
        ExcelUtil<LED> util = new ExcelUtil<LED>(LED.class);
        return util.exportExcel(list, "LED监测数据");
    }


    /**
     * 导入用户数据
     *
     * @param LEDList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
     * @return 结果
     */
    public String importLED(List<LED> ledList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(ledList) || ledList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得LEDlist，再通过遍历LEDlist去将每一行数据插入数据库中*/
        for (LED led : ledList) {
            try {
                ILEDService.insertLED(led);
                successNum++;
                successMsg.append("<br/>" + successNum + "用户 " + led.getId() + " 导入成功");
            } catch (Exception e) {
                String msg = led.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }


}
