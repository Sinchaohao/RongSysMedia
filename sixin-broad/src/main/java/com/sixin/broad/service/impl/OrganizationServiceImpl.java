package com.sixin.broad.service.impl;

import com.sixin.broad.domain.Organization;
import com.sixin.broad.domain.TerminalTels;
import com.sixin.broad.mapper.OrganizationMapper;
import com.sixin.broad.service.IOrganizationService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.support.Convert;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 终端管理 服务层实现
 *
 * @author cx
 * @date 2019-10-03
 */
@Service
public class OrganizationServiceImpl implements IOrganizationService {
	@Autowired
	private OrganizationMapper organizationMapper;

	/**
	 * 查询终端地域信息
	 *
	 * @param tid 设备IMEI
	 * @return 终端地域信息
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public Organization selectOrganizationByTid(String tid){
		return organizationMapper.selectOrganizationByTid(tid);
	}

	/**
	 * 新增终端
	 *
	 * @param organization 终端信息
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int insertOrganization(Organization organization) {
		return organizationMapper.insertOrganization(organization);
	}

	/**
	 * 新增终端地址图片
	 *
	 * @param organization 终端信息
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int insertOrganizationPic(Organization organization)
	{
		return organizationMapper.insertOrganizationPic(organization);
	}

	/**
	 * 删除终端对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteOrganizationByIds(String[] ids){
		return organizationMapper.deleteOrganizationByIds(ids);
	}

	/**
	 * 查询节目单终端列表
	 *
	 * @param organization 终端信息
	 * @return 终端集合
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Organization> selectProBroadList(Organization organization)
	{
		return organizationMapper.selectProBroadList(organization) ;
	}

	/**
	 * 查询终端信息
	 *
	 * @param tid 终端IMEI
	 * @return 终端信息
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public Organization selectOrganizationById(String tid)
	{
		return organizationMapper.selectOrganizationByTid(tid);
	}

	/**
	 * 设置终端的RDS码
	 *
	 * @return
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateRdsByIds(String id , String number)
	{
		return organizationMapper.updateRdsByIds(Convert.toStrArray(id),number);
	}

	/**
	 * 设置终端的频率码
	 ** @return
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateFmfrequencyByIds(String id , String number)
	{
		return organizationMapper.updateFmfrequencyByIds(Convert.toStrArray(id),number);
	}

	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateIsuseByTid(String tid, Boolean isuse)
	{
		return organizationMapper.updateIsuseByTid(tid,isuse);
	}

	/**
	 * 	通过 tid 查询对应终端的RDS码
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public Organization selectRdsByTid(String tid){
		return organizationMapper.selectRdsByTid(tid);
	}
	/**
	 * 	通过 tid 查询对应终端的维护电话
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<TerminalTels> selectTelsByTid(String tid){
		return organizationMapper.selectTelsByTid(tid);
	}

	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Organization> selectOrganizationList(Organization organization){
		return organizationMapper.selectOrganizationList(organization);
	}

	/**
	 * @author cx
	 * @param organization
	 *
	 * @Description 更新终端数据
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateOrganization(Organization organization){
		return organizationMapper.updateOrganization(organization);
	}

	/**
	 * @author cx
	 * @param organization
	 *
	 * @Description 更新终端数据
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateUsername(Organization organization){
		return organizationMapper.updateUsername(organization);
	}

	/**
	 * 根据条件分页查询终端对象
	 *
	 * @param organization 导出终端字段
	 * @return 终端信息集合信息
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Organization> exportOrganization(Organization organization){return organizationMapper.exportOrganization(organization);}
	/**
	 * 根据aid查询终端对象(村务宝典)
	 *
	 * @param aid 导出终端字段
	 * @return 终端信息集合信息
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Organization> selectByaid(String aid){return organizationMapper.selectByaid(aid);}

	/**
	 * 流媒体直播---选择直播终端(村务宝典)
	 *
	 * @return 终端信息集合信息
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Organization> selecttidBytwo(String tid , String userid){return organizationMapper.selecttidBytwo(Convert.toStrArray(tid),userid);}


	/**
	 * 根据用户id获取LED终端列表
	 *
	 * @param organization 导出终端字段
	 * @return 终端信息集合信息
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Organization> selectByLedUserid(Organization organization){return organizationMapper.selectByLedUserid(organization);}
	@Override
	public int addphoneEdit(TerminalTels terminalTels) {
		return organizationMapper.addphoneEdit(terminalTels);
	}

	public int deletephoneedit(String telid) {
		return organizationMapper.deletephoneedit(telid);
	}

	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Organization> selectOrganizationListByTids(String[] tids){
		return organizationMapper.selectOrganizationListByTids(tids);
	}

	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int terinfoedittime(String time,List<String> tids){
		return organizationMapper.terinfoedittime(time,tids);
	}

	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int terinfoeditrds(String time, List<String> tids) {
		return organizationMapper.terinfoeditrds(time,tids);
	}

	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int terinfoeditfrequency(String time, List<String> tids) {
		return organizationMapper.terinfoeditfrequency(time,tids);
	}

	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int terinfoeditphone(List<Organization> organizations) {
		return organizationMapper.terinfoeditphone(organizations);
	}

	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int terinfoeditphonedelete(String time,List<String> tids) {
		return organizationMapper.terinfoeditphonedelete(time,tids);
	}
}