package com.sixin.broad.service.impl;

import java.util.List;

import com.sixin.broad.domain.Recivedmessages;
import com.sixin.broad.mapper.RecivedmessagesMapper;
import com.sixin.broad.service.IRecivedmessagesService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.common.support.Convert;

/**
 * 短信接收记录 服务层实现
 * 
 * @author 张超
 * @date 2019-01-11
 */
@Service
public class RecivedmessagesServiceImpl implements IRecivedmessagesService
{
	@Autowired
	private RecivedmessagesMapper recivedmessagesMapper;

	/**
     * 查询短信接收记录信息
     * 
     * @param rmid 短信接收记录ID
     * @return 短信接收记录信息
     */
    @Override
	@DataSource(value = DataSourceType.SLAVE)
	public Recivedmessages selectRecivedmessagesById(Integer rmid)
	{
	    return recivedmessagesMapper.selectRecivedmessagesById(rmid);
	}
	
	/**
     * 查询短信接收记录列表
     * 
     * @param recivedmessages 短信接收记录信息
     * @return 短信接收记录集合
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Recivedmessages> selectRecivedmessagesList(Recivedmessages recivedmessages)
	{
	    return recivedmessagesMapper.selectRecivedmessagesList(recivedmessages);
	}
	
    /**
     * 新增短信接收记录
     * 
     * @param recivedmessages 短信接收记录信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int insertRecivedmessages(Recivedmessages recivedmessages)
	{
	    return recivedmessagesMapper.insertRecivedmessages(recivedmessages);
	}
	
	/**
     * 修改短信接收记录
     * 
     * @param recivedmessages 短信接收记录信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateRecivedmessages(Recivedmessages recivedmessages)
	{
	    return recivedmessagesMapper.updateRecivedmessages(recivedmessages);
	}

	/**
     * 删除短信接收记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteRecivedmessagesByIds(String ids)
	{
		return recivedmessagesMapper.deleteRecivedmessagesByIds(Convert.toStrArray(ids));
	}
	
}
