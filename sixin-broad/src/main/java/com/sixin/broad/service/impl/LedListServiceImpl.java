package com.sixin.broad.service.impl;

import com.sixin.broad.domain.LedList;
import com.sixin.broad.mapper.LedListMapper;
import com.sixin.broad.service.ILedListService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: RongSys
 * @description:
 * @开发人员：王豪杰
 * @开发单位：湖南农业大学物联网工程专业
 * @create: 2020-04-01 21:20
 */
@Service
public class LedListServiceImpl implements ILedListService {

    @Autowired
    private LedListMapper ledListMapper;


    /**
     * 查询led显示列表
     *
     * @param ledList led显示信息
     * @return led显示集合
     */
    @Override
    @DataSource(value = DataSourceType.SXIOT)
    public List<LedList> selectLedList(LedList ledList)
    {
        return ledListMapper.selectLedList(ledList);
    }

    /**
     * 新增led显示
     *
     * @param ledList
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXIOT)
    public int insertLedList(LedList ledList)
    {
        return ledListMapper.insertLedList(ledList);
    }

    /**
     * 根据id查询led
     *
     * @param id
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXIOT)
    public LedList selectLedListByid(Long id)
    {
        return ledListMapper.selectLedListByid(id);
    }


    /**
     * 根据id批量查询led
     *
     * @param ids
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXIOT)
    public List<LedList> selectLedListByids(List<String> ids)
    {
        return ledListMapper.selectLedListByids(ids);
    }


    /**
     * 保存Led修改
     * @param ledList
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXIOT)
    public int updateLedList(LedList ledList)
    {
        return ledListMapper.updateLedList(ledList);
    }

    /**
     * 删除led信息
     * @param id
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXIOT)
    public int deleteLedListByid(Long id)
    {
        return ledListMapper.deleteLedListByid(id);
    }

    /**
     * 批量删除led信息
     * @param ids
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXIOT)
    public int deleteLedListByids(String ids)
    {
        return ledListMapper.deleteLedListByids(Convert.toStrArray(ids));
    }

}
