package com.sixin.village.mapper;

import com.sixin.village.domain.Fileinfo;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * 文件夹管理 数据层
 * 
 * @author 张鸿权
 * @date 2019-08-22
 */
public interface FileinfoMapper 
{
	/**
     * 查询文件夹管理信息
     * 
     * @param fileid 文件夹管理ID
     * @return 文件夹管理信息
     */
	public Fileinfo selectFileinfoById(Integer fileid);
	
	/**
     * 查询文件夹管理列表
     * 
     * @param fileinfo 文件夹管理信息
     * @return 文件夹管理集合
     */
	public List<Fileinfo> selectFileinfoList(Fileinfo fileinfo);
	
	/**
     * 新增文件夹管理
     * 
     * @param fileinfo 文件夹管理信息
     * @return 结果
     */
	public int insertFileinfo(Fileinfo fileinfo);
	
	/**
     * 修改文件夹管理
     * 
     * @param fileinfo 文件夹管理信息
     * @return 结果
     */
	public int updateFileinfo(Fileinfo fileinfo);
	
	/**
     * 删除文件夹管理
     * 
     * @param fileid 文件夹管理ID
     * @return 结果
     */
	public int deleteFileinfoById(Integer fileid);
	
	/**
     * 批量删除文件夹管理
     * 
     * @param fileids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFileinfoByIds(String[] fileids);

	public double selectFilesizeSum(String uid);

	public String selectFileurlById(Integer fileid);

	public List<Fileinfo> selectMyFileListById(pubObjApi myfile);
}