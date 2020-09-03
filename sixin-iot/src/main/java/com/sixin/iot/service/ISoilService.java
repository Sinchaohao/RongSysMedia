package com.sixin.iot.service;


import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Soil;
import com.sixin.iot.domain.Torrent;

import java.util.List;

/**
 * @program: SoilSys
 * @description: 土壤系统
 * @author: 蔡文静
 * @create: 2019-12-25 13:40
 **/
public interface ISoilService {
    /**
     * 查询土壤记录列表
     *
     * @return
     */
    public List<Soil> selectSoilList(Soil soil);

    /**
     * 根据sid批量删除数据
     * @param id
     */
    public int deleteSoilByids(String sid);

    /**
     * 添加土壤公告数据
     * @param  Soil
     * @return
     */
    public int insertSoil(Soil soil);
    public List<Soil> selectSoilByids(List<String> sfids);
    public List<Torrent> queryList(Torrent torrent);
    /**
     * 根据sid选择数据
     * @param sid
     * @return
     */
    public List<Iotype> selectList(Iotype iotype);
    public Soil selectByid(Long id);


    /**
     * 保存用户修改
     * @param Soil
     * @return
     */
    public int updateSoil(Soil soil);

    /**
     * 删除土壤信息
     * @param sid
     * @return
     */
    public int deleteSoilByid(String sid);

    /**
     * 角色状态修改
     *
     * @param role 角色信息
     * @return 结果
     */
    public int changeStatus(Soil delflag);
}
