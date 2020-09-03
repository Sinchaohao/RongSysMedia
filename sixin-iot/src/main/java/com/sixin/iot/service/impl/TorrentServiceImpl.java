package com.sixin.iot.service.impl;

import java.util.List;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.iot.domain.IotgetPa;
import com.sixin.iot.domain.Iotype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.iot.mapper.TorrentMapper;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.service.ITorrentService;
import com.sixin.common.support.Convert;

/**
 * 终端运转 服务层实现
 *
 * @author 张超
 * @date 2019-03-03
 */
@Service

public class TorrentServiceImpl implements ITorrentService
{
	@Autowired
	private TorrentMapper torrentMapper;

	/**
	 * 查询终端运转信息
	 *
	 * @param id 终端运转ID
	 * @return 终端运转信息
	 */

	@Override
	@DataSource(value = DataSourceType.SXINFOM)
	public List<Torrent> selectTorrentList(Torrent torrent)
	{
		return torrentMapper.selectTorrentList(torrent);
	}



	@Override
	@DataSource(value = DataSourceType.SXINFOM)
	public List<Torrent> selectTorrentByids(List<String> sfids)
	{
		return torrentMapper.selectTorrentByids(sfids);
	}

	@Override
	@DataSource(value = DataSourceType.SXINFOM)
	public List<String> getTypes() {
		return torrentMapper.getTypes();
	}

	/**
	 * 新增终端运转
	 *
	 * @param torrent 终端运转信息
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SXINFOM)
	public int insertTorrent(Torrent torrent)
	{
		return torrentMapper.insertTorrent(torrent);
	}

	/**
	 * 修改终端运转
	 *
	 * @param torrent 终端运转信息
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.SXINFOM)
	public int updateTorrent(Torrent torrent)
	{
		return torrentMapper.updateTorrent(torrent);
	}

	@Override
	@DataSource(value = DataSourceType.SXINFOM)
	public Torrent selectByid(Long id) {
		return torrentMapper.selectByid(id);
	}

	@Override
	@DataSource(value = DataSourceType.SXINFOM)
	public int deleteTorrentByids(String ids) {
		return torrentMapper.deleteTorrentByids(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(value = DataSourceType.SXINFOM)
	public int changeStatus(Torrent torrent) {
		return torrentMapper.updateTorrent(torrent);
	}

	/**
	 * 统计物联网数据条目
	 *
	 * @param id 终端运转ID
	 * @return 终端运转信息
	 */
	@Override
	@DataSource(value = DataSourceType.SXINFOM)
	public int countall(){
		return torrentMapper.countall();
	}

	/**
	 * 统计物联网设备数量
	 *
	 * @param id 终端运转ID
	 * @return 终端运转信息
	 */
	@Override
	@DataSource(value = DataSourceType.SXINFOM)
	public int devicecount(){
		return torrentMapper.devicecount();
	}

	@Override
	@DataSource(value = DataSourceType.SXINFOM)
	public int batchInsertTorrent(List<Torrent> list){
		return torrentMapper.batchInsertTorrent(list);
	}

	@Override
	@DataSource(value = DataSourceType.SXINFOM)
	public List<Torrent> selectTorrentListByids(List<String> sfids){
		return torrentMapper.selectTorrentListByids(sfids);
	}

	@Override
	@DataSource(value = DataSourceType.SXINFOM)
	public List<Iotype> selectList(Iotype iotype) {return torrentMapper.selectList(iotype);}

	@Override
	public int onlineOrDisOnline(Torrent torrent) {
		return torrentMapper.onlineOrDisOnline(torrent);
	}
}
