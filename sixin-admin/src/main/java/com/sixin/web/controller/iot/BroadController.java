package com.sixin.web.controller.iot;

import com.sixin.broad.domain.*;
import com.sixin.broad.service.*;
import com.sixin.broad.utils.bFileUtil;
import com.sixin.common.annotation.Log;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.support.Convert;
import com.sixin.common.utils.DateUtil;
import com.sixin.common.utils.StringUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.iot.domain.Broad;
import com.sixin.iot.domain.BroadTerminal;
import com.sixin.iot.service.IBroadService;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysUserService;
import com.sixin.village.domain.Files;
import com.sixin.village.util.bFileUtil1;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 终端信息操作处理
 *
 * @author 蔡文静
 * @date 2020-4-10
 */
@Controller
@RequestMapping("/iot/broad")
public class BroadController extends BaseController {

    private String prefix = "iot/broad";


    @Autowired
    private IBroadService broadService;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IAreaService areaService;

    @Autowired
    IAreaGroupingService iAreaGroupingService;

    @RequiresPermissions("iot:broadinfo:view")
    @GetMapping()
    public String broad()
    {
        return prefix + "/broad";
    }
    //查询==========================================================================================================
    @RequiresPermissions("iot:broadinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Broad broad){
        startPage();
        List<Broad> list = broadService.selectBroadList(broad);
        return getDataTable(list);
    }
    //删除==========================================================================================================
    @Log(title = "终端信息删除", businessType = BusinessType.DELETE)
    @RequiresPermissions("iot:broadinfo:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids){

        broadService.deleteManByIds(Convert.toStrArray(ids));
        broadService.deleteConByIds(Convert.toStrArray(ids));
        return toAjax(broadService.deleteBroadByIds(Convert.toStrArray(ids)));
    }
    //更新==========================================================================================================
    @GetMapping("/edit/{tid}")
    public String edit(@PathVariable("tid") String[] tid, ModelMap mmap){
        Broad broad = broadService.selectBroadListByTids(tid).get(0);
        mmap.put("broad", broad);
        return prefix + "/edit";
    }
    /**
     * 编辑保存终端信息
     */
    @RequiresPermissions("iot:broadinfo:edit")
    @Log(title = "终端信息修改", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Broad broad){
        broadService.updateCon(broad);
        broadService.updateMan(broad);
        return toAjax( broadService.updateBroad(broad));
    }
    //新增===========================================================================================================
    @GetMapping("/add")
    public String add(){
        return prefix + "/add";
    }

