package com.sixin.broadserver.server.handle.impl;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.sixin.broadserver.global.ProtocolsToClient;
import com.sixin.broadserver.server.handle.DefaultCommand;
import com.sixin.common.utils.spring.SpringUtils;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.service.ITorrentService;
import com.sixin.iot.service.impl.TorrentServiceImpl;
import org.apache.mina.core.session.IoSession;

import java.net.InetSocketAddress;
import java.util.Date;


//物联网终端参数透传接口 终端回传参数
public class ReadIOT extends DefaultCommand {
	private ITorrentService torrentService = (TorrentServiceImpl) SpringUtils.getBean(TorrentServiceImpl.class);
	public ReadIOT(IoSession session, byte[] content) {
		super(session, content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] execute() {
		// TODO Auto-generated method stub
		String[] infos = new String(content).split(",");
		logger.info(new String(content));
		logger.info(Tid);
		save(infos);//保存信息
		loggersession();//插入日志
		
		return null;
	}

	@Override
	public boolean save(Object obj) {
		// TODO Auto-generated method stub
		String[] datas = (String[]) obj;
		Torrent torrent = new Torrent();
		datainfo = "山洪灾害数据获取:	IP:"+((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
		for(int i = 3;i<datas.length;i++) {
			if(datas[i].equals("cc")) {
				break;
			}
			datainfo += "	"+ ProtocolsToClient.TORRENTDATA[(i-3)]+": "+datas[i];
		}
		torrent.setImei(Tid);
		torrent.setCtype(((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress());
		torrent.setUid(Long.valueOf(datas[4]));
		torrent.setDelflag(datas[5]);
		torrent.setExdate(new Date());
		torrent.setDemo(datas[11]);
		torrent.setConip(datas[12]);
		torrent.setStartdate(new Date());
		try {
			torrentService.insertTorrent(torrent);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return false;
	}

	@Override
	public Object get(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
