package com.sixin.socket.nettyserver;

import com.sixin.socket.utils.SpringContextUtils;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.service.impl.TorrentServiceImpl;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 存储整个工程的全局配置
 * @author liuyazhuang
 *
 */
public class ChannelManage {
	private static final Logger logger = LoggerFactory.getLogger(ChannelManage.class);
	private static TorrentServiceImpl tbIoterminalService = (TorrentServiceImpl) SpringContextUtils.getBeanByClass(TorrentServiceImpl.class);
	/**
	 *  不需要接收数据的集合, MIME-1
	 */
	public static ConcurrentHashMap<String , Integer> dontIn = new ConcurrentHashMap<>();
	//初始化不需要接收数据的集合
	static {
		List<Torrent> tbIoterminals = tbIoterminalService.selectTorrentList(new Torrent());
		for (Torrent tb : tbIoterminals){
			if("0".equals(tb.getStatus())){
				dontIn.put(tb.getImei(),1);
			}
		}
	}
	/**
	 * 存储每一个客户端接入进来时的channel对象
	 */
	public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	/**保存终端连接信息  MIME：channel**/
	public static ConcurrentHashMap<String,Channel> dev1Mime = new ConcurrentHashMap<>();
	/**保存终端连接信息  channelId：MIME**/
	public static ConcurrentHashMap<String,String> dev2id = new ConcurrentHashMap<>();
	/**读锁**/
    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);


	/**
	 * 根据终端MIME开始接收数据
	 * @param MIME
	 * @return
	 */
	public static boolean startInDataChannelByMIME(String MIME){
		try {
			//移除禁止
			dontIn.remove(MIME);
			logger.info("启动终端："+MIME+"接收数据成功!!!");
			return true;
		}catch (Exception e){
			return false;
		}
//		Channel channel = dev1Mime.get(MIME);
//		if(channel == null){
//			return false;
//		}else {
//			dontIn.remove(MIME);
//			System.err.println("启动成功+"+dontIn.get(MIME));
//			return true;
//		}
	}
	/**
	 * 根据终端MIME停止接收数据
	 * @param MIME
	 * @return
	 */
    public static boolean stopInDataChannelByMIME(String MIME){
		try {
			dontIn.put(MIME,1);
			logger.info("停止终端："+MIME+"接收数据成功!!!");
			return true;
		}catch (Exception e){
			return false;
		}
//		Channel channel = dev1Mime.get(MIME);
//		if(channel == null){
//			return false;
//		}else {
//			dontIn.put(MIME,1);
//			System.err.println("停止成功+"+dontIn.get(MIME));
//			return true;
//		}
	}

	/**
	 * 保存相关信息
	 * @param ctx
	 * @param imei
	 */
	public static void addCache(ChannelHandlerContext ctx , String imei){
		//保存通道
		dev1Mime.put(imei,ctx.channel());
		dev2id.put(ctx.channel().id().toString(),imei);
	}
	/**
	 * 移除相关信息
	 * @param ctx
	 * @param imei
	 */
	public static void delCache(ChannelHandlerContext ctx,String imei){
		//如果通过MIME能够获取到channel，并且是对应的channel 则直接删除
		dev1Mime.remove(imei);
		dontIn.remove(imei);
	}
}
