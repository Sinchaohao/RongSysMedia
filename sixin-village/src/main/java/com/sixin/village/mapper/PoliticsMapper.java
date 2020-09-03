package com.sixin.village.mapper;

import com.sixin.village.domain.Politics;
import com.sixin.village.domain.Comment;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * @Description: 政策Mapper$
 * @Param: dyq_RongSys$
 * @return: $
 * @Author: Mr.DYQ
 * @Date: $
 */
public interface PoliticsMapper {
    /**
     * 政策记录列表
     *
     * @return
     */
    List<Politics> selectPoliticsList(Politics politics);

    /**
     * 根据shiid删除数据
     * @param id
     */
    int deletePoliticsByids(String[] id);

    /**
     * 添加政策信息
     * @param politics
     * @return
     */
    int insertpolitics(Politics politics);

    /**
     * 根据shiid选择数据
     * @param shiid
     * @return
     */
    Politics selectByshiid(Integer shiid);

    /**
     * 保存用户修改
     * @param politics
     * @return
     */
    int updatePolitics(Politics politics);

    /**
     * 删除政策信息
     * @param shiid
     * @return
     */
    int deletePoliticsByIds(String shiid);

    /**
     * 按照时间倒序获取十条政策信息
     *
     * @return 最近的十条数据
     */
    List<Politics> selectpoliticslimitten();

    public List<Comment> selectpoliticsListlimit(pubObjApi info);

    public List<Comment> selectpoliticsrecommentList(int coid);

    //新增评论
    public int insertPoliticsCM(Comment comment);
}
