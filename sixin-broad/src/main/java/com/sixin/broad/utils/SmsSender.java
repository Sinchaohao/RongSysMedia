package com.sixin.broad.utils;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * @program: RongSys
 * @description:
 * @开发人员：王豪杰
 * @开发单位：湖南农业大学物联网工程专业
 * @create: 2020-04-15 09:59
 */
public class SmsSender {

    public void sendSms(String[] phones,String content) throws Exception{
        System.out.println("start!");
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM3");
        CommPort commPort = portIdentifier.open("COM3", 2000);
        try{
            SerialPort serialPort = (SerialPort) commPort;
            serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            OutputStream out = serialPort.getOutputStream();
            InputStream ins = serialPort.getInputStream();
            out.write("AT+CPIN?".getBytes());
            out.write(0x0D);
            out.flush();
            Thread.sleep(500);

            if(!checkResult(ins)){
                throw new Exception("短信卡未就绪");
            }
            out.write("AT+CMGF=1".getBytes());
            out.write(0x0D);
            out.flush();
            Thread.sleep(500);
            if(!checkResult(ins)){
                throw new Exception("设置文本模式失败");
            }
            out.write("AT+CSMP=17,167,1,8".getBytes());
            out.write(0x0D);
            out.flush();
            Thread.sleep(500);
            if(!checkResult(ins)){
                throw new Exception("设置中英文失败");
            }

            out.write("AT+CSCS=\"UCS2\"".getBytes());
            out.write(0x0D);
            out.flush();
            Thread.sleep(500);
            if(!checkResult(ins)){
                throw new Exception("设置为 UCS2 字符集编码失败");
            }

            for (int i = 0; i< phones.length;i++) {
                //中文信息转换
                String phoneunic=stringToUnicodeforphone(phones[i]);
                out.write(("AT+CMGS=\""+phoneunic+"\" ").getBytes());
                out.write(0x0D);
                out.flush();
                Thread.sleep(500);

                String contentunic=string2Unicodeforcontent(content);
                out.write(contentunic.getBytes());
                Thread.sleep(500);
                out.write(0x0D);
                Thread.sleep(500);
                out.write(0x1A);
                Thread.sleep(5000);
                if(!checkResult(ins)){
                    throw new Exception("发送失败");
                }
                out.flush();
            }
            System.out.println("finish");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            commPort.close();
        }
    }

    private static boolean checkResult(InputStream ins) {
        boolean flag=false;
        Scanner scanner=new Scanner(ins);
        while( scanner.hasNextLine()){
            String  echostr=scanner.nextLine();
            if(!StringUtils.isEmpty(echostr)){
                System.out.println(echostr);
                if("OK".equalsIgnoreCase(echostr)){
                    return true;
                }
            }
        }
        return false;
    }


    public static String stringToUnicodeforphone(String s) {
        try {
            StringBuffer out = new StringBuffer("");
            //直接获取字符串的unicode二进制
            byte[] bytes = s.getBytes("unicode");
            //然后将其byte转换成对应的16进制表示即可
            for (int i = 0; i < bytes.length - 1; i += 2) {
                out.append("\\u");
                String str = Integer.toHexString(bytes[i + 1] & 0xff);
                for (int j = str.length(); j < 2; j++) {
                    out.append("0");
                }
                String str1 = Integer.toHexString(bytes[i] & 0xff);
                out.append(str1);
                out.append(str);
            }
            return out.toString().toUpperCase().replaceFirst("FEFF", "").replaceFirst("\\\\U", "").replaceAll("\\\\U", "0");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    //中英文转换成unicode
    public static String string2Unicodeforcontent(String string) {
        StringBuffer unicode = new StringBuffer();
        String str="";
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            str = Integer.toHexString(c);
            if(str.length()<4) {
                str = "00" + str;
            }
            unicode.append(str);
        }
        return unicode.toString();
    }

}
