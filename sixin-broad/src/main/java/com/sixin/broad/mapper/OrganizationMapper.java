package com.sixin.broad.mapper;

import com.sixin.broad.domain.Organization;
import com.sixin.broad.domain.TerminalTels;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 终端地域 数据层
 *
 * @author cx
 * @date 2019-02-17
 */
public interface OrganizationMapper
{
	/**
	 * 查询终端地域信息
	 *
	 * @param tid 设备IMEI
	 * @return 终端地域信息
	 */
	Organization selectOrganizationByTid(String tid);

	/**
	 * 查询终端地域列表
	 *
	 * @param organization 终端地域信息
	 * @return 终端地域集合
	 */
	List<Organization> selectOrganizationList(Organization organization);

	/**
	 * 新增终端地域
	 *
	 * @param organization 终端地域信息
	 * @return 结果
	 */
	int insertOrganization(Organization organization);


	int insertOrganizationCon(Organization organization);
	/**
	 * 新增终端地址图片
	 *
	 * @param organization 终端地域信息
	 * @return 结果
	 */
	 int insertOrganizationPic(Organization organization);

	/**
	 * 批量删除终端地域
	 *
	 * @param tids 需要删除的数据ID
	 * @return 结果
	 */
	int deleteOrganizationByIds(String[] tids);

	/**
	 * 查询节目单终端列表
	 *
	 * @param organization 终端信息
	 * @return 终端集合
	 */
	List<Organization> selectProBroadList(Organization organization);

	/**
	 *
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 * @throws Exception 异常
	 */
	int addProIdAll(Long[] ids);

	/**
	 *  设置终端的RDS码
	 *
	 * @param ids 需要修改终端的RDS码
	 * @return 结果
	 */
	int updateRdsByIds(@Param("ids") String[] ids, @Param("number")String number);

	/**
	 *  设置终端的频率码
	 *
	 * @param ids 需要修改终端的RDS码
	 * @return 结果
	 */
	int updateFmfrequencyByIds(@Param("ids") String[] ids, @Param("number")String number);

	int updateIsuseByTid(@Param("tid") String tid, @Param("isuse")Boolean isuse);


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
	int updateOrganization(Organization organization);

	/**
	 * @author cx
	 * @param organization
	 *
	 * @Description 更新终端所属用户
	 */
	int updateUsername(Organization organization);

	/**
	 * 根据条件分页查询终端对象
	 *
	 * @param organization 导出终端字段
	 * @return 终端信息集合信息
	 */
	List<Organization> exportOrganization(Organization organization);
	/**
	 * 根据用户id查询终端对象
	 *
	 * @param aid 导出终端字段
	 * @return 终端信息集合信息
	 */
	List<Organization> selectByaid(@Param("aid") String aid);

	List<Organization> selecttidBytwo(@Param("tids") String[] tids , @Param("userid") String userid);

	/**
	 * 根据用户id获取LED终端列表
	 *
	 * @param organization 导出终端字段
	 * @return 终端信息集合信息
	 */
	List<Organization> selectByLedUserid(Organization organization);
	int addphoneEdit(TerminalTels terminalTels);

	int deletephoneedit(String telid);

	List<Organization> selectOrganizationListByTids(String[] tids);

	int terinfoedittime(String time,List<String> sfids);

	int terinfoeditrds(String time,List<String> sfids);

	int terinfoeditfrequency(String time,List<String> sfids);

	int terinfoeditphone(List<Organization> organizations);

	int terinfoeditphonedelete(String time,List<String> sfids);

	int insertTerminalTes(Organization organization);

}