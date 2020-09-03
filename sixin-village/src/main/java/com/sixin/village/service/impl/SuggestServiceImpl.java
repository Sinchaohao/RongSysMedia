package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.Suggest;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.mapper.SuggestMapper;
import com.sixin.village.service.ISuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 我的建议 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-08-15
 */
@Service
public class SuggestServiceImpl implements ISuggestService 
{
	@Autowired
	private SuggestMapper suggestMapper;

	/**
     * 查询我的建议信息
     * 
     * @param mid 我的建议ID
     * @return 我的建议信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Suggest selectSuggestById(Integer mid)
	{
	    return suggestMapper.selectSuggestById(mid);
	}
	
	/**
     * 查询我的建议列表
     * 
     * @param suggest 我的建议信息
     * @return 我的建议集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Suggest> selectSuggestList(Suggest suggest)
	{
	    return suggestMapper.selectSuggestList(suggest);
	}
	
    /**
     * 新增我的建议
     * 
     * @param suggest 我的建议信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertSuggest(Suggest suggest)
	{
	    return suggestMapper.insertSuggest(suggest);
	}
	
	/**
     * 修改我的建议
     * 
     * @param suggest 我的建议信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateSuggest(Suggest suggest)
	{
	    return suggestMapper.updateSuggest(suggest);
	}

	/**
     * 删除我的建议对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deleteSuggestByIds(String ids)
	{
		return suggestMapper.deleteSuggestByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Suggest> selectSuggestByIds(List<String> id) {
		return suggestMapper.selectSuggestByIds(id);
	}


	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Suggest> selectSuggestListById(pubObjApi meet)
	{
		return suggestMapper.selectSuggestListById(meet);
	}
}
