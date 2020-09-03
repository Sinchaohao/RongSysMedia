package com.sixin.broadserver.server.handle.impl;

import com.sixin.broad.domain.Organization;
import com.sixin.broadserver.domain.SocketInfo;
import com.sixin.broadserver.global.ProtocolsToClient;
import com.sixin.broadserver.server.MinaCastHandler;
import com.sixin.broadserver.server.handle.DefaultCommand;
import org.apache.mina.core.session.IoSession;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Register extends DefaultCommand {
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

    public Register(IoSession session, byte[] content) {
        super(session, content);
        // TODO Auto-generated constructor stub
    }

    @Override
    public byte[] execute() {
        // TODO Auto-generated method stub
        try{
            String command = save(get(datainfo))?"1":"0";//保存信息
            String data = df.format(new Date());//返回当前时间给终端
            loggersession();//插入日志
            return returnBytes(ProtocolsToClient.REGISTER, command, data,true);
        }catch (Exception e){
            logger.error("终端注册登录失败"+e);
        }
        return null;
    }

    @Override
    public boolean save(Object obj) {
        // TODO 保存终端信息到内存
        try{
            if(obj!= null){
                Organization ter = (Organization)obj;
                session.setAttribute(MinaCastHandler.CLIENTINFO, ter.getTid());
                SocketInfo info = getSocketInfoByIMEI(ter.getTid());
                if(info==null) {
                    info = new SocketInfo();
                    info.setImei(ter.getTid());
                    putClientToMap(info);

                }
                synchronized (info) {
                    info.setCommandSession(session);
                    info.setIpAddress(((InetSocketAddress) session.getRemoteAddress()).getAddress());
                    info.setType(ter.getTerminaltype());
                    info.setLastTime(new Date());
                }
                return true;
            }
        }catch (Exception e){
            logger.error("保存终端注册信息出错",e);
        }
        return  false;
    }

    @Override
    public Object get(Object obj) {//获通过IMEI取终端信息
        // TODO Auto-generated method stub
        return  organizationService.selectOrganizationByTid(obj.toString());
    }
}
