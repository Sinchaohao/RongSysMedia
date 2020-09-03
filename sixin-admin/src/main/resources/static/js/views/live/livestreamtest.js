// 流媒体插件 RTMP推流
swfobject.embedSWF("/stream/RtmpStreamer.swf",
    "rtmp-streamer",
    "270", "190", "9",
    "/stream/src/expressInstall.swf",
    {},{bgcolor:"#e5eaf1",wmode:"opaque"},{});
//wmode,tranparent:透明，opaque:不透明,表示将Flash置于最底层

var ws = null;
// 流媒体 id
var streamid = null;
//流媒体网页与服务器通信之间的信息载体变量
var message_1 = null;
//此变量用来统计直播终端数量
var terminalCount = null;
// imei 的列表 农大终端测试机器 IMEI 号码
var imeilist= "862105024040770,862105024020277";
var imeiNameList= "测试";
$("#test").html(imeilist);
console.log('源码指定测试终端'+imeilist);
// 流媒体状态 0:正在开启，1:已经开启直播，2:未直播或已经关闭直播，3:正在关闭
var isstreamliving=2;

// 思信RED5流媒体
// var  rtmpAddress = "rtmp://110.53.162.164:1936/live";

// 陈霞 RED5流媒体
var  rtmpAddress = "rtmp://120.27.241.221:1936/live";
var list = new Array();
var nameList = new Array();
/**
 * 在加载之前
 */
function onbeforeunload(){
    // 按钮设置
    if(isstreamliving==0||isstreamliving==1){
        endlive();
    }
}

/**
 * 直播按钮状态
 * @param isliving
 */
function setLiveButton(isliving){
    isstreamliving = isliving;
    if(isliving==1){//已经开启直播，可以关闭直播
        $("#start").attr("disabled","disabled");
        $("#end").removeAttr("disabled");
    }else if(isliving==2){//已经关闭直播，可以开启直播
        $("#end").attr("disabled","disabled");
        $("#start").removeAttr("disabled");
    }else{//正在开启或关闭，不可以操作
        $("#end").attr("disabled","disabled");
        $("#start").attr("disabled","disabled");
    }
}

/**
 * 连接 websocket
 */
function connectWS() {
    // 当前地址
    var path = window.location.pathname;
    // 当前主机
    var hostaddress = window.location.host + path.substring(0,path.substr(1).indexOf('/')+1);
    // 后台wb控制器url
    var target = "/stream";
    // 将http协议换成ws
    if (window.location.protocol == 'http:') {
        target = 'ws://' + hostaddress + target;
    } else {
        target = 'wss://' + hostaddress + target;
    }
    console.log('target'+target);
    //创建一个针对控制器的 webSocket 对象
    if ('WebSocket' in window) {
        ws = new WebSocket(target);
    } else if ('MozWebSocket' in window) {
        ws = new MozWebSocket(target);
    } else {
        $.modal.confirm("您的浏览器不支持 WebSocket！");
        return;
    }
    // 如果没有ws对象 直播状态为2 设置对应按钮
    if(ws==null){
        setLiveButton(2);
    }
    // 开启连接的回调函数
    ws.onopen = function () {
        // 滚动状态
        scrollStatus("text-info","正在开启直播...");
        //向后台发送开始直播命令
        startsent();
        console.log('Info:  WebSocket直播已打开');
    };


    ws.onmessage = function (event) {
        //终端直播状态
        if(event.data.charAt(0)=="2"){
            // 滚动状态
            scrollStatus("text-success","正在直播...");
            setLiveButton(1);
        }else{
            console.log('WS Received: ' + event.data);
            switch(event.data){
                case "start:success"://开始直播成功
                    scrollStatus("text-info","开始直播...");
                    break;
                case "end:success":
                    scrollStatus("text-warning","关闭直播...");
                    break;
                case "error:socketconnect":
                    scrollStatus("text-danger","开启失败");
                    $.modal.confirm("服务器 StreamSocket 连接失败，请联系管理员！");
                    setLiveButton(2);
                    streamerDisconnect();
                    closeWS();
                    break;
                case "1:success":
                    scrollStatus("text-info","开始直播...");
                    break;
                case "0:success":
                    setLiveButton(2);
                    closeWS();
                    scrollStatus("text-danger","直播结束");
                    break;
            }
        }

    };
    // 关闭连接的回调函数
    ws.onclose = function (event) {
        if(event.code==1006){
            $.modal.confirm("服务器 WebSocket 连接失败！");
            setLiveButton(2);
            streamerDisconnect();
            scrollStatus("text-danger","直播结束");
        }
        console.log('Info: WebSocket 连接已关闭, Code: ' + event.code + (event.reason == "" ? "" : ", Reason: " + event.reason));
    };
}

/**
 * 开始直播
 * @param obj
 * @returns {boolean}
 */
function startlive(obj){
    list[0] = imeilist;
    nameList[0] = imeiNameList;
    for(var i = 0; i < imea.length; i++) {
        list[i+1] = imea[i];
        nameList[i+1] = imeiName[i];
    }
    // 如果未选择则 提示要选择
    if(imeilist==null || imeilist==""){
        $.modal.confirm("无测试终端，请查看源码调试 ----》 方法startlive(obj)");
        return false;
    }else{
        // 设置 streamid 为当前时间
        streamid = getCurTime();
        setLiveButton(0);
        // 推流
        publishRtmp();
    }

}


/**
 * 向后台发送开始直播命令
 */
