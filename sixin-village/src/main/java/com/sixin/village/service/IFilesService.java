package com.sixin.village.service;

import com.sixin.village.domain.Files2;

import java.util.List;

/**
 * 文件上传 服务层
 * 
 * @author yc
 * @date 2018-12-20
 */
public interface IFilesService 
{
	/**
     * 查询文件上传信息
     * 
     * @param id 文件上传ID
     * @return 文件上传信息
     */
	public Files2 selectFilesById(Integer id);
	
	/**
     * 查询文件上传列表
     * 
     * @param files 文件上传信息
     * @return 文件上传集合
     */
	public List<Files2> selectFilesList(Files2 files);
	
	/**
     * 新增文件上传
     * 
     * @param files 文件上传信息
     * @return 结果
     */
	public int insertFiles(Files2 files);
	
	/**
     * 修改文件上传
     * 
     * @param files 文件上传信息
     * @return 结果
     */
	public int updateFiles(Files2 files, Boolean isFile);
		
	/**
     * 删除文件上传信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFilesByIds(String ids);

    String checkFileNameUnqiue(Files2 files);

    List<Files2> selectFilesListNoSave(Files2 files);
}
