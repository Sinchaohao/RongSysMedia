package com.sixin.broad.service;

import com.sixin.broad.domain.Recivedmessages;
import java.util.List;

/**
 * 短信接收记录 服务层
 * 
 * @author 张超
 * @date 2019-01-11
 */
public interface IRecivedmessagesService 
{
	/**
     * 查询短信接收记录信息
     * 
     * @param rmid 短信接收记录ID
     * @return 短信接收记录信息
     */
	public Recivedmessages selectRecivedmessagesById(Integer rmid);
	
	/**
     * 查询短信接收记录列表
     * 
     * @param recivedmessages 短信接收记录信息
     * @return 短信接收记录集合
     */
	public List<Recivedmessages> selectRecivedmessagesList(Recivedmessages recivedmessages);
	
	/**
     * 新增短信接收记录
     * 
     * @param recivedmessages 短信接收记录信息
     * @return 结果
     */
	public int insertRecivedmessages(Recivedmessages recivedmessages);
	
	/**
     * 修改短信接收记录
     * 
     * @param recivedmessages 短信接收记录信息
     * @return 结果
     */
	public int updateRecivedmessages(Recivedmessages recivedmessages);
		
	/**
     * 删除短信接收记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteRecivedmessagesByIds(String ids);
	
}
