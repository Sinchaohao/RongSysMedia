/**  
 * @Title:  ee.java   
 * @Package com.shuofang.util   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Shuofang     
 * @date:   2019年1月3日 下午3:56:56   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.socket.utils;

/**
 * @author Shuofang
 *	TODO
 */

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.*;

public class ConvertBlobTypeHandler extends BaseTypeHandler<String> {    
    //###指定字符集    
    private static final String DEFAULT_CHARSET = "ISO-8859-1";    
    
    @Override    
    public void setNonNullParameter(PreparedStatement ps, int i,    
            String parameter, JdbcType jdbcType) throws SQLException {    
        ByteArrayInputStream bis;    
        try {    
            //###把String转化成byte流    
            bis = new ByteArrayInputStream(parameter.getBytes(DEFAULT_CHARSET));    
        } catch (UnsupportedEncodingException e) {    
            throw new RuntimeException("Blob Encoding Error!");    
        }       
        ps.setBinaryStream(i, bis, parameter.length());    
    }    
    
    @Override    
    public String getNullableResult(ResultSet rs, String columnName)    
            throws SQLException {    
        Blob blob = rs.getBlob(columnName);    
        byte[] returnValue = null;    
        if (null != blob) {    
            returnValue = blob.getBytes(1, (int) blob.length());    
        }    
        try {    
            //###把byte转化成string    
            return new String(returnValue, DEFAULT_CHARSET);    
        } catch (UnsupportedEncodingException e) {    
            throw new RuntimeException("Blob Encoding Error!");    
        }    
    }    
    
    @Override    
    public String getNullableResult(CallableStatement cs, int columnIndex)    
            throws SQLException {    
        Blob blob = cs.getBlob(columnIndex);    
        byte[] returnValue = null;    
        if (null != blob) {    
            returnValue = blob.getBytes(1, (int) blob.length());    
        }    
        try {    
            return new String(returnValue, DEFAULT_CHARSET);    
        } catch (UnsupportedEncodingException e) {    
            throw new RuntimeException("Blob Encoding Error!");    
        }    
    }  
  
    @Override  
    public String getNullableResult(ResultSet arg0, int arg1)  
            throws SQLException {  
        // TODO Auto-generated method stub  
        return null;  
    }    
}   
