package com.sixin.broad.service.impl;

import com.sixin.broad.domain.ProSpec;
import com.sixin.broad.mapper.ProSpecMapper;
import com.sixin.broad.service.IProSpecService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 特种节目管理
 *
 * @author 周博
 * @date 2019-03-27
 */
@Service
public class ProSpecServiceImpl implements IProSpecService {
    @Autowired
    private ProSpecMapper proSpecMapper;

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public ProSpec selectProSpecById(String fid) {
        return proSpecMapper.selectProSpecById(fid);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<ProSpec> selectProSpecList(ProSpec proSpec) {
        return proSpecMapper.selectProSpecList(proSpec);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<ProSpec> selectAllProSpec() {
        return proSpecMapper.selectAllProSpec();
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int updateprospec(ProSpec proSpec){
        return proSpecMapper.updateprospec(proSpec);
    }
}
