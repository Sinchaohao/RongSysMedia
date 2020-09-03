package com.sixin.iot.mapper;

import com.sixin.iot.domain.Save;
import com.sixin.iot.domain.Securitype;

import java.util.List;

public interface SaveMapper {
    /**
     * 安防记录列表
     *
     * @return
     */
    List<Save> selectSaveList(Save save);


    List<Securitype> selectSecuritypeList(Securitype securitype);
    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    Save selectSaveByid(String id);

    /**
     * 添加安防信息
     * @param save
     * @return
     */
    int insertSave(Save save);

    /* 根据id删除数据
     * 根据id批量删除数据
     * @param id
     */
    int deleteSaveByids(String[] ids);

    /**
     * 保存用户修改
     * @param save
     * @return
     */
    int updateSave(Save save);
    /**
     * 删除安防信息
     * @param id
     * @return

    int deleteSaveByid(String id);*/

    public List<Save> selectSaveByids(List<String> sfids);
}