package cn.blmdz.security;

import java.io.IOException;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DesUtils {
	private static Key key;
	private static String KEY_STR = "my_key_str";
	private static String UTF_8 = "UTF8";
	private static String DES = "DES";
	
	static {
		try {
			KeyGenerator generator = KeyGenerator.getInstance(DES);
			generator.init(new SecureRandom(KEY_STR.getBytes()));
			key = generator.generateKey();
//			generator = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 对字符串进行DES加密, 返回BASE64编码的加密字符串
	public static String getEncryptString(String str) {
		try {
			byte[] strBytes = str.getBytes(UTF_8);
			Cipher cipher = Cipher.getInstance(DES);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(strBytes);
			return new BASE64Encoder().encode(encryptStrBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//对BASE64编码的加密字符串进行解密, 返回解密后的字符串
	public static String getDecryptString(String str) {
		try {
			byte[] strBytes = new BASE64Decoder().decodeBuffer(str);
			Cipher cipher = Cipher.getInstance(DES);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] decryptStrBytes = cipher.doFinal(strBytes);
			return new String(decryptStrBytes, UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(new BASE64Encoder().encode(new BASE64Decoder().decodeBuffer("str")));
		
		System.out.println(getEncryptString("str"));
		System.out.println(getDecryptString("Cwk7ObSxpyQ="));
	}
}
