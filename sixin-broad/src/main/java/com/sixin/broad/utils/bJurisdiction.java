package com.sixin.broad.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import java.util.List;
import java.util.Map;

/**
 * @author 张超 teavamc
 * @Description: SX 用户地域分级以及权限认证
 * @ClassName bJurisdiction
 * @date 2019/2/17 20:59
 **/
public class bJurisdiction {

    /**获取当前登录的用户名
     * @return
     */
    public static String getUname(){
        return getSession().getAttribute(bConst.SESSION_UNAME).toString();
    }
    /**获取当前登录的登陆名
     * @return
     */
    public static String getUsername(){
        return getSession().getAttribute(bConst.SESSION_USERNAME).toString();
    }
    /**获取当前登录的用户编号
     * @return
     */
    public static String getUserid(){
        return getSession().getAttribute(bConst.SESSION_USERID).toString();
    }
    /**获取当前登录的用户角色
     * @return
     */
    public static String getUserRid(){
        return getSession().getAttribute(getUsername() +bConst.SESSION_USERROLEID).toString();
    }
    /**获取显示文字
     * @return
     */
    public static Map<String, String> getShowText(){
        return (Map<String, String>) getSession().getAttribute(bConst.SHOWTEXT);
    }
    /**获取修改权限,按钮查询权限第一位
     * @return
     */
    public static Boolean getEditRight(){
        String buttonrights = getSession().getAttribute(getUsername() + bConst.SESSION_QX).toString();
        Boolean flag = buttonrights.toCharArray()[0]=='1'?true:false;
        return flag;
    }
    /**获取设置节目公共权限,按钮查询权限第二位
     * @return
     */
    public static Boolean getSetProPublicRight(){
        String buttonrights = getSession().getAttribute(getUsername() + bConst.SESSION_QX).toString();
        Boolean flag = buttonrights.toCharArray()[1]=='1'?true:false;
        return flag;
    }
    /**获取接收节目申请权限,按钮查询权限第三位
     * @return
     */
    public static Boolean getRecProApplyRight(){
        String buttonrights = getSession().getAttribute(getUsername() + bConst.SESSION_QX).toString();
        Boolean flag = buttonrights.toCharArray()[2]=='1'?true:false;
        return flag;
    }
    /**shiro管理的session
     * @return
     */
    public static Session getSession(){
        //Subject currentUser = SecurityUtils.getSubject();
        return SecurityUtils.getSubject().getSession();
    }
}
