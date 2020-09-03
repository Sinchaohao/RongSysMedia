package com.sixin.iot.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;

import com.sixin.iot.domain.Save;
import com.sixin.iot.domain.Securitype;
import com.sixin.iot.mapper.SaveMapper;
import com.sixin.iot.service.SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixin.common.support.Convert;import com.sixin.common.utils.StringUtils;
import java.util.List;

/**
 * @program: SaveSys
 * @description: 安防惯例
 * @author: 饶泽敏
 * @create: 2019-12-24 15:40
 **/
@Service
public class SaveServiceImpl implements SaveService {
    @Autowired
    private SaveMapper saveMapper;

    /**
     * 查询安防记录列表
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Save> selectSaveList(Save save) {
        return saveMapper.selectSaveList(save);
    }


    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Securitype> selectSecuritypeList(Securitype securitype) {
        return saveMapper.selectSecuritypeList(securitype);
    }

    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public Save selectSaveByid(String id) {
        return saveMapper.selectSaveByid(id);
    }

    /**
     * 添加安防公告数据
     * @param  save
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int insertSave(Save save) {
        return saveMapper.insertSave(save);
    }


    /**
     * 根据id批量删除数据
     * @param id

     @Override
     @DataSource(value = DataSourceType.SXINFOM)
     public int deleteSaveByid(String id) {
     return saveMapper.deleteSaveByids(Convert.toStrArray(id));
     }*/


    /**
     * 根据id删除安防信息
     * @param ids
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteSaveByids(String ids) {
        return saveMapper.deleteSaveByids(Convert.toStrArray(ids));
    }

    /**
     * 保存用户修改
     * @param save
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int updateSave(Save save) {
        return saveMapper.updateSave(save);
    }

    /**
     * 用户状态修改
     *
     * @param user 用户信息
     * @return 结果
     */

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int changeStatus(Save save)
    {
        return saveMapper.updateSave(save);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Save> selectSaveByids(List<String> sfids) {
        return saveMapper.selectSaveByids(sfids);
    }
}
