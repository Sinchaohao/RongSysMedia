package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.village.domain.Andrpic;
import com.sixin.village.service.IAndrpicService;
import com.sixin.village.mapper.AndrpicMapper;
import com.sixin.village.domain.pubObjApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 三级图片 服务层实现
 *
 * @author hfz
 * @date 2019-11-3
 */
@Service
public class AndrpicServiceImpl implements IAndrpicService {
    @Autowired
    private AndrpicMapper andrpicMapper;

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public Andrpic selectAndrpicById(Integer aid)
    {
        return andrpicMapper.selectAndrpicById(aid);
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Andrpic> selectAndrpicList(Andrpic andrpic)
    {
        return andrpicMapper.selectAndrpicList(andrpic);
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Andrpic> selectAndrpicListById(pubObjApi andrpic)
    {
        return andrpicMapper.selectAndrpicListById(andrpic);
    }
}