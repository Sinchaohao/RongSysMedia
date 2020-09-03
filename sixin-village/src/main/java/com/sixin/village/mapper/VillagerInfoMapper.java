package com.sixin.village.mapper;

import com.sixin.village.domain.Mcount;
import com.sixin.village.domain.PersonApi;
import com.sixin.village.domain.VillagerInfo;
import com.sixin.village.domain.pubObjApi;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 村民 数据层
 *
 * @author 张鸿权
 * @date 2019-05-02
 */
public interface VillagerInfoMapper
{
	/**
	 * 查询村民信息
	 *
	 * @param vid 村民ID
	 * @return 村民信息
	 */
	public VillagerInfo selectVillagerInfoById(Integer vid);

	/**
	 * 使用jpa,程序运行时自动识别findBy后面的字段 查询idcard并获取与idcard匹配的信息
	 *
	 * @return*/
	public VillagerInfo findByIdcardAndPassword(@Param(value = "idcard")String idcard);

	/**
	 * 通过区域查询村名集合
	 * @param grouptype 区域
	 * @return 村民集合
	 */
	public List<VillagerInfo> selectVillagerInfoBygrouptype(@Param(value="aid") String aid);

	/**
	 * 查询村民列表
	 *
	 * @param villagerInfo 村民信息
	 * @return 村民集合
	 */
	public List<VillagerInfo> selectVillagerInfoList(VillagerInfo villagerInfo);

	/**
	 * 新增村民
	 *
	 * @param villagerInfo 村民信息
	 * @return 结果
	 */
	public int insertVillagerInfo(VillagerInfo villagerInfo);

	/**
	 * 修改村民
	 *
	 * @param villagerInfo 村民信息
	 * @return 结果
	 */
	public int updateVillagerInfo(VillagerInfo villagerInfo);

	/**
	 * 删除村民
	 *
	 * @param vid 村民ID
	 * @return 结果
	 */
	public int deleteVillagerInfoById(Integer vid);

	/**
	 * 批量删除村民
	 *
	 * @param vids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteVillagerInfoByIds(String[] vids);

	public List<VillagerInfo> selectPersonAllByApi(PersonApi person);

	public Mcount countpmBySex(PersonApi person);

	public List<VillagerInfo> selectvillagerInfoListBytype(PersonApi person);

	public List<VillagerInfo> selectGrouplist(PersonApi person);

	public Mcount countNewPre(pubObjApi per);

	public List<VillagerInfo> selectbycadre(PersonApi person);

	public Mcount countbylowincome(pubObjApi per);

	public Mcount countbydemob(pubObjApi per);

	public Mcount countbyagePart(PersonApi per);

	public List<VillagerInfo> selectVillagerInfoByIds(List<String> id);

	public int updateIsuse(@Param("vid")Integer vid,@Param("isuse")Integer isuse);
}