package com.sixin.village.mapper;

import com.sixin.village.domain.Varea;
import java.util.List;	

/**
 * 地域 数据层
 * 
 * @author 张超
 * @date 2019-01-20
 */
public interface VareaMapper 
{
	/**
     * 查询地域信息
     * 
     * @param aid 地域ID
     * @return 地域信息
     */
	public Varea selectVareaById(String aid);
	
	/**
     * 查询地域列表
     * 
     * @param varea 地域信息
     * @return 地域集合
     */
	public List<Varea> selectVareaList(Varea varea);
	
	/**
     * 新增地域
     * 
     * @param varea 地域信息
     * @return 结果
     */
	public int insertVarea(Varea varea);
	
	/**
     * 修改地域
     * 
     * @param varea 地域信息
     * @return 结果
     */
	public int updateVarea(Varea varea);
	
	/**
     * 删除地域
     * 
     * @param aid 地域ID
     * @return 结果
     */
	public int deleteVareaById(String aid);
	
	/**
     * 批量删除地域
     * 
     * @param aids 需要删除的数据ID
     * @return 结果
     */
	public int deleteVareaByIds(String[] aids);

	/**
	 * 获取到下面所有的 aid
	 * @param aid
	 * @return
	 */
	public  List<String> listNextAid(String aid);
	public  List<Varea> listNextAidName(String aid);
}