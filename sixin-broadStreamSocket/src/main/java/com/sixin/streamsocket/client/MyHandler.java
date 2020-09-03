package com.sixin.streamsocket.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MyHandler extends ChannelInboundHandlerAdapter {

    private static Channel channel ;

    //数据库初始化
    public Connection initsql()
    {
        String Url = "jdbc:mysql://localhost:3306/iotdata?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";//参数参考MySql连接数据库常用参数及代码示例
        String name = "root";//数据库用户名
        String psd = "hn123456Aa";//数据库密码
        String jdbcName = "com.mysql.cj.jdbc.Driver";//连接MySql数据库
        Connection con=null;
        try {
            Class.forName(jdbcName);//向DriverManager注册自己
            con = DriverManager.getConnection(Url, name, psd);//与数据库建立连接
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {//执行与数据库建立连接需要抛出SQL异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }

    //读取服务端发送来的内容存入数据库
    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception
    {

          String s=(String)msg;
          String[] str=s.split(",");
          System.err.println(s);
          String sql = "insert into tb_collect(ctype,cid,vdata,uid,vunit) values('湿度','10031402','"+str[3].toString()+"','yt','H');";//数据库操作语句（插入）
          String sql1 = "insert into tb_collect(ctype,cid,vdata,uid,vunit) values('温度','10031401','"+str[1].toString()+"','yt','C');";//数据库操作语句（插入）
          String sql2 = "insert into tb_collect(ctype,cid,vdata,uid,vunit) values('光照强度','10031403','"+str[4].toString()+"','yt','lx');";//数据库操作语句（插入）
          String sql3 = "insert into tb_collect(ctype,cid,vdata,uid) values('土壤湿度','10031404','"+str[5].toString()+"','yt');";//数据库操作语句（插入）
          String sql4 = "insert into tb_collect(ctype,cid,vdata,uid,vunit) values('降雨量','10031405','"+str[6].toString()+"','yt','ml');";//数据库操作语句（插入）

          Connection con=initsql();
          Connection con1=initsql();
          Connection con2=initsql();
          Connection con3=initsql();
          Connection con4=initsql();
          PreparedStatement pst = con.prepareStatement(sql);
          PreparedStatement pst1 = con1.prepareStatement(sql1);
          PreparedStatement pst2 = con2.prepareStatement(sql2);
          PreparedStatement pst3 = con3.prepareStatement(sql3);
          PreparedStatement pst4 = con4.prepareStatement(sql4);
          pst.executeUpdate();//解释在下
          pst1.executeUpdate();
          pst2.executeUpdate();
          pst3.executeUpdate();
          pst4.executeUpdate();



    }

}

