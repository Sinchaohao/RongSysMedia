package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.Mcount;
import com.sixin.village.domain.PersonApi;
import com.sixin.village.domain.VillagerInfo;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.mapper.VillagerInfoMapper;
import com.sixin.village.service.IVillagerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 村民 服务层实现
 *
 * @author 张鸿权
 * @date 2019-05-02
 */
@Service
public class VillagerInfoServiceImpl implements IVillagerInfoService
{
	@Autowired
	private VillagerInfoMapper villagerInfoMapper;

	/**
	 * 查询村民信息
	 *
	 * @param vid 村民ID
	 * @return 村民信息
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public VillagerInfo selectVillagerInfoById(Integer vid)
	{
		return villagerInfoMapper.selectVillagerInfoById(vid);
	}

	/**
	 * 在service层中将controller层获取的数据与mapper层读取的数据库的数据进行比较
	 * */
	@DataSource(value = DataSourceType.SXVILLAGE)
	@Override
	public VillagerInfo findByIdcardAndPassword(String idcard, String password) {
		VillagerInfo b = villagerInfoMapper.findByIdcardAndPassword(idcard);
		if (b==null) {
			return b;
		}
		String aString = b.getIdcard();
		String bString = b.getPassword();
		if (aString.equals(idcard)&&bString.equals(password)) {
			System.out.println(9);
			return b;
		} else {
			System.out.println(0);
			return b;
		}
	}

	/**
	 * 通过区域查询村名集合
	 * @param grouptype 区域
	 * @return 村民集合
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<VillagerInfo> selectVillagerInfoListBygrouptype(String grouptype){
		return villagerInfoMapper.selectVillagerInfoBygrouptype(grouptype);
	}


	/**
	 * 查询村民列表
	 *
	 * @param villagerInfo 村民信息
	 * @return 村民集合
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<VillagerInfo> selectVillagerInfoList(VillagerInfo villagerInfo)
	{
		return villagerInfoMapper.selectVillagerInfoList(villagerInfo);
	}

	/**
	 * 新增村民
	 *
	 * @param villagerInfo 村民信息
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertVillagerInfo(VillagerInfo villagerInfo)
	{
		return villagerInfoMapper.insertVillagerInfo(villagerInfo);
	}

	/**
	 * 修改村民
	 *
	 * @param villagerInfo 村民信息
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateVillagerInfo(VillagerInfo villagerInfo)
	{
		return villagerInfoMapper.updateVillagerInfo(villagerInfo);
	}
	/**
	 * 删除村民对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deleteVillagerInfoByIds(String ids)
	{
		return villagerInfoMapper.deleteVillagerInfoByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<VillagerInfo> selectPersonAllByApi(PersonApi person)
	{
		return villagerInfoMapper.selectPersonAllByApi(person);
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Mcount countpmBySex(PersonApi person)
	{
		return villagerInfoMapper.countpmBySex(person);
	}


	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<VillagerInfo> selectvillagerInfoListBytype(PersonApi person)
	{
		return villagerInfoMapper.selectvillagerInfoListBytype(person);
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<VillagerInfo> selectGrouplist(PersonApi person)
	{
		return villagerInfoMapper.selectGrouplist(person);
	}



	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Mcount countNewPre(pubObjApi per)
	{
		return villagerInfoMapper.countNewPre(per);
	}


	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<VillagerInfo> selectbycadre(PersonApi person)
	{
		return villagerInfoMapper.selectbycadre(person);
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Mcount countbylowincome(pubObjApi per)
	{
		return villagerInfoMapper.countbylowincome(per);
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Mcount countbydemob(pubObjApi per)
	{
		return villagerInfoMapper.countbydemob(per);
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Mcount countbyagePart(PersonApi per)
	{
		return villagerInfoMapper.countbyagePart(per);
	}


	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<VillagerInfo> selectVillagerInfoByIds(List<String> id)
	{
		return villagerInfoMapper.selectVillagerInfoByIds(id);
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int changeStatus(Integer vid,Integer isuse) {
		return villagerInfoMapper.updateIsuse(vid,isuse);
	}

}
