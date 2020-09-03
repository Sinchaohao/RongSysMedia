package com.sixin.web.controller.broad;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.broad.domain.Organization;
import com.sixin.broad.domain.Program;
import com.sixin.broad.service.IOrganizationService;
import com.sixin.broad.service.IProgramService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.page.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张超 teavamc
 * @Description: 流媒体直播
 * @ClassName LiveController
 * @date 2019/2/16 16:55
 **/
@Controller
@RequestMapping("/broad/live")
public class LiveController extends BaseController {
    @Autowired
    private IProgramService iProgramService;

    @Autowired
    private IOrganizationService organizationService;

    // 配置文件中拿到流媒体地址
//    @Value("${SX.rtmpAddress}")
//    private String rtmpAddress;
    // 张超服务器
    private String rtmpAddress = "rtmp://114.67.88.76:1936/live";
    private String prefix = "broad/live";

    /**
     * 直播页面控制器
     * @author 张超 teavamc
     * @date 2019/2/17
     * @param
     * @return java.lang.String
     */
    @RequiresPermissions("broad:live:view")
    @GetMapping("/view")
    public String liveview(ModelMap mmap){
        //传给前台流媒体地址 传给 /streamlive 页面
        mmap.put("rtmpAddress",rtmpAddress);
        return prefix + "/streamlive";
    }

    /**
     * 本地直播页面控制器 - 直播测试
     * @author 张超 teavamc
     * @date 2019/2/17
     * @param
     * @return java.lang.String
     */
    @RequiresPermissions("broad:live:testview")
    @GetMapping("/testview")
    public String testliveview(ModelMap mmap){
        //传给前台流媒体地址 传给 /streamlive 页面
        mmap.put("rtmpAddress",rtmpAddress);
        return prefix + "/streamlivetest";
    }

    /**
     * 返回节目单选择界面
     * @param mmap
     * @return
     */
    @GetMapping("/getdofile")
    public String doFile(ModelMap mmap){
        return prefix+"/listfile";
    }
    /**
     * 获取节目单数据
     * @param program
     * @return
     */
    @PostMapping("/listfile")
    @ResponseBody
    public TableDataInfo listFile(Program program)
    {
        startPage();
        List<Program> list = iProgramService.selectProList(program);
        return getDataTable(list);
    }

//    /**
//        * 选择终端的弹出树
//        * @author 张超 teavamc
//        * @date 2019/2/22
//        * @param [tid]
//        * @return com.sixin.api.domain.RongApiRes
//        */
//    @RequiresPermissions("broad:live:setStreamTer")
//    @GetMapping("/setStreamTer")
//    public String  setStreamTer(String tid)
//            throws Exception{
//        String selecttid = tid;
//        String lastjson;
//        return prefix + "/selecttree";
//    }

