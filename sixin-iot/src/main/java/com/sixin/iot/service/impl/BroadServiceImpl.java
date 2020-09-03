package com.sixin.iot.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.iot.domain.BroadTerminal;
import com.sixin.common.support.Convert;
import com.sixin.iot.domain.Broad;
import com.sixin.iot.mapper.BroadMapper;
import com.sixin.iot.service.IBroadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: BroadSys
 * @description: 广播系统
 * @author: 蔡文静
 * @create: 2019-12-24 15:40
 **/
@Service
public class BroadServiceImpl implements IBroadService {
    @Autowired
    private BroadMapper broadMapper;

    /**
     * 查询终端地域信息
     *
     * @param tid 设备IMEI
     * @return 终端地域信息
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public Broad selectBroadByTid(String tid){
        return broadMapper.selectBroadByTid(tid);
    }

    /**
     * 新增终端
     *
     * @param broad 终端信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int insertBroad(Broad broad) {

        return broadMapper.insertBroad(broad);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int insertCon(Broad broad) {
        return broadMapper.insertCon(broad);
    }
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int insertMan(Broad broad) {
        return broadMapper.insertMan(broad);
    }

    /**
     * 新增终端地址图片
     *
     * @param broad 终端信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int insertBroadPic(Broad broad)
    {
        return broadMapper.insertBroadPic(broad);
    }

    /**
     * 删除终端对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int deleteBroadByIds(String[] ids){
        return broadMapper.deleteBroadByIds(ids);
    }
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int deleteManByIds(String[] ids){
        return broadMapper.deleteManByIds(ids);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int deleteConByIds(String[] ids){
        return broadMapper.deleteConByIds(ids);
    }


    /**
     * 查询节目单终端列表
     *
     * @param broad 终端信息
     * @return 终端集合
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Broad> selectProBroadList(Broad broad)
    {
        return broadMapper.selectProBroadList(broad) ;
    }

    /**
     * 查询终端信息
     *
     * @param tid 终端IMEI
     * @return 终端信息
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public Broad selectBroadById(String tid)
    {
        return broadMapper.selectBroadByTid(tid);
    }

    /**
     * 设置终端的RDS码
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int updateRdsByIds(String id , String number)
    {
        return broadMapper.updateRdsByIds(Convert.toStrArray(id),number);
    }

    /**
     * 设置终端的频率码
     ** @return
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int updateFmfrequencyByIds(String id , String number)
    {
        return broadMapper.updateFmfrequencyByIds(Convert.toStrArray(id),number);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int updateIsuseByTid(String tid, Boolean isuse)
    {
        return broadMapper.updateIsuseByTid(tid,isuse);
    }

    /**
     * 	通过 tid 查询对应终端的RDS码
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public Broad selectRdsByTid(String tid){
        return broadMapper.selectRdsByTid(tid);
    }
    /**
     * 	通过 tid 查询对应终端的维护电话
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<BroadTerminal> selectTelsByTid(String tid){
        return broadMapper.selectTelsByTid(tid);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Broad> selectBroadList(Broad broad){
        return broadMapper.selectBroadList(broad);
    }

    /**
     * @author cx
     * @param broad
     *
     * @Description 更新终端数据
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int updateBroad(Broad broad){
        return broadMapper.updateBroad(broad);
    }
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int updateCon(Broad broad){
        return broadMapper.updateCon(broad);
    }
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int updateMan(Broad broad){
        return broadMapper.updateMan(broad);
    }

    /**
     * @author cx
     * @param broad
     *
     * @Description 更新终端数据
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int updateUsername(Broad broad){
        return broadMapper.updateUsername(broad);
    }

    /**
     * 根据条件分页查询终端对象
     *
     * @param broad 导出终端字段
     * @return 终端信息集合信息
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Broad> exportBroad(Broad broad){return broadMapper.exportBroad(broad);}
    /**
     * 根据aid查询终端对象(村务宝典)
     *
     * @param aid 导出终端字段
     * @return 终端信息集合信息
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Broad> selectByaid(String aid){return broadMapper.selectByaid(aid);}

    /**
     * 流媒体直播---选择直播终端(村务宝典)
     *
     * @return 终端信息集合信息
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Broad> selecttidBytwo(String tid , String userid){return broadMapper.selecttidBytwo(Convert.toStrArray(tid),userid);}


    /**
     * 根据用户id获取LED终端列表
     *
     * @param broad 导出终端字段
     * @return 终端信息集合信息
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Broad> selectByLedUserid(Broad broad){return broadMapper.selectByLedUserid(broad);}
    @Override
    public int addphoneEdit(BroadTerminal terminalTels) {
        return broadMapper.addphoneEdit(terminalTels);
    }

    public int deletephoneedit(String telid) {
        return broadMapper.deletephoneedit(telid);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Broad> selectBroadListByTids(String[] tids){
        return broadMapper.selectBroadListByTids(tids);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int terinfoedittime(String time,List<String> tids){
        return broadMapper.terinfoedittime(time,tids);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int terinfoeditrds(String time, List<String> tids) {
        return broadMapper.terinfoeditrds(time,tids);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int terinfoeditfrequency(String time, List<String> tids) {
        return broadMapper.terinfoeditfrequency(time,tids);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int terinfoeditphone(List<Broad> broads) {
        return broadMapper.terinfoeditphone(broads);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int terinfoeditphonedelete(String time,List<String> tids) {
        return broadMapper.terinfoeditphonedelete(time,tids);
    }
}