    @Log(title = "新增终端", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @RequiresPermissions("iot:broadinfo:add")
    @ResponseBody
    public AjaxResult addSave(Broad broad){
//            ,@RequestParam(value = "files") MultipartFile file[],
//                              @RequestParam(value = "filesnum", required = false) Integer filesnum,
//                              @RequestParam(value = "filename", required = false) String fname,
//                              @RequestParam(value = "flenth" ,required = false)String flenth, //时长
//                              @RequestParam(value = "fsize",required = false) String fsize){//大小

        //获取上传的图片，保存
        MultipartFile pic= broad.getPoscenepic();
        String fileurl = bFileUtil.saveImg(pic );
        broad.setPoscene(fileurl);
        broadService.insertCon(broad);
        broadService.insertMan(broad);
//        String year = DateUtil.getYear();
//        Date date = new Date();
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
//        System.out.println(dateFormat.format(date));
//        String maxfileid = dateFormat.format(date);
//        //图片上传调用工具类
//        try{
//            int i;
//            String fileaddress = "";
//            for(i=0;i<filesnum;i++)
//            {
//                //保存文件
//                Files g = bFileUtil1.uplodeFile(maxfileid,file[i],fname,flenth,fsize,year);
//                System.out.println(g.toString());//在控制台输出文件信息
//                fileaddress = fileaddress + g.getAddress() + ";";//通过fileaddress来储存文件地址
//            }
//            broad.setPoscene(fileaddress);//给project实体的“文件地址”赋值
//
//            return toAjax(broadService.insertBroad(broad));//将project实体中的值插入数据表中
//        }catch (Exception e){
//            //return "上传文件失败";
//            System.out.println("失败");
        return toAjax(broadService.insertBroad(broad));
    }
    //导出===========================================================================================================
    @Log(title = "终端管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:broadinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Broad broad){
        List<Broad> list = broadService.selectBroadList(broad);
        ExcelUtil<Broad> util = new ExcelUtil<Broad>(Broad.class);
        return util.exportExcel(list, "终端管理表");
    }

    //根据Tids批量导出===========================================================================================================
    @Log(title = "终端管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:broadinfo:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult exportBroadByTids(@RequestParam("sjids") String[] tids) {
        List<Broad> list = broadService.selectBroadListByTids(tids);
        ExcelUtil<Broad> util = new ExcelUtil<Broad>(Broad.class);
        return util.exportExcel(list, "终端管理表");
    }


    @GetMapping("/listProBroadTree")
    @ResponseBody
    public List<Map<String, Object>> listProBroadTree(){
        List<Map<String, Object>> tree = messageService.selectMessageList(new BroadMessage());
        return tree;
    }

    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData(){
        SysUser currentUser = ShiroUtils.getSysUser();//从session中获取当前登陆用户的userid
        Long userid =  currentUser.getUserId();
        //int returnId = new Long(userid).intValue();
        int roleid = sysUserService.selectRoleid(userid);//通过所获取的userid去广播用户表中查询用户所属区域的Roleid
        if(roleid == 1) {
            List<Map<String, Object>> tree = areaService.selectAreaTree(new Area());
            return tree;
        }else {
            long aid;
            aid =Long.valueOf(sysUserService.selectAid(userid));//通过所获取的userid去广播用户表中查询用户所属区域的Aid
            Area update_area = new Area();
            update_area.setAid(aid);
            List<Map<String, Object>> tree = areaService.selectAreaTree(update_area);
            return tree;
        }
    }
    /**
     * 选择区域树
     * @description 目前村务在调用此接口
     */
    @GetMapping("/selectAidTree")
    public String selectAidTree()
    {
        return prefix + "/aidTree";
    }

    @GetMapping("/selectBroadTree/{aid}")
    public String selectBroadTree(@PathVariable("aid") long aid, ModelMap mmap) {
        mmap.put("broad", areaService.selectAreaById(aid));
        return prefix + "/listProBroadTree";
    }

    @GetMapping("/phoneEdit/{tid}")
    public String phoneEdit(@PathVariable("tid") String tid, ModelMap mmap){
        return prefix + "/phoneEdit";
    }

    @PostMapping("/phoneEdit/{tid}")
    @ResponseBody
    public List<BroadTerminal> phoneEditpost(@PathVariable("tid") String tid, ModelMap mmap){
        List<BroadTerminal> terminalTels= broadService.selectTelsByTid(tid);
        mmap.put("terminalTels", terminalTels);
        return terminalTels;
    }

    @PostMapping("/addphoneedit")
    @ResponseBody
    public int addphoneEdit(BroadTerminal terminalTels){
        return broadService.addphoneEdit(terminalTels);
    }

    @GetMapping("/deletephoneedit/{telid}")
    @ResponseBody
    public String deletephoneedit(@PathVariable("telid") String telid){
        if(broadService.deletephoneedit(telid)==1);
        return "操作成功";
    }

    @PostMapping("/listProBroad")
    @ResponseBody
    public TableDataInfo listProBroad(Broad broad){
        startPage() ;
        List<Broad> list = broadService.selectProBroadList(broad);
        return getDataTable(list);
    }

    @PostMapping( "/isuseSet")
    @ResponseBody
    public AjaxResult isuseSet(String tid, Boolean isuse){
        return toAjax(broadService.updateIsuseByTid(tid,isuse));
    }
    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Broad> util = new ExcelUtil<Broad>(Broad.class);
        List<Broad> broadList = util.importExcel(file.getInputStream());
        String message = importBroad(broadList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入用户数据
     *
     * @param BroadList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
     * @return 结果
     */
    public String importBroad(List<Broad> broadList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(broadList) || broadList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得Broadlist，再通过遍历Broadlist去将每一行数据插入数据库中*/
        for (Broad broad : broadList) {
            try {
                broadService.insertCon(broad);
                broadService.insertMan(broad);
                broadService.insertBroad(broad);
                successNum++;
                successMsg.append("<br/>" + successNum + "用户 " + broad.getTid() + " 导入成功");
            } catch (Exception e) {
                String msg = broad.getTid() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }
}