package com.sixin.village.service;

import com.sixin.village.domain.Warnset;

import java.util.List;

/**
 * @开发人员: 申超豪
 * @开发单位：湖南农业大学物联网工程专业
 * @Description:
 * @Date:
 */
public interface IWarnsetService {
    /**
     * 查询报警提示信息
     *
     * @param id 参数ID
     * @return 报警提示信息
     */
    public Warnset selectWarnsetById(Long id);

    /**
     * 查询报警提示列表
     *
     * @param warnset 报警提示对象
     * @return 报警提示集合
     */
    public List<Warnset> selectWarnsetList(Warnset warnset);

    /**
     * 新增报警提示
     *
     * @param warnset 报警提示对象
     * @return 结果
     */
    public int insertWarnset(Warnset warnset);

    /**
     * 修改报警提示
     *
     * @param warnset 报警提示对象
     * @return 结果
     */
    public int updateWarnset(Warnset warnset);

    /**
     * 批量删除报警提示
     *
     * @param ids 需要删除的参数ID
     * @return 结果
     */
    public int deleteWarnsetByIds(String ids);

    /**
     * @Author TanXY
     * @Description 按需导出数据
     * @Date 2020/4/18 15:16
     * @Param [warnset]
     * @return java.util.List<com.sixin.village.domain.Warnset>
     */
    public List<Warnset> selectWarnsetByIds(List<String> id);
}
