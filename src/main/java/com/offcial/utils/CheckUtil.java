package com.offcial.utils;


import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

/**
 * 核心检查工具
 *
 * @author Zza
 * @date 2017-10-18 16:07
 */
public class CheckUtil {

    private static final String TOKEN = "zza";

    public static boolean checkSignature(String signature, String timestamp, String nonce) {

        String[] arr = new String[]{TOKEN, timestamp, nonce};
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }

        // sha1加密
        String temp = getSHA1String(content.toString());

        // 与微信传递过来的签名进行比较
        return temp.equals(signature);
    }

    private static String getSHA1String(String data) {

        // 使用commons codec生成sha1字符串
        return DigestUtils.sha1Hex(data);
    }

}
