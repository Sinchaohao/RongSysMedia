package com.sixin.broadserver.server.handle.impl;

import com.sixin.broadserver.global.ProtocolsToClient;
import com.sixin.broadserver.server.handle.DefaultCommand;
import org.apache.mina.core.session.IoSession;

//获取终端RDS码/发射频率/发射功率/反射功率/工作电压（对系统只读）
public class ReadClientFM  extends DefaultCommand {

	public ReadClientFM(IoSession session, byte[] content) {
		super(session, content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] execute() {
		// TODO Auto-generated method stub
		String[] infos = datainfo.split(",");

		String command = save(infos)?"1":"0";//保存信息

		loggersession();//插入日志
		
		return returnBytes(ProtocolsToClient.CLIENTREAD, command, null,false);
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
