package EncryptionModel;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class Pwd3DESUtil {
	/**
     * 转换成十六进制字符串
     * @param pwdkey
     * @return
     */
    public static byte[] BytetoString(String key){  
        String pwd = DigestUtils.md5Hex(key);  
        byte[] bKeys = new String(pwd).getBytes();  
        byte[] Key = new byte[24];  
        for (int i=0;i<24;i++){  
        	Key[i] = bKeys[i];  
        }  
        return Key;  
    }
    
    /**
     * 3DES加密
     * @param key 密钥，24位
     * @param PwdStr 将加密的字符串
     * @return
     */
    public static String  encode3Des(String key,String PwdStr){  
    	byte[] byteKey = BytetoString(key);
    	byte[] pwdKey = PwdStr.getBytes();
        try {  
           //生成密钥  
           SecretKey deskey = new SecretKeySpec(byteKey, "DESede");
           //加密  
           Cipher cipher = Cipher.getInstance("DESede");
           cipher.init(Cipher.ENCRYPT_MODE, deskey);  
           
           String pwd = Base64.encodeBase64String(cipher.doFinal(pwdKey));
           return pwd;
       } catch (java.security.NoSuchAlgorithmException e) {  
           // TODO: handle exception  
            e.printStackTrace();  
       }catch(javax.crypto.NoSuchPaddingException e){  
           e.printStackTrace();  
       }catch(java.lang.Exception e){  
           e.printStackTrace();  
       }  
       return null;  
   }
    
   /**
    * 3DES解密
    * @param key 加密密钥，长度为24字节  
    * @param desPwd 解密后的字符串
    * @return
    */ 
    public static String decode3Des(String key, String desPwd){  
    	Base64 base64 = new Base64();
    	byte[] byteKey = BytetoString(key);
    	byte[] pwdKey = base64.decode(desPwd);
    			
        try {  
            //生成密钥  
            SecretKey deskey = new SecretKeySpec(byteKey, "DESede");  
            //解密  
            Cipher cipher = Cipher.getInstance("DESede");  
            cipher.init(Cipher.DECRYPT_MODE, deskey);  
            String pwd = new String(cipher.doFinal(pwdKey));
            return pwd;
        } catch (java.security.NoSuchAlgorithmException e) {  
            // TODO: handle exception  
            e.printStackTrace();  
        }catch(javax.crypto.NoSuchPaddingException e){  
            e.printStackTrace();  
        }catch(java.lang.Exception e){  
            e.printStackTrace();  
        }  
        return null;          
    }
}
