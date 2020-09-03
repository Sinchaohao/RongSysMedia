package com.sixin.api.controller.system;

import com.sixin.api.domain.RongApiRes;
import com.sixin.api.service.RongApiService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.utils.DateUtil;
import com.sixin.common.utils.StringUtils;
import com.sixin.framework.shiro.service.SysPasswordService;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.system.domain.SysUser;
import com.sixin.system.service.ISysMenuService;
import com.sixin.system.service.ISysUserService;
import com.sixin.village.domain.Files;
import com.sixin.village.domain.Varea;
import com.sixin.village.service.IVareaService;
import com.sixin.village.util.bFileUtil1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@Api(value = "用户信息")
public class User extends BaseController {
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysMenuService menuService;
    @Autowired
    private SysPasswordService passwordService;
    @Autowired
    private IVareaService vareaService;

    @PostMapping("/resetPwd")
    @CrossOrigin
    @ApiOperation(value = "修改用户密码信息")
    public AjaxResult resetUserPwd(SysUser user, String new_pwd)
    {
        if(sysUserService.selectUserById(user.getUserId()).getPassword().equals(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()))){

            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), new_pwd, user.getSalt()));
            return toAjax(sysUserService.resetUserPwd(user));
        }else{
            String msg = "修改密码失败";
            return error(msg);
        }

    }

    @PostMapping("/upUser")
    @CrossOrigin
    @ApiOperation(value = "修改用户详细信息")
    public AjaxResult updateUser(SysUser user)
    {
        return toAjax(sysUserService.updateUserInfo(user));
    }

    @GetMapping("/selectById")
    @CrossOrigin
    @ApiOperation(value = "通过用户ID查询用户")
    public RongApiRes selectUserById(Long userId)
    {
        return RongApiService.get_bean(sysUserService.selectUserById(userId));
    }
    @GetMapping("/selectByName")
    @CrossOrigin
    @ApiOperation(value = "通过用户名查询用户")
    public RongApiRes selectUserByLoginName(String userName)
    {
        return RongApiService.get_bean(sysUserService.selectUserByLoginName(userName));
    }

    @PostMapping("/login")
    @CrossOrigin
    @ApiOperation(value = "用户登录")
    public AjaxResult ajaxLogin(String loginid, String pwd, Boolean rememberMe)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(loginid, pwd, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return AjaxResult.success(sysUserService.selectUserForAppByLoginName(loginid));
        }
        catch (AuthenticationException e)
        {
            String msg = "用户名或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/selectMenu")
    @CrossOrigin
    @ApiOperation(value = "通过用户名查询用户")
    public RongApiRes selectMenuById(SysUser user)
    {
        return RongApiService.get_list(menuService.selectMenusByUser(user));
    }

    @PostMapping("/insertHeadImg")
    @CrossOrigin
    @ApiOperation(value = "更换头像")
    public AjaxResult insertheadimg(@RequestParam(value = "userid") Long userid,@RequestParam(value = "files", required = false) MultipartFile file,
                                    @RequestParam(value = "filename", required = false) String fname,
                                    @RequestParam(value = "flenth" ,required = false)String flenth, //时长
                                    @RequestParam(value = "fsize",required = false) String fsize)
    {
        SysUser user =new SysUser();
        user.setUserId(userid);

        String year = DateUtil.getYear();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String maxfileid = dateFormat.format(date); //获取文件上传时的时间参数字符串作为文件名
        try{
            //保存图片
            Files g = bFileUtil1.uplodeFile(maxfileid, file, fname, flenth, fsize, year);
            user.setAvatar(g.getAddress());
        } catch (Exception e) {
            //return "上传图片失败";
            System.out.println("失败");
            return toAjax(0);
        }
        return toAjax(sysUserService.updateUserHeadImg(user));
    }

    @GetMapping("/aidForCli")
    @CrossOrigin
    @ApiOperation(value = "获得登录用户所属区域及下属区域列表")
    public RongApiRes aidForCli(@RequestParam(value = "aid", required = false) String aid)
    {
        List<Varea> allaid = vareaService.listNextAidName(aid);
        if (allaid.size()>1){
            //获得所有的子 aid 放入 list
            List<Varea> temp;
            temp = vareaService.listNextAidName(allaid.get(0).getAid());
            for (int i = 1; i < allaid.size(); i++){
                List<Varea> l = vareaService.listNextAidName(allaid.get(i).getAid());
                if (!l.isEmpty()){
                    temp.addAll(l);
                }
            }
            allaid.addAll(temp);
        }
        return RongApiService.get_bean(allaid);
    }
}
