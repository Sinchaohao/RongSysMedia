package com.sixin.broad.utils;

import com.sixin.common.support.Convert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;

/**
 * @author 张超 teavamc
 * @Description:TODO
 * @ClassName Convert
 * @date 2019/2/16 16:13
 **/
public class bConvert {
    public static void main(String[] args) throws UnsupportedEncodingException {
//		String hex = Integer.toHexString(514);
//		byte[] l =Convert.hexStringToBytes(hex);
////		byte a=Byte.parseByte(hex,16);
//		byte b = (byte)514;
        String l = Integer.toHexString(519);
        byte[] lb = bConvert.hexStringToBytes(l);
        String aa = "862105022977619";
        String dd =Long.toHexString(new Long(aa));
        byte[] db = bConvert.hexStringToBytes(dd);
        String ds = bConvert.bytesToHexString(db).substring(1);
        Long x = Long.parseLong(ds,16);
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
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
    public static byte[] intToByteArray(int i) {
        byte[] result;
        if(i > 255) {
            result = new byte[4];
            result[3] = (byte) ((i >> 24) & 0xFF);
            //必须把我们要的值弄到最低位去，有人说不移位这样做也可以， result[0] = (byte)(i  & 0xFF000000);
            //，这样虽然把第一个字节取出来了，但是若直接转换为byte类型，会超出byte的界限，出现error。再提下数//之间转换的原则（不管两种类型的字节大小是否一样，原则是不改变值，内存内容可能会变，比如int转为//float肯定会变）所以此时的int转为byte会越界，只有int的前三个字节都为0的时候转byte才不会越界。虽//然 result[0] = (byte)(i  & 0xFF000000); 这样不行，但是我们可以这样 result[0] = (byte)((i  & //0xFF000000) >>24);
            result[2] = (byte) ((i >> 16) & 0xFF);
            result[1] = (byte) ((i >> 8) & 0xFF);
            result[0] = (byte) (i & 0xFF);
            return result;
        }else{
            result= new byte[2];
            result[1] = (byte) ((i >> 8) & 0xFF);
            result[0] = (byte) (i & 0xFF);//低字节在前
            return result;
        }
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
     *
     * @param src
     * @param begin
     * @param count
     * @return
     * TODO 在字节数组中截取指定长度数组
     * 时间：2019年7月14日
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }


//	/**
//	 * 生成16进制累加和校验码
//	 *
//	 * @param data 除去校验位的数据
//	 * @return
//	 */
//	public static String makeChecksum(String data) {
//	    if (StringUtils.isNullOrEmpty(data)) {
//	        return "";
//	    }
//	    int total = 0;
//	    int len = data.length();
//	    int num = 0;
//	    while (num < len) {
//	        String s = data.substring(num, num + 2);
//	        System.out.println(s);
//	        total += Integer.parseInt(s, 16);
//	        num = num + 2;
//	    }
//	    /**
//	     * 用256求余最大是255，即16进制的FF
//	     */
//	    int mod = total % 256;
//	    String hex = Integer.toHexString(mod);
//	    len = hex.length();
//	    //如果不够校验位的长度，补0,这里用的是两位校验
//	    if (len < 2) {
//	        hex = "0" + hex;
//	    }
//	    return hex;
//	}
}

