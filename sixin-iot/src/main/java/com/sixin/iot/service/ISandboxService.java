package com.sixin.iot.service;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Sandbox;
import com.sixin.iot.domain.Torrent;

import java.util.List;


public interface ISandboxService {
    /**
     * 查询沙盘记录列表
     *
     * @return
     */
    public List<Sandbox> selectSandboxList(Sandbox sandbox);

    public List<Torrent> queryList(Torrent torrent);
    public List<Iotype> selectList(Iotype iotype);

    /**
     * 添加沙盘公告数据
     * @param  sandbox
     * @return
     */
    public int insertSandbox(Sandbox sandbox);

    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    public Sandbox selectByid(Long id);

    /**
     * 根据id批量删除数据
     * @param sfids
     */
    public List<Sandbox> selectSandboxByids(List<String> sfids);

    /**
     * 保存用户修改
     * @param sandbox
     * @return
     */
    public int updateSandbox(Sandbox sandbox);

    /**
     * 删除沙盘信息
     * @param id
     * @return
     */
    public int deleteSandboxByid(Long id);

    public int deleteSandboxByids(String ids);

    public int changeStatus(Sandbox sandbox);

}
