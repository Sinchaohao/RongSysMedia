package com.sixin.broad.service.impl;

import java.util.List;

import com.sixin.broad.domain.ProList;
import com.sixin.broad.domain.ProTerminal;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.broad.mapper.ProSinmanageMapper;
import com.sixin.broad.domain.ProSinmanage;
import com.sixin.broad.service.IProSinmanageService;
import com.sixin.common.support.Convert;

/**
 * 节目播出单 服务层实现
 * 
 * @author 张超
 * @date 2019-03-02
 */
@Service
public class ProSinmanageServiceImpl implements IProSinmanageService
{
	@Autowired
	private ProSinmanageMapper proSinmanageMapper;

	/**
     * 查询节目播出单信息
     * 
     * @param sfid 节目播出单ID
     * @return 节目播出单信息
     */
    @Override
	@DataSource(value = DataSourceType.SLAVE)
	public ProSinmanage selectProSinmanageById(Integer sfid)
	{
	    return proSinmanageMapper.selectProSinmanageById(sfid);
	}
	
	/**
     * 查询节目播出单列表
     * 
     * @param proSinmanage 节目播出单信息
     * @return 节目播出单集合
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<ProSinmanage> selectProSinmanageList(ProSinmanage proSinmanage)
	{
	    return proSinmanageMapper.selectProSinmanageList(proSinmanage);
	}
	/**
	 * 查询节目播出单列表（村务）
	 *
	 * @param proSinmanage 节目播出单信息
	 * @return 节目播出单集合
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<ProSinmanage> selectProSinmanageList1(ProSinmanage proSinmanage)
	{
		return proSinmanageMapper.selectProSinmanageList1(proSinmanage);
	}
	/**
	 * 查询紧急节目播出单列表
	 *
	 * @param userid 节目播出单信息
	 * @return 节目播出单集合
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<ProSinmanage> selectProSinmanageListForWarning(Long userid){
		return proSinmanageMapper.selectProSinmanageListForWarning(userid);
	}
	
    /**
     * 新增节目播出单
     * 
     * @param proSinmanage 节目播出单信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int insertProSinmanage(ProSinmanage proSinmanage)
	{
	    proSinmanageMapper.insertProSinmanage(proSinmanage);
	    return proSinmanageMapper.selectLastInsertID();
	}
	
	/**
     * 修改节目播出单
     * 
     * @param proSinmanage 节目播出单信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateProSinmanage(ProSinmanage proSinmanage)
	{
	    return proSinmanageMapper.updateProSinmanage(proSinmanage);
	}

	/**
     * 删除节目播出单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteProSinmanageByIds(String ids)
	{
		return proSinmanageMapper.deleteProSinmanageByIds(Convert.toStrArray(ids));
	}


	/**
	 * 通过 sfid 查询节目编号List
	 * @author 张超 teavamc
	 * @date 2019/3/9
	 * @param
	 * @return java.util.List<com.sixin.broad.domain.ProSinmanage>
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<ProSinmanage> selectProSinmanageListbySfid(String sfid){
		return proSinmanageMapper.selectProSinmanageListbySfid(sfid);
	}

	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<ProSinmanage> selectProSinmanagebyoneday(){
		return proSinmanageMapper.selectProSinmanagebyoneday();
	}

	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<ProSinmanage> selectProSinmanageByTId(String tid) {
		return proSinmanageMapper.selectProSinmanageByTId(tid);
	}

	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int addProList(List<ProList> proList) {
		return proSinmanageMapper.addProList(proList);
	}

	@Override
	public int addProTerminals(List<ProTerminal> proTerminal) {
		return proSinmanageMapper.addProTerminals(proTerminal);
	}

	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<ProSinmanage> selectProSinmanageListByids(List<String> sfids) {
		return proSinmanageMapper.selectProSinmanageListByids(sfids);
	}
}
