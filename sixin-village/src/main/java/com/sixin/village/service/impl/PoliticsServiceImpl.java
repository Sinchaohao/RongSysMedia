package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.Comment;
import com.sixin.village.domain.Politics;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.mapper.PoliticsMapper;
import com.sixin.village.service.IPoliticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: dyq_RongSys
 * @description: 村务政策
 * @author: 戴誉琪
 * @create: 2019-01-14 15:40
 **/
@Service
public class PoliticsServiceImpl implements IPoliticsService {
    @Autowired
    private PoliticsMapper politicsMapper;

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Politics> selectPoliticsList(Politics politics) {
        return politicsMapper.selectPoliticsList(politics);
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public int deletePoliticsByids(String id) {
        return politicsMapper.deletePoliticsByids(Convert.toStrArray(id));
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public int insertpolitics(Politics politics) {
        return politicsMapper.insertpolitics(politics);
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public Politics selectByshiid(Integer shiid) {
        return politicsMapper.selectByshiid(shiid);
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public int deletePoliticsByIds(String shiid) {
        return politicsMapper.deletePoliticsByIds(shiid);
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public int updatePolitics(Politics politics) {
        return politicsMapper.updatePolitics(politics);
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Politics> selectpoliticslimitten() { return politicsMapper.selectpoliticslimitten();}

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Comment> selectpoliticsListlimit(pubObjApi info) {
        return politicsMapper.selectpoliticsListlimit(info);
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Comment> selectpoliticsrecommentList( int coid) {
        return politicsMapper.selectpoliticsrecommentList(coid);
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public int insertPoliticsCM(Comment comment) {
        return politicsMapper.insertPoliticsCM(comment);
    }
}
