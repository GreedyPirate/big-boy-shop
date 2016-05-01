package cn.sina.core.common.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class MD5EncryptImp implements MD5Encrypt{

	@Override
	public String encrypt(String pwd) {
		
		try {
			MessageDigest instance = MessageDigest.getInstance("MD5");
			
			//加密
			byte[] digest = instance.digest(pwd.getBytes());
			//十六进制加密
			char[] encodeHex = Hex.encodeHex(digest);
			return new String(encodeHex);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("md5加密失败");
		}
		
	}

}
