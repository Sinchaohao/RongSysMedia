package com.sixin.iot.service;
import com.sixin.iot.domain.Environl;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.domain.User;

import java.util.List;


public interface IEnvironlService {
    /**
     * 查询环境记录列表
     *
     * @return
     */
    public List<Environl> selectEnvironlList(Environl environl);

    /**
     * 批量导出
     *
     * @return
     */
    public List<Environl> selecEnvironlListByids(List<String> sfids);
    /**
     * 添加环境公告数据
     *
     */
    public int insertEnvironl(Environl environl);
    public List<Torrent> queryList(Torrent torrent);
    /**
     * 根据hid选择数据
     *
     */
    public Environl selectByid(Long id);
    /**
     * 保存用户修改
     *
     */
    public int updateEnvironl(Environl environl);

    /**
     * 根据hid批量删除数据
     *
     */
    public int deleteEnvironlByids(String ids);
//    /**
//     * 清空数据
//     *
//     */
//    public int deleteAllEnergy();
    /**
     * 修改终端状态
     *
     * @param environl
     * @return 结果
     */
    public int changeDelflag(Environl environl);
    /**
     * 下拉显示终端编号
     *
     * @param
     * @return 结果
     */
    public List<Ioterminal> selectimei(Ioterminal ioterminal);
    /**
     * 下拉显示录入人姓名
     *
     * @param
     * @return 结果
     */
    public List<User> selectuser(User user);

    /**
     * 控制台环境数据图表查询
     *
     * @param deptId
     * @return 结果
     */
    public List<Environl> selectEnvironlListOnControl(String deptId);

}
