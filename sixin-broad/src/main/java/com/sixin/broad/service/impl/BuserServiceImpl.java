package com.sixin.broad.service.impl;

import com.sixin.broad.domain.Buser;
import com.sixin.broad.mapper.BuserMapper;
import com.sixin.broad.service.IBuserService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 根据用户对广播数目的统计
 *
 * @author 周博
 * @date 2019-03-21
 */

@Service
public class BuserServiceImpl implements IBuserService {
    @Autowired
    private BuserMapper buserMapper;

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Buser> selectProgramsByUser() {
        return buserMapper.selectProgramsByUser();
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Buser> selectProgramsByLenth(String deptId) {
        return buserMapper.selectProgramsByLenth(Convert.toLong(deptId));
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Buser> selectProgramsIfPublic(String deptId) {
        List<Buser> res = buserMapper.selectProgramsIfPublic(Convert.toLong(deptId));
        for (Buser buser : res) {
            switch (buser.getIsPublic()) {
                case "0":
                    buser.setIsPublic("非公共");
                    break;
                case "1":
                    buser.setIsPublic("公共");
                    break;
                case "2":
                    buser.setIsPublic("总数");
                    break;
            }
        }
        return res;
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Buser> selectProgramsPtp(String deptId) {
        return buserMapper.selectProgramsPtp(Convert.toLong(deptId));
    }
}
