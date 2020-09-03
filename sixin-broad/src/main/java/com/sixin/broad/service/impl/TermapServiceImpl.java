package com.sixin.broad.service.impl;

import com.sixin.broad.domain.Organization;
import com.sixin.broad.domain.Termap;
import com.sixin.broad.mapper.TermapMapper;
import com.sixin.broad.service.ITermapService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;
/**
 * 地图管理 服务层
 *
 * @author 张鸿权
 * @date 2019-01-18
 */
@Service
public class TermapServiceImpl implements ITermapService
{
	@Autowired
	private TermapMapper termapMapper;
	/**
	 * 获取所有经纬度信息
	 * @param @param pd
	 * @return List<MapInfo>
	 */
	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Termap> selectMap(Organization organization)
	{
		List<Termap> menus = new LinkedList<Termap>();
			menus = termapMapper.getAllMapInfo(organization);
		return menus;
	}

	@Override
	@DataSource(value = DataSourceType.SLAVE)
	public List<Termap> getAllMapInfoForIot()
	{
		return termapMapper.getAllMapInfoForIot();
	}
}
