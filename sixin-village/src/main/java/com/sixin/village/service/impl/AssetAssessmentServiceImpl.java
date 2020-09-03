package com.sixin.village.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.support.Convert;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.village.mapper.AssetAssessmentMapper;
import com.sixin.village.domain.AssetAssessment;
import com.sixin.village.service.IAssetAssessmentService;

/**
 * 资产清查 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
@Service
public class AssetAssessmentServiceImpl implements IAssetAssessmentService 
{
	@Autowired
	private AssetAssessmentMapper assetAssessmentMapper;

	/**
     * 查询资产清查信息
     * 
     * @param aaid 资产清查ID
     * @return 资产清查信息
     */
    @Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public AssetAssessment selectAssetAssessmentById(Integer aaid)
	{
	    return assetAssessmentMapper.selectAssetAssessmentById(aaid);
	}
	
	/**
     * 查询资产清查列表
     * 
     * @param assetAssessment 资产清查信息
     * @return 资产清查集合
     */
	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public List<AssetAssessment> selectAssetAssessmentList(AssetAssessment assetAssessment)
	{
	    return assetAssessmentMapper.selectAssetAssessmentList(assetAssessment);
	}
	
    /**
     * 新增资产清查
     * 
     * @param assetAssessment 资产清查信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public int insertAssetAssessment(AssetAssessment assetAssessment)
	{
	    return assetAssessmentMapper.insertAssetAssessment(assetAssessment);
	}
	
	/**
     * 修改资产清查
     * 
     * @param assetAssessment 资产清查信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public int updateAssetAssessment(AssetAssessment assetAssessment)
	{
	    return assetAssessmentMapper.updateAssetAssessment(assetAssessment);
	}

	/**
     * 删除资产清查对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXBAODIAN)
	public int deleteAssetAssessmentByIds(String ids)
	{
		return assetAssessmentMapper.deleteAssetAssessmentByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(DataSourceType.SXVILLAGE)
	public List<AssetAssessment> selectAssetAssessmentByIds(List<String> id) {
		return assetAssessmentMapper.selectAssetAssessmentByIds(id);
	}

}