    @RequiresPermissions("broad:live:getTerByTidTest")
    @PostMapping("/getTerByTidTest")
    public RongApiRes  getTerByTidTest(String tids) throws Exception{
//        将前端回传的 tids 按照， 分割成Array 再填充List
        List<String> idlist = new ArrayList<String>();
        if(tids!=null && !tids.equals("")){
            String[] idarr = tids.split(",");
            for(int i=0;i<idarr.length;i++){
                idlist.add(idarr[i]);
            }
        }

//        这里写死,测试机终端号
        String idTest = "862105024020277";
        Organization ter = organizationService.selectOrganizationByTid(idTest);
        return RongApiService.get_bean(ter);
//        批量查询 功能待完善
//        List<PageData> terlist = organizationService.getTerInfoBytids(pd);

    }

//    /**
//        * 设置 流媒体终端
//        * @author 张超 teavamc
//        * @date 2019/2/17
//        * @param []
//        * @return java.lang.String
//        */
//    @RequiresPermissions("broad:live:set")
//    @PostMapping("/set")
//    public String setStreamTer(HttpServletRequest request,ModelMap mmap){
//        // 前端拿到选中的 终端id
//        String selecttid = request.getParameter("sid");
//        String lastjson;
//        // 填充 lastjson
//        if(selecttid!= null && !selecttid.equals("null") && selecttid != ""){
//            // 新建被选择的地区对象，使用 List 结构，多选id，转数组，遍历
//            List<String> selectaid = new ArrayList<String>();
//            String[] tids = selecttid.split(",");
//           // 遍历选中设备的数组 获取其所在的 终端组 id
//            for(int i=0;i<tids.length;i++){
//                String parentaid = organizationService.getOrgnzByOid(tids[i]).getAid();
//                // 添加进 List<String> selectaid
//                selectaid.add(parentaid);
//                while(!parentaid.equals("0")){
//                    // 找到 Area 地区id
//                    parentaid = areaService.getAreaByAid(parentaid).getParentaid();
//                    // 添加进 List<String> selectaid
//                    selectaid.add(parentaid);
//                }
//            }
//            lastjson  = getAreaAndTer(pd,selecttid,selectaid);
//        }else{
//            lastjson  = getAreaAndTer(pd,"",null);
//        }
//        // 扔给前台
//        mmap.put("areaTerJson",lastjson);
//        return prefix + "/streamter_set";
//    }
//
//    /**
//        * 通过 id 找到终端
//        * @author 张超 teavamc
//        * @date 2019/2/17
//        * @param []
//        * @return com.sixin.api.domain.RongApiRes
//        */
//    @RequiresPermissions("broad:live:getid")
//    @PostMapping("/getTerByTid")
//    @ResponseBody
//    public RongApiRes getTerByTid(HttpServletRequest request){
//        // 拿到前台选择的 多台 终端id
//        String tids =  request.getParameter("tids");
//        //  数组 转 List
//        List<String> teridlist = new ArrayList<String>();
//
//        if(tids!=null && !tids.equals("")){
//            String[] tidlist = tids.split(",");
//            for(int i=0;i<tidlist.length;i++){
//                teridlist.add(tidlist[i]);
//            }
//        }
//
//        List<PageData> terlist = organizationService.getTerInfoBytids(tids);
//        return RongApiService.get_list(terlist);;
//    }
//
//    /**
//        * 新增 直播日志
//        * @author 张超 teavamc
//        * @date 2019/2/17
//        * @param [out]
//        * @return void
//        */
//    @RequiresPermissions("broad:live:addLog")
//    @GetMapping("/addLog")
//    public void addLog(HttpServletRequest request,PrintWriter out){
//        String type = request.getParameter("type");
//        String streamid = request.getParameter("streamid");
//        String logcontent = "";
//        String note = "" ;
//        if(type.equals("open")){
//            logcontent = "开启直播";
//            note = "直播编号:"+streamid+":"+request.getParameter("imeilist");
//        }else{
//            logcontent = "关闭直播";
//            note = "直播编号:"+streamid;
//        }
//        logService.saveLog(Const.LOGTYPE[1],
//                            "流媒体直播",logcontent,
//                            this.getRemortIP(),
//                            note.length()>200?note.substring(0, 190)+"...等":note);
//        out.write("success");
//        out.close();
//    }
//
//    /**
//        * 展示地区和终端
//        * @author 张超 teavamc
//        * @date 2019/2/17
//        * @param [pd, selecttid, selectaid]
//        * @return java.lang.String
//        */
//    private String getAreaAndTer(PageData pd,String selecttid,List<String> selectaid){
//        String lastjson = null;
//        List<Area> arealist = new ArrayList<Area>();
//        String aid = usersService.getAidByUserid(Jurisdiction.getUserid());
//        if(aid==null||aid.equals("0")||"1".equals(Jurisdiction.getUserRid())) {
//            aid="0";
//        }else{
//            Area a = new Area();
//            a = areaService.getAreaByAid(aid);
//            arealist.add(a); //获取当前用户所在区域编号加入当前区域
//        }
//        arealist.addAll(areaService.listAllSubByPAid(aid)); //所有子区域（级联）信息
//        List<Organization> terminals = new ArrayList<Organization>();
//        pd.put("aid", aid);
//        if("1".equals(Jurisdiction.getUserRid())){
//            pd.put("rid", 1);
//        }
//        terminals = organizationService.listTersByAids(pd);
//        JSONArray lastarr = new JSONArray();
//        JSONArray areaarr = JSONArray.fromObject(arealist); //所有分组列表
//        for(int i=0;i<areaarr.size();i++){//设置jsonobject类型：0表示分组，1表示终端
//            JSONObject obj= areaarr.getJSONObject(i);
//            if(selectaid!=null&&selectaid.contains(obj.getString("aid"))){
//                obj.element("checked", true);
//            }
//            obj.element("type", "0");
//            obj.element("isParent", true);
//            lastarr.add(obj);
//        }
//        JSONArray terarr = JSONArray.fromObject(terminals);//所有终端列表
//        String terjson = terarr.toString().replaceAll("\"aid\"", "pId").replaceAll("\"tid\"", "id").replaceAll("\"tname\"", "name");
//        terarr = JSONArray.fromObject(terjson);
//        for(int i=0;i<terarr.size();i++){
//            JSONObject obj= terarr.getJSONObject(i);
//            if(!selecttid.equals("")&&selecttid.contains(obj.getString("id"))){
//                obj.element("checked", true );
//            }
//            obj.element("type", "1");
//            lastarr.add(obj);
//        }
//        lastjson = lastarr.toString().replaceAll("\"parentaid\"", "pId").replaceAll("\"aid\"", "id").replaceAll("\"aname\"", "name");
//        return lastjson;
//    }

}
