package com.sixin.system.service.impl;

import com.sixin.common.annotation.DataScope;
import com.sixin.common.support.Convert;
import com.sixin.system.domain.SysPhone;
import com.sixin.system.mapper.SysPhoneMapper;
import com.sixin.system.service.ISysPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
@Service
public class SysPhoneServiceImpl implements ISysPhoneService {
    @Autowired
    private SysPhoneMapper sysPhoneMapper;


    /**
     * 查询授权号码集合
     *
     * @param phone 授权号码对象
     * @return 授权号码集合
     */
    @Override
    public List<SysPhone> selectPhonelist(SysPhone phone){
        BigInteger a = BigInteger.valueOf(10000);
        List<SysPhone> phonelist=sysPhoneMapper.selectPhonelist(phone);
        for(SysPhone phone1: phonelist){
            phone1.setDeptid(phone1.getDeptid().divide(a));
        }
        return phonelist;
    }

    /**
     * 新增保存授权号码
     *
     * @param phone 授权号码对象
     * @return 结果
     */
    @Override
    public int  insertPhone(SysPhone phone){
        return sysPhoneMapper.insertPhone(phone);
    }
    /**
     * 删除授权号码
     *
     * @param ids 删除授权号码编号
     * @return 结果
     */
    @Override
    public int deletePhone(String ids){
        return sysPhoneMapper.deletePhone(Convert.toStrArray(ids));
    }

    /**
     * 按编号查询
     *
     * @param pid 授权号码编号
     * @return 结果
     */
    @Override
    public SysPhone selectPhoneById(Long pid){
        return sysPhoneMapper.selectPhoneById(pid);
    }
    /**
     * 修改保存授权编号
     *
     * @param phone 授权号码对象
     * @return 结果
     */
    @Override
    public int updatePhone(SysPhone phone){
        return sysPhoneMapper.updatePhone(phone);
    }
}
