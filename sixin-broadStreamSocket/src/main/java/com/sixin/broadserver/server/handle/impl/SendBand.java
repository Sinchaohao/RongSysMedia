package com.sixin.broadserver.server.handle.impl;

import com.sixin.broadserver.global.ProtocolsToClient;
import com.sixin.broadserver.server.handle.DefaultCommand;
import org.apache.mina.core.session.IoSession;


//发送终端紧急接收的频率
public class SendBand extends DefaultCommand {

	public SendBand(IoSession session, byte[] content) {
		super(session, content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] execute() {
		// TODO Auto-generated method stub
		if(content[4] == 49) {//命令为1
			String reinfo = (String)get(session);//获取频率信息
			return returnBytes(ProtocolsToClient.REQUEST, "1", reinfo,false);
		}else {
			return null;
		}
	}

	@Override
	public boolean save(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
