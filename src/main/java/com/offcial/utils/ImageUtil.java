package com.offcial.utils;

import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 图片下载
 *
 * @author Zza
 * @date 2017-10-19 14:09
 */
@Service
public class ImageUtil {

    /**
     * 根据文件id下载文件
     *
     * @param mediaId 媒体id
     * @throws Exception
     */

    public InputStream getInputStream(String accessToken, String mediaId) {
        InputStream is = null;
        String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="
                + accessToken + "&media_id=" + mediaId;
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            // 必须是get方式请求
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            // 连接超时30秒
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
            // 读取超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");
            http.connect();
            // 获取文件转化为byte流
            is = http.getInputStream();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;

    }

    /**
     * 获取下载图片信息（jpg）
     *
     * @param mediaId 文件的id
     * @throws Exception
     */
    public void saveImageToDisk(String accessToken, String mediaId, String picName, String picPath) throws Exception {
        InputStream inputStream = getInputStream(accessToken, mediaId);
        byte[] data = new byte[10240];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(picPath + picName + ".jpg");
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 图片下载
     *
     * @param accessToken
     * @param mediaId
     */
    /*public static void getPic(String accessToken, String mediaId) {
        String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
                "MEDIA_ID", mediaId);
        JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
        System.out.println(jsonObject);
    }*/

}
