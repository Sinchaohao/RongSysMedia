package com.sixin.iot.service;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.iot.domain.IotgetPa;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Torrent;
import java.util.List;

/**
 * 终端运转 服务层
 *
 * @author 张超
 * @date 2019-03-03
 */
public interface ITorrentService
{
	public Torrent selectByid(Long id);

	/**
	 * 查询终端运转列表
	 *
	 * @param torrent 终端运转信息
	 * @return 终端运转集合
	 */
	public List<Torrent> selectTorrentList(Torrent torrent);
	public List<Torrent> selectTorrentByids(List<String> sfids);
	public List<String> getTypes();
	/**
	 * 新增终端运转
	 *
	 * @param torrent 终端运转信息
	 * @return 结果
	 */
	public int insertTorrent(Torrent torrent);

	/**
	 * 修改终端运转
	 *
	 * @param torrent 终端运转信息
	 * @return 结果
	 */
	public int updateTorrent(Torrent torrent);

	/**
	 * 删除终端运转信息
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	public int changeStatus(Torrent torrent);
	public int deleteTorrentByids(String ids);

	/**
	 * 统计物联网数据条目
	 *
	 * @param id 终端运转ID
	 * @return 终端运转信息
	 */
	public int countall();
	public List<Iotype> selectList(Iotype iotype);
	/**
	 * 统计物联网设备数量
	 *
	 * @param id 终端运转ID
	 * @return 终端运转信息
	 */
	public int devicecount();

	public int batchInsertTorrent(List<Torrent> list);

	public List<Torrent> selectTorrentListByids(List<String> sfids);

	/**
	 * 终端设备上下线
	 * @param torrent
	 * @return
	 */
	public int onlineOrDisOnline(Torrent torrent);
}
