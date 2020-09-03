package com.sixin.broad.mapper;

import com.sixin.broad.domain.ProApplyUser;
import com.sixin.broad.domain.ProreApply;
import java.util.List;	

/**
 * 节目申请 数据层
 * 
 * @author 张超
 * @date 2019-03-02
 */
public interface ProreApplyMapper 
{
	/**
     * 查询节目申请信息
     * 
     * @param paid 节目申请ID
     * @return 节目申请信息
     */
	public ProreApply selectProreApplyById(Integer paid);
	
	/**
     * 查询节目申请列表
     * 
     * @param proreApply 节目申请信息
     * @return 节目申请集合
     */
	public List<ProreApply> selectProreApplyList(ProreApply proreApply);
	
	/**
     * 新增节目申请
     * 
     * @param proreApply 节目申请信息
     * @return 结果
     */
	public int insertProreApply(ProreApply proreApply);
	
	/**
     * 修改节目申请
     * 
     * @param proreApply 节目申请信息
     * @return 结果
     */
	public int updateProreApply(ProreApply proreApply);
	
	/**
     * 删除节目申请
     * 
     * @param paid 节目申请ID
     * @return 结果
     */
	public int deleteProreApplyById(Integer paid);
	
	/**
     * 批量删除节目申请
     * 
     * @param paids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProreApplyByIds(String[] paids);

	/**
	    * 该方法只提供节目申请表中的目录
	    * @author 张超 teavamc
	    * @date 2019/3/2
	    * @param []
	    * @return java.util.List<com.sixin.broad.domain.ProApplyUser>
	    */
	public List<ProApplyUser> selectProrApplyUserList(ProApplyUser proApplyUser);

	int recall(String fid);
}