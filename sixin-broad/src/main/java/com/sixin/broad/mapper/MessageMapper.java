package com.sixin.broad.mapper;

import com.sixin.broad.domain.BroadMessage;
import com.sixin.broad.domain.MessageExample;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MI on 2019/3/29.
 */
@Repository
public interface MessageMapper {

    public List<BroadMessage> selectMessageList(BroadMessage broadMessage);
    public List<BroadMessage> selectMessageListUser(BroadMessage broadMessage);
    public List<MessageExample> selectMessageExampleList();

    List<BroadMessage> selectMessageList1(BroadMessage broadMessage);
}
