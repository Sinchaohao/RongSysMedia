package com.sixin.iot.mapper;

import com.sixin.iot.domain.Broad;
import com.sixin.iot.domain.BroadTerminal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BroadMapper {
    /**
     * 查询终端地域信息
     *
     * @param tid 设备IMEI
     * @return 终端地域信息
     */
    Broad selectBroadByTid(String tid);

    /**
     * 查询终端地域列表
     *
     * @param broad 终端地域信息
     * @return 终端地域集合
     */
    List<Broad> selectBroadList(Broad broad);

    /**
     * 新增终端地域
     *
     * @param broad 终端地域信息
     * @return 结果
     */
    int insertBroad(Broad broad);
    int insertCon(Broad broad);
    int insertMan(Broad broad);


    int insertBroadCon(Broad broad);
    /**
     * 新增终端地址图片
     *
     * @param broad 终端地域信息
     * @return 结果
     */
    int insertBroadPic(Broad broad);

    /**
     * 批量删除终端地域
     *
     * @param tids 需要删除的数据ID
     * @return 结果
     */
    int deleteBroadByIds(String[] tids);
    int deleteManByIds(String[] tids);
    int deleteConByIds(String[] tids);

    /**
     * 查询节目单终端列表
     *
     * @param broad 终端信息
     * @return 终端集合
     */
    List<Broad> selectProBroadList(Broad broad);

    /**
     *
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    int addProIdAll(Long[] ids);

    /**
     *  设置终端的RDS码
     *
     * @param ids 需要修改终端的RDS码
     * @return 结果
     */
    int updateRdsByIds(@Param("ids") String[] ids, @Param("number")String number);

    /**
     *  设置终端的频率码
     *
     * @param ids 需要修改终端的RDS码
     * @return 结果
     */
    int updateFmfrequencyByIds(@Param("ids") String[] ids, @Param("number")String number);

    int updateIsuseByTid(@Param("tid") String tid, @Param("isuse")Boolean isuse);


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
    int updateBroad(Broad broad);
    int updateCon(Broad broad);
    int updateMan(Broad broad);

    /**
     * @author cx
     * @param broad
     *
     * @Description 更新终端所属用户
     */
    int updateUsername(Broad broad);

    /**
     * 根据条件分页查询终端对象
     *
     * @param broad 导出终端字段
     * @return 终端信息集合信息
     */
    List<Broad> exportBroad(Broad broad);
    /**
     * 根据用户id查询终端对象
     *
     * @param aid 导出终端字段
     * @return 终端信息集合信息
     */
    List<Broad> selectByaid(@Param("aid") String aid);

    List<Broad> selecttidBytwo(@Param("tids") String[] tids , @Param("userid") String userid);

    /**
     * 根据用户id获取LED终端列表
     *
     * @param broad 导出终端字段
     * @return 终端信息集合信息
     */
    List<Broad> selectByLedUserid(Broad broad);
    int addphoneEdit(BroadTerminal terminalTels);

    int deletephoneedit(String telid);

    List<Broad> selectBroadListByTids(String[] tids);

    int terinfoedittime(String time,List<String> sfids);

    int terinfoeditrds(String time,List<String> sfids);

    int terinfoeditfrequency(String time,List<String> sfids);

    int terinfoeditphone(List<Broad> broads);

    int terinfoeditphonedelete(String time,List<String> sfids);

    int insertTerminalTes(Broad broad);

}