package com.sixin.village.service;

import com.sixin.village.domain.Company;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * 企业 服务层
 *
 * @author 张鸿权
 * @date 2019-08-08
 */
public interface ICompanyService
{
	/**
	 * 查询企业信息
	 *
	 * @param cid 企业ID
	 * @return 企业信息
	 */
	public Company selectCompanyById(Integer cid);

	/**
	 * 查询企业列表
	 *
	 * @param company 企业信息
	 * @return 企业集合
	 */
	public List<Company> selectCompanyList(Company company);

	/**
	 * 新增企业
	 *
	 * @param company 企业信息
	 * @return 结果
	 */
	public int insertCompany(Company company);

	/**
	 * 修改企业
	 *
	 * @param company 企业信息
	 * @return 结果
	 */
	public int updateCompany(Company company);

	/**
	 * 删除企业信息
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteCompanyByIds(String ids);

	public List<Company> selectCompanyListById(pubObjApi company);


	public List<Company> selectCompanyByIds(List<String> sfids);



}
