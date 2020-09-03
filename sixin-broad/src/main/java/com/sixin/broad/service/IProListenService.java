package com.sixin.broad.service;

import com.sixin.broad.domain.ProApplyUser;
import com.sixin.broad.domain.ProListen;
import com.sixin.broad.domain.ProreApply;

import java.util.List;

/**
 * Created by ASUS on 2019/8/11.
 * @author cx
 * @description 节目省听 业务层
 */
public interface IProListenService {
    /**
     * 查询节目申请信息
     *
     * @param paid 节目申请ID
     * @return 节目申请信息
     */
    ProListen selectProListenById(Integer paid);

    /**
     * 查询节目申请列表
     *
     * @param proListen 节目申请信息
     * @return 节目申请集合
     */
    List<ProListen> selectProListenList(ProListen proListen);

    /**
     * 新增节目申请
     *
     * @param proListen 节目申请信息
     * @return 结果
     */
    int insertProListen(ProListen proListen);

    /**
     * 修改节目申请
     *
     * @param proListen 节目申请信息
     * @return 结果
     */
    int updateProListen(ProListen proListen);

    /**
     * 删除节目申请信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteProListenByIds(String ids);


    /**
     * 该方法只提供节目申请表中的目录
     * @date 2019/3/2
     * @param proApplyUser
     * @return java.util.List<com.sixin.broad.domain.ProApplyUser>
     */
    List<ProApplyUser> selectProrApplyUserList(ProApplyUser proApplyUser);

    int checkpass(String paid);

}
