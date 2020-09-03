package com.sixin.broadserver.server.handle.impl;


import com.sixin.broadserver.global.ProtocolsToClient;
import com.sixin.broadserver.server.handle.DefaultCommand;
import org.apache.mina.core.session.IoSession;


//获取节目的下载播出，短信播出，重启的次数
public class ReadProInfo extends DefaultCommand {
   // private ClientManager clientservice = (ClientService) SpringContextUtils.getBeanByClass(ClientService.class);

	public ReadProInfo(IoSession session, byte[] content) {
		super(session, content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] execute() {
		// TODO Auto-generated method stub
		try{
			String[] infos = datainfo.split(",");
			String command = save(infos)?"1":"0";//保存信息

			loggersession();//插入日志

			return returnBytes(ProtocolsToClient.PROGINFO, command, null,false);
		}catch (Exception e){
			logger.error("解析信息出错",e);
		}
		return  null;
	}

	@Override
	public boolean save(Object obj) {
		// TODO Auto-generated method stub
		/*String[] datas = (String[]) obj;
		try {
			Client client = new Client();
			client.setDownloads(datas[0]);
			client.setPoints(datas[1]);
			client.setNotebroads(datas[2]);
			client.setReboots(datas[3]);
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
