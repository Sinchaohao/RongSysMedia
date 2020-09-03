package com.sixin.broadserver.server.handle.impl;

import com.sixin.broad.domain.*;
import com.sixin.broad.service.IProListService;
import com.sixin.broad.service.IProSinmanageService;
import com.sixin.broad.service.IProgramService;
import com.sixin.broad.service.impl.ProListServiceImpl;
import com.sixin.broad.service.impl.ProSinmanageServiceImpl;
import com.sixin.broad.service.impl.ProgramServiceImpl;
import com.sixin.broad.utils.bConst;
import com.sixin.broadserver.domain.SocketInfo;
import com.sixin.broadserver.global.ProtocolsToClient;
import com.sixin.broadserver.server.MinaCastHandler;
import com.sixin.broadserver.server.handle.DefaultCommand;
import com.sixin.common.utils.spring.SpringUtils;
import org.apache.mina.core.session.IoSession;

import java.io.*;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SendFile extends DefaultCommand {
    protected static IProSinmanageService proSinmanageService = (ProSinmanageServiceImpl) SpringUtils.getBean(ProSinmanageServiceImpl.class);
    protected static IProListService proListService = (ProListServiceImpl) SpringUtils.getBean(ProListServiceImpl.class);
    protected static IProgramService programService = (ProgramServiceImpl) SpringUtils.getBean(ProgramServiceImpl.class);
    public static final String ProgrammeListTypes_file = "文件转播";
    public static final String ProgrammeListTypes_radio = "电台播音";
    public static final String ProgrammeListTypes_openGF = "开启功放";
    public static final String ProgrammeListTypes_closeGF = "关闭功放";
    public static final String ProgrammeListTypes_shutdown = "停止播放";
    private int senderlenth = 510;
    public SendFile(IoSession session, byte[] content) {
        super(session, content);
    }

    @Override
    public byte[] execute() {
        try {
            SocketInfo info =new SocketInfo();
            info.setImei(session.getAttribute("CLIENTINFO").toString());
            info.setByteCount(0);
            info.setLastTime(new Date());
            logger.info("终端请求获取文件"+"l"+datainfo);
            if(info != null){
                byte[] ReturnData = new byte[0];
                String byteOrder = (int)content[4]+"";
                if(!byteOrder.equals("0")){
                    senderlenth = (Integer.parseInt(byteOrder)*16);//定义每次发送的字节长度 出去包尾和校验两个字节
                }
                if(info.getByteCount()==0 && info.getByteFile()==null){//获取新的文件，并保存到变量中
                    info.setByteFile(FileContent(info.getImei(),datainfo));
                    logger.info("正常日志   记录：申请发送文件:" + datainfo + " 总字节数：" +info.getByteFile().limit() + ";byteOrder:" + byteOrder + "; 信息："+ info.getImei());
                }
                ByteBuffer buffer = info.getByteFile();
                if(buffer != null && buffer.hasRemaining()){
                    if(buffer.remaining()>senderlenth){//当文件字节过长时，进行切割，仅发送定义的字节长度，剩余字节暂时保存等待下次发送
                        ReturnData = new byte[senderlenth];
                        buffer.get(ReturnData);
                        info.setByteCount(info.getByteCount()+ senderlenth);
                        byteOrder="0";//标示有后续文件还需要继续发送的命令
                        logger.info("正常日志   记录：正在发送文件:" + datainfo + "  字节数：" + senderlenth + ";byteOrder:" + byteOrder + "; 信息："+  info.getImei());
                    }else{
                        byteOrder="1";//标示该文件最后的一段发送完毕
                        ReturnData = new byte[buffer.remaining()];//发送剩余所有的字节
                        buffer.get(ReturnData);
                        buffer.clear();
                        info.setByteFile(null);
                        logger.info("正常日志   记录：申请发送文件:" + datainfo + "发送完毕!  字节数：" + senderlenth + ";byteOrder:" + byteOrder + "; 信息："+ info.getImei());

                    }
                    //记录终端下载文件使用流量
                    if(!datainfo.equals("list.txt"))
                        info.setByteCount(info.getByteCount()+ ReturnData.length);
                }
                return returnBytes(ProtocolsToClient.LIST, byteOrder, new String(ReturnData,GBK),true);
            }else{
                logger.info("未获取终端信息");
            }
        } catch (Exception e) {
            logger.error("终端获取文件出错：", e);
        }
        return null;
    }

    @Override
    public boolean save(Object obj) {
        return false;
    }

    @Override
    public Object get(Object obj) {
        if(session.getAttribute(MinaCastHandler.CLIENTINFO) != null) {
            return getSocketInfoByIMEI(session.getAttribute(MinaCastHandler.CLIENTINFO).toString());
        }else{
            return  null;
        }
    }

    /**
     * 文件转换数据流.
     * @param IMEI
     * @param fileName 文件名
     * @return
     */
    private ByteBuffer FileContent(String IMEI, String fileName) {
//    	long starttime = System.currentTimeMillis();

        Date nowTime=new Date();
        byte[] mybytes = new byte[0];
        SimpleDateFormat ftime=new SimpleDateFormat("yyyy-MM-dd");
        String date=ftime.format(nowTime);//现在的时间按照sdf1模式返回字符串
        try
        {
            List<ProList> list = null;
            List<ProSinmanage> model = proSinmanageService.selectProSinmanageByTId(IMEI);//根据IMEI号获取节目单
            if(model != null){
                for(ProSinmanage sinmanage:model){
//                    if(sinmanage.getBroaddate().equals(date)){
                    list = proListService.selectProListListByPid(sinmanage.getSfid());
//                    }
                }
            }
            switch (fileName)
            {
                case "list.txt"://旧终端获取节目单
                    String mylist = "";
                    if(list!=null)   {
                        //SetReceives(IMEI,model.getSfid());
                        mylist = GetProgrammes(list,"list");
                    }else{
                        //SetReceives(IMEI,"");
                    }
                    mybytes = mylist.getBytes(GBK);
                    break;
                case "playlist.txt"://新终端获取节目单
                    String playlist = "";
                    if(list!=null)   {
                        //SetReceives(IMEI,model.getSfid());
                        playlist = GetProgrammes(list,"playlist");
                    }else{
                        //SetReceives(IMEI,"");
                    }
                    mybytes = playlist.getBytes(GBK);
                    break;
                case "config.ini"://获取配置文件
                    String myConfig = GetConfig(IMEI);
                    mybytes = myConfig.getBytes(GBK);
                    break;
                case "upgrade.bin"://获取升级文件
                    mybytes = GetFiles(bConst.UpdateUrl);
                    break;
                default://获取其他文件
                    String filepath = null;
                    if(model!=null){
                        for(ProList prolist : list){
                            if(prolist.getPtp().trim().equals(ProgrammeListTypes_file)){
                                if(prolist.getfN().trim().equals(fileName.trim())){
                                    filepath=prolist.getUrls();
                                }
                            }
                        }
                    }else{
                        Program program = null;
                        program.setFilename(fileName);
                        program = programService.selectProList(program).get(0);
                        if(program != null){
                            filepath = program.getAddress();
                        }
                    }
                    mybytes = GetFiles(filepath);
                    break;

            }
            if(mybytes==null) mybytes = new byte[0];
        }
        catch (Exception err)
        {
            logger.error("出错日志 :文件转换数据流错误,FileContent",err);
        }
        ByteBuffer buffer = ByteBuffer.allocate(mybytes.length+1);
        buffer.put(mybytes);
        buffer.flip();
        logger.info("获取文件buffer");
        return  buffer;
    }
    /**
     * 获取节目单
     * @param list,type
     * @return
     */
    private String GetProgrammes(List<ProList> list,String type)
    {
        String listtxt = "";
        try {
            for(ProList prolist : list) {
                switch (prolist.getPtp()) {
                    case ProgrammeListTypes_file:
                        if(type.equals("list")){
                            listtxt += prolist.getBt() + ",mp3," + prolist.getfN() + "\r\n";
                        }else{
                            listtxt += prolist.getBt() + ",mp3," + bConst.ServerPath+prolist.getfN() + ","+ prolist.getUrls() + "\r\n";
                        }
                        break;
                    case ProgrammeListTypes_radio:
                        listtxt += prolist.getBt() + ",fm," + prolist.getfN() + "\r\n";
                        break;
                    case ProgrammeListTypes_openGF:
                        listtxt += prolist.getBt() + ",amp-on\r\n";
                        break;
                    case ProgrammeListTypes_closeGF:
                        listtxt += prolist.getBt() + ",amp-off\r\n";
                        break;
                    case ProgrammeListTypes_shutdown:
                        listtxt += prolist.getBt() + ",stop\r\n";
                        break;
                }
            }
        }
        catch (Exception err) {
            logger.error("出错日志 :获取节目单错误,GetProgrammes",err);
        }
        return listtxt;
    }

    private String GetConfig(String IMEI)
    {
        String config = null;
        config = "boot-server:192.168.1.102:8600\r\n";
        //系统端IP地址及端口号
        config += "app-server:110.53.162.165:8600\r\n";

        //上游终端号码
        config += "upstream-number:" + GetTels(IMEI) + "\r\n";

        //维护终端号码，如果设置了该号码，设备掉电时将往该号码发送通知短信
        config += "miantain-number:" + "" + "\r\n";

        //下游终端号码，保留
        config += "downstream-number:" + "" + "\r\n";

//        terminalMapper= (TerminalMapper) applicationContext.getBean("terminalMapper");
        Conditions tc = new Conditions();
        try {
            tc = conditionsService.selectConditionsById(IMEI);
        } catch (Exception e) {
            logger.error("出错日志  记录:" + e.getMessage().toString() + "  信息:获取配置文件,Getconfig" ,e);
        }
        //音频通道默认音量
        config += "chan-volume:" + tc.getMp3() + "," + tc.getFm() + "," + tc.getGsm() + "," + tc.getNullv() + "\r\n";
        return config;
    }

    /**
     * 获取终端授权电话
     * @param IMEI
     * @return
     */
    public String GetTels(String IMEI)
    {
        try
        {
            String tels = "";
            List<TerminalTels> tts = organizationService.selectTelsByTid(IMEI);
            if(tts.size()>0){
                for(int i=0;i<tts.size();i++){
                    if(i==0) tels += tts.get(i).getTel().trim() ;
                    else tels += ","+tts.get(i).getTel().trim()  ;
                }
            }
            return tels;
        }
        catch (Exception err)
        {
            logger.error("出错日志:获取终端授权电话错误,GetTels" ,err);
            return "";
        }
    }

    private byte[] GetFiles(String filepath)
    {

        FileInputStream fis = null ;
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        if (filepath != null & filepath != "")
        {
            byte[] buffer = null;
            try {
                File file = new File(filepath);
                if(file.exists()){
                    fis = new FileInputStream(file);
                    byte[] b = new byte[1024];
                    int n;
                    while ((n = fis.read(b)) != -1) {
                        bos.write(b, 0, n);
                    }
                    buffer = bos.toByteArray();
                }else{
                    logger.error("出错日志  记录:下载文件 "+filepath+"不存在   信息:下载文件,GetFiles" );
                }
                return buffer;

            } catch (FileNotFoundException e) {
                logger.error("出错日志  记录:" + e.getMessage().toString() + "  信息:下载文件,GetFiles" );
                return null;
            } catch (IOException e) {
                logger.error("出错日志  记录:" + e.getMessage().toString() + "  信息:下载文件,GetFiles" );
                return null;
            }
            finally
            {
                if (bos != null)
                {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        System.out.print("文件资源关闭出错");
                    }
                }
                if (fis != null)
                {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        System.out.print("文件资源关闭出错");
                    }
                }
            }

        } else
        {
            return null;
        }
    }
}
