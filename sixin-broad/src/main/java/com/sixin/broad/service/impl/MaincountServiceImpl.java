package com.sixin.broad.service.impl;

import com.sixin.broad.domain.Maincount;
import com.sixin.broad.mapper.MaincountMapper;
import com.sixin.broad.service.IMaincountService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 终端维护记录统计 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-01-15
 */
@Service
public class MaincountServiceImpl implements IMaincountService
{
	@Autowired
	private MaincountMapper maincountMapper;

	/**
     * 查询终端维护记录信息
     * 
     * @param tmid 终端维护记录ID
     * @return 终端维护记录信息
     */
    @Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Maincount> selectMaincountById(String tmid)
	{
	    return maincountMapper.selectMaincountById(tmid);
	}
	
	/**
     * 查询终端维护记录列表
     * 
     * @param maincount 终端维护记录信息
     * @return 终端维护记录集合
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Maincount> selectMaincountList(Maincount maincount)
	{
	    return maincountMapper.selectMaincountList(maincount);
	}
	
    /**
     * 新增终端维护记录
     *
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int insertMaincount(String tid,String fault,String mstaff,String remark)
	{
	    return maincountMapper.insertMaincount(tid,fault,mstaff,remark);
	}
	
	/**
     * 修改终端维护记录
     * 
     * @param maincount 终端维护记录信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int updateMaincount(Maincount maincount)
	{
	    return maincountMapper.updateMaincount(maincount);
	}

	/**
     * 删除终端维护记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public int deleteMaincountByIds(String ids)
	{
		return maincountMapper.deleteMaincountByIds(Convert.toStrArray(ids));
	}
}
