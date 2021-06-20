package com.ruoyi.common.utils.cfy;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 创富云加密解密
 */
public class DESUtil {
    /**
     * 创富云解密数据
     * @param data
     * @param key
     * @return String
     */
    public static String decryptData(String data, String key)
    {
        try
        {
            // 密钥字符串转为byte数组
            byte[] keyBytes     = key.getBytes(StandardCharsets.UTF_8);

            // 加密字符串转为byte数组
            byte[] encryptBytes = data.getBytes(StandardCharsets.UTF_8);

            // 对加密数据byte进行base64解码
            byte[] base64decoded = Base64.getDecoder().decode(encryptBytes);

            // 通过 DES工具解密数据
            byte[] bt = decrypt(base64decoded, keyBytes);

            return new String(bt);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 创富云解密
     * @param src byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    private static byte[] decrypt(byte[] src,byte[] key) throws Exception
    {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key);
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }

    /**
     * 创富云加密数据
     * @param data 待加密的数据
     * @param key 加密key
     * @return String
     */
    public static String encryptData(String data, String key)
    {
        try
        {
            // 加密字符串转为byte数组
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);

            // 密钥字符串转为byte数组
            byte[] keyBytes  = key.getBytes(StandardCharsets.UTF_8);

            // 通过 DES工具加密
            byte[] bt = encrypt(dataBytes, keyBytes);

            // 对加密数据byte进行base64解码
            byte[] encode = Base64.getEncoder().encode(bt);

            return new String(encode);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 创富云加密
     * @param src
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] src,byte[] key) throws Exception
    {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key);
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }
}
