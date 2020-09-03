package com.sixin.broad.service.impl;

import java.util.List;

import com.sixin.broad.domain.ProApplyUser;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.broad.mapper.ProreApplyMapper;
import com.sixin.broad.domain.ProreApply;
import com.sixin.broad.service.IProreApplyService;
import com.sixin.common.support.Convert;

/**
 * 节目申请 服务层实现
 * 
 * @author 张超
 * @date 2019-03-02
 */
@Service
public class ProreApplyServiceImpl implements IProreApplyService 
{
	@Autowired
	private ProreApplyMapper proreApplyMapper;

	/**
     * 查询节目申请信息
     * 
     * @param paid 节目申请ID
     * @return 节目申请信息
     */
    @Override
	@DataSource(value = DataSourceType.SLAVE)
	public ProreApply selectProreApplyById(Integer paid)
	{
	    return proreApplyMapper.selectProreApplyById(paid);
	}
	
	/**
     * 查询节目申请列表
     * 
     * @param proreApply 节目申请信息
     * @return 节目申请集合
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<ProreApply> selectProreApplyList(ProreApply proreApply)
	{
	    return proreApplyMapper.selectProreApplyList(proreApply);
	}
	
    /**
     * 新增节目申请
     * 
     * @param proreApply 节目申请信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int insertProreApply(ProreApply proreApply)
	{
	    return proreApplyMapper.insertProreApply(proreApply);
	}
	
	/**
     * 修改节目申请
     * 
     * @param proreApply 节目申请信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateProreApply(ProreApply proreApply)
	{
	    return proreApplyMapper.updateProreApply(proreApply);
	}

	/**
     * 删除节目申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteProreApplyByIds(String ids)
	{
		return proreApplyMapper.deleteProreApplyByIds(Convert.toStrArray(ids));
	}

	/**
	 * 该方法只提供节目申请表中的目录
	 * @author 张超 teavamc
	 * @date 2019/3/2
	 * @param []
	 * @return java.util.List<com.sixin.broad.domain.ProApplyUser>
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<ProApplyUser> selectProrApplyUserList(ProApplyUser proApplyUser){
		return proreApplyMapper.selectProrApplyUserList(proApplyUser);
	}

	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int recall(String fid) {
		return proreApplyMapper.recall(fid);
	}
}
