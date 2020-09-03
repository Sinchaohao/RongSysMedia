package com.sixin.broadserver.server.handle.impl;

import com.sixin.broadserver.server.handle.DefaultCommand;
import org.apache.mina.core.session.IoSession;

import com.sixin.broadserver.global.ProtocolsToClient;
//紧急界面播出回执（新建连接）
public class BroadCast extends DefaultCommand {

	public BroadCast(IoSession session, byte[] content) {
		super(session, content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] execute() {
		// TODO Auto-generated method stub
		try {
			String[] infos = datainfo.split(",");
			String command = save(infos)?"1":"0";//保存信息并执行对应操作
			loggersession();//插入日志
			return returnBytes(ProtocolsToClient.BROADCAST, command, null,false);
		} catch (Exception e) {
			logger.error("解析信息出错",e);
		}
		return null;
	}

	@Override
	public boolean save(Object obj) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object get(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
