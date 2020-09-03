package com.sixin.village.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.village.mapper.VareaMapper;
import com.sixin.village.domain.Varea;
import com.sixin.village.service.IVareaService;
import com.sixin.common.support.Convert;

/**
 * 地域 服务层实现
 * 
 * @author 张超
 * @date 2019-01-20
 */
@Service
public class VareaServiceImpl implements IVareaService 
{
	@Autowired
	private VareaMapper vareaMapper;

	/**
     * 查询地域信息
     * 
     * @param aid 地域ID
     * @return 地域信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Varea selectVareaById(String aid)
	{
	    return vareaMapper.selectVareaById(aid);
	}
	
	/**
     * 查询地域列表
     * 
     * @param varea 地域信息
     * @return 地域集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Varea> selectVareaList(Varea varea)
	{
	    return vareaMapper.selectVareaList(varea);
	}
	
    /**
     * 新增地域
     * 
     * @param varea 地域信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertVarea(Varea varea)
	{
	    return vareaMapper.insertVarea(varea);
	}
	
	/**
     * 修改地域
     * 
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateVarea(Varea varea)
	{
	    return vareaMapper.updateVarea(varea);
	}

	/**
     * 删除地域对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deleteVareaByIds(String ids)
	{
		return vareaMapper.deleteVareaByIds(Convert.toStrArray(ids));
	}


	/**
	 * 获取到下面所有的 aid
	 * @param aid
	 * @return
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public  List<String> listNextAid(String aid){
		return vareaMapper.listNextAid(aid);
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public  List<Varea> listNextAidName(String aid){
		return vareaMapper.listNextAidName(aid);
	}
}
