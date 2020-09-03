package com.sixin.village.mapper;

import com.sixin.village.domain.Meeting;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * 会议记录 数据层
 * 
 * @author 张鸿权
 * @date 2019-08-15
 */
public interface MeetingMapper 
{
	/**
     * 查询会议记录信息
     * 
     * @param mid 会议记录ID
     * @return 会议记录信息
     */
	public Meeting selectMeetingById(Integer mid);
	
	/**
     * 查询会议记录列表
     * 
     * @param meeting 会议记录信息
     * @return 会议记录集合
     */
	public List<Meeting> selectMeetingList(Meeting meeting);
	
	/**
     * 新增会议记录
     * 
     * @param meeting 会议记录信息
     * @return 结果
     */
	public int insertMeeting(Meeting meeting);
	
	/**
     * 修改会议记录
     * 
     * @param meeting 会议记录信息
     * @return 结果
     */
	public int updateMeeting(Meeting meeting);
	
	/**
     * 删除会议记录
     * 
     * @param mid 会议记录ID
     * @return 结果
     */
	public int deleteMeetingById(Integer mid);
	
	/**
     * 批量删除会议记录
     * 
     * @param mids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMeetingByIds(String[] mids);

	/**
	 * @Author TanXY
	 * @Description 按需导出数据
	 * @Date 2020/4/18 9:22
	 * @Param [id]
	 * @return java.util.List<com.sixin.village.domain.Meeting>
	 */
	public List<Meeting>  selectMeetingByIds(List<String> id);

	public List<Meeting> selectMeetingListById(pubObjApi meet);
}