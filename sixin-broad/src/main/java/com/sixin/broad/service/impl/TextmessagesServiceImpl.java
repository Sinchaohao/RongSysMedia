package com.sixin.broad.service.impl;

import com.sixin.broad.domain.Textmessages;
import com.sixin.broad.domain.typenum;
import com.sixin.broad.mapper.TextmessagesMapper;
import com.sixin.broad.service.ITextmessagesService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 获取短信终端类型及数量以及号码$
 * @Param: $
 * @return: $
 * @Author: 戴誉琪
 * @Date: $
 */
@Service
public class TextmessagesServiceImpl  implements ITextmessagesService {

    @Autowired
    private TextmessagesMapper textmessagesMapper;
    /**
     * 获取所有类型及数量
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<typenum> selectTypeNum() {
        return textmessagesMapper.selectTypeNum();
    }
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Textmessages> selectTextMessages(){ return  textmessagesMapper.selectTextMessages();}
}
