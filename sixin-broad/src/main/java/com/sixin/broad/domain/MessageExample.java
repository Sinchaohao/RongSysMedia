package com.sixin.broad.domain;

/**
 * 短信模板
 *
 * @author 周博
 * @date 2019-04-03
 */
public class MessageExample {
    /*短信模板ID*/
    private int id;
    /*短信模板名称*/
    private String name;
    /*短信模板内容*/
    private String details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
