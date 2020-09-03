package com.sixin.system.service.impl;

import com.sixin.common.support.Convert;
import com.sixin.system.domain.SysPerson;
import com.sixin.system.mapper.SysPersonMapper;
import com.sixin.system.service.ISysPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
@Service
public class SysPersonServiceImpl implements ISysPersonService {
    @Autowired
    private SysPersonMapper sysPersonMapper;


    /**
     * 查询特殊人员集合
     *
     * @param person 特殊人员对象
     * @return 特殊人员集合
     */
    @Override
    public List<SysPerson> selectPersonlist(SysPerson person){
        return sysPersonMapper.selectPersonlist(person);
    }

    /**
     * 新增保存特殊人员
     *
     * @param person 特殊人员对象
     * @return 结果
     */
    @Override
    public int insertPerson(SysPerson person){
        return sysPersonMapper.insertPerson(person);
    }

    /**
     * 删除特殊人员
     *
     * @param ids 特殊人员编号
     * @return 结果
     */
    @Override
    public int deletePersonById(String ids){
        return sysPersonMapper.deletePersonById(Convert.toStrArray(ids));
    }

    /**
     * 按编号查询
     *
     * @param id 特殊人员编号
     * @return 结果
     */
    @Override
    public SysPerson selectPersonById(Long id){
        return sysPersonMapper.selectPersonById(id);
    }

    /**
     * 修改特殊人员
     *
     * @param person 特殊人员对象
     * @return 结果
     */
    @Override
    public int updatePerson(SysPerson person){
        return sysPersonMapper.updatePerson(person);
    }
}
