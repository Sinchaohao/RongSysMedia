package com.sixin.broad.service.impl;

import com.sixin.broad.domain.UserInfoDTO;
import com.sixin.broad.mapper.UserInfoDTOMapper;
import com.sixin.broad.service.IUserInfoDTOService;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoDTOServiceImpl implements IUserInfoDTOService {

    @Autowired
    private UserInfoDTOMapper userInfoDTOMapper;

    @Override
    public List<UserInfoDTO> findAll(){
        return userInfoDTOMapper.findAll();
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public UserInfoDTO findById(String userid){
        return userInfoDTOMapper.findById(userid);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<UserInfoDTO> findUserByName(String uname){
        return userInfoDTOMapper.findUserByName(uname);
    }

}
