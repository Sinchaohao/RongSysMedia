package com.sixin.village.service;

import com.sixin.village.domain.Varea;
import java.util.List;

/**
 * 地域 服务层
 * 
 * @author 张超
 * @date 2019-01-20
 */
public interface IVareaService 
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
     * 删除地域信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteVareaByIds(String ids);

	/**
	 * 获取到下面所有的 aid
	 * @param aid
	 * @return
	 */
	public  List<String> listNextAid(String aid);
	public  List<Varea> listNextAidName(String aid);
	
}
