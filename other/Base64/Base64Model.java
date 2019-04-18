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
     * Base64������Կ
     * @param key       ��Կ
     * @return byte[]   �������Կ
     */
    public static byte[] decryptBASE64(String key) {
        return Base64.decodeBase64(key);
    }

    /**
     * Base64������Կ
     * @param bytes    ��Կ
     * @return String  �������Կ
     */
    public static String encryptBASE64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }
    
    /**
     * �ù�Կ�������
     * @param data     ԭʼ����
     * @param key      ��Կ����
     * @return byte[]  ���ܺ�����
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(String data, String key) throws Exception {

        //������Base64����Ĺ�Կ
        byte[] keyBytes = decryptBASE64(key);
        // ���²�������ԭ��Կ�Ĺ���
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = (Key) keyFactory.generatePublic(x509KeySpec);
        //�����ݼ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, (java.security.Key) publicKey);
        return cipher.doFinal(data.getBytes());
    }
    /**
     * ��˽Կ��������
     * @param data     ���ܺ�����
     * @param key      ˽Կ
     * @return byte[]  ���ܺ�����
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception{

        //������Base64�����˽Կ
        byte[] keyBytes = decryptBASE64(key);
        /**
         * ���²�������ԭ˽Կ�Ĺ���
         */
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = (Key) keyFactory.generatePrivate(pkcs8KeySpec);
        //�����ݽ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, (java.security.Key) privateKey);
        return cipher.doFinal(data);
    }
    
    /**
     * ��ȡ��Կ����
     * @param keyMap   �ѳ�ʼ����Կ����
     * @return Key     ��Կ����
     * @throws Exception
     */
    public static Key getPublicKeyNoBase64(Map<String, Key> keyMap)
            throws Exception {
        return keyMap.get(PUBLIC_KEY);
    }
    
    /**
     * ��ȡ˽Կ����
     * @param keyMap   �ѳ�ʼ����Կ����
     * @return Key     ˽Կ����
     * @throws Exception
     */
    public static Key getPrivateKeyNoBase64(Map<String, Key> keyMap)
            throws Exception {
        return keyMap.get(PRIVATE_KEY);
    }
}
