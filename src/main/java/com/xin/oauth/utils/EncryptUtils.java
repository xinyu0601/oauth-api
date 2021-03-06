package com.xin.oauth.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import java.util.Random;


/**
 * @author xinyu.huang02
 * @date 2020-02-27 21:28
 * @class 数据加密相关操作的工具类
 */
public class EncryptUtils {

    private static final String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * 生成随机长度的字符串
     *
     * @param num
     * @return
     */
    public static String generateRandomStr(final int num) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= num; i++) {
            sb.append(CHARS.charAt(new Random().nextInt(CHARS.length())));
        }
        return sb.toString();
    }

    /**
     * 使用sha1加密
     *
     * @param data
     * @return
     */
    public static String sha1Hex(final String data) {
        return DigestUtils.sha1Hex(data);
    }


    /**
     * 使用md5加密
     *
     * @param data
     * @return
     */
    public static String md5Hex(final String data) {
        return DigestUtils.md5Hex(data);
    }


    /**
     * HMAC_SHA_1
     *
     * @param key
     * @param value
     * @return
     */
    public static String hmacSha1(final String key, final String value) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_1, key).hmacHex(value);
    }


    /**
     * HMAC_SHA_256
     *
     * @param key
     * @param value
     * @return
     */
    public static String hmacSha256(final String key, final String value) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_256, key).hmacHex(value);
    }


    /**
     * HMAC_SHA_512
     *
     * @param key
     * @param value
     * @return
     */
    public static String hmacSha512(final String key, final String value) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_512, key).hmacHex(value);
    }
}
