package com.offcial.utils;


import com.alibaba.fastjson.JSONObject;
import com.offcial.dao.AccessTokenDao;
import com.offcial.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 获取access_token工具
 *
 * @author Zza
 * @date 2017-10-19 15:52
 */
@Controller
@RequestMapping("/")
public class AccessTokenUtil {

    @Autowired
    private AccessTokenDao accessTokenDao;

    private String getAccessToken() throws IOException, KeyManagementException, NoSuchProviderException, NoSuchAlgorithmException {
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx08d7c9d49f79e475&secret=7b16598dcdf328a6e9072699f7c87bfb";
        //建立连接
        URL url = new URL(tokenUrl);
        HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
        // 创建SSLContext对象，并使用我们指定的信任管理器初始化
        TrustManager[] tm = {new MyX509TrustManager()};
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        httpUrlConn.setSSLSocketFactory(ssf);
        httpUrlConn.setDoOutput(true);
        httpUrlConn.setDoInput(true);
        // 设置请求方式（GET/POST）
        httpUrlConn.setRequestMethod("GET");
        // 取得输入流
        InputStream inputStream = httpUrlConn.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        //读取响应内容
        StringBuffer buffer = new StringBuffer();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        // 释放资源
        inputStream.close();
        httpUrlConn.disconnect();
        //输出返回结果
        JSONObject jsonObject = JSONObject.parseObject(buffer.toString());
        return jsonObject.getString("access_token");
    }

    /**
     * 每1.5小时获取个token并存库
     */
    @RequestMapping("/time")
    public void insertToken() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    TokenDto tokenDto = new TokenDto();
                    tokenDto.setAccessToken(getAccessToken());
                    tokenDto.setTime(LocalDateTime.now().toString());
                    accessTokenDao.save(tokenDto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 1000, 7200000);
    }

}
