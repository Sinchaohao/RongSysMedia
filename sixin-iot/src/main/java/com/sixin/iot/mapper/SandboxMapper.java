package com.sixin.iot.mapper;

import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Sandbox;
import com.sixin.iot.domain.Torrent;

import java.util.List;

public interface SandboxMapper {
    /**
     * 沙盘记录列表
     *
     * @return
     */
    List<Sandbox> selectSandboxList(Sandbox sandbox);

    /**
     * 删除沙盘信息
     * @param id
     * @return
     */
    public int deleteSandboxByid(Long id);

    /**
     * 根据id批量删除数据
     * @param ids
     */
    public int deleteSandboxByids(String[] ids);
    /**
     * 添加沙盘信息
     * @param sandbox
     * @return
     */
    int insertSandbox(Sandbox sandbox);
    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    Sandbox selectByid(Long id);
    /**
     * 根据id批量选择数据
     * @param sfids
     * @return
     */
    public List<Sandbox> selectSandboxByids(List<String> sfids);

    List<Torrent> queryList(Torrent torrent);

    List<Iotype> selectList(Iotype iotype);

    /**
     * 保存用户修改
     * @param sandbox
     * @return
     */
    int updateSandbox(Sandbox sandbox);


}