package com.sixin.broad.utils;

import com.sixin.broad.domain.Program;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class bFileUtil {
    private static Logger logger = LoggerFactory.getLogger(bFileUtil.class);
    /**
     * 图片存储 完整路径（E:/profile/uploadPath/img/XXX.jpg）
     * @param file
     * @return 返回相对路径
     */
    public static String saveImg(MultipartFile file) {
        String  path = bConst.UPLOAD_PATH + bConst.IMG_PATH; //图片存储路径
        String saveName = bCommonUtil.getUuid() + "." + getFileSuffix(file.getOriginalFilename());
        logger.info(" --- 终端图片储存路径：{}, 终端图片保存名称：{},终端图片名称：{} --- ", path, saveName,file.getOriginalFilename());
        try {
            File targetFile = new File(path);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(new File(path + "/" + saveName));  //文件保存，写入硬盘
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("--- 图片存储异常：{} ---" + e.getMessage());
            return null;
        }
        return "/profile/img/"+saveName;
    }
    /**
     * mp3文件存储 完整路径（{user.home}/img/coldStone/XXX.jpg）
     * @param file
     * @return 返回相对路径
     */
    public static String saveMusic(MultipartFile file,String saveName) {
        Encoder encoder = new Encoder();
        long ms = 0;
        String  path = bConst.UPLOAD_PATH + bConst.MP3_FILE_NAME; //图片存储路径
        logger.info(" --- 音频保存路径：{}, 音频保存名称：{},文件名称：{} --- ", path, saveName,file.getOriginalFilename());
        String duration = "";
        try {
            File targetFile = new File(path);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            System.out.println("=======1");
            File excelFile =  new File(path+"/"+saveName);
            file.transferTo(excelFile);
            MultimediaInfo m = encoder.getInfo(excelFile);
            ms = m.getDuration();
            int ss = 1000;
            int mi = ss * 60;
            int hh = mi * 60;
            int dd = hh * 24;
            long day = ms / dd;
            long hour = (ms - day * dd) / hh;
            long minute = (ms - day * dd - hour * hh) / mi;
            long second = (ms - day * dd - hour * hh - minute * mi) / ss;
            String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
            String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
            String strSecond = second < 10 ? "0" + second : "" + second;//秒
            duration = strHour + ":" + strMinute + ":" + strSecond;
            System.out.println("=======2");
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("--- 音频保存异常：{} ---" + e.getMessage());
            return null;
        }
        return duration+saveName;
    }
    /**
     * doc文档存储 完整路径（{user.home}/img/coldStone/XXX.doc）
     * @param file
     * @return 返回相对路径
     */

    /**
     * Doc文件上传封装
     * @param file
     * @return docName 保存成功的文件名
     */
    public static String saveDoc(MultipartFile file) {
        String  path = bConst.UPLOAD_PATH+"applyfile";
        String filename =file.getOriginalFilename();
        try {
            File targetFile = new File(path);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(new File(path + "/" + filename));  //文件保存，写入硬盘
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return "/profile/applyfile/"+filename;
    }


    /**
     * 返回截取的文件后缀
     * @param path
     * @return
     */
    public static String getFileSuffix(String path) {
        return getFileSuffix(path, "2");
    }

    /**
     * 获取文件名称或后缀(最后一个"."之后内容)
     * @param path
     * @param type 1名称 2后缀
     * @return
     */
    public static String getFileSuffix(String path, String type){
        if(StringUtils.isNotEmpty(path) && path.indexOf(".") > 0) {
            // 名称
            String name = path.substring(0, path.lastIndexOf("."));

            // 后缀
            String suffix = path.substring(path.lastIndexOf(".") + 1);

            return StringUtils.equals("1", type) ? name : suffix;
        } else {
            return null;
        }
    }

    /**
     * 文件上传封装
     * @param maxfileid
     * @param file
     * @param fname
     * @param fsize
     * @param year
     * @return Program
     */
    public static Program uplodeFile(String maxfileid,MultipartFile file,String fname,String fsize,String year,String uname){
        Program g = new Program();
        int fileid = 0;
        if(maxfileid!=null&& Integer.parseInt(maxfileid)>0){
            fileid = Integer.parseInt(maxfileid)+1;
        }else{
            fileid = 1;
        }
        int j=0;
        String filename ="";
        if(file!=null && !file.isEmpty()){
            filename = fileid+j+"";
            while(filename.length()<5){
                filename = "0"+filename;
            }
            filename = year.substring(2)+filename;
            filename =filename+"."+bFileUtil.getFileSuffix(file.getOriginalFilename()); //filename字段

            String mp3 = saveMusic(file,filename);//保存mp3文件

            g.setFlenth(mp3.substring(0,8));
            mp3 = mp3.substring(8,mp3.length());
            g.setFname(fname);
            g.setFilename(filename);
            g.setAddress(bPathUtil.getClasspath() + bConst.FILEPATHPER+mp3);
            g.setUrls(bConst.FILEPATHPER+mp3);
            g.setIspublic(true);
            g.setUserid(uname);

            if(fsize!=null&&!fsize.equals("")){
                BigDecimal b   =   new   BigDecimal(fsize);
                g.setFsize(b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );//四舍五入 两位小数
            }
            j++;
        }
        return g;
    }

    /**
     * 删除文件
     * @param filePathAndName
     * @param filePathAndName
     * @return boolean
     */
    public static boolean delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            java.io.File myDelFile = new java.io.File(filePath);
            if(myDelFile.exists()) {
                myDelFile.delete();
                return true;
            }
        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
        }
        return false;
    }
}