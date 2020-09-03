package com.sixin.broad.service;

//import com.github.pagehelper.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sixin.broad.domain.BroadMessage;
import com.sixin.broad.domain.MessageExample;
import com.sixin.common.utils.PageData;

import java.util.List;
import java.util.Map;

/**
 * 短信播出端
 *
 * @author 周博
 * @date 2019-03-29
 */
public interface IMessageService {

    public List<Map<String, Object>> selectMessageList (BroadMessage broadMessage);

    List<Map<String, Object>> selectMessageList1(BroadMessage broadMessage);
    public List<Map<String, Object>> selectMessageListUser(BroadMessage broadMessage);
    public List<MessageExample> selectMessageExampleList();

    /**
     * 获取发件箱短信
     * @param page
     * @return
     * @throws Exception
     */

    public List<PageData> listSendMessage(Page page) throws Exception;
    /**
     * 获取收件箱短信
     * @param page
     * @return
     * @throws Exception
     */

    public List<PageData> listReceiveMessage(Page page) throws Exception;
    /**
     * 查询未发送短信
     * @param str
     * @return
     * @throws Exception
     */
    public List<PageData> getUnSendMessage(String str) throws Exception;
    /**
     * 添加发送短信记录
     * @param pd
     * @throws Exception
     */
    public void addSendMessage(List<PageData> pdlist) throws Exception;
    /**
     * 添加接收短信记录
     * @param pd
     * @throws Exception
     */
    public void addReceiveMessage(PageData pd) throws Exception;
    /**
     * 设置短信已发送
     * @param smid
     * @throws Exception
     */
    public void setMessageSend(String smid) throws Exception;
    /**
     * 根据编号获取发送记录
     * @param string
     * @return
     */
    public PageData getSendBySmid(String smid) throws Exception;
    /**
     * 根据编号获取接收记录
     * @param rmid
     * @return
     * @throws Exception
     */
    public PageData getReceiveByRmid(String rmid) throws Exception;
}
