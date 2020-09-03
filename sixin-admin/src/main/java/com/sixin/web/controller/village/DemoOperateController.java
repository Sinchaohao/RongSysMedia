package com.sixin.web.controller.village;

import com.sixin.framework.web.base.BaseController;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.page.TableDataInfo;
import com.sixin.framework.web.page.TableSupport;
import com.sixin.common.support.Convert;
import com.sixin.common.page.PageDomain;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.utils.StringUtils;
import com.sixin.village.domain.UserOperateModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作控制
 * 
 * @author sixin
 */
@Controller
@RequestMapping("/village/operate")
public class DemoOperateController extends BaseController
{
    private String prefix = "village/operate";

    private final static Map<Integer, UserOperateModel> users = new LinkedHashMap<Integer, UserOperateModel>();
    {
        users.put(1, new UserOperateModel(1,  "测试1", "0", "15888888888", "ry@qq.com", 150.0, "0"));
        users.put(2, new UserOperateModel(2,  "测试2", "1", "15666666666", "ry@qq.com", 180.0, "1"));
        users.put(3, new UserOperateModel(3, "测试3", "0", "15666666666", "ry@qq.com", 110.0, "1"));
        users.put(4, new UserOperateModel(4,  "测试4", "1", "15666666666", "ry@qq.com", 220.0, "1"));
        users.put(5, new UserOperateModel(5,  "测试5", "0", "15666666666", "ry@qq.com", 140.0, "1"));
        users.put(6, new UserOperateModel(6, "测试6", "1", "15666666666", "ry@qq.com", 330.0, "1"));
        users.put(7, new UserOperateModel(7, "测试7", "0", "15666666666", "ry@qq.com", 160.0, "1"));
        users.put(8, new UserOperateModel(8, "测试8", "1", "15666666666", "ry@qq.com", 170.0, "1"));
        users.put(9, new UserOperateModel(9,  "测试9", "0", "15666666666", "ry@qq.com", 180.0, "1"));
        users.put(10, new UserOperateModel(10,  "测试10", "0", "15666666666", "ry@qq.com", 210.0, "1"));
        users.put(11, new UserOperateModel(11,  "测试11", "1", "15666666666", "ry@qq.com", 110.0, "1"));
        users.put(12, new UserOperateModel(12,  "测试12", "0", "15666666666", "ry@qq.com", 120.0, "1"));
    }

    /**
     * 表格
     */
    @GetMapping("/table")
    public String table()
    {
        return prefix + "/table";
    }

    /**
     * 其他
     */
    @GetMapping("/other")
    public String other()
    {
        return prefix + "/other";
    }

    /**
     * 查询数据
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserOperateModel userModel)
    {
        TableDataInfo rspData = new TableDataInfo();
        List<UserOperateModel> userList = new ArrayList<UserOperateModel>(users.values());
        // 查询条件过滤
        if (StringUtils.isNotEmpty(userModel.getSearchValue()))
        {
            userList.clear();
            for (Map.Entry<Integer, UserOperateModel> entry : users.entrySet())
            {
                if (entry.getValue().getUserName().equals(userModel.getSearchValue()))
                {
                    userList.add(entry.getValue());
                }
            }
        }
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (null == pageDomain.getPageNum() || null == pageDomain.getPageSize())
        {
            rspData.setRows(userList);
            rspData.setTotal(userList.size());
            return rspData;
        }
        Integer pageNum = (pageDomain.getPageNum() - 1) * 10;
        Integer pageSize = pageDomain.getPageNum() * 10;
        if (pageSize > userList.size())
        {
            pageSize = userList.size();
        }
        rspData.setRows(userList.subList(pageNum, pageSize));
        rspData.setTotal(userList.size());
        return rspData;
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserOperateModel user)
    {
        Integer userId = users.size() + 1;
        user.setUserId(userId);
        /*return AjaxResult.success(users.put(userId, user));*/
        return AjaxResult.success();
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Integer userId, ModelMap mmap)
    {
        mmap.put("user", users.get(userId));
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserOperateModel user)
    {
        /*return AjaxResult.success(users.put(user.getUserId(), user));*/
        return AjaxResult.success();
    }

    /**
     * 导出
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserOperateModel user)
    {
        List<UserOperateModel> list = new ArrayList<UserOperateModel>(users.values());
        ExcelUtil<UserOperateModel> util = new ExcelUtil<UserOperateModel>(UserOperateModel.class);
        return util.exportExcel(list, "用户数据");
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<UserOperateModel> util = new ExcelUtil<UserOperateModel>(UserOperateModel.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<UserOperateModel> util = new ExcelUtil<UserOperateModel>(UserOperateModel.class);
        List<UserOperateModel> userList = util.importExcel(file.getInputStream());
        String message = importUser(userList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 删除用户
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        Integer[] userIds = Convert.toIntArray(ids);
        for (Integer userId : userIds)
        {
            users.remove(userId);
        }
        return AjaxResult.success();
    }

    /**
     * 查看详细
     */
    @GetMapping("/detail/{userId}")
    public String detail(@PathVariable("userId") Integer userId, ModelMap mmap)
    {
        mmap.put("user", users.get(userId));
        return prefix + "/detail";
    }

    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean()
    {
        users.clear();
        return success();
    }

    /**
     * 导入用户数据
     * 
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importUser(List<UserOperateModel> userList, Boolean isUpdateSupport)
    {
        if (StringUtils.isNull(userList) || userList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (UserOperateModel user : userList)
        {
            try
            {
                // 验证是否存在这个用户
                boolean userFlag = false;
                for (Map.Entry<Integer, UserOperateModel> entry : users.entrySet())
                {
                    if (entry.getValue().getUserName().equals(user.getUserName()))
                    {
                        userFlag = true;
                        break;
                    }
                }
                if (!userFlag)
                {
                    Integer userId = users.size() + 1;
                    user.setUserId(userId);
                    users.put(userId, user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、用户 " + user.getUserName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    users.put(user.getUserId(), user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、用户 " + user.getUserName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、用户 " + user.getUserName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getUserName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
