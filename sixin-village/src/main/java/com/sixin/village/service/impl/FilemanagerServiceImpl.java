package com.sixin.village.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.village.mapper.FilemanagerMapper;
import com.sixin.village.domain.Filemanager;
import com.sixin.village.service.IFilemanagerService;
import com.sixin.common.support.Convert;

/**
 * 文件管理 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-01-20
 */
@Service
public class FilemanagerServiceImpl implements IFilemanagerService 
{
	@Autowired
	private FilemanagerMapper filemanagerMapper;

	/**
     * 查询文件管理信息
     * 
     * @param fileid 文件管理ID
     * @return 文件管理信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Filemanager selectFilemanagerById(Integer fileid)
	{
	    return filemanagerMapper.selectFilemanagerById(fileid);
	}
	
	/**
     * 查询文件管理列表
     * 
     * @param filemanager 文件管理信息
     * @return 文件管理集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Filemanager> selectFilemanagerList(Filemanager filemanager)
	{
	    return filemanagerMapper.selectFilemanagerList(filemanager);
	}
	
    /**
     * 新增文件管理
     * 
     * @param filemanager 文件管理信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertFilemanager(Filemanager filemanager)
	{
	    return filemanagerMapper.insertFilemanager(filemanager);
	}
	
	/**
     * 修改文件管理
     * 
     * @param filemanager 文件管理信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateFilemanager(Filemanager filemanager)
	{
	    return filemanagerMapper.updateFilemanager(filemanager);
	}

	/**
     * 删除文件管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deleteFilemanagerByIds(String ids)
	{
		return filemanagerMapper.deleteFilemanagerByIds(Convert.toStrArray(ids));
	}
	
}
