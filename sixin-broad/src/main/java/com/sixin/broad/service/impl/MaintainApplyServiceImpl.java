package com.sixin.broad.service.impl;

import com.sixin.broad.domain.MaintainApply;
import com.sixin.broad.mapper.MaintainApplyMapper;
import com.sixin.broad.service.IMaintainApplyService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/7/12.
 * @author 陈霞
 */
@Service
public class MaintainApplyServiceImpl implements IMaintainApplyService {

    @Autowired
    private MaintainApplyMapper maintainApplyMapper;

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public MaintainApply selectMaintainApplyById(String maid){
        return maintainApplyMapper.selectMaintainApplyById(maid);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<MaintainApply> selectMaintainApplyList(MaintainApply maintainApply){
        return maintainApplyMapper.selectMaintainApplyList(maintainApply);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<MaintainApply> selectAllMaintainApply(){
        return maintainApplyMapper.selectAllMaintainApply();
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int deleteMaintainApplyById(String maid){return maintainApplyMapper.deleteMaintainApplyById(Convert.toStrArray(maid));}

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int insertMaintainApply(MaintainApply maintainApply){return maintainApplyMapper.insertMaintainApply(maintainApply);}

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int updateMaintainApply(MaintainApply maintainApply){return maintainApplyMapper.updateMaintainApply(maintainApply);}
}
