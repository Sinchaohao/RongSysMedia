package com.sixin.web.controller.broad;

import com.sixin.broad.domain.Textmessages;
import com.sixin.broad.service.ITextmessagesService;
import com.sixin.framework.web.base.BaseController;
import com.sixin.common.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 广播用户 终端信息操作处理
 * 
 * @author 戴誉琪
 * @date 2019-01-18
 */
@Controller
@RequestMapping("/broad/textmessages")
public class TextmessagesController extends BaseController
{
    private String prefix = "broad/textmessages";
	
	@Autowired
	private ITextmessagesService iTextmessagesService;
	//private IBroaduserService broaduserService;
	
	//@RequiresPermissions("broad:broaduser:view")
	@GetMapping()
	public String broaduser(ModelMap mmap)
	{
		mmap.put("typeNum",iTextmessagesService.selectTypeNum());
	    return prefix + "/textmessages";
	}
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(){
		startPage();
		List<Textmessages> list = iTextmessagesService.selectTextMessages();
		return getDataTable(list);
	}

}
