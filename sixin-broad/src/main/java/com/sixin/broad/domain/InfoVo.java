package com.sixin.broad.domain;

/**
 * @program: RongSys
 * @description:
 * @author: Mr.Liu
 * @create: 2020-04-14 21:56
 **/
public class InfoVo {
    String id;
    String name;
    String phone;
    String content;

    public InfoVo(String id, String name, String phone, String content) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.content = content;
    }

    public InfoVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
