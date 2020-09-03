package com.sixin.iot.mapper;

import com.sixin.iot.domain.Environl;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnvironlMapper {
    /**
     * 环境终端检测记录列表
     *
     * @return
     */
    public List<Environl> selectEnvironlList(Environl environl);
    /**
     * 批量导出用
     *
     * @return
     */
    public List<Environl> selecEnvironlListByids(List<String> sfids);
    /**
     * 添加环境终端检测信息
     * @param environl
     * @return
     */
    public int insertEnvironl(Environl environl);
    /**
     * 根据hid选择数据
     * @param hid
     * @return
     */
    public Environl selectByid(Long id);
    List<Torrent> queryList(Torrent torrent);

    /**
     * 保存用户修改
     * @param environl
     * @return
     */
    public int updateEnvironl(Environl environl);
    /**
     * 开关
     * @param environl
     * @return
     */
    public int updateEnvironl1(Environl environl);
    /**
     * 下来终端编号
     * @param
     * @return
     */
    public List<Ioterminal> selectimei(Ioterminal ioterminal);
    /**
     * 下拉录入人编号
     * @param
     * @return
     */
    public List<User> selectuser(User user);
    /**
     * 根据hid批量删除数据
     * @param hids
     */
    public int deleteEnvironlByids(String[] hids);

    /**
     * 控制台环境数据图表查询
     *
     * @param deptId
     * @return 结果
     */
    public List<Environl> selectEnvironlListOnControl(@Param("deptid") String deptId);
}