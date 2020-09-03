package com.sixin.broad.service;

import com.sixin.broad.domain.Organization;
import com.sixin.broad.domain.TerminalTels;

import java.util.List;

/**
 * 终端地域 服务层
 *
 * @author cx
 * @date 2019-10-03
 */
public interface IOrganizationService
{
	/**
	 * 查询终端地域信息
	 *
	 * @param tid 终端地域ID
	 * @return 终端地域信息
	 */
	Organization selectOrganizationById(String tid);

	/**
	 * 查询终端地域信息
	 *
	 * @param tid 设备IMEI
	 * @return 终端地域信息
	 */
	Organization selectOrganizationByTid(String tid);

	/**
	 * 查询终端列表
	 *
	 * @param organization 终端信息
	 * @return 终端集合
	 */
	List<Organization> selectOrganizationList(Organization organization);

	/**
	 * 新增终端
	 *
	 * @param organization 终端信息
	 * @return 结果
	 */
	int insertOrganization(Organization organization);

	/**
	 * 新增终端地址图片
	 *
	 * @param organization 终端信息
	 * @return 结果
	 */
	int insertOrganizationPic(Organization organization);

	/**
	 * 修改终端
	 *
	 * @param organization 终端信息
	 * @return 结果
	 */
	int updateOrganization(Organization organization);

	/**
	 * 删除终端信息
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	int deleteOrganizationByIds(String[] ids);

	/**
	 * 查询节目单终端列表
	 *
	 * @param organization 终端信息
	 * @return 终端集合
	 */
	List<Organization> selectProBroadList(Organization organization);

	/**
	 * 设置终端的RDS码
	 *
	 * @return
	 */
	int updateRdsByIds(String ids , String number);

	/**
	 * 设置终端的Fmfrequency码
	 *
	 * @return
	 */
	int updateFmfrequencyByIds(String ids , String number);

	int updateIsuseByTid(String tid, Boolean isuse);

	//	通过 tid 查询对应终端的RDS码
	Organization selectRdsByTid(String tid);

	/**
	 * 通过IMEI获取终端维护员电话
	 * @param tid
	 * @return
	 */
	List<TerminalTels> selectTelsByTid(String tid);

	/**
	 * @author cx
	 * @param organization
	 *
	 * @Description 更新终端数据
	 */
	int updateUsername(Organization organization);

	/**
	 * 根据条件分页查询终端对象
	 *
	 * @param organization 导出终端字段
	 * @return 终端信息集合信息
	 */
	List<Organization> exportOrganization(Organization organization);

	int addphoneEdit(TerminalTels terminalTels);

	int deletephoneedit(String telid);

	List<Organization> selectOrganizationListByTids(String[] tids);

	int terinfoedittime(String time,List<String> tids);

	int terinfoeditrds(String time,List<String> tids);

	int terinfoeditfrequency(String time,List<String> tids);

	int terinfoeditphone(List<Organization> organizations);

	int terinfoeditphonedelete(String time,List<String> tids);
	/**
	 * 根据aid查询终端对象(村务宝典)
	 * hfz
	 * @param aid 导出终端字段
	 * @return 终端信息集合信息
	 */
	List<Organization> selectByaid(String aid);

	/**
	 * 流媒体直播---选择直播终端(村务宝典)
	 * hfz
	 * @return 终端信息集合信息
	 */
	List<Organization> selecttidBytwo(String tids , String userid);
	/**
	 * 根据用户id获取LED终端列表
	 * hfz
	 * @param organization
	 * @return 终端信息集合信息
	 */
	List<Organization> selectByLedUserid(Organization organization);
}
