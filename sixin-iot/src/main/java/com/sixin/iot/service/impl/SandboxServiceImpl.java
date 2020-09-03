package com.sixin.iot.service.impl;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Sandbox;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.mapper.SandboxMapper;
import com.sixin.iot.service.ISandboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class SandboxServiceImpl implements ISandboxService {
    @Autowired
    private SandboxMapper sandboxMapper;

    /**
     * 查询沙盘记录列表
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Sandbox> selectSandboxList(Sandbox sandbox) {
        return sandboxMapper.selectSandboxList(sandbox);
    }

    /**
     * 根据id批量删除数据
     * @param id
     */
    //@Override
    //@DataSource(value = DataSourceType.SXINFOM)
    //public int deleteSandboxByids(String id) {
    //    return sandboxMapper.deleteSandboxByids(Convert.toStrArray(id));
    //}

    /**
     * 添加沙盘公告数据
     * @param  Sandbox
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int insertSandbox(Sandbox sandbox) {
        return sandboxMapper.insertSandbox(sandbox);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Torrent> queryList(Torrent torrent) {return sandboxMapper.queryList(torrent);}

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Iotype> selectList(Iotype iotype) {return sandboxMapper.selectList(iotype);}

    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public Sandbox selectByid(Long id) {
        return sandboxMapper.selectByid(id);
    }


    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Sandbox> selectSandboxByids(List<String> sfids){
        return sandboxMapper.selectSandboxByids(sfids);
    }

    /**
     * 删除沙盘信息
     * @param Sandbox
     * @return
     */
    //@Override
    //@DataSource(value = DataSourceType.SXINFOM)
    //public int deleteSandboxByid(String id) {
    //    return sandboxMapper.deleteSandboxByid(id);
    //}

    /**
     * 保存用户修改
     * @param Sandbox
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int updateSandbox(Sandbox sandbox) {
        return sandboxMapper.updateSandbox(sandbox);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteSandboxByid(Long id) {
        return sandboxMapper.deleteSandboxByid(id);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteSandboxByids(String ids) {
        return sandboxMapper.deleteSandboxByids(Convert.toStrArray(ids));
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int changeStatus(Sandbox sandbox)
    {
        return sandboxMapper.updateSandbox(sandbox);
    }


}
