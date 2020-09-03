package com.sixin.village.mapper;

import com.sixin.village.domain.Comment;
import com.sixin.village.domain.Policyinfo;
import com.sixin.village.domain.pubObjApi;

import java.util.List;

/**
 * @Description: 政策Mapper$
 * @Param: dyq_RongSys$
 * @return: $
 * @Author: Mr.DYQ
 * @Date: $
 */
public interface PolicyinfoMapper {
       /**
     * 政策记录列表
     *
     * @return
     */
    List<Policyinfo> selectPolicyinfoList(Policyinfo policyinfo);

    /**
     * 根据shiid删除数据
     * @param id
     */
    int deletePolicyinfoByids(String[] id);

    /**
     * 添加公告信息
     * @param policyinfo
     * @return
     */
    int insertpolicyinfo(Policyinfo policyinfo);

    /**
     * 根据shiid选择数据
     * @param shiid
     * @return
     */
    Policyinfo selectBypoinid(Integer shiid);

    /**
     * 保存用户修改
     * @param policyinfo
     * @return
     */
    int updatePolicyinfo(Policyinfo policyinfo);

    /**
     * 删除政策信息
     * @param poinid
     * @return
     */
    int deletePolicyinfoByIds(String poinid);

    /**
     * 按照时间倒序获取十条政策信息
     *
     * @return 最近的十条数据
     */
    List<Policyinfo> selectpoliclimitten();

    public List<Comment> selectinfocommentListlimit(pubObjApi info);

    public List<Comment> selectinforecommentList(int coid);

    //新增评论
    public int insertInfoCM(Comment comment);
}