function startsent(){
    if (ws != null) {
        // 拼接 nessage
        //var message = "start:"+streamid+":"+imeilist;
        //var message = aa();
        // 控制台打印
        console.log('Sent Start ');
        // 推送信息
        console.log("message_1:",message_1);
        ws.send(message_1);

    } else {
        $.modal.confirm("WebSocket 连接建立失败，请重新连接");
        setLiveButton(2);
    }
    if(streamid!=null)
        addlog("open",streamid,list, nameList);
}


// /**
//  * 结束直播命令
//  */
// function endlive(){
//     setLiveButton(3);
//     scrollStatus("text-info","正在关闭直播...");
//     addlog("close",streamid,list,nameList);
//     if(isOpen) streamerDisconnect();
//     if (ws != null) {
//         var message = "end:"+streamid;
//         console.log('Sent End');
//         ws.send(message);
//     } else {
//         setLiveButton(2);
//     }
// }


/**
 * 获得当前时间
 * @returns {string}
 */
function getCurTime(){
    var myDate = new Date();
    var hour = myDate.getHours();       //获取当前小时数(0-23)
    var minu = myDate.getMinutes();     //获取当前分钟数(0-59)
    var sec = myDate.getSeconds();     //获取当前秒数(0-59)
    if(hour<10) hour = "0"+hour;
    if(minu<10) minu = "0"+minu;
    if(sec<10) sec = "0"+sec;
    return hour+""+minu+""+sec;
}

/**
 * 关闭webSocket连接
 */
function closeWS(){
    if (ws != null) {
        ws.close();
        ws = null;
    }
}

/**
 * 增加直播日志
 * @param type
 * @param streamid
 * @param imeilist
 */
function addlog(type,streamid,imeilist, imeiNameList){
    var data ;
    if(type=="open"){
        data= {streamid:streamid,type:type,imeilist:imeilist, imeiNameList:imeiNameList};
        console.log("addlog(): list: "+imeilist);
        for(var i = 0; i < imeilist.length; i++)
            if(imeilist[i] != '' && imeilist[i] != null)
                $("#tbody").append("<tr><td class='center'>"+data.imeilist[i]+"</td><td class='center'>"+data.imeiNameList[i]+"</td> " +
                    "<td class='center'>"+data.streamid+"</td><td class='center'>"+data.type+"</td></tr>");
    }else{
        data= {streamid:streamid,type:type,imeilist:imeilist, imeiNameList:imeiNameList};
        for(var i = 0; i < imeilist.length; i++)
            if(imeilist[i] != '' && imeilist[i] != null)
                $("#tbody").append("<tr><td class='center'>"+data.imeilist[i]+"</td><td class='center'>"+data.imeiNameList[i]+"</td> " +
                    "<td class='center'>"+data.streamid+"</td><td class='center'>"+data.type+"</td></tr>");
    }
}


/**
 * 字符滚动效果
 */
var Mar = null;
var picH = 21;//移动高度
var scrollstep = 3;//移动步幅,越大越快
var scrolltime = 30;//移动频度(毫秒)越大越慢
var tmpH = 0;
$(document).ready(function(){
    Mar = document.getElementById("livestatus");
    picH =Mar.offsetHeight;

});
//字符向上滚动
function scrollStatus(textclass,text) {
    $(Mar).append("<p class='"+textclass+"'>"+text+"</p>")
    Marqueeh();
}

function Marqueeh() {
    if (tmpH < picH) {
        tmpH += scrollstep;
        if (tmpH > picH)
            tmpH = picH;
        Mar.scrollTop = tmpH;
        setTimeout(Marqueeh, scrolltime);
    } else {
        tmpH = 0;
        var child_div=Mar.getElementsByTagName("p");
        Mar.removeChild(child_div[0]);
        Mar.scrollTop = 0;
    }
}


/**
 * 使用flash 方法二，AS3与JS交互通信(使用swfObject插入Flash)
 * swfobject.js使用方法
 * http://www.cnblogs.com/snowinmay/p/3373892.html
 */

// 推流准备 与 开启
var isReady = false;
var isOpen = false;

// Global method for ActionScript
/**
 * 设置推流器就绪
 */
function setSWFIsReady() {
    if (!isReady) {
        console.log('推流器已就绪');
        isReady = true;
        thisMovie("rtmp-streamer").setShowText("录音未开始");
    }
}

/**
 * 设置麦克风开启
 */
function mrophoneIsOpen() {
    if (!isOpen) {
        console.log('麦克风打开');
        isOpen = true;
    }
    //麦克风打开之后向后台发送直播请求
    connectWS();//打开ws连接
}

/**
 * 推流错误
 * @param infocode
 */
function streamError(infocode){
    if (infocode != "NetConnection.Connect.Closed") {
        var msg="连接流媒体服务器出错！\n"+infocode;
        $.modal.confirm(msg);
    }
}

/**
 * 推流 推送流媒体
 */
function publishRtmp() {
    setMicQuality(10);
    thisMovie("rtmp-streamer").publish(rtmpAddress, streamid,"正在使用麦克风...");
    setLiveButton(1);
}

/**
 * 推流结束
 */
function streamerDisconnect() {
    thisMovie("rtmp-streamer").disconnect("麦克风使用结束。");
    isOpen = false;
}

/**
 * 设置麦克风参数
 * @param quality
 */
function setMicQuality(quality) {
    thisMovie("rtmp-streamer").setMicQuality(quality);
}
function setMicRate (rate) {
    thisMovie("rtmp-streamer").setMicRate(rate);
}

//搭建js与flash互通的环境
function thisMovie(movieName) {
    if (navigator.appName.indexOf("Microsoft") != -1) {
        return window[movieName];
    } else {
        return document[movieName];
    }
}