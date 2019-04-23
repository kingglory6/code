package com.newer.mall.common.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 
 * @author LiuBin
 *
 */
public class Password {

	/**
	 * 生成MD5(消息摘要算法 5)
	 * 
	 * @param text 任意长度
	 * @return 128 bit 数据(长度为 32 的十六进制字符串)
	 */
	public static String toMd5(String text) {
		String md5 = null;

		try {
			byte[] hash = MessageDigest.getInstance("MD5").digest(text.getBytes());
			System.out.println(Arrays.toString(hash));
			// 1 参数全部转换为正数
			md5 = new BigInteger(1, hash).toString(16);
			// String er = new BigInteger(1,hash).toString(2);
			// System.out.println(er);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return md5;
	}

	public static String toSHA2(String password) {
		String sha256 = null;

		try {
			byte[] hash = MessageDigest.getInstance("SHA-256").digest(password.getBytes());
			//System.out.println(Arrays.toString(hash));
			// 1 参数全部转换为正数
			sha256 = new BigInteger(1, hash).toString(16);
			// String er = new BigInteger(1,hash).toString(2);
			// System.out.println(er);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sha256;
	}

	public static String toSHA2(File file) {
		String sha2 = null;
		try (BufferedInputStream bs = new BufferedInputStream(new FileInputStream(file),1024*32)) {
			byte[] buf = new byte[1024*8];
			ByteArrayOutputStream data = new ByteArrayOutputStream();
			int size;
			while((size=bs.read(buf))!=-1) {
				//脏数据
				data.write(buf, 0, size);
			}
			byte[] src = data.toByteArray();
			bs.close();
			data.close();
			byte[] hash = MessageDigest.getInstance("SHA-256").digest(src);
			sha2 = new BigInteger(1, hash).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sha2;
	}


}
