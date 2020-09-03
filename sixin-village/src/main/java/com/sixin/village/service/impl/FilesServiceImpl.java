package com.sixin.village.service.impl;

import com.sixin.common.constant.UserConstants;
import com.sixin.common.support.Convert;
import com.sixin.common.utils.file.FileUploadUtils2;
import com.sixin.framework.util.ShiroUtils;
import com.sixin.village.domain.Files2;
import com.sixin.village.mapper.FilesMapper;
import com.sixin.village.service.IFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;


/**
 * 文件上传 服务层实现
 *
 * @author yc
 * @date 2018-12-20
 */
@Service
public class FilesServiceImpl implements IFilesService {
    @Autowired
    private FilesMapper filesMapper;

    /**
     * 查询文件上传信息
     *
     * @param id 文件上传ID
     * @return 文件上传信息
     */
    @Override
    public Files2 selectFilesById(Integer id) {
        return filesMapper.selectFilesById(id);
    }

    /**
     * 查询文件上传列表
     *
     * @param files 文件上传信息
     * @return 文件上传集合
     */
    @Override
    public List<Files2> selectFilesList(Files2 files) {
        return filesMapper.selectFilesList(files);
    }

    /**
     * 新增文件上传
     *
     * @param files 文件上传信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertFiles(Files2 files) {
        files.setCreateBy(ShiroUtils.getLoginName());
        files.setCreateByName(ShiroUtils.getSysUser().getUserName());
        return filesMapper.insertFiles(files);
    }

    /**
     * 修改文件上传
     *
     * @param files 文件上传信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateFiles(Files2 files, Boolean isFile) {
        int rtn = 0;
        //修改新数据
        rtn = filesMapper.updateFiles(files);
        if (rtn > 0) {
            //先判断有没有文件
            Files2 oldFiles = filesMapper.selectFilesById(files.getId());
            File file = new File(oldFiles.getUrl());
            if (file.isFile()) {
                if (files != null && isFile) {
                    file.delete();
                } else {
                    String url = FileUploadUtils2.getDefaultBaseDir() + files.getFileName() + "." + oldFiles.getSuffix();
                    file.renameTo(new File(url ));
                }
            }
        }

        return rtn;
    }

    /**
     * 删除文件上传对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFilesByIds(String ids) {
        int rtn = 0;
        if (ids.length() > 0) {
            String[] id = ids.split(",");
            for (int i = 0; i < id.length; i++) {
                Files2 files = filesMapper.selectFilesById(Integer.valueOf(id[i]));
                if (files != null) {
                    rtn = filesMapper.deleteFilesByIds(Convert.toStrArray(ids));
                    //删除成功后要移除文件
                    if (rtn > 0) {
                        File file = new File(files.getUrl());
                        if (file.isFile()) {
                            file.delete();
                        }
                    }
                }
            }
        }
        return rtn;
    }

    @Override
    public String checkFileNameUnqiue(Files2 files) {
        List<Files2> filesList = filesMapper.selectFilesList(files);
        if (filesList != null && filesList.size() > 0) {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    @Override
    public List<Files2> selectFilesListNoSave(Files2 files) {
        List<Files2> list = filesMapper.selectFilesListNoSave(files);

        return list;
    }

}
