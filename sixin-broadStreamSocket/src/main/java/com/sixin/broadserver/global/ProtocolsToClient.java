/**  
 * @Title:  ProtocolsToClient.java   
 * @Package com.shuofang.server   
 * @Description:    TODO(终端服务器交互指令解析与打包)   
 * @author: Shuofang     
 * @date:   2019年1月18日 下午2:35:55   
 * @version V1.0 
 * @Copyright: 2019 
 */
package com.sixin.broadserver.global;


/**
 * @author Shuofang
 *	TODO 
 */
public class ProtocolsToClient {
	public static final String PACKETHEAD = "aa";//包头
	public static final String VOL = "03";//终端设置音量
	public static final String REGISTER = "01";//终端注册登录
	public static final String STATU = "02";//终端状态
	public static final String LIST = "05";//终端节目list表发放
	public static final String PHONELIST = "06";//允许终端维护电话发放
	public static final String MUTUALTIME = "07";//终端交互时间设置
	public static final String E_FM_RECIVE = "0a";//设置应急FM调频接收RDS码/接收频率
	public static final String CLIENTREAD = "0b";//获取终端RDS码/发射频率/发射功率/反射功率/工作电压（对系统只读）
	public static final String IPCHANGE = "0c";//获取终端IP（对系统可读可写）并为流媒体心跳包
	public static final String CELLPOS = "0d";//基站信息（LAC,CID）
	public static final String OPENSTREAM = "0e";//启动流媒体传输
	public static final String CLOSESTREAM = "0f";//停止流媒体传输
	public static final String UPDATELED = "10";//LED字幕更新（终端主动请求获取）
	public static final String GETPARAMATER = "11";//物联网终端参数透传接口（终端主动请求获取，端口:8900）
	public static final String GETCLIENTDATA = "12";//物联网终端参数透传接口 终端回传参数
	public static final String POSITION = "13";//终端经纬度信息
	public static final String REQUEST = "14";//紧急接收的频率
	public static final String PROGINFO = "15";//统计节目的下载播出，短信播出，重启的次数
	public static final String RESET = "1c";//终端重启
	public static final String BROADCAST = "16";//紧急界面播出回执（新建连接）
	public static final String COMMAND = "1";//命令正确
	public static final String READLENGTH = "0a00";///获取终端RDS码/发射频率/发射功率/反射功率/工作电压（对系统只读）命令长度
	public static final String CHECKCODE = "1e";//固定校验码END CHECK
	public static final String ENDCHECK = "cc";//包尾
	public static final int SESSIONUSER = 0;//终端为客机
	public static final int SESSIONCLIENT = 1;//服务器为主机
	public static final String[] TORRENTDATA = {
			"电池组电压",
			"电池1电压",
			"18V1输出电压",
			"18V2输出电压",
			"24V输出电压",
			"28V输出电压",
			"外部电源电压",
			"太阳能电压",
			"充电状态",
			"工作状态"
	};
}
