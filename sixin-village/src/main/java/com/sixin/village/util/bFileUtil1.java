package com.sixin.village.util;

import com.sixin.village.domain.Files;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class bFileUtil1 {
    private static Logger logger = LoggerFactory.getLogger(bFileUtil1.class);
    /**
     * 文件上传
     * @param file
     * @return 返回相对路径
     */
    public static String saveImg(MultipartFile file,String saveName) {
        //获取文件上传的目录
        String  path = bConstant1.UPLOAD_PATH + bConstant1.IMG_FILE_NAME;
        logger.info(" --- bConstant1.UPLOAD_PATH：{} --- ",bConstant1.UPLOAD_PATH);
        logger.info(" --- bConstant1.IMG_FILE_NAME：{} --- ",bConstant1.IMG_FILE_NAME);
        logger.info(" --- 文件保存路径：{}, 文件保存名称：{},文件原名称：{} --- ", path, saveName,file.getOriginalFilename());
        // 保存
        try {
            // 保存文件
            File targetFile = new File(path);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            //file.transferTo(new File(path + "/" + saveName));
            /*文件上传地址：在本地用户根目录bPathUtil1.getClasspath()+指定文件夹路径"/profile/img/"+文件名*/
            file.transferTo(new File(bPathUtil1.getClasspath() + bConst1.FILEPATHPER2 +saveName));
            System.out.println("文件上传路径为：" + bPathUtil1.getClasspath() + bConst1.FILEPATHPER2 +saveName);//在控制台输出文件信息
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("--- 文件保存异常：{} ---" + e.getMessage());
            return null;
        }
        //String filePath =  bConstant1.UPLOAD_PATH;
        //返回相对路径  img/virtual/4ca64e85b1544c96b4a6154bb521476f.jpg
        return saveName; //filePath + "/" + saveName
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
     * 文件保存封装
     * @param maxfileid
     * @param file
     * @param fname
     * @param flenth
     * @param fsize
     * @param year
     * @return Program
     */
    public static com.sixin.village.domain.Files uplodeFile(String maxfileid, MultipartFile file, String fname, String flenth, String fsize, String year){
        Files g = new Files();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        int j=0; //上传多个的时候用的
        String filename ="";
        String failfile="";//添加失败的节目
        if(file!=null ){
           /* filename = maxfileid+j+"";*/
            filename = maxfileid+"";
            while(filename.length()<5){
                filename = "0"+filename;
            }
            filename = year.substring(2)+filename;
            if (null != file && !file.isEmpty()) {
                filename =filename+"."+bFileUtil1.getFileSuffix(file.getOriginalFilename()); //filename字段
                if(flenth!="NaN"&&!flenth.equals("")){
                    g.setFlenth(flenth);
                }else{
                    failfile = fname+"获取时长信息出错，";
                }
                //path是返回文件的名字（纯名字）
                String path =  bFileUtil1.saveImg(file,maxfileid+file.getOriginalFilename());
                g.setFilename(filename);
               // g.setAddress(bPathUtil1.getClasspath() + bConst1.FILEPATHPER2 + maxfileid+file.getOriginalFilename());
                //给以后开发的小伙伴的留言：
                /*原来的bPathUtil1.getClasspath()是获得目前根目录用户地址*/
                /*为什么这里是bConstant1.WEB_IMG_ADDRESS呢？因为在服务器中设置了地址映射，文件储存到根目录/root/profile/img/中，
                   然后在tomcat的conf/sever.xml中设置了bConstant1.WEB_IMG_ADDRESS指向/root/profile/img/，所以这里文件保存到数据库的地址就是bConstant1.WEB_IMG_ADDRESS*/
                /*setAddress是文件保存在数据库中的文件地址*/
                g.setAddress(bConstant1.WEB_ADDRESS + bConstant1.WEB_IMG_ADDRESS + maxfileid + file.getOriginalFilename());
                /*感觉这个url好像是没有用的。。。*/
                g.setUrls(bConst1.FILEPATHPER2 + maxfileid+file.getOriginalFilename());
                g.setCreatedtime(df.format(new Date()));
                g.setIspublic(false);
                g.setIslisten(true);
                if(fsize!=null&&!fsize.equals("")){
                    BigDecimal b   =   new   BigDecimal(fsize);
                    g.setFsize(b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );//四舍五入 两位小数
                }
                j++;
            }

        }
        return g;
    }




    /**
     * 文件保存封装
     * @param maxfileid
     * @param file
     * @param fname
     * @param fsize
     * @param year
     * @return Program
     */
    public static com.sixin.village.domain.Files uplodeFilepic(String maxfileid, MultipartFile file, String fname, String fsize, String year){
        Files g = new Files();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        int j=0; //上传多个的时候用的
        String filename ="";
        String failfile="";//添加失败的节目
        if(file!=null ){
            /* filename = maxfileid+j+"";*/
            filename = maxfileid+"";
            while(filename.length()<5){
                filename = "0"+filename;
            }
            filename = year.substring(2)+filename;
            if (null != file && !file.isEmpty()) {
                filename =filename+"."+bFileUtil1.getFileSuffix(file.getOriginalFilename()); //filename字段

                //path是返回文件的名字（纯名字）
                String path =  bFileUtil1.saveImg(file,maxfileid+file.getOriginalFilename());
                g.setFilename(filename);
                // g.setAddress(bPathUtil1.getClasspath() + bConst1.FILEPATHPER2 + maxfileid+file.getOriginalFilename());
                //给以后开发的小伙伴的留言：
                /*原来的bPathUtil1.getClasspath()是获得目前根目录用户地址*/
                /*为什么这里是bConstant1.WEB_IMG_ADDRESS呢？因为在服务器中设置了地址映射，文件储存到根目录/root/profile/img/中，
                   然后在tomcat的conf/sever.xml中设置了bConstant1.WEB_IMG_ADDRESS指向/root/profile/img/，所以这里文件保存到数据库的地址就是bConstant1.WEB_IMG_ADDRESS*/
                /*setAddress是文件保存在数据库中的文件地址*/
                g.setAddress(bConstant1.WEB_ADDRESS + bConstant1.WEB_IMG_ADDRESS + maxfileid + file.getOriginalFilename());
                /*感觉这个url好像是没有用的。。。*/
                g.setUrls(bConst1.FILEPATHPER2 + maxfileid+file.getOriginalFilename());
                g.setCreatedtime(df.format(new Date()));
                g.setIspublic(false);
                g.setIslisten(true);
                if(fsize!=null&&!fsize.equals("")){
                    BigDecimal b   =   new   BigDecimal(fsize);
                    g.setFsize(b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );//四舍五入 两位小数
                }
                j++;
            }

        }
        return g;
    }
}