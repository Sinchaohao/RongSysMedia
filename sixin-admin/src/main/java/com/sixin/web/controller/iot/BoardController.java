package com.sixin.web.controller.iot;

import com.sixin.broad.service.IAreaService;
import com.sixin.common.annotation.Log;
import com.sixin.common.base.AjaxResult;
import com.sixin.common.enums.BusinessType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.page.TableDataInfo;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.poi.ExcelUtil;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.framework.web.base.BaseController;
import com.sixin.iot.domain.Board;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.service.IBoardService;
import com.sixin.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
@RequestMapping("/iot/board")
public class BoardController extends BaseController {

    private String prefix = "iot/board";

    @Autowired
    private IBoardService IBoardService;


    @RequiresPermissions("iot:boardinfo:view")
    @GetMapping()
    public String Board(){
        return prefix+"/board";
    }

    /**
     * Board列表
     * @param board
     * @return
     */
    @RequiresPermissions("iot:boardinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Board board){
        startPage();
        List<Board> list = IBoardService.selectBoardList(board);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add(ModelMap mmap, Torrent torrent){
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        mmap.put("imeis", IBoardService.queryList(torrent));
        return prefix+"/add";
    }

    /**
     * 新增Board信息
     */
    @RequiresPermissions("iot:boardinfo:add")
    @Log(title = "新增Board信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Board board)
    {
        return toAjax(IBoardService.insertBoard(board));
    }
    /**
     * 修改Board信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap, Torrent torrent, Iotype iotype)
    {
        Board board = IBoardService.selectByid(id);
        mmap.put("board", board);
        Long operId = ShiroUtils.getSysUser().getUserId();
        mmap.put("user", operId);
        mmap.put("imeis", IBoardService.queryList(torrent));
        mmap.put("iotypes", IBoardService.selectList(iotype));
        return prefix + "/edit";
    }

    /**
     * 导出
     */
    @Log(title = "Board导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:boardinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Board board)
    {
        List<Board> list = IBoardService.selectBoardList(board);
        ExcelUtil<Board> util = new ExcelUtil<Board>(Board.class);
        return util.exportExcel(list, "广告数据");
    }


    /**
     * 选择导出终端运转列表
     */
    @Log(title = "Board", businessType = BusinessType.EXPORT)
    @RequiresPermissions("iot:boardinfo:export")
    @PostMapping("/exportbysingle")
    @ResponseBody
    public AjaxResult export(@RequestParam("sjids") List<String> sfids)
    {
        List<Board> list = IBoardService.selectBoardByids(sfids);
        ExcelUtil<Board> util = new ExcelUtil<Board>(Board.class);
        return util.exportExcel(list, "Board");
    }

    /**
     * 修改保存Board信息
     */
    @RequiresPermissions("iot:boardinfo:edit")
    @Log(title = "Board信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Board board)
    {
        return toAjax(IBoardService.updateBoard(board));
    }

    /**
     * 删除Board信息
     */
    @PostMapping( "/remove")
    @Log(title = "删除Board信息", businessType = BusinessType.DELETE)
    @RequiresPermissions("iot:boardinfo:remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(IBoardService.deleteBoardByids(ids));
    }


    /**
     * 修改设备状态
     */
    @Log(title = "Board信息", businessType = BusinessType.UPDATE)
    @RequiresPermissions("iot:board:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(Board board)
    {
        return toAjax(IBoardService.changeStatus(board));
    }

    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Board> util = new ExcelUtil<Board>(Board.class);
        List<Board> BoardList = util.importExcel(file.getInputStream());
        String message = importBoard(BoardList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入用户数据
     *
     * @param BoardList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据(该功能未实现)
     * @return 结果
     */
    public String importBoard(List<Board> BoardList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(BoardList) || BoardList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        /*通过读取表格内容获得Boardlist，再通过遍历Boardlist去将每一行数据插入数据库中*/
        for (Board board : BoardList) {
            try {
                IBoardService.insertBoard(board);
                successNum++;
                successMsg.append("<br/>" + successNum + "用户 " + board.getId() + " 导入成功");
            } catch (Exception e) {
                String msg = board.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        return successMsg.toString();
    }

    @GetMapping("/selectIotTree")
    public String selectIotTree()
    {
        return prefix + "/listIotTree";
    }

    @PostMapping("/listIot")
    @ResponseBody
    public TableDataInfo listIotTree(Torrent torrent)
    {
        startPage() ;
        List<Torrent> list = IBoardService.queryList(torrent);
        return getDataTable(list);
    }


}