package com.sixin.common.utils.file;

/**
 * @Systemname:湖南灾害应急管理系统
 * @Author:刘波
 * @Description:
 * @Date:Created in $[TIME] $[DATE]
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:综合训练系统V1.0
 * @Rewriting:刘波
 */
import com.sixin.common.config.sixinConfig2;
import com.sixin.common.exception.file.FileNameLengthLimitExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/*import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;*/

/**
 * 文件上传工具类
 *
 * @author sixin
 */
public class FileUploadUtils2
{
    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 52428800;

    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = sixinConfig2.getProfile();

    /**
     * 默认的文件名最大长度
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 200;

    /**
     * 默认文件类型jpg
     */
    public static final String IMAGE_JPG_EXTENSION = ".jpg";

    private static int counter = 0;

    public static void setDefaultBaseDir(String defaultBaseDir)
    {
        FileUploadUtils2.defaultBaseDir = defaultBaseDir;
    }

    public static String getDefaultBaseDir()
    {
        return defaultBaseDir;
    }

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(MultipartFile file) throws IOException
    {
        try
        {
            return upload(getDefaultBaseDir(), file, FileUploadUtils2.IMAGE_JPG_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e);
        }
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(String baseDir, MultipartFile file) throws IOException
    {
        try
        {
            return upload(baseDir, file, FileUploadUtils2.IMAGE_JPG_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e);
        }
    }

    public static String dealName(String fileName) {
        int dot = fileName.lastIndexOf('.');
        if ((dot > -1) && (dot < (fileName.length() - 1))) {
            return fileName.substring(dot + 1);
        }
        return "";
    }

    /**
     * 文件上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @param //needDatePathAndRandomName 是否需要日期目录和随机文件名前缀
     * @param extension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws //FileSizeLimitExceededException 如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException 比如读写文件出错时
     */
    public static final String upload(String baseDir, MultipartFile file, String extension)
            throws  IOException, FileNameLengthLimitExceededException
    {

        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils2.DEFAULT_FILE_NAME_LENGTH)
        {
            //throw new FileNameLengthLimitExceededException(file.getOriginalFilename(), fileNamelength,FileUploadUtils2.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file);

        String fileName = encodingFilename(file.getOriginalFilename(), extension);

        File desc = getAbsoluteFile(baseDir, baseDir + fileName);
        file.transferTo(desc);
        return fileName;
    }

    public static final File getAbsoluteFile(String uploadDir, String filename) throws IOException
    {
        File desc = new File(File.separator + filename);

        if (!desc.getParentFile().exists())
        {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists())
        {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 编码文件名
     */
    private static final String encodingFilename(String filename, String extension)
    {
        filename = filename.replace("_", " ");
//        filename = new Md5Hash(filename + System.nanoTime() + counter++).toHex().toString() + extension;

        return filename;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     * @throws //FileSizeLimitExceededException 如果超出最大大小
     */
    public static final void assertAllowed(MultipartFile file)
    {
        long size = file.getSize();
        /*if (DEFAULT_MAX_SIZE != -1 && size > DEFAULT_MAX_SIZE)
        {
            throw new FileSizeLimitExceededException("not allowed upload upload", size, DEFAULT_MAX_SIZE);
        }*/
    }
}
