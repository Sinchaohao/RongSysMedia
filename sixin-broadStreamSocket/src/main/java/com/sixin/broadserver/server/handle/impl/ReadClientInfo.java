package com.sixin.broadserver.server.handle.impl;

import com.sixin.broad.domain.Conditions;
import com.sixin.broadserver.global.ProtocolsToClient;
import com.sixin.broadserver.server.handle.DefaultCommand;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

//获取终端硬件信息状态
public class ReadClientInfo extends DefaultCommand {

    public ReadClientInfo(IoSession session, byte[] content) {
        super(session, content);
        // TODO Auto-generated constructor stub
    }

    @Override
    public byte[] execute() {
        // TODO Auto-generated method stub
        try{
            //logger.info("终端主动请求获取");
            String command = save(datainfo)?"1":"0";//保存信息

            loggersession();//插入日志

            return returnBytes(ProtocolsToClient.STATU, command, null,true);
        }catch (Exception e){
            logger.error("解析信息出错",e);
        }
        return  null;
    }

    @Override
    public boolean save(Object obj) {
        // TODO Auto-generated method stub
        try {
            if(Tid != null) {
                Conditions conditions = conditionsService.selectConditionsById(Tid);
                if(conditions != null){
                    dispose(conditions,obj);
                    conditionsService.updateConditions(conditions);
                    logger.info("更新终端配置信息");
                }else{
                    conditions =  dispose(new Conditions(new Date(),Tid),obj);
                    conditionsService.insertConditions(conditions);
                    logger.info("添加终端配置信息");
                }
                return true;
            }
        } catch (Exception e) {
           logger.error("保存终端硬件信息出错："+e);
        }
        return false;
    }

    @Override
    public Object get(Object obj) {
        // TODO Auto-generated method stub
        return null;
    }
    private  Conditions dispose(Conditions conditions,Object obj){
        String[] mylist = obj.toString().split("，");
        for (int i = 0; i < mylist.length; i++)
        {
            String[] myy = mylist[i].split("：");
            switch (myy[0])
            {
                case "硬件版本":
                    conditions.setHversion( myy[1].trim());
                    break;
                case "软件版本":
                    conditions.setSversion(myy[1].trim());
                    break;
                case "内核温度":
                    conditions.setTemp(myy[1].trim());
                    break;
                case "网络类型":
                    conditions.setNettype(myy[1].trim());
                    break;
                case "信号质量":
                    conditions.setQsignal(myy[1].trim());
                    break;
                case "SD卡容量":
                    conditions.setSd(myy[1].trim());
                    break;
                case "发射功率":
                    conditions.setTransmitpower(myy[1]==null||myy[1].equals("")?0:Integer.parseInt(myy[1].trim()));
                    break;
                case "反射功率":
                    conditions.setReflectedpower(myy[1]==null||myy[1].equals("")?0:Integer.parseInt(myy[1].trim()));
                    break;
                case "工作电压":
                    conditions.setWorkvoltage(myy[1]==null||myy[1].equals("")?0:Integer.parseInt(myy[1].trim()));
                    break;
            }
        }
        conditions.setupdatetime(new Date());
        return conditions;
    }
}
