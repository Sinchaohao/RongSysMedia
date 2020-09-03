package com.sixin.village.util;

/**
 * @author 张超 teavamc
 * @Description:TODO
 * @ClassName bConst1
 * @date 2019/2/17 17:29
 **/
public class bConst1 {
    // 验证码
    public static final String SESSION_SECURITY_CODE = "sessionSecCode";
    // session用的用户
    public static final String SESSION_USER = "sessionUser";
    public static final String SESSION_MENU_RIGHTS = "sessionMenuRights";
    // 当前菜单
    public static final String SESSION_menuList = "menuList";
    // 当前菜单
    public static final String SESSION_allmenuList = "allmenuList";
    // 未登录时的菜单
    public static final String SESSION_nousermenuList = "nousermenuList";
    // 修改权限
    public static final String SESSION_QX = "QX";
    // 用户ID
    public static final String SESSION_USERID = "SX_USERID";
    // 用户名
    public static final String SESSION_UNAME = "SX_UNAME";
    // 登陆名
    public static final String SESSION_USERNAME = "SX_USERNAME";
    // 用户角色
    public static final String SESSION_USERROLEID = "SX_USERROLEID";
    // 用户所管理的所有下属区域列表(用户管理用)
    public static final String SESSION_USERAREALIST = "USERAREALIST";
    // 用户所管理的区域及所有下属区域列表(经纬度信息管理用)
    public static final String SESSION_MAPAREALIST = "MAPAREALIST";
    public static final String TRUE = "T";
    public static final String FALSE = "F";

    // 文件路径
    public static final String FILEPATH = "profile/";
    // 图片上传路径
    //	public static final String FILEPATHIMG = "profile/uploadImgs/";
    // 系统文件路径
    public static final String FILEPATHFILE = "profile/file/";
    // 系统文件路径
    public static final String FILEPATHBACKIMAGE = "profile/file/backimage/";
    //申请文件上传路径
    public static final String FILEPATHAPPLY = "profile/applyfile/";
    //导出节目excel文件压缩包临时文件夹
    public static final String FILEPATHPROTEMP = "profile/protemp/";
    //特种节目文件夹
    public static final String FILEPATHPROSPEC = "profile/audiofile/prospec/";
    //节目文件夹
    public static final String FILEPATHPER = "profile/audiofile/";
    //项目文件夹
    public static final String FILEPATHPER2 = "img/";

    public static final String[] LOGTYPE=new String[]{"登陆日志","操作日志"};

    public static final String SHOWTEXT = "SHOWTEXT";

    //终端流量清零任务名
    public static final String TRAFFICRESEJOB = "TRAFFICRESEJOB";

    public static final String SHOWTEXTURL = "config/showtext.properties";
    public static final String MENU_CH = "config/menu_zh_CN.properties";
    public static final String MENU_UYG = "config/menu_uyg.properties";
    // 节目文件上传限制路径
    public static final String PROFILELIMIT = "config/PROFILELIMIT.txt";
    // 流量清零日期路径
    public static final String TRAFFICRESEDATE = "config/TRAFFICRESEDATE.txt";
    // 分页条数配置路径
    public static final String PAGE	= "config/PAGE.txt";
    // 短信猫配置路径
    public static final String CONFIG = "config/config.properties";
}
