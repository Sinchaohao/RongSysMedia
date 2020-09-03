package com.sixin.broad.domain;

public class UserInfoDTO {

    private String userid;
    private String uname;
    private  String phonenumber;

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getUserid() {
        return userid;
    }

    public String getUname() {
        return uname;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "userid='" + userid + '\'' +
                ", uname='" + uname + '\'' +
                '}';
    }
}
