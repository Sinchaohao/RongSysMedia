package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.Fileinfo;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.mapper.FileinfoMapper;
import com.sixin.village.service.IFileinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件夹管理 服务层实现
 * 
 * @author 张鸿权
 * @date 2019-08-22
 */
@Service
public class FileinfoServiceImpl implements IFileinfoService 
{
	@Autowired
	private FileinfoMapper fileinfoMapper;

	/**
     * 查询文件夹管理信息
     * 
     * @param fileid 文件夹管理ID
     * @return 文件夹管理信息
     */
    @Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public Fileinfo selectFileinfoById(Integer fileid)
	{
	    return fileinfoMapper.selectFileinfoById(fileid);
	}
	
	/**
     * 查询文件夹管理列表
     * 
     * @param fileinfo 文件夹管理信息
     * @return 文件夹管理集合
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Fileinfo> selectFileinfoList(Fileinfo fileinfo)
	{
	    return fileinfoMapper.selectFileinfoList(fileinfo);
	}

	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Map<String, Object>> selectFileTree(Fileinfo fileinfo){
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		List<Fileinfo> fileList = fileinfoMapper.selectFileinfoList(fileinfo);
		trees = getTrees(fileList);
		return trees;
	}


	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Map<String, Object>> getTrees(List<Fileinfo> fileList)
	{
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		for (Fileinfo file : fileList)
		{
			Map<String, Object> fileMap = new HashMap<String, Object>();
			fileMap.put("id", file.getAid());
			fileMap.put("filename", file.getFilename());
			fileMap.put("furl", file.getFurl());
			fileMap.put("filesize", file.getFilesize());
			fileMap.put("pushdate", file.getPushdate());
			fileMap.put("suffix", file.getSuffix());
			trees.add(fileMap);
		}
		return trees;
	}
    /**
     * 新增文件夹管理
     * 
     * @param fileinfo 文件夹管理信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int insertFileinfo(Fileinfo fileinfo)
	{
	    return fileinfoMapper.insertFileinfo(fileinfo);
	}
	
	/**
     * 修改文件夹管理
     * 
     * @param fileinfo 文件夹管理信息
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int updateFileinfo(Fileinfo fileinfo)
	{
	    return fileinfoMapper.updateFileinfo(fileinfo);
	}

	/**
     * 删除文件夹管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public int deleteFileinfoByIds(String ids)
	{
		return fileinfoMapper.deleteFileinfoByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public double selectFilesizeSum(String uid)
	{
		return fileinfoMapper.selectFilesizeSum(uid);
	}

	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public String selectFileurlById(Integer fileid)
	{
		return fileinfoMapper.selectFileurlById(fileid);
	}


	@Override
	@DataSource(value = DataSourceType.SXVILLAGE)
	public List<Fileinfo> selectMyFileListById(pubObjApi myfile)
	{
		return fileinfoMapper.selectMyFileListById(myfile);
	}
}
