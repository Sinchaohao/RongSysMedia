package com.sixin.broad.service.impl;

import java.util.List;

import com.sixin.broad.domain.Maintainx;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.broad.mapper.MaintainMapper;
import com.sixin.broad.domain.Maintain;
import com.sixin.broad.service.IMaintainService;
import com.sixin.common.support.Convert;

/**
 * 终端维护记录 服务层实现
 * 
 * @author 张超
 * @date 2019-01-15
 */
@Service
public class MaintainServiceImpl implements IMaintainService 
{
	@Autowired
	private MaintainMapper maintainMapper;

	/**
     * 查询终端维护记录信息
     * 
     * @param tmid 终端维护记录ID
     * @return 终端维护记录信息
     */
    @Override
	@DataSource(value = DataSourceType.SLAVE)
	public Maintain selectMaintainById(Integer tmid)
	{
	    return maintainMapper.selectMaintainById(tmid);
	}
	
	/**
     * 查询终端维护记录列表
     * 
     * @param maintain 终端维护记录信息
     * @return 终端维护记录集合
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Maintain> selectMaintainList(Maintain maintain)
	{
	    return maintainMapper.selectMaintainList(maintain);
	}
	
    /**
     * 新增终端维护记录
     *
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int insertMaintain(Maintain maintain)
	{
	    return maintainMapper.insertMaintain(maintain);
	}
	
	/**
     * 修改终端维护记录
     * 
     * @param maintain 终端维护记录信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateMaintain(Maintain maintain)
	{
	    return maintainMapper.updateMaintain(maintain);
	}

	/**
     * 删除终端维护记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteMaintainByIds(String ids)
	{
		return maintainMapper.deleteMaintainByIds(Convert.toStrArray(ids));
	}


	/**
	 * 连表查询设备维修记录和经纬度
	 * @author 张超 teavamc
	 * @date 2019/1/25
	 * @param []
	 * @return java.util.List<com.sixin.broad.domain.Maintainx>
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Maintainx> selectlal(){
		return maintainMapper.selectlal();
	}
	
}
