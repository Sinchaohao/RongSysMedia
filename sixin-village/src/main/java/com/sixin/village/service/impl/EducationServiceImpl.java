package com.sixin.village.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.village.domain.Education;
import com.sixin.village.domain.pubObjApi;
import com.sixin.village.mapper.EducationMapper;
import com.sixin.village.service.IEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: dyq_RongSys
 * @description: 党员学习
 * @author: 戴誉琪
 * @create: 2019-01-14 15:40
 **/
@Service
public class EducationServiceImpl implements IEducationService {
        @Autowired
        private EducationMapper educationmapper;

        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public List<Education> selectEducationList(Education education) {
            return educationmapper.selectEducationList(education);
        }
        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public int deleteEducationByids(String id) {
            return educationmapper.deleteEducationByids(Convert.toStrArray(id));
        }
        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public int inserteducation(Education education) {
            return educationmapper.inserteducation(education);
        }

        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public Education selectByeid(Integer eid) {
            return educationmapper.selectByeid(eid);
        }

        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public int deleteEducationByIds(String eid) {
            return educationmapper.deleteEducationByIds(eid);
        }

        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public int updateEducation(Education education) {
            return educationmapper.updateEducation(education);
        }

        @Override
        @DataSource(value = DataSourceType.SXVILLAGE)
        public List<Education> selectEducationListById(pubObjApi party) {
            return educationmapper.selectEducationListById(party);
        }
    }
