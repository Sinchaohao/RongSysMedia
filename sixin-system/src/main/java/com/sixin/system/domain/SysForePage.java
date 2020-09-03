package com.sixin.system.domain;

public class SysForePage {

    private int id;
    private String page;
    private char status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysForePage{" +
                "id=" + id +
                ", page='" + page + '\'' +
                ", status=" + status +
                '}';
    }
}
