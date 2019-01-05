package com.zh.encrypt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

/** 
 * RSA算法，实现数据的加密解密。 
 */
public class RSA {
	private static Cipher cipher;  
    
    static{  
        try {
            cipher = Cipher.getInstance("RSA");  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
        }  
    }
    
    /** 
     * 生成密钥对
     * @return
     */  
    public static Map<String,String> generateKeyPair(){
        try {  
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");  
            // 密钥位数  
            keyPairGen.initialize(2048);  
            // 密钥对  
            KeyPair keyPair = keyPairGen.generateKeyPair();  
            // 公钥  
            PublicKey publicKey = keyPair.getPublic();  
            // 私钥  
            PrivateKey privateKey = keyPair.getPrivate();  
            // 得到公钥字符串
            String publicKeyString = getKeyString(publicKey);  
            // 得到私钥字符串
            String privateKeyString = getKeyString(privateKey);  
            // 将生成的密钥对返回
            Map<String,String> map = new HashMap<String,String>();  
            map.put("publicKey", publicKeyString);  
            map.put("privateKey", privateKeyString);  
            return map;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }
    
    /** 
     * 得到公钥 
     *  
     * @param key 
     *            密钥字符串（经过base64编码） 
     * @throws Exception 
     */  
    public static PublicKey getPublicKey(String key) throws Exception {  
        byte[] keyBytes;  
        keyBytes = Base64.decodeBase64(key); 
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
        PublicKey publicKey = keyFactory.generatePublic(keySpec);  
        return publicKey;  
    }  
      
    /** 
     * 得到私钥 
     *  
     * @param key 
     *            密钥字符串（经过base64编码） 
     * @throws Exception 
     */  
    public static PrivateKey getPrivateKey(String key) throws Exception {  
        byte[] keyBytes;  
        keyBytes = Base64.decodeBase64(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);  
        return privateKey;  
    }
    
    /** 
     * 得到密钥字符串（经过base64编码） 
     *  
     * @return 
     */  
    public static String getKeyString(Key key) throws Exception {  
        byte[] keyBytes = key.getEncoded();  
        String s = Base64.encodeBase64URLSafeString(keyBytes);  
        return s;  
    }
    
    /** 
     * 使用公钥对明文进行加密，返回BASE64编码的字符串 
     * @param publicKey 
     * @param plainText 
     * @return 
     */  
    public static String encrypt(PublicKey publicKey, String plainText){  
        try {             
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
            byte[] enBytes = cipher.doFinal(plainText.getBytes());            
            return Base64.encodeBase64URLSafeString(enBytes);  
        } catch (InvalidKeyException e) {  
            e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
            e.printStackTrace();  
        } catch (BadPaddingException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
      
    /** 
     * 使用keystore对明文进行加密 
     * @param publicKeystore 公钥文件路径 
     * @param plainText      明文 
     * @return 
     */  
    public static String fileEncrypt(String publicKeystore, String plainText){  
        try {             
            FileReader fr = new FileReader(publicKeystore);  
            BufferedReader br = new BufferedReader(fr);  
            String publicKeyString="";  
            String str;  
            while((str=br.readLine())!=null){  
                publicKeyString+=str;  
            }  
            br.close();  
            fr.close();  
            cipher.init(Cipher.ENCRYPT_MODE,getPublicKey(publicKeyString));  
            byte[] enBytes = cipher.doFinal(plainText.getBytes());            
            return Base64.encodeBase64URLSafeString(enBytes);  
        } catch (InvalidKeyException e) {  
            e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
            e.printStackTrace();  
        } catch (BadPaddingException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }
    
    /** 
     * 使用公钥对明文进行加密 
     * @param publicKey      公钥 
     * @param plainText      明文 
     * @return 
     */  
    public static String encrypt(String publicKey, String plainText){  
        try {              
            cipher.init(Cipher.ENCRYPT_MODE,getPublicKey(publicKey));  
            byte[] enBytes = cipher.doFinal(plainText.getBytes());            
            return Base64.encodeBase64URLSafeString(enBytes);
        } catch (InvalidKeyException e) {
            e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
            e.printStackTrace();  
        } catch (BadPaddingException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    } 
      
    /** 
     * 使用私钥对明文密文进行解密 
     * @param privateKey 
     * @param enStr 
     * @return 
     */  
    public static String decrypt(PrivateKey privateKey, String enStr){  
        try {  
            cipher.init(Cipher.DECRYPT_MODE, privateKey);  
            byte[] deBytes = cipher.doFinal(Base64.decodeBase64(enStr));  
            return new String(deBytes);  
        } catch (InvalidKeyException e) {  
            e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
            e.printStackTrace();  
        } catch (BadPaddingException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }
    
    /** 
     * 使用私钥对密文进行解密 
     * @param privateKey       私钥 
     * @param enStr            密文 
     * @return 
     */  
    public static String decrypt(String privateKey, String enStr){  
        try {           
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));  
            byte[] deBytes = cipher.doFinal(Base64.decodeBase64(enStr));  
            return new String(deBytes);  
        } catch (InvalidKeyException e) {  
            e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
            e.printStackTrace();  
        } catch (BadPaddingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }
    
    /** 
     * 使用keystore对密文进行解密 
     * @param privateKeystore  私钥路径 
     * @param enStr            密文 
     * @return 
     */  
    public static String fileDecrypt(String privateKeystore, String enStr){  
        try {  
            FileReader fr = new FileReader(privateKeystore);  
            BufferedReader br = new BufferedReader(fr);  
            String privateKeyString="";  
            String str;  
            while((str=br.readLine())!=null){  
                privateKeyString+=str;  
            }  
            br.close();  
            fr.close();           
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKeyString));  
            byte[] deBytes = cipher.doFinal(Base64.decodeBase64(enStr));  
            return new String(deBytes);  
        } catch (InvalidKeyException e) {  
            e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
            e.printStackTrace();  
        } catch (BadPaddingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }
    
    public static void main(String[] args) {
        Map<String, String> keyMap = generateKeyPair();

    	String publicKey = keyMap.get("publicKey");
    	String privateKey = keyMap.get("privateKey");
    	System.err.println("公钥加密——私钥解密");
        String source = "高可用架构对于互联网服务基本是标配。";
        System.out.println("\r加密前文字：\r\n" + source);
        String aData = RSA.encrypt(publicKey, source);
        System.out.println("加密后文字：\r\n" + aData);
        String dData = RSA.decrypt(privateKey, aData);
        System.out.println("解密后文字: \r\n" + dData);
    }
}
