package com.sixin.broadserver.server.handle.impl;

import com.sixin.broadserver.global.ProtocolsToClient;
import com.sixin.broadserver.server.handle.DefaultCommand;
import org.apache.mina.core.session.IoSession;

//启动流媒体传输
public class OpenStream extends DefaultCommand {

	public OpenStream(IoSession session, byte[] content) {
		super(session, content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] execute() {
		// TODO Auto-generated method stub
		try {
			String command = save(datainfo)?"1":"0";//保存信息

			loggersession();//插入日志

			return returnBytes(ProtocolsToClient.OPENSTREAM, command, "OK",false);
		} catch (Exception e) {
			logger.error("解析信息出错",e);
		}
		return null;
	}

	@Override
	public boolean save(Object obj) {
		// TODO Auto-generated method stub
		if(obj.equals("stream")) {
			//to do sth
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Object get(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
