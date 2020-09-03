package com.sixin.iot.service;


import com.sixin.iot.domain.Broad;
import com.sixin.iot.domain.BroadTerminal;

import java.util.List;

/**
 * @program: BroadSys
 * @description: 广播系统
 * @author: 蔡文静
 * @create: 2019-12-24 15:40
 **/


public interface IBroadService {
    /**
     * 查询终端地域信息
     *
     * @param tid 终端地域ID
     * @return 终端地域信息
     */
    Broad selectBroadById(String tid);

    /**
     * 查询终端地域信息
     *
     * @param tid 设备IMEI
     * @return 终端地域信息
     */
    Broad selectBroadByTid(String tid);

    /**
     * 查询终端列表
     *
     * @param broad 终端信息
     * @return 终端集合
     */
    List<Broad> selectBroadList(Broad broad);

    /**
     * 新增终端
     *
     * @param broad 终端信息
     * @return 结果
     */
    int insertBroad(Broad broad);
    int insertCon(Broad broad);
    int insertMan(Broad broad);

    /**
     * 新增终端地址图片
     *
     * @param broad 终端信息
     * @return 结果
     */
    int insertBroadPic(Broad broad);

    /**
     * 修改终端
     *
     * @param broad 终端信息
     * @return 结果
     */
    int updateBroad(Broad broad);
    int updateCon(Broad broad);
    int updateMan(Broad broad);
    /**
     * 删除终端信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBroadByIds(String[] ids);
    int deleteManByIds(String[] ids);
    int deleteConByIds(String[] ids);
    /**
     * 查询节目单终端列表
     *
     * @param broad 终端信息
     * @return 终端集合
     */
    List<Broad> selectProBroadList(Broad broad);

    /**
     * 设置终端的RDS码
     *
     * @return
     */
    int updateRdsByIds(String ids , String number);

    /**
     * 设置终端的Fmfrequency码
     *
     * @return
     */
    int updateFmfrequencyByIds(String ids , String number);

    int updateIsuseByTid(String tid, Boolean isuse);

    //	通过 tid 查询对应终端的RDS码
    Broad selectRdsByTid(String tid);

    /**
     * 通过IMEI获取终端维护员电话
     * @param tid
     * @return
     */
    List<BroadTerminal> selectTelsByTid(String tid);

    /**
     * @author cx
     * @param broad
     *
     * @Description 更新终端数据
     */
    int updateUsername(Broad broad);

    /**
     * 根据条件分页查询终端对象
     *
     * @param broad 导出终端字段
     * @return 终端信息集合信息
     */
    List<Broad> exportBroad(Broad broad);

    int addphoneEdit(BroadTerminal terminalTels);

    int deletephoneedit(String telid);

    List<Broad> selectBroadListByTids(String[] tids);

    int terinfoedittime(String time,List<String> tids);

    int terinfoeditrds(String time,List<String> tids);

    int terinfoeditfrequency(String time,List<String> tids);

    int terinfoeditphone(List<Broad> broads);

    int terinfoeditphonedelete(String time,List<String> tids);
    /**
     * 根据aid查询终端对象(村务宝典)
     * hfz
     * @param aid 导出终端字段
     * @return 终端信息集合信息
     */
    List<Broad> selectByaid(String aid);

    /**
     * 流媒体直播---选择直播终端(村务宝典)
     * hfz
     * @return 终端信息集合信息
     */
    List<Broad> selecttidBytwo(String tids , String userid);
    /**
     * 根据用户id获取LED终端列表
     * hfz
     * @param broad
     * @return 终端信息集合信息
     */
    List<Broad> selectByLedUserid(Broad broad);
}
