package com.offcial.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * access_token
 *
 * @author Zza
 * @date 2017-10-19 15:40
 */
@Entity
public class TokenDto {

    @Id
    @GeneratedValue
    private int id;
    /**
     * 接口访问凭证
     */
    private String accessToken;
    /**
     * 凭证有效期，单位：秒
     */
    private String time;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
