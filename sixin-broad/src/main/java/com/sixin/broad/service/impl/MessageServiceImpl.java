package com.sixin.broad.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sixin.broad.dao.DaoSupport;
import com.sixin.broad.domain.BroadMessage;
import com.sixin.broad.domain.MessageExample;
import com.sixin.broad.mapper.MessageMapper;
import com.sixin.broad.service.IMessageService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信播出端
 *
 * @author 周博
 * @date 2019-03-29
 */
@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    MessageMapper messageMapper;

    @Autowired
    private DaoSupport dao;

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Map<String, Object>>  selectMessageList(BroadMessage broadMessage) {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<BroadMessage> areaList = messageMapper.selectMessageList(broadMessage);
        trees = getTrees(areaList);
        return trees;
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Map<String, Object>>  selectMessageList1(BroadMessage broadMessage) {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<BroadMessage> areaList = messageMapper.selectMessageList1(broadMessage);
        trees = getTrees(areaList);
        return trees;
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Map<String, Object>> selectMessageListUser(BroadMessage broadMessage) {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<BroadMessage> areaList = messageMapper.selectMessageListUser(broadMessage);
        trees = getTrees(areaList);
        return trees;
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<MessageExample> selectMessageExampleList() {
        return messageMapper.selectMessageExampleList();
    }

    public List<Map<String, Object>> getTrees(List<BroadMessage> broadMessages)
    {

        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (BroadMessage broadMessage : broadMessages)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", broadMessage.getAid());
            map.put("aid", broadMessage.getAid());
            map.put("pId", broadMessage.getParentaid());
            map.put("name", broadMessage.getAname());
            map.put("phone", broadMessage.getPhone());
            map.put("isDev", broadMessage.getIsDev());
            trees.add(map);
        }
        return trees;
    }

    @SuppressWarnings("unchecked")
    public List<PageData> listSendMessage(Page page) throws Exception {
        // TODO Auto-generated method stub
        return (List<PageData>)dao.findForList("MessageMapper.getSendMessagelistPage", page);
    }

    @SuppressWarnings("unchecked")
    public List<PageData> listReceiveMessage(Page page) throws Exception {
        // TODO Auto-generated method stub
        return (List<PageData>)dao.findForList("MessageMapper.getReceiveMessagelistPage", page);
    }

    @Override
    public void addSendMessage(List<PageData> pdlist) throws Exception {
        dao.batchSave("MessageMapper.addSendMessage", pdlist);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PageData> getUnSendMessage(String str) throws Exception {
        return (List<PageData>)dao.findForList("MessageMapper.getUnSendMessage", str);
    }

    @Override
    public void addReceiveMessage(PageData pd) throws Exception {
        dao.save("MessageMapper.addReceiveMessage", pd);
    }

    @Override
    public void setMessageSend(String smid) throws Exception {
        dao.update("MessageMapper.setMessageSend", smid);
    }

    @Override
    public PageData getSendBySmid(String smid) throws Exception {
        return (PageData)dao.findForObject("MessageMapper.getSendBySmid", smid);
    }

    @Override
    public PageData getReceiveByRmid(String rmid) throws Exception {
        return (PageData)dao.findForObject("MessageMapper.getReceiveByRmid", rmid);
    }
}
