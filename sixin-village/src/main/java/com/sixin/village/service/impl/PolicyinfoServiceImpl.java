package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.Comment;
import com.sixin.village.domain.Policyinfo;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.mapper.PolicyinfoMapper;
import com.sixin.village.service.IPolicyinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: dyq_RongSys
 * @description: 村务公告
 * @author: 戴誉琪
 * @create: 2019-01-14 15:40
 **/
@Service
public class PolicyinfoServiceImpl implements IPolicyinfoService {
        @Autowired
        private PolicyinfoMapper policyinfoMapper;

        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public List<Policyinfo> selectPolicyinfoList(Policyinfo policyinfo) {
            return policyinfoMapper.selectPolicyinfoList(policyinfo);
        }

        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public int deletePolicyinfoByids(String id) {
            return policyinfoMapper.deletePolicyinfoByids(Convert.toStrArray(id));
        }

        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public int insertpolicyinfo(Policyinfo policyinfo) {
            return policyinfoMapper.insertpolicyinfo(policyinfo);
        }

        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public Policyinfo selectBypoinid(Integer shiid) {
            return policyinfoMapper.selectBypoinid(shiid);
        }

        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public int deletePolicyinfoByIds(String shiid) {
            return policyinfoMapper.deletePolicyinfoByIds(shiid);
        }

        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public int updatePolicyinfo(Policyinfo politics) {
            return policyinfoMapper.updatePolicyinfo(politics);
        }

        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public List<Policyinfo> selectpoliclimitten(){    return policyinfoMapper.selectpoliclimitten();}

        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public List<Comment> selectinfocommentListlimit(pubObjApi info){
            return policyinfoMapper.selectinfocommentListlimit(info);
        }

        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public List<Comment> selectinforecommentList(int coid){
            return policyinfoMapper.selectinforecommentList(coid);
        }

        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public int insertInfoCM(Comment comment) {
            return policyinfoMapper.insertInfoCM(comment);
        }
    }
