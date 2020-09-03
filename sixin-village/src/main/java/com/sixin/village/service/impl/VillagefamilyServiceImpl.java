package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.Villagefamily;
import com.sixin.village.mapper.VillagefamilyMapper;
import com.sixin.village.service.IVillagefamilyService;
import com.sixin.village.util.FilterText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: lyb_RongSys
 * @description: 小村家事 服务层接口方法实现
 * @author: Mr.Liu
 * @create: 2019-01-15 15:01
 **/
@Service
public class VillagefamilyServiceImpl implements IVillagefamilyService {
    @Autowired
    private VillagefamilyMapper villagefamilyMapper;

    /*
     *  @DataSource注解是连接数据库数据源
     * */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Villagefamily> selectvillagefamilylist(Villagefamily villagefamily) {
        List<Villagefamily> list = villagefamilyMapper.selectVillageFamilyList(villagefamily);
        for(Villagefamily village : list){
            if(village.getContent().length() > 0)
                village.setContent(FilterText.delHTMLTag(village.getContent())); //过滤html标签
        }
        return list;
    }


    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public int insertvillagefamily(Villagefamily villagefamily) {
        return villagefamilyMapper.insertVillageFamily(villagefamily);
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public Villagefamily selectByfbid(Integer jsid) {
        return villagefamilyMapper.selectByfbid(jsid);
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public int updatevillagefamily(Villagefamily villagefamily) {
        return villagefamilyMapper.updateVillageFamily(villagefamily);
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public int deletevillagefamilybyids(String jsid) {
        return villagefamilyMapper.deleteVillageFamilyByids(Convert.toStrArray(jsid));
    }

}
