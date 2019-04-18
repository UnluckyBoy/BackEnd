package Base64;

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class Base64Model {
	
	public static final String KEY_ALGORITHM = "RSA";
	private static final String PUBLIC_KEY = "PublicKey";
	private static final String PRIVATE_KEY = "PrivateKey";
	
	/**
     * Base64解码密钥
     * @param key       密钥
     * @return byte[]   解码后密钥
     */
    public static byte[] decryptBASE64(String key) {
        return Base64.decodeBase64(key);
    }

    /**
     * Base64编码密钥
     * @param bytes    密钥
     * @return String  编码后密钥
     */
    public static String encryptBASE64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }
    
    /**
     * 用公钥对象加密
     * @param data     原始数据
     * @param key      公钥对象
     * @return byte[]  加密后数据
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(String data, String key) throws Exception {

        //解码由Base64编码的公钥
        byte[] keyBytes = decryptBASE64(key);
        // 如下操作即还原公钥的过程
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = (Key) keyFactory.generatePublic(x509KeySpec);
        //对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, (java.security.Key) publicKey);
        return cipher.doFinal(data.getBytes());
    }
    /**
     * 用私钥解密数据
     * @param data     加密后数据
     * @param key      私钥
     * @return byte[]  解密后数据
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception{

        //解码由Base64编码的私钥
        byte[] keyBytes = decryptBASE64(key);
        /**
         * 如下操作即还原私钥的过程
         */
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = (Key) keyFactory.generatePrivate(pkcs8KeySpec);
        //对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, (java.security.Key) privateKey);
        return cipher.doFinal(data);
    }
    
    /**
     * 获取公钥对象
     * @param keyMap   已初始化密钥数据
     * @return Key     公钥对象
     * @throws Exception
     */
    public static Key getPublicKeyNoBase64(Map<String, Key> keyMap)
            throws Exception {
        return keyMap.get(PUBLIC_KEY);
    }
    
    /**
     * 获取私钥对象
     * @param keyMap   已初始化密钥数据
     * @return Key     私钥对象
     * @throws Exception
     */
    public static Key getPrivateKeyNoBase64(Map<String, Key> keyMap)
            throws Exception {
        return keyMap.get(PRIVATE_KEY);
    }
}
