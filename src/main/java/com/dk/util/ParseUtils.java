package com.dk.util;

import java.math.BigInteger;

public class ParseUtils {
	/**
	 * 截取byte数组
	 * @param src
	 * @param begin
	 * @param count
	 * @return
	 * 小端，有符号
	 */
	public static byte[] subBytes(byte[] src, int begin, int count) {
		byte[] bs = new byte[count];
		System.arraycopy(src, begin, bs, 0, count);
		return bs;
	}

	/**
	 * 字节数组转int
	 * @param b
	 * @return
	 */
	public static int byteArrayToInt(byte[] b) {
		int intValue = 0;
		for (int i = 0; i < b.length; i++) {
			intValue += (b[i] & 0xFF) << (8 * (3 - i));
		}
		return intValue;
	}

	/**
	 * 字节数组转不同进制字符串
	 * @param bytes
	 * @param radix
	 * @return
	 */
	public static String binary(byte[] bytes, int radix){
		return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
	}

	/**
	 * 把16进制字符串转换成字节数组
	 * @param hex
	 * @return byte[]
	 */
	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	/**
	 * 数组转换成十六进制字符串
	 * @param bArray
	 * @return HexString
	 */
	public static String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	private static int toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}


}
