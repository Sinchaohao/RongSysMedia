// 流媒体插件 RTMP推流
swfobject.embedSWF("/stream/RtmpStreamer.swf",
    "rtmp-streamer",
    "270", "190", "9",
    "/stream/src/expressInstall.swf",
    {}, {bgcolor: "#e5eaf1", wmode: "opaque"}, {});
//wmode,tranparent:透明，opaque:不透明,表示将Flash置于最底层

//ws 对象
var ws = null;
// 流媒体 id
var streamid = null;
// imei 的列表
var imeilist = null;
// 流媒体状态 0:正在开启，1:已经开启直播，2:未直播或已经关闭直播，3:正在关闭
var isstreamliving = 2;

/**
 * 在加载之前
 */
function onbeforeunload() {
    if (isstreamliving == 0 || isstreamliving == 1) {
        endlive();
    }
}

/**
 * 直播按钮状态
 * @param isliving
 */
function setLiveButton(isliving) {
    isstreamliving = isliving;
    if (isliving == 1) {//已经开启直播，可以关闭直播
        $("#start").attr("disabled", "disabled");
        $("#end").removeAttr("disabled");
        $("#selectter").attr("disabled", "disabled");
    } else if (isliving == 2) {//已经关闭直播，可以开启直播
        $("#end").attr("disabled", "disabled");
        $("#start").removeAttr("disabled");
        $("#selectter").removeAttr("disabled");
    } else {//正在开启或关闭，不可以操作
        $("#end").attr("disabled", "disabled");
        $("#start").attr("disabled", "disabled");
        $("#selectter").attr("disabled", "disabled");
    }
}

/**
 * 连接 websocket
 */
function connectWS() {
    // 当前地址
    var path = window.location.pathname;
    // 当前主机
    var hostaddress = window.location.host + path.substring(0, path.substr(1).indexOf('/') + 1);
    // 后台控制器url
    var target = "/stream";
    // 将http协议换成ws
    if (window.location.protocol == 'http:') {
        target = 'ws://' + hostaddress + target;
    } else {
        target = 'wss://' + hostaddress + target;
    }
    //创建一个针对控制器的 webSocket 对象
    if ('WebSocket' in window) {
        ws = new WebSocket(target);
    } else if ('MozWebSocket' in window) {
        ws = new MozWebSocket(target);
    } else {
        Dialog.alert('您的浏览器不支持 WebSocket！');
        return;
    }
    // 如果没有ws对象 直播状态为2 设置对应按钮
    if (ws == null) {
        setLiveButton(2);
    }
    // 开启直播，打印提醒
    ws.onopen = function () {
        scrollStatus("text-info", "正在开启直播...");
        startsent();//向后台发送开始直播命令
        console.log('Info: WebSocket connection opened.\n 中文： WebSocket直播打开');
    };


    ws.onmessage = function (event) {
        //终端直播状态
        if (event.data.charAt(0) == "2") {
            scrollStatus("text-success", "正在直播...");
            setLiveButton(1);
            //终端状态刷新
            refreshTerStatus(event.data.substring(2, event.data.length));
        } else {
            console.log('WS Received: ' + event.data);
            switch (event.data) {
                case "start:success"://开始直播成功
                    scrollStatus("text-info", "开始直播...");
                    break;
                case "end:success":
                    scrollStatus("text-warning", "关闭直播...");
                    break;
                case "error:socketconnect":
                    scrollStatus("text-danger", "开启失败");
                    Dialog.alert('服务器 StreamSocket 连接失败，请联系管理员！');
                    setLiveButton(2);
                    streamerDisconnect();
                    closeWS();
                    setShowCloseStatus();
                    break;
                case "1:success":
                    scrollStatus("text-info", "开始直播...");
                    break;
                case "0:success":
                    setLiveButton(2);
                    closeWS();
                    setShowCloseStatus();
                    scrollStatus("text-danger", "直播结束");
                    break;
            }
        }

    };
    // 直播关闭
    ws.onclose = function (event) {
        if (event.code == 1006) {
            Dialog.alert('服务器 WebSocket 连接失败！');
            setLiveButton(2);
            streamerDisconnect();
//             		setShowCloseStatus();
            scrollStatus("text-danger", "直播结束");
        }
        console.log('Info: WebSocket connection closed, Code: ' + event.code + (event.reason == "" ? "" : ", Reason: " + event.reason));
    };
}

/**
 * 开始直播
 * @param obj
 * @returns {boolean}
 */
function startlive(obj) {
    if (imeilist == null || imeilist == "") {
        $(obj).tips({
            side: 3,
            msg: '请选择直播终端',
            bg: '#AE81FF',
            time: 2
        });
        return false;
    } else {
        streamid = getCurTime();
        setLiveButton(0);
        publishRtmp();
    }
}

/**
 * 向后台发送开始直播命令
 */
function startsent() {
    if (ws != null) {
        var message = "start:" + streamid + ":" + imeilist;
        console.log('Sent Start ');
        ws.send(message);
    } else {
        Dialog.alert('WebSocket 连接建立失败，请重新连接');
        setLiveButton(2);
    }
    if (streamid != null)
        addlog("open", streamid);
}


/**
 * 结束直播命令
 */
function endlive() {
    setLiveButton(3);
    scrollStatus("text-info", "正在关闭直播...");
    addlog("close", streamid);
    if (isOpen) streamerDisconnect();
    if (ws != null) {
        var message = "end:" + streamid;
        console.log('Sent End');
        ws.send(message);
    } else {
        setLiveButton(2);
    }
}

/**
 * 选择直播终端
 * @param obj
 */
