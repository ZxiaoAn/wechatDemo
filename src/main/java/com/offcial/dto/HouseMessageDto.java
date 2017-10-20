package com.offcial.dto;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 房屋信息
 *
 * @author Zza
 * @date 2017-10-18 16:24
 */
@Entity
public class HouseMessageDto {

    @Id
    @GeneratedValue
    private int id;

    private String user_name;

    private String info;

    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo_time() {
        return time;
    }

    public void setInfo_time(String info_time) {
        this.time = info_time;
    }
}
