package com.sixin.iot.service.impl;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.iot.domain.Blowdown;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.mapper.BlowdownMapper;
import com.sixin.iot.service.IBlowdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BlowdownServiceImpl implements IBlowdownService {
    @Autowired
    private BlowdownMapper blowdownMapper;


    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Blowdown> selectBlowdownList(Blowdown blowdown) {
        return blowdownMapper.selectBlowdownList(blowdown);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int insertBlowdown(Blowdown blowdown) {
        return blowdownMapper.insertBlowdown(blowdown);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public Blowdown selectByid(Long id) {
        return blowdownMapper.selectByid(id);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Blowdown> selectBlowdownByids(List<String> sfids) {
        return blowdownMapper.selectBlowdownByids(sfids);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int updateBlowdown(Blowdown blowdown) {
        return blowdownMapper.updateBlowdown(blowdown);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteBlowdownByid(Long id) {
        return blowdownMapper.deleteBlowdownByid(id);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteBlowdownByids(String ids) {
        return blowdownMapper.deleteBlowdownByids(Convert.toStrArray(ids));
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Torrent> queryList(Torrent torrent) {return blowdownMapper.queryList(torrent);}
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int changeStatus(Blowdown blowdown) {
        return blowdownMapper.updateBlowdown(blowdown);
    }


}
