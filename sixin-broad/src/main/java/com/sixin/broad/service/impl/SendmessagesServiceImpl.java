package com.sixin.broad.service.impl;

import com.sixin.broad.domain.BroadMessage;
import com.sixin.broad.domain.Sendmessages;
import com.sixin.broad.mapper.SendmessagesMapper;
import com.sixin.broad.service.ISendmessagesService;
import com.sixin.common.annotation.DataScope;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.system.mapper.SysUserMapper;
import com.sixin.village.domain.PersonApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信发送记录 服务层实现
 *
 * @author 张超
 * @date 2019-01-11
 */
@Service
public class SendmessagesServiceImpl implements ISendmessagesService
{
	@Autowired
	private SendmessagesMapper sendmessagesMapper;

	@Autowired
	private SysUserMapper userMapper;

	/**
	 * 查询短信发送记录信息
	 *
	 * @param smid 短信发送记录ID
	 * @return 短信发送记录信息
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public Sendmessages selectSendmessagesById(Integer smid) {
		return sendmessagesMapper.selectSendmessagesById(smid);
	}

	/**
	 * 查询短信发送记录列表
	 *
	 * @param sendmessages 短信发送记录信息
	 * @return 短信发送记录集合
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Sendmessages> selectSendmessagesList(Sendmessages sendmessages)
	{
		return sendmessagesMapper.selectSendmessagesList(sendmessages);
	}

	/**
	 * 查询短信发送记录列表（村务宝典）
	 *
	 * @param sendmessages 短信发送记录信息
	 * @return 短信发送记录集合
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Sendmessages> selectSendmessagesList1(PersonApi sendmessages)
	{
		return sendmessagesMapper.selectSendmessagesList1(sendmessages);
	}

	/**
	 * 新增短信发送记录
	 *
	 * @param sendmessages 短信发送记录信息
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int insertSendmessages(Sendmessages sendmessages)
	{
		return sendmessagesMapper.insertSendmessages(sendmessages);
	}

	/**
	 * 修改短信发送记录
	 *
	 * @param sendmessages 短信发送记录信息
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateSendmessages(Sendmessages sendmessages)
	{
		return sendmessagesMapper.updateSendmessages(sendmessages);
	}

	/**
	 * 删除短信发送记录对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteSendmessagesByIds(String ids)
	{
		return sendmessagesMapper.deleteSendmessagesByIds(Convert.toStrArray(ids));
	}

	/**
	 * 短信转语音播出
	 * 通过选择终端，发送短信直接到终端进行语音播出。(村务宝典)
	 *
	 * @param sendmessages 短信发送记录信息
	 * @return 短信发送记录集合
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Sendmessages> selectSendmessagesBytid(Sendmessages sendmessages)
	{
		return sendmessagesMapper.selectSendmessagesBytid(sendmessages);
	}

	/**
	 * 获取系统用户列表树
	 *
	 */
	@Override
	@DataScope(deptAlias = "d", userAlias = "u")
	public List<Map<String, Object>> selectDeptUserList ()
	{
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		List<BroadMessage> broadMessageList = sendmessagesMapper.selectDeptUserList();
		trees = getTrees(broadMessageList);
		return trees;
	}

	public List<Map<String, Object>> getTrees(List<BroadMessage> broadMessages)
	{

		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		for (BroadMessage broadMessage : broadMessages)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", broadMessage.getAid());
			map.put("aid", broadMessage.getAid());
			map.put("pId", broadMessage.getParentaid());
			map.put("name", broadMessage.getAname());
			map.put("phone", broadMessage.getPhone());
			trees.add(map);
		}
		return trees;
	}

}
