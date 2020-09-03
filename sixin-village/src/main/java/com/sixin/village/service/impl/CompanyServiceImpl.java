package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.Company;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.mapper.CompanyMapper;
import com.sixin.village.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业 服务层实现
 *
 * @author 张鸿权
 * @date 2019-08-08
 */
@Service
public class CompanyServiceImpl implements ICompanyService
{
	@Autowired
	private CompanyMapper companyMapper;

	/**
	 * 查询企业信息
	 *
	 * @param cid 企业ID
	 * @return 企业信息
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Company selectCompanyById(Integer cid)
	{
		return companyMapper.selectCompanyById(cid);
	}

	/**
	 * 查询企业列表
	 *
	 * @param company 企业信息
	 * @return 企业集合
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Company> selectCompanyList(Company company)
	{
		return companyMapper.selectCompanyList(company);
	}

	/**
	 * 新增企业
	 *
	 * @param company 企业信息
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertCompany(Company company)
	{
		return companyMapper.insertCompany(company);
	}

	/**
	 * 修改企业
	 *
	 * @param company 企业信息
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateCompany(Company company)
	{
		return companyMapper.updateCompany(company);
	}

	/**
	 * 删除企业对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deleteCompanyByIds(String ids)
	{
		return companyMapper.deleteCompanyByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Company> selectCompanyListById(pubObjApi company)
	{
		return companyMapper.selectCompanyListById(company);
	}


	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Company> selectCompanyByIds(List<String> sfids){

		return companyMapper.selectCompanyByIds(sfids);
	}
}
