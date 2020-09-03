package com.sixin.web.controller.broad;

import com.sixin.common.base.AjaxResult;
import com.sixin.common.base.Ztree;
import com.sixin.iot.service.CameraRecorder;
import com.sixin.system.domain.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cx
 * @Time 2019/10/26 15:19
 * @Description
 */
@Controller
@RequestMapping("/broad/camera")
public class BroadCameraController {
    private String prefix = "broad/camera";
    @Autowired
    private CameraRecorder cameraRecorder;
    @GetMapping()
    public String pannel() {
        return prefix + "/camera";
    }

    @PostMapping("/recorder")
    @ResponseBody
    public AjaxResult recorder(@RequestParam("url") String url){
        cameraRecorder.recorder(url);
        return AjaxResult.success(1);
    }
    @PostMapping("/stop")
    @ResponseBody
    public AjaxResult stop(@RequestParam("url") String url){
        cameraRecorder.stop(url);
        return AjaxResult.success(1);
    }

}
