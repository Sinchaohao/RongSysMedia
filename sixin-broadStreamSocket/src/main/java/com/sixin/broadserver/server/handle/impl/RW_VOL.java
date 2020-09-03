package com.sixin.broadserver.server.handle.impl;

import com.sixin.broad.domain.Conditions;
import com.sixin.broadserver.global.ProtocolsToClient;
import com.sixin.broadserver.server.MinaCastHandler;
import com.sixin.broadserver.server.handle.DefaultCommand;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

//终端设置音量
public class RW_VOL extends DefaultCommand {

	public RW_VOL(IoSession session, byte[] content) {
		super(session, content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] execute() {
		// TODO Auto-generated method stub
		/**获取修改的音量*/
		String reinfo = (String)get(dataresourecs);

		/**保存音量或者执行对应操作*/
		String command = save("")?"1":"0";

		/**插入日志*/
		loggersession();

		return returnBytes(ProtocolsToClient.VOL, command, reinfo,true);
	}

	@Override
	public boolean save(Object obj) {
		// TODO Auto-generated method stub
		try {
			if(dataresourecs.length == 4 && session.getAttribute(MinaCastHandler.TID).toString() != null){
				Conditions conditions = new Conditions(session.getAttribute(MinaCastHandler.TID).toString());
				conditions.setMp3(String.valueOf((int)dataresourecs[0]));
				conditions.setFm(String.valueOf((int)dataresourecs[1]));
				conditions.setGsm(String.valueOf((int)dataresourecs[2]));
				conditions.setNullv(String.valueOf((int)dataresourecs[3]));
				conditions.setupdatetime(new Date());
				conditionsService.updateConditions(conditions);
				return true;
			}
		} catch (Exception e) {
			logger.error("保存终端音量值出错：",e);
		}
		return false;
	}

	@Override
	public Object get(Object obj) {
		// TODO Auto-generated method stub
		try {
			if(session.getAttribute(MinaCastHandler.TID).toString() != null) {
				Conditions conditions = conditionsService.selectConditionsById(session.getAttribute(MinaCastHandler.TID).toString());
				byte[] vols = new byte[4];
				vols[0] = conditions.getMp3() != null?(byte) Integer.parseInt(conditions.getMp3()):(byte)0;
				vols[1] = conditions.getFm() != null?(byte) Integer.parseInt(conditions.getFm()):(byte)0;
				vols[2] = conditions.getGsm() != null?(byte) Integer.parseInt(conditions.getGsm()):(byte)0;
				vols[3] = conditions.getNullv() != null?(byte) Integer.parseInt(conditions.getNullv()):(byte)0;
				return new String(vols,GBK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}