function selectter(obj) {
    top.jzts();
    var diag = new top.Dialog();
    diag.Drag = true;
    diag.Title = "选择直播终端";
    if (imeilist == null)
        diag.URL = "<%=basePath%>stream/setStreamTer.do";
    else
        diag.URL = "<%=basePath%>stream/setStreamTer.do?selecttid=" + imeilist;
    diag.Width = 300;
    diag.Height = 450;
    diag.CancelEvent = function () {
        if (diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none') {
            scrollStatus("text-primary", "未直播");
// 			 		if(imeilist!=null){
            $("#tbody").empty();
// 			 		}
            imeilist = diag.innerFrame.contentWindow.document.getElementById('selectter').value;
            $.post("stream/getTerByTid.do", {tids: imeilist}, function (data) {
                if (data.terlistarr != null) {
                    var terjson = eval(data.terlistarr);
                    $("#liveternum").text(terjson.length);
                    $.each(terjson, function (i, item) {
                        $("#tbody").append("<tr><td class='center'>" + item.tid + "</td> <td class='center'>" + item.tname + "</td>" +
                            "<td class='center'>" + item.aname + "</td> <td class='center'>" + item.uname + "</td>" +
                            "<td class='center blue'>未直播</td>" +
                            "</tr>");
                    });
                }
            });
        }
        diag.close();
    };
    diag.show();
}

/**
 * 获得当前时间
 * @returns {string}
 */
function getCurTime() {
    var myDate = new Date();
    var hour = myDate.getHours();       //获取当前小时数(0-23)
    var minu = myDate.getMinutes();     //获取当前分钟数(0-59)
    var sec = myDate.getSeconds();     //获取当前秒数(0-59)
    if (hour < 10) hour = "0" + hour;
    if (minu < 10) minu = "0" + minu;
    if (sec < 10) sec = "0" + sec;
    return hour + "" + minu + "" + sec;
}


/**
 * // 刷新终端状态
 * @param tidlist
 */
function refreshTerStatus(tidlist) {
// 		var tidlength = tidlist.split(",");
    var startternum = 0;
    var trs = $("#tbody").find("tr");
    for (var i = 0; i < trs.length; i++) {
        var tid = trs[i].children[0].innerText;
        var terstatus = trs[i].children[4];
        if (tidlist.indexOf(tid) != -1) {
            terstatus.innerText = "正在直播...";
            $(terstatus).attr("class", "center green");
            startternum++;
        } else if (terstatus.innerText == "正在直播...") {
            terstatus.innerText = "直播结束";
            $(terstatus).attr("class", "center red");
        }
    }
    $("#startternum").text(startternum);
}

/**
 * 关闭webSocket连接
 */
function closeWS() {
    if (ws != null) {
        ws.close();
        ws = null;
    }
}

function setShowCloseStatus() {
    streamid = null;
    imeilist = null;
    $("#startternum").text(0);
    var trs = $("#tbody").find("tr");
    for (var i = 0; i < trs.length; i++) {
        var terstatus = trs[i].children[4].innerText;
        if (terstatus == "正在直播...") {
            var terstatus = trs[i].children[4];
            terstatus.innerText = "直播结束";
            $(terstatus).attr("class", "center red");
        }
    }
}

/**
 * 增加直播日志
 * @param type
 * @param streamid
 */
function addlog(type, streamid) {
    var data;
    if (type == "open") {
        data = {streamid: streamid, type: type, imeilist: imeilist};
    } else {
        data = {streamid: streamid, type: type};
    }
    $.post("stream/addLog.do", data, function (data) {
        if (data.terlistarr != null) {
            var terjson = eval(data.terlistarr);
            $("#liveternum").text(terjson.length);
            $.each(terjson, function (i, item) {
                $("#tbody").append("<tr><td class='center'>" + item.tid + "</td> <td class='center'>" + item.tname + "</td>" +
                    "<td class='center'>" + item.aname + "</td> <td class='center'>" + item.uname + "</td>" +
                    "<td class='center blue'>未直播</td>" +
                    "</tr>");
            });
        }
    });
}


/**
 * 字符滚动效果
 */
var Mar = null;
var picH = 21;//移动高度
var scrollstep = 3;//移动步幅,越大越快
var scrolltime = 30;//移动频度(毫秒)越大越慢
var tmpH = 0;
$(document).ready(function () {
    Mar = document.getElementById("livestatus");
    picH = Mar.offsetHeight;

});

//字符向上滚动
function scrollStatus(textclass, text) {
    $(Mar).append("<p class='" + textclass + "'>" + text + "</p>")
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
        var child_div = Mar.getElementsByTagName("p");
// 				$(child_div[0]).remove();
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
        console.log('swf is ready!');
        isReady = true;
        thisMovie("rtmp-streamer").setShowText("录音未开始");
    }
}

/**
 * 设置麦克风开启
 */
function mrophoneIsOpen() {
    if (!isOpen) {
        console.log('mrophone is open!');
        isOpen = true;
    }
    //麦克风打开之后向后台发送直播请求
    connectWS();//打开ws连接
}

/**
 * 推流错误
 * @param infocode
 */
function streamError(infocode) {
    if (infocode != "NetConnection.Connect.Closed") {
        var msg = "连接流媒体服务器出错！\n" + infocode;
        Dialog.alert(msg);
    }
}

/**
 * 推流 推送流媒体
 */
function publishRtmp() {
    setMicQuality(10);
    //console.log('MicQuality:'+10);
    thisMovie("rtmp-streamer").publish("${rtmpAddress}", streamid, "正在使用麦克风...");
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

function setMicRate(rate) {
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