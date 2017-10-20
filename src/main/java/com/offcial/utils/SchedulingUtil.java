package com.offcial.utils;

import com.offcial.dao.AccessTokenDao;
import com.offcial.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * 定时器(定时获取accessToken)
 *
 * @author Zza
 * @date 2017-10-20 9:40
 */
@Configuration
@EnableScheduling
public class SchedulingUtil {

    @Autowired
    private AccessTokenDao accessTokenDao;
    @Autowired
    private AccessTokenUtil accessTokenUtil;

    // 每2小时执行一次
    @Scheduled(cron = "0 0 0/2 * * ? *")
    public void scheduler() {
        try {
            TokenDto tokenDto = new TokenDto();
            tokenDto.setAccessToken(accessTokenUtil.getAccessToken());
            tokenDto.setTime(LocalDateTime.now().toString());
            accessTokenDao.save(tokenDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
