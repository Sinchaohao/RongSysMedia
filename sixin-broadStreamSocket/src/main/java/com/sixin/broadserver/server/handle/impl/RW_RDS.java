package com.sixin.broadserver.server.handle.impl;

import com.sixin.broad.domain.Organization;
import com.sixin.broadserver.global.ProtocolsToClient;
import com.sixin.broadserver.server.handle.DefaultCommand;
import org.apache.mina.core.session.IoSession;
//读取并写入RDS码
public class RW_RDS extends DefaultCommand {
	public RW_RDS(IoSession session, byte[] content) {
		super(session, content);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] execute() {
		// TODO Auto-generated method stub
		String command = null;//保存信息
		String reinfo = null;//获取需要修改的码率频段，后续可进行修改
		try {
			String[] infos = datainfo.split(",");

			command = save(infos)?"1":"0";

			loggersession();//插入日志

			reinfo = get(datainfo).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(reinfo != null)
			return returnBytes(ProtocolsToClient.E_FM_RECIVE, command, reinfo,false);
		else
			return returnBytes(ProtocolsToClient.E_FM_RECIVE, command, datainfo,false);
	}
	
	@Override
	public boolean save(Object obj) {
		try {
			logger.info("获取终端应急rds码和调频频率："+obj);
			return true;
		} catch (Exception e) {
			logger.error("获取应急rds码和调频频率失败：",e);
		}
		return false;
	}

	@Override
	public Object get(Object obj) {
		// TODO Auto-generated method stub
		try {
			if(Tid != null) {
				Organization organization = organizationService.selectRdsByTid(Tid);
				if (organization != null) {
					String RDS = organization.getRds();
					String fm = organization.getFmfrequency();
					if (RDS != null && fm != null) {
						while (RDS.length() < 8) {
							RDS = "0" + RDS;
						}
						RDS = RDS.substring(0, 8);
						while (fm.length() < 5) {
							fm = "0" + fm;
						}
						fm = fm.substring(0, 5);
						return RDS + "," + fm;
					}
				}
			}
		} catch (Exception e) {
			logger.error("终端设置应急rds码和调频频率失败：",e);
		}
		return datainfo;
	}
}
