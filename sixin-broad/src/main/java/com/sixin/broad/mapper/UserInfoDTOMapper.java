package com.sixin.broad.mapper;

import com.sixin.broad.domain.UserInfoDTO;

import java.util.List;

public interface UserInfoDTOMapper {

    List<UserInfoDTO> findAll();

    UserInfoDTO findById(String userid);

    List<UserInfoDTO> findUserByName(String uanme);
}
