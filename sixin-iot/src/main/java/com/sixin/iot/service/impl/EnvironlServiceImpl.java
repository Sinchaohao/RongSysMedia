package com.sixin.iot.service.impl;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.iot.domain.Environl;
import com.sixin.iot.domain.Ioterminal;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.domain.User;
import com.sixin.iot.mapper.EnvironlMapper;
import com.sixin.iot.service.IEnvironlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EnvironlServiceImpl implements IEnvironlService {
    @Autowired
    private EnvironlMapper environlMapper;

    /**
     * 查询环境记录列表
     *
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Environl> selectEnvironlList(Environl environl) {
        return environlMapper.selectEnvironlList(environl);
    }
    /**
     * 根据id批量删除数据
     *
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteEnvironlByids(String ids) {
        return environlMapper.deleteEnvironlByids(Convert.toStrArray(ids));
    }


    @DataSource(value = DataSourceType.SXINFOM)
    public List<Torrent> queryList(Torrent torrent) {return environlMapper.queryList(torrent);}
    /**
     * 添加环境公告数据
     *
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int insertEnvironl(Environl environl) {
        return environlMapper.insertEnvironl(environl);
    }

    /**
     * 批量导出
     *
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Environl> selecEnvironlListByids(List<String> sfids) {
        return environlMapper.selecEnvironlListByids(sfids);
    }
    /**
     * 根据hid选择数据
     *
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public Environl selectByid(Long id) {
        return environlMapper.selectByid(id);
    }


    /**
     * 保存用户修改
     *
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int updateEnvironl(Environl environl) {
        return environlMapper.updateEnvironl(environl);
    }
    /**
     * 设备状态修改
     *
     * @param environl 设备信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int changeDelflag(Environl environl)
    {
        return environlMapper.updateEnvironl1(environl);
    }
    /**
     * 下拉显示终端编号
     *
     * @param ioterminal 设备信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Ioterminal> selectimei(Ioterminal ioterminal)
    {
        return environlMapper.selectimei(ioterminal);
    }
    /**
     * 下拉显示录入人姓名
     *
     * @param user 设备信息
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<User> selectuser(User user)
    {
        return environlMapper.selectuser(user);
    }
    /**
     * 控制台环境数据图表查询
     *
     * @param deptid
     * @return 结果
     */
    @Override
    public List<Environl>selectEnvironlListOnControl(String deptId){
        return environlMapper.selectEnvironlListOnControl(deptId);
    }
}
