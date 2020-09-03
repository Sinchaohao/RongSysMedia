package com.sixin.village.mapper;

import com.sixin.village.domain.Filemanager;
import java.util.List;	

/**
 * 文件管理 数据层
 * 
 * @author 张鸿权
 * @date 2019-01-20
 */
public interface FilemanagerMapper 
{
	/**
     * 查询文件管理信息
     * 
     * @param fileid 文件管理ID
     * @return 文件管理信息
     */
	public Filemanager selectFilemanagerById(Integer fileid);
	
	/**
     * 查询文件管理列表
     * 
     * @param filemanager 文件管理信息
     * @return 文件管理集合
     */
	public List<Filemanager> selectFilemanagerList(Filemanager filemanager);
	
	/**
     * 新增文件管理
     * 
     * @param filemanager 文件管理信息
     * @return 结果
     */
	public int insertFilemanager(Filemanager filemanager);
	
	/**
     * 修改文件管理
     * 
     * @param filemanager 文件管理信息
     * @return 结果
     */
	public int updateFilemanager(Filemanager filemanager);
	
	/**
     * 删除文件管理
     * 
     * @param fileid 文件管理ID
     * @return 结果
     */
	public int deleteFilemanagerById(Integer fileid);
	
	/**
     * 批量删除文件管理
     * 
     * @param fileids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFilemanagerByIds(String[] fileids);
	
}