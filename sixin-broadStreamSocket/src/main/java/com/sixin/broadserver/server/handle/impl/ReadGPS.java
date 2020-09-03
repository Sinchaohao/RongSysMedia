package com.sixin.broadserver.server.handle.impl;

import com.sixin.broadserver.global.ProtocolsToClient;
import com.sixin.broadserver.server.handle.DefaultCommand;
import org.apache.mina.core.session.IoSession;


//终端经纬度信息
public class ReadGPS extends DefaultCommand {
    //private ClientManager clientservice = (ClientService) SpringContextUtils.getBeanByClass(ClientService.class);

	public ReadGPS(IoSession session, byte[] content) {
		super(session, content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] execute() {
		// TODO Auto-generated method stub
		String[] infos = datainfo.split(",");

		String command = save(infos)?"1":"0";//保存信息

		loggersession();//插入日志
		
		return returnBytes(ProtocolsToClient.POSITION, command, null,false);
	}

	@Override
	public boolean save(Object obj) {
		// TODO Auto-generated method stub
		String[] datas = (String[]) obj;
		/*try {
			Client client = new Client();
			client.setLongitude(datas[0]);
			client.setLatitude(datas[1]);
			client.setIsregister(true);
			client.setIMEI((String)session.getAttribute(MinaCastHandler.CLIENTINFO));
			client.setFinallogindate(new Date());
			clientservice.updateClient(client);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}*/
		return false;
	}

	@Override
	public Object get(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
