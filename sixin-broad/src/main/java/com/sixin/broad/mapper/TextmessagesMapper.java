package com.sixin.broad.mapper;


import com.sixin.broad.domain.typenum;
import com.sixin.broad.domain.Textmessages;

import java.util.List;
/**
 * Created by ASUS on 2019/3/20.
 * @author 戴誉琪
 */
public interface TextmessagesMapper {
    /**
     * 获取所有类型及数量和电话号码
     * @return
     */
    public List<typenum> selectTypeNum();
    /**
     * 获取所有数据
     */
    public List<Textmessages> selectTextMessages();

}
