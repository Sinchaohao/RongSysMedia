package com.sixin.broad.service;
import com.sixin.broad.domain.Organization;
import com.sixin.broad.domain.Termap;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;

import java.util.List;

/**
 * 地图管理 服务层
 * 
 * @author 张鸿权
 * @date 2019-01-18
 */
public interface ITermapService
{
	/**
	 * 获取所有经纬度信息(不分页,用于excel导出)
	 * @param @param pd
	 * @return List<MapInfo>
	 */
	@DataSource(value = DataSourceType.SLAVE)
	List<Termap> selectMap(Organization organization);

	@DataSource(value = DataSourceType.SLAVE)
	List<Termap> getAllMapInfoForIot();
}
