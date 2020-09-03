package com.sixin.broad.service;

import com.sixin.broad.domain.UserInfoDTO;

import java.util.List;

public interface IUserInfoDTOService {

    List<UserInfoDTO> findAll();

    UserInfoDTO findById(String userid);

    List<UserInfoDTO> findUserByName(String uname);
}
