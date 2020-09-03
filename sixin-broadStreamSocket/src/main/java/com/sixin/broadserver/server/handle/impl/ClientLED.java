package com.sixin.broadserver.server.handle.impl;

import com.sixin.broadserver.global.ProtocolsToClient;
import com.sixin.broadserver.server.handle.DefaultCommand;
import org.apache.mina.core.session.IoSession;

//LED字幕更新（终端主动请求获取）
public class ClientLED extends DefaultCommand {

	public ClientLED(IoSession session, byte[] content) {
		super(session, content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] execute() {
		// TODO Auto-generated method stub
		try {
			String command = save(datainfo)?"1":"0";//保存信息

			loggersession();//插入日志

			String returnStr = (String)get(session);
			return returnBytes(ProtocolsToClient.UPDATELED, command, returnStr,false);
		} catch (Exception e) {
			logger.error("解析信息出错",e);
		}
		return  null;
	}

	@Override
	public boolean save(Object obj) {
		// TODO Auto-generated method stub
		if(obj.equals("LED")) {
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
