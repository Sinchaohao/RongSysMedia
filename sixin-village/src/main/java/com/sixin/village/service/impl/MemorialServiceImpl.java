package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.Memorial;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.mapper.MemorialMapper;
import com.sixin.village.service.IMemorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 备忘录 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-08-15
 */
@Service
public class MemorialServiceImpl implements IMemorialService 
{
	@Autowired
	private MemorialMapper memorialMapper;

	/**
     * 查询备忘录信息
     * 
     * @param mid 备忘录ID
     * @return 备忘录信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Memorial selectMemorialById(Integer mid)
	{
	    return memorialMapper.selectMemorialById(mid);
	}
	
	/**
     * 查询备忘录列表
     * 
     * @param memorial 备忘录信息
     * @return 备忘录集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Memorial> selectMemorialList(Memorial memorial)
	{
	    return memorialMapper.selectMemorialList(memorial);
	}
	
    /**
     * 新增备忘录
     * 
     * @param memorial 备忘录信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertMemorial(Memorial memorial)
	{
	    return memorialMapper.insertMemorial(memorial);
	}
	
	/**
     * 修改备忘录
     * 
     * @param memorial 备忘录信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateMemorial(Memorial memorial)
	{
	    return memorialMapper.updateMemorial(memorial);
	}

	/**
     * 删除备忘录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deleteMemorialByIds(String ids)
	{
		return memorialMapper.deleteMemorialByIds(Convert.toStrArray(ids));
	}

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/17 22:31
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.Memorial>
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Memorial> selectMemorialByIds(List<String> id) {
		return memorialMapper.selectMemorialByIds(id);
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Memorial> selectMemorialListById(pubObjApi memorial)
	{
		return memorialMapper.selectMemorialListById(memorial);
	}
}
