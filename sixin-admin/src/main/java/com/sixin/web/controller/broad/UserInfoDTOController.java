package com.sixin.web.controller.broad;

import com.sixin.broad.domain.UserInfoDTO;
import com.sixin.broad.service.IUserInfoDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/broad/dto")
public class UserInfoDTOController {

    @Autowired
    private IUserInfoDTOService userInfoDTOService;

    @GetMapping("/userinfos")
    public List<UserInfoDTO> findAll(){
        return userInfoDTOService.findAll();
    }

    @GetMapping("/userinfo/{userid}")
    public UserInfoDTO findById(@PathVariable("userid") String userid){
        return userInfoDTOService.findById(userid);
    }

    @GetMapping("/userinfo/{uname}")
    public List<UserInfoDTO> findUserByName(@PathVariable("uname")String uname){
        return userInfoDTOService.findUserByName(uname);
    }
}
