package com.sixin.village.service.impl;
/**
 * @function:舆情上报 服务层实现
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:刘波
 * @Description:
 * @Date:Created in 2020-01-28 14:38
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.Yuqing;
import com.sixin.village.mapper.YuqingMapper;
import com.sixin.village.service.IYuqingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class YuqingServiceImpl implements IYuqingService
{
	@Autowired
	private YuqingMapper yuqingMapper;

	/**
	 * 查询舆情上报信息
	 *
	 * @param yqid 舆情上报ID
	 * @return 舆情上报信息
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Yuqing selectYuqingById(Integer yqid)
	{
		return yuqingMapper.selectYuqingById(yqid);
	}

	/**
	 * 查询舆情上报列表
	 *
	 * @param yuqing 舆情上报信息
	 * @return 舆情上报集合
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Yuqing> selectYuqingList(Yuqing yuqing)
	{
		return yuqingMapper.selectYuqingList(yuqing);
	}

	/**
	 * 新增舆情上报
	 *
	 * @param yuqing 舆情上报信息
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertYuqing(Yuqing yuqing)
	{
		return yuqingMapper.insertYuqing(yuqing);
	}

	/**
	 * 修改舆情上报
	 *
	 * @param yuqing 舆情上报信息
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateYuqing(Yuqing yuqing)
	{
		return yuqingMapper.updateYuqing(yuqing);
	}

	/**
	 * 删除舆情上报对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deleteYuqingByIds(String ids)
	{
		return yuqingMapper.deleteYuqingByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateIsuseByYqid(String tid, String remark)
	{
		return yuqingMapper.updateIsuseByYqid(tid,remark);
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Yuqing> selectYuqingByIds(List<String> id)
	{
		return yuqingMapper.selectYuqingByIds(id);
	}

}
