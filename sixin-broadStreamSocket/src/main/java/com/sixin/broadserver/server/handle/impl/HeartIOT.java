package com.sixin.broadserver.server.handle.impl;

import com.sixin.broadserver.domain.SocketInfo;
import com.sixin.broadserver.global.ProtocolsToClient;
import com.sixin.broadserver.server.MinaCastHandler;
import com.sixin.broadserver.server.handle.DefaultCommand;
import org.apache.mina.core.session.IoSession;

//物联网终端参数透传接口（终端主动请求获取，端口:8900）
public class HeartIOT extends DefaultCommand {

	public HeartIOT(IoSession session, byte[] content) {
		super(session, content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] execute() {
		// TODO Auto-generated method stub
		try {
			//logger.info("终端主动请求获取");
			String command = save(datainfo)?"1":"0";//保存信息

			loggersession();//插入日志
			return returnBytes(ProtocolsToClient.GETPARAMATER, command, "ok",false);
		} catch (Exception e) {
			logger.error("解析信息出错",e);
		}
		return null;
	}

	@Override
	public boolean save(Object obj) {
		// TODO Auto-generated method stub
		session.setAttribute(MinaCastHandler.CLIENTINFO, obj);
		SocketInfo info = getSocketInfoByIMEI(obj.toString());
		if(info != null){
			synchronized (info){
				info.setIotSession(session);
			}
		}else{

		}
		return true;
	}

	@Override
	public Object get(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
