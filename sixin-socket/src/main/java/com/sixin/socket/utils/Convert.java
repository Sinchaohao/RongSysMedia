package com.sixin.socket.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;

public class Convert {
	public static void main(String[] args) {
		//		String hex = Integer.toHexString(514);
		//		byte[] l =Convert.hexStringToBytes(hex);
		////		byte a=Byte.parseByte(hex,16);
		//		byte b = (byte)514;
		//		String l = Integer.toHexString(519);
		//		byte[] lb = Convert.hexStringToBytes(l);
		//		int a = (int)0x0a;
		@SuppressWarnings("unused")
		String[] ips = "224.0.1.15".split("\\.");
//		System.out.println((byte)Integer.parseInt("13"));
//		System.out.println(Convert.hexStringToBytes("0d")[0]);
	}
	/* Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。  
	 * @param src byte[] data  
	 * @return hex string  
	 */     
	public static String bytesToHexString(byte[] src){  
		StringBuilder stringBuilder = new StringBuilder("");  
		if (src == null || src.length <= 0) {  
			return null;  
		}  
		for (int i = 0; i < src.length; i++) {  
			int v = src[i] & 0xFF;  
			String hv = Integer.toHexString(v);  
			if (hv.length() < 2) {  
				stringBuilder.append(0);  
			}  
			stringBuilder.append(hv);  
		}  
		return stringBuilder.toString();  
	}  
	public static String byteToHexString(byte src){  
		StringBuilder stringBuilder = new StringBuilder("");  
		int v = src & 0xFF;  
		String hv = Integer.toHexString(v);  
		if (hv.length() < 2) {  
			stringBuilder.append(0);  
		}  
		stringBuilder.append(hv);  
		return stringBuilder.toString();  
	}  
	/** 
	 * Convert hex string to byte[] 
	 * @param hexString the hex string 
	 * @return byte[] 
	 */  
	public static byte[] hexStringToBytes(String hexString) {  
		if (hexString == null || hexString.equals("")) {  
			return null;  
		}  
		if(hexString.length()%2>0){
			hexString="0"+hexString;
		}
		hexString = hexString.toUpperCase();  
		int length = hexString.length() / 2;  
		char[] hexChars = hexString.toCharArray();  
		byte[] d = new byte[length];  
		for (int i = 0; i < length; i++) {  
			int pos = i * 2;  
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
		}  
		return d;  
	}  

	/** 
	 * Convert char to byte 
	 * @param c char 
	 * @return byte 
	 */  
	public static byte charToByte(char c) {  
		return (byte) "0123456789ABCDEF".indexOf(c);  
	}  
	public static byte[] intToByteArray(int i) {   
		byte[] result = new byte[4];   
		result[0] = (byte)((i >> 24) & 0xFF);
		//必须把我们要的值弄到最低位去，有人说不移位这样做也可以， result[0] = (byte)(i  & 0xFF000000);
		//，这样虽然把第一个字节取出来了，但是若直接转换为byte类型，会超出byte的界限，出现error。再提下数//之间转换的原则（不管两种类型的字节大小是否一样，原则是不改变值，内存内容可能会变，比如int转为//float肯定会变）所以此时的int转为byte会越界，只有int的前三个字节都为0的时候转byte才不会越界。虽//然 result[0] = (byte)(i  & 0xFF000000); 这样不行，但是我们可以这样 result[0] = (byte)((i  & //0xFF000000) >>24);
		result[1] = (byte)((i >> 16) & 0xFF);
		result[2] = (byte)((i >> 8) & 0xFF); 
		result[3] = (byte)(i & 0xFF);
		return result;
	}
	public static int byteToInt(byte b) {  
		//Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值  
		return b & 0xFF;  
	}
	public static byte[] unsignedInteger4ToByte(int data) 
	{ 
		long value = data&0x0FFFFFFFF;//将int数据转换为0~4294967295 (0xFFFFFFFF即DWORD)。
		String hexString = Long.toHexString(value); 
		while(hexString.length()<2) //补齐8个16进制字符代表4个字节。 
		{ 
			hexString = "0" + hexString; 
		} 
		byte[] bytes = hexStringToBytes(hexString); 
		return bytes; 
	}
	/**
	 * 计算字节数组的校验和
	 * @param buf
	 * @return
	 */
	public static String checksum(byte[] buf) {
		try {
			CheckedInputStream cis = new CheckedInputStream(
					new ByteArrayInputStream(buf),new Adler32());
			byte[] tempBuf = new byte[128];
			while (cis.read(tempBuf) >= 0);   
			long value = cis.getChecksum().getValue();
			return Long.toHexString(value);
		} catch (IOException e) {
			return "-1";
		}
	}

	/**
	 * 字符串转换unicode
	 */
	public static String string2Unicode(String string) {
	StringBuffer unicode = new StringBuffer();
	for (int i = 0; i < string.length(); i++) {
	// 取出每一个字符
	    char c = string.charAt(i);
	    // 转换为unicode
	    unicode.append("\\u" + Integer.toHexString(c));
	}
	return unicode.toString();
	}   
	/**
	 * 字符串转化成为16进制字符串
	 * @param s
	 * @return
	 */
	public static String strTo16(String s) {
	    String str = "";
	    for (int i = 0; i < s.length(); i++) {
	        int ch = (int) s.charAt(i);
	        String s4 = Integer.toHexString(ch);
	        str = str + s4;
	    }
	    return str;
	}
	/**
	 * 16进制转换成为string类型字符串
	 * @param s
	 * @return
	 */
	public static String hexStringToString(String s) {
	    if (s == null || s.equals("")) {
	        return null;
	    }
	    s = s.replace(" ", "");
	    byte[] baKeyword = new byte[s.length() / 2];
	    for (int i = 0; i < baKeyword.length; i++) {
	        try {
	            baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    try {
	        s = new String(baKeyword, "UTF-8");
	        new String();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
	    return s;
	}
	/**
	 * 字符串转换成为16进制(无需Unicode编码)
	 * @param str
	 * @return
	 */
	public static String str2HexStr(String str) {
	    char[] chars = "0123456789ABCDEF".toCharArray();
	    StringBuilder sb = new StringBuilder("");
	    byte[] bs = str.getBytes();
	    int bit;
	    for (int i = 0; i < bs.length; i++) {
	        bit = (bs[i] & 0x0f0) >> 4;
	        sb.append(chars[bit]);
	        bit = bs[i] & 0x0f;
	        sb.append(chars[bit]);
	        // sb.append(' ');
	    }
	    return sb.toString().trim();
	}
	/**
	 * 16进制直接转换成为字符串(无需Unicode解码)
	 * @param hexStr
	 * @return
	 */
	public static String hexStr2Str(String hexStr) {
	    String str = "0123456789ABCDEF";
	    char[] hexs = hexStr.toCharArray();
	    byte[] bytes = new byte[hexStr.length() / 2];
	    int n;
	    for (int i = 0; i < bytes.length; i++) {
	        n = str.indexOf(hexs[2 * i]) * 16;
	        n += str.indexOf(hexs[2 * i + 1]);
	        bytes[i] = (byte) (n & 0xff);
	    }
	    return new String(bytes);
	}
}
