package com.sixin.broad.utils;

import com.sixin.common.utils.DateUtil;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;
/**
 * @author 张超 teavamc
 * @Description:TODO
 * @ClassName Tools
 * @date 2019/2/16 16:11
 **/
public class bTools {

    /**
     * 随机生成六位数验证码
     * @return
     */
    public static int getRandomNum(){
        Random r = new Random();
        return r.nextInt(900000)+100000;//(Math.random()*(999999-100000)+100000)
    }

    /**
     * 检测字符串是否不为空(null,"","null")
     * @param s
     * @return 不为空则返回true，否则返回false
     */
    public static boolean notEmpty(String s){
        return s!=null && !"".equals(s) && !"null".equals(s);
    }

    /**
     * 检测字符串是否为空(null,"","null")
     * @param s
     * @return 为空则返回true，不否则返回false
     */
    public static boolean isEmpty(String s){
        return s==null || "".equals(s) || "null".equals(s);
    }

    /**
     * 字符串转换为字符串数组
     * @param str 字符串
     * @param splitRegex 分隔符
     * @return
     */
    public static String[] str2StrArray(String str,String splitRegex){
        if(isEmpty(str)){
            return null;
        }
        return str.split(splitRegex);
    }

    /**
     * 用默认的分隔符(,)将字符串转换为字符串数组
     * @param str	字符串
     * @return
     */
    public static String[] str2StrArray(String str){
        return str2StrArray(str,",\\s*");
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String date2Str(Date date){
        return date2Str(date,"yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
     * @param date
     * @return
     */
    public static Date str2Date(String date){
        if(notEmpty(date)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Date();
        }else{
            return null;
        }
    }

    /**
     * 按照参数format的格式，日期转字符串
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date,String format){
        if(date!=null){
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }else{
            return "";
        }
    }

    /**
     * 把时间根据时、分、秒转换为时间段
     * @param StrDate
     */
    public static String getTimes(String StrDate){
        String resultTimes = "";

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date now;

        try {
            now = new Date();
            java.util.Date date=df.parse(StrDate);
            long times = now.getTime()-date.getTime();
            long day  =  times/(24*60*60*1000);
            long hour = (times/(60*60*1000)-day*24);
            long min  = ((times/(60*1000))-day*24*60-hour*60);
            long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);

            StringBuffer sb = new StringBuffer();
            //sb.append("发表于：");
            if(hour>0 ){
                sb.append(hour+"小时前");
            } else if(min>0){
                sb.append(min+"分钟前");
            } else{
                sb.append(sec+"秒前");
            }

            resultTimes = sb.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return resultTimes;
    }

    /**
     * 写txt里的单行内容
     * @param filePath  文件路径
     * @param content  写入的内容
     */
    public static void writeFile(String fileP,String content){
        String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
        filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
        if(filePath.indexOf(":") != 1){
            filePath = File.separator + filePath;
        }
        try {
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");
            BufferedWriter writer=new BufferedWriter(write);
            writer.write(content);
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码
     * @param mobiles
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber){
        boolean flag = false;
        try{
            Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    /**
     * 检测KEY是否正确
     * @param paraname  传入参数
     * @param FKEY		接收的 KEY
     * @return 为空则返回true，不否则返回false
     */
    public static boolean checkKey(String paraname, String FKEY){
        paraname = (null == paraname)? "":paraname;
        return bMD5.md5(paraname+ DateUtil.getDays()+",fh,").equals(FKEY);
    }

    /**
     * 读取txt里的单行内容
     * @param filePath  文件路径
     */
    public static String readTxtFile(String fileP) {
        try {

            String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
            filePath = filePath.replaceAll("file:/", "");
            filePath = filePath.replaceAll("%20", " ");
            filePath = filePath.trim() + fileP.trim();
            if(filePath.indexOf(":") != 1){
                filePath = File.separator + filePath;
            }
            String encoding = "utf-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { 		// 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);	// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    return lineTxt;
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件,查看此路径是否正确:"+filePath);
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
        }
        return "";
    }
    /**
     * 根据Key读取Properties中Value
     * @Title: GetValueByKey
     * @param @param fileP
     * @param @param key
     * @return String
     */
    public static String GetValueByKey(String fileP, String key) {
        String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
        filePath = filePath.replaceAll("file:/", "");
        filePath = filePath.replaceAll("%20", " ");
        filePath = filePath.trim() + fileP.trim();
        if(filePath.indexOf(":") != 1){
            filePath = File.separator + filePath;
        }
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream (new FileInputStream(filePath));
            pps.load(in);
            String value = pps.getProperty(key);
            //	             System.out.println(key + " = " + value);
            in.close();
            return new String(value.getBytes("ISO-8859-1"),"UTF-8");

        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 更新（或插入）一对properties信息(主键及其键值)
     * 如果该主键已经存在，更新该主键的值；
     * 如果该主键不存在，则插件一对键值。
     * @param fileP
     * @param key
     * @param content
     */
    public static void setProValue(String fileP, String key,String content) {
        String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
        filePath = filePath.replaceAll("file:/", "");
        filePath = filePath.replaceAll("%20", " ");
        filePath = filePath.trim() + fileP.trim();
        if(filePath.indexOf(":") != 1){
            filePath = File.separator + filePath;
        }
        Properties pps = new Properties();
        try {
            OutputStream fos = new FileOutputStream(filePath, true);
            pps.setProperty(key, content);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            pps.store(fos, "Update '" + key + "' value");
            fos.close();
//			FileOutputStream oFile = new FileOutputStream(filePath, true);//true表示追加打开
//			pps.setProperty(key, content);
//			pps.store(oFile, "");
//			oFile.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取Properties的全部信息
     * @Title: GetAllProperties
     * @param @param fileP
     * @param @throws IOException
     * @return void
     */
    public static  Map<String,String> GetAllProperties(String fileP) throws IOException {
        String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
        filePath = filePath.replaceAll("file:/", "");
        filePath = filePath.replaceAll("%20", " ");
        filePath = filePath.trim() + fileP.trim();
        if(filePath.indexOf(":") != 1){
            filePath = File.separator + filePath;
        }
        Properties pps = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        pps.load(in);
        Enumeration en = pps.propertyNames(); //得到配置文件的名字
        Map<String,String> map = new HashMap<String,String>();
        while(en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
            //	             byte[] utf8 = strValue.getBytes("ISO-8859-1");
            //	             String lasValue= new String(utf8, "UTF-8");
            map.put(strKey, strValue.trim());
            //	             System.out.println(strKey + "=" + strValue);
            in.close();
        }
        return map;
    }
    /**
     * 将unicode编码 "\u4F60\u597D\uFF01" 转换成中文 "你好！"
     * @param dataStr 要转化的字符串
     * @return 转换后的中文字符串
     */
    public static String Unicode2GBK(String dataStr) {
        int index = 0;
        StringBuffer buffer = new StringBuffer();
        while (index < dataStr.length()) {
            if (!"\\u".equals(dataStr.substring(index, index + 2))) {
                buffer.append(dataStr.charAt(index));
                index++;
                continue;
            }
            String charStr = "";
            charStr = dataStr.substring(index + 2, index + 6);
            char letter = 0;
            try{letter = (char) Integer.parseInt(charStr, 16);}catch (Exception e) {}
            buffer.append(letter);
            index += 6;
        }
        return buffer.toString();
    }
    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        //		System.out.println(getRandomNum());
//		String test="xx/test.xls";
//		String  testname = test.substring(0, test.lastIndexOf("/")+1);
//		String finalname = test.substring(test.lastIndexOf("/")+1);
        isNumeric("15");
    }
}

