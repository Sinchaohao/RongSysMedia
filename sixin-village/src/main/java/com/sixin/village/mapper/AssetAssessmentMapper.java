package com.sixin.village.mapper;

import com.sixin.village.domain.AssetAssessment;
import java.util.List;	

/**
 * 资产清查 数据层
 * 
 * @author 张鸿权
 * @date 2019-05-02
 */
public interface AssetAssessmentMapper 
{
	/**
     * 查询资产清查信息
     * 
     * @param aaid 资产清查ID
     * @return 资产清查信息
     */
	public AssetAssessment selectAssetAssessmentById(Integer aaid);
	
	/**
     * 查询资产清查列表
     * 
     * @param assetAssessment 资产清查信息
     * @return 资产清查集合
     */
	public List<AssetAssessment> selectAssetAssessmentList(AssetAssessment assetAssessment);
	
	/**
     * 新增资产清查
     * 
     * @param assetAssessment 资产清查信息
     * @return 结果
     */
	public int insertAssetAssessment(AssetAssessment assetAssessment);
	
	/**
     * 修改资产清查
     * 
     * @param assetAssessment 资产清查信息
     * @return 结果
     */
	public int updateAssetAssessment(AssetAssessment assetAssessment);
	
	/**
     * 删除资产清查
     * 
     * @param aaid 资产清查ID
     * @return 结果
     */
	public int deleteAssetAssessmentById(Integer aaid);
	
	/**
     * 批量删除资产清查
     * 
     * @param aaids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAssetAssessmentByIds(String[] aaids);

	/**
	 * @Author TanXY
	 * @Description
	 * @Date 2020/4/18 21:02
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.AssetAssessment>
	 */
	public List<AssetAssessment> selectAssetAssessmentByIds(List<String> id);

	
}