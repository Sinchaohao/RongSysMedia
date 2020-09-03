package com.sixin.broadserver.server.handle.impl;

import com.sixin.broad.domain.Conditions;
import com.sixin.broad.domain.Organization;
import com.sixin.broad.domain.TerminalTels;
import com.sixin.broadserver.global.ProtocolsToClient;
import com.sixin.broadserver.server.handle.DefaultCommand;
import org.apache.mina.core.session.IoSession;

import java.util.List;

public class RW_Time extends DefaultCommand {
    public RW_Time(IoSession session, byte[] content) {
        super(session, content);
    }

    @Override
    public byte[] execute() {
        try{
            ;
            String command = save(datainfo)?"1":"0";//保存信息

            String data = get(datainfo).toString();//返回信息
            if(data != null)
                logger.info("获取数据库终端交互时间："+data);
            else
                logger.info("获取数据库终端交互时间为空");
            loggersession();//插入日志
            return returnBytes(ProtocolsToClient.MUTUALTIME, command, data == null?datainfo:data,true);
        }catch (Exception e){
            logger.error("终端更新交互时间失败"+e);
        }
        return null;
    }

    @Override
    public boolean save(Object obj) {
        try {
            logger.info("获取终端当前交互时间设置："+obj);
            return true;
        } catch (Exception e) {
            logger.error("获取终端当前交互时间失败：",e);
        }
        return false;
    }

    @Override
    public Object get(Object obj) {
        try {
            if(Tid != null){
                Organization organization = organizationService.selectOrganizationByTid(Tid);
                if(organization != null){
                    return organization.getIntertime();
                }
            }else{
                logger.info("TID 为空");
            }
        } catch (Exception e) {
            logger.error("获取终端交互时间出错：",e);
        }
        return datainfo;
    }
}
