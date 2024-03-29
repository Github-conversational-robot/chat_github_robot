package com.robot.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;

import java.util.Base64;
import java.util.Base64.Decoder;


public class EncryptUtils {

    private static final String SECRET_KEY = "LmMGStGtOpF4xNyvYt54EQ==";

    /**
     * DES加密
     * @param source 明文
     * @return 密文
     * @throws Exception 异常
     */
    public static String desEncrypt(String source) throws Exception {
        byte[] key = Base64.getDecoder().decode(SECRET_KEY);

        DES des = SecureUtil.des(key);
        return des.encryptHex(source);
    }

    /**
     * DES解密
     * @param encrypt 密文
     * @return 明文
     * @throws Exception 异常
     */
    public static String desDecrypt(String encrypt) throws Exception {

        byte[] key = Base64.getDecoder().decode(SECRET_KEY);

        DES des = SecureUtil.des(key);
        return des.decryptStr(encrypt);
    }
}
