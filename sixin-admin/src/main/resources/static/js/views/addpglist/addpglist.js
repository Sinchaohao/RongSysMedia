var zTree;
var prointerval = 5;
var baseTime = "08:00:00";
if("${prointerval}"!=""){//节目单时间间隔
    prointerval=parseInt("${prointerval}");
}

/**
 * 设置节目
 * @param obj
 */
function setPro(obj){
    var type="";
    if($("#broaddate").val() == ""){
        layer.tips('请选择播出日期','#broaddate', {
            tips: [1, '#3595CC'],
            time: 4000
        });
        return false;
    }
    var data = $("#broaddate").val();
    var time = getTime(data+" "+baseTime);
    //选择填充进tbody
    if(obj=="app-on"){
        type="开启功放";
    }else if(obj=="app-off"){
        type="关闭功放";
    }else{
        type="停止播放";
    }
    $("#tbody").append("<tr style='max-height: 70px;min-height:70px'><td class='center'>"+type+"</td> <td class='center'></td>"+
        "<td class='center'></td> <td class='center'>"+obj+"</td>"+
        "<td class='center' name='00:00:00'>"+time+"</td> <td class='center'>00:00:00</td>"+
    " <td class='center'>"+
        "<div class='action-buttons'>"+
        "<a class='green' onclick='editTime(this);'>"+
        "<i class='ace-icon fa fa-pencil-square-o bigger-130' title='修改时间'></i>"+
        "</a> <a class='red' onclick='deletePro(this);'>"+
        "<i class='ace-icon fa fa-trash-o bigger-120' title='删除'></i>"+
        "</a> </div>"+
        "</td><td class='center'><div class='action-buttons'><a class='red'>"+
        "<i class='ace-icon fa fa-stop-circle bigger-120'></i></a></div></td></tr>");
}

/**
 * 删除节目
 * @param obj
 */
function deletePro(obj){
    $(obj).parent().parent().parent().remove();
}

/**
 * 设置时间
 * @param obj
 */
function editTime(obj){
    var time = $(obj).parent().parent().prev().prev().text();
    console.log("TIME="+time)
    var _url = "/broad/proSinmanage/getTime?time="+time;
    var _title = '修改时间';
    var _width = "500";
    var _height = ($(window).height() - 200);
    layer.open({
        type: 2,
        maxmin: true,
        shade: 0.3,
        title: _title,
        fix: false,
        area: [_width + 'px', _height + 'px'],
        content: _url,
        shadeClose: true,
        btn: ['<i class="fa fa-chat"></i> 确认', '<i class="fa fa-close"></i> 关闭'],
        yes: function (index, layero) {
            layer.close(index);
            //获取子页面关闭前的回调函数获取到的值
            var res = $(layero).find("iframe")[0].contentWindow.callback();
            $(obj).parent().parent().prev().prev().text(res);
        }, cancel: function () {
            return true;
        }
    });
    // top.jzts();
    // var diag = new top.Dialog();
    // diag.Drag=true;
    // diag.Title ="设置开始时间";
    // diag.URL = "<%=basePath%>probroad/setTime.do?time="+$(obj).parent().parent().prev().prev().text();
    // diag.Width = 400;
    // diag.Height = 250;
    // diag.CancelEvent = function(){
    //     if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
    //         var time = diag.innerFrame.contentWindow.document.getElementById('begintime').value;
    //         if(time.length==5){
    //             time=time+":00";
    //         }
    //         $(obj).parent().parent().prev().prev().text(time);
    //     }
    //     diag.close();
    // };
    // diag.show();

}



/**
 * FM转播
 */
function setFM(){
    var time = getTime();
    top.jzts();
    var diag = new top.Dialog();
    diag.Drag=true;
    diag.Title ="设置转播电台";
    diag.URL = "<%=basePath%>probroad/setFM.do";
    diag.Width = 400;
    diag.Height = 250;
    diag.CancelEvent = function(){
        if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
            var fm = diag.innerFrame.contentWindow.document.getElementById('fm').value;
            var broadtime = diag.innerFrame.contentWindow.document.getElementById('broadtime').value;
            if(broadtime.length==5){
                broadtime=broadtime+":00";
            }
            var cid = fm.split(";")[0];
            var cname = fm.split(";")[1];
            var frequencies = fm.split(";")[2];
            $("#tbody").append("<tr><td class='center'>电台播音</td> <td class='center'>"+cid+"</td>"+
                "<td class='center'>"+cname+"</td> <td class='center'>"+frequencies+"</td>"+
                "<td class='center'>"+time+"</td> <td class='center'>"+broadtime+"</td>"+
                " <td class='center'>"+
                "<div class='action-buttons'>"+
                "<a class='green' onclick='editTime(this);'>"+
                "<i class='ace-icon fa fa-pencil-square-o bigger-130' title='修改'></i>"+
                "</a> <a class='red' onclick='deletePro(this);'>"+
                "<i class='ace-icon fa fa-trash-o bigger-120' title='删除'></i>"+
                "</a> </div>"+
                "</td></tr>");
        }
        diag.close();
    };
    diag.show();
}

/**
 * 设置文件转播
 */
function setFile(){
    var time = getTime();
    top.jzts();
    var diag = new top.Dialog();
    diag.Drag=true;
    diag.Title ="设置文件转播";
    diag.URL = "<%=basePath%>probroad/setFile.do?from=nomal";
    diag.Width = 0.6;
    diag.Height = 0.7;
    diag.CancelEvent = function(){
        if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
            var filepro = diag.innerFrame.contentWindow.document.getElementById('filepro').value;
            var repeatnum = Number(diag.innerFrame.contentWindow.document.getElementById('repeatnum').value);
            var intervaltime = diag.innerFrame.contentWindow.document.getElementById('intervaltime').value;
            var fid = filepro.split(";")[0];
            var fname = filepro.split(";")[1];
            var filename = filepro.split(";")[2];
            var flenth = filepro.split(";")[3];
            for(var i=1;i<=repeatnum;i++){
                $("#tbody").append("<tr><td class='center'>文件转播</td> <td class='center'>"+fid+"</td>"+
                    "<td class='center'>"+fname+"</td> <td class='center'>"+filename+"</td>"+
                    "<td class='center'>"+time+"</td> <td class='center'>"+flenth+"</td>"+
                    " <td class='center'>"+
                    "<div class='action-buttons'>"+
                    "<a class='green' onclick='editTime(this);'>"+
                    "<i class='ace-icon fa fa-pencil-square-o bigger-130' title='修改'></i>"+
                    "</a> <a class='red' onclick='deletePro(this);'>"+
                    "<i class='ace-icon fa fa-trash-o bigger-120' title='删除'></i>"+
                    "</a> </div>"+
                    "</td></tr>");
                time = getTime(intervaltime);
            }
        }
        diag.close();
    };
    diag.show();
}

/**
 * 保存
 * @returns {boolean}
 */
function save(){
    if($("#tbody").children().length<=0){
        $("#appon").tips({
            side:3,
            msg:'请添加节目',
            bg:'#AE81FF',
            time:3
        });
        return false;
    }
    if($("#broaddate").val()==""||$("#broaddate").val()==null){
        $("#broaddate").tips({
            side:3,
            msg:'请选择播出日期',
            bg:'#AE81FF',
            time:3
        });
        return false;
    }
    var broaddate = $("#broaddate").val();
    var trs = $("#tbody").find("tr");
    var prolist="";
    var onindex=-1,offindex=-1,stopindex=-1;
    for(var i=0;i<trs.length;i++){
        var type = trs[i].children[0].innerText;
        var fid = trs[i].children[1].innerText;
        var fn = trs[i].children[3].innerText;
        var begintime = trs[i].children[4].innerText;
        var broadtime = trs[i].children[5].innerText;
        prolist+="{'type':'"+type+"','fid':'"+fid+"','fn':'"+fn+"','begintime':'"+begintime+"','broadtime':'"+broadtime+"'},";
        if(fn=='app-on'&& onindex==-1){
            onindex=i;
        }else if(fn=='app-off'){
            offindex=i;
        }else{
            stopindex=i;
        }
    }
    if(onindex<0){
        $("#appon").tips({
            side:3,
            msg:'请设置开启功放时间',
            bg:'#AE81FF',
            time:2
        });
        return false;
    }
    if(offindex<0){
        $("#appoff").tips({
            side:3,
            msg:'请设置关闭功放时间',
            bg:'#AE81FF',
            time:2
        });
        return false;
    }
    if(stopindex<0){
        $("#appstop").tips({
            side:3,
            msg:'请设置停止播放时间',
            bg:'#AE81FF',
            time:2
        });
        return false;
    }
    if(onindex!=0 ||(offindex-onindex)<=0 || (stopindex-offindex)!=1){
        $("#appon").tips({
            side:3,
            msg:'请设置正确的节目顺序',
            bg:'#AE81FF',
            time:2
        });
        return false;
    }
    //获取选择终端
    var nodes = zTree.getCheckedNodes();
    var terids = "";
    for(var i=0; i<nodes.length; i++){
        if(nodes[i].type=="1"){
            terids += nodes[i].id+",";
        }
    }
    terids = terids.substring(0,terids.length-1);
    if(terids==""){
        $("#tertitle").tips({
            side:3,
            msg:'请选择播出终端',
            bg:'#AE81FF',
            time:3
        });
        return false;
    }

    var continuenum = $("#continuenum").val();
    if(/^\d+$/.test(continuenum)) { //全为数字
        if(parseInt(continuenum)<1||parseInt(continuenum)>15){
            $("#continuenum").tips({
                side:3,
                msg:'请输入1-15的数字',
                bg:'#AE81FF',
                time:3
            });
            return false;
        }
    }else{
        $("#continuenum").tips({
            side:3,
            msg:'请输入1-15的数字',
            bg:'#AE81FF',
            time:3
        });
        return false;
    }
    prolist = "["+prolist.substring(0,prolist.length-1)+"]";
    $.post("<%=basePath%>probroad/addProBroad.do",{broaddate:broaddate,prolist:prolist,terids:terids,continuenum:continuenum},function(data){
        if(data.result=="success"){
            window.location.href="<%=basePath%>probroad/listProBroad.do";
        }
    });
}

//js日期格式化函数yyyy-MM-dd
function data_string(datetimeStr) {
    var mydateint = Date.parse(datetimeStr);//数值格式的时间
    if (!isNaN(mydateint)) {
        var mydate = new Date(mydateint);
        return mydate;
    }
    var mydate = new Date(datetimeStr);//字符串格式时间
    var monthstr = mydate.getMonth() + 1;
    if (!isNaN(monthstr)) {//转化成功
        return mydate;
    }//字符串格式时间转化失败
    var dateParts = datetimeStr.split(" ");
    var dateToday = new Date();
    var year = dateToday.getFullYear();
    var month = dateToday.getMonth();
    var day = dateToday.getDate();
    if (dateParts.length >= 1) {
        var dataPart = dateParts[0].split("-");//yyyy-mm-dd  格式时间
        if (dataPart.length == 1) {
            dataPart = dateParts[0].split("/");//yyyy/mm/dd格式时间
        }
        if (dataPart.length == 3) {
            year = Math.floor(dataPart[0]);
            month = Math.floor(dataPart[1]) - 1;
            day = Math.floor(dataPart[2]);
        }
    }
    if (dateParts.length == 2) {//hh:mm:ss格式时间
        var timePart = dateParts[1].split(":");//hh:mm:ss格式时间
        if (timePart.length == 3) {
            var hour = Math.floor(timePart[0]);
            var minute = Math.floor(timePart[1]);
            var second = Math.floor(timePart[2]);
            return new Date(year, month, day, hour, minute, second);
        }
    }
    else {
        return new Date(year, month, day);
    }

}
/**
 * 获得时间
 * @param intervaltime
 * @returns {string}
 */
function getTime(intervaltime,times){
    var basenum = 5;  //5秒钟延迟播放
    var restData = intervaltime;
    var trs = $("#tbody").find("tr");
    if(trs.length>=1){
        var lasttime = trs[trs.length-1].cells[4].innerText;
        var timelenth = trs[trs.length-1].cells[5].innerText;
        if(timelenth.length==0){
            timelenth = "00:00:00";
        }
        var seconds =0;
        if(lasttime!=null&&lasttime!=""){
            var H2 = parseInt(lasttime.split(":")[0]);
            var M2 = parseInt(lasttime.split(":")[1]);
            var S2 = parseInt(lasttime.split(":")[2]);
            seconds += H2 * 3600  + M2 * 60  + S2 ;
        }
        if(times!=null&&times!=""){
            var H3 = parseInt(times.split(":")[0]);
            var M3 = parseInt(times.split(":")[1]);
            var S3 = parseInt(times.split(":")[2]);
            seconds += H3 * 3600  + M3 * 60  + S3 ;
        }
        if(timelenth!=null &&timelenth.length>0){
            var H = parseInt(timelenth.split(":")[0]);
            var M = parseInt(timelenth.split(":")[1]);
            var S = parseInt(timelenth.split(":")[2]);
            seconds += H * 3600  + M * 60  + S ;
        }
        var Se = seconds-28800+basenum;
        restData = addTime(intervaltime,Se);
    }
    var nule = restData.toString().split(" ")[1];
    if(nule!=null &&nule.length>0){
        var H,M,S;
        H = parseInt(nule.split(":")[0]);
        M = parseInt(nule.split(":")[1]);
        S = parseInt(nule.split(":")[2]);
        if(H<9){
            H = '0'+H;
        }
        if(M<9){
            M = '0'+M;
        }
        if(S<9){
            S = '0'+S;
        }
        nule = H+":"+M+":"+S;
    }
    return nule;
}


//时间相加
function addTime(d,num) {
    var d = new Date(d.substring(0,4),
        d.substring(5,7)-1,
        d.substring(8,10),
        d.substring(11,13),
        d.substring(14,16),
        d.substring(17,19));
    d.setTime(d.getTime()+num*1000);
    //console.log(d.toLocaleString());
    return d.getFullYear()+"-"
        +(d.getMonth()+1)
        +"-"+d.getDate()
        +" "+d.getHours().toString()
        +":"+d.getMinutes().toString()
        +":"+d.getSeconds().toString();

}

Date.prototype.format = function(format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}

/*流媒体直播-选择终端树*/
function selectOrganTree() {
    var _url = "/broad/organization/selectOrganizationTree/01";
    var _title = '选择终端';
    var _width = "1200";
    var _height = ($(window).height() - 50);
    layer.open({
        type: 2,
        maxmin: true,
        shade: 0.3,
        title: _title,
        fix: false,
        area: [_width + 'px', _height + 'px'],
        content: _url,
        shadeClose: true,
        btn: ['<i class="fa fa-chat"></i> 确认', '<i class="fa fa-close"></i> 关闭'],
        yes: function (index, layero) {
            doSubmit(index, layero)
            //获取子页面关闭前的回调函数获取到的值
            var res = $(layero).find("iframe")[0].contentWindow.callback();
            //console.log(">>>",res.data_role.ids)
            $("#treeName").val(res.data_role.ids)
        }, cancel: function () {
            return true;
        }
    });
}
//文件转播选择
function doFile() {
    var baseTime = "08:00:00";
    if($("#broaddate").val() == ""){
        layer.tips('请选择播出日期','#broaddate', {
            tips: [1, '#3595CC'],
            time: 4000
        });
        return false;
    }
    var data = $("#broaddate").val();

    //console.log("timeFile="+time);
    var _url = "/broad/proSinmanage/getdoFile";
    var _title = '文件选择';
    var _width = "1200";
    var _height = ($(window).height() - 50);
    layer.open({
        type: 2,
        maxmin: true,
        shade: 0.3,
        title: _title,
        fix: false,
        area: [_width + 'px', _height + 'px'],
        content: _url,
        shadeClose: true,
        btn: ['<i class="fa fa-chat"></i> 确认', '<i class="fa fa-close"></i> 关闭'],
        yes: function (index, layero) {
            layer.close(index);
            //获取子页面关闭前的回调函数获取到的值
            var res = $(layero).find("iframe")[0].contentWindow.callbackfile();
            var id = res.data_fileID.toString().replace("[","").replace("]","").replace("\"","").split(",");
            var filenames = res.data_filenames.toString().replace("[","").replace("]","").replace("\"","").split(",");
            var filename = res.data_filename.toString().replace("[","").replace("]","").replace("\"","").split(",");
            var filetime = res.data_file.toString().replace("[","").replace("]","").replace("\"","").split(",");
           //console.log(">>>重复次数"+res.data_num+">>>文件id"+res.data_fileID+">>>节目"+res.data_filenames+">>>节目文件"+res.data_filename+">>>时长"+res.data_file+">>>间隔时长"+res.data_time)
           // console.log(">>>重复次数"+res.data_num+">>>文件id"+id+">>>节目"+filenames+">>>节目文件"+filename+">>>时长"+filetime+">>>间隔时长"+res.data_time)
           var time = getTime(data+" "+baseTime,res.data_time);
            for(var i=1;i<=res.data_num;i++){
                for(var j=0;j<res.data_length;j++){
                    $("#tbody").append("<tr><td class='center'>文件转播</td> <td class='center'>"+id[j]+"</td>"+
                        "<td class='center'>"+filenames[j]+"</td> <td class='center'>"+filename[j]+"</td>"+
                        "<td class='center' name='"+res.data_time+"'>"+time+"</td> <td class='center'>"+filetime[j]+"</td>"+
                        " <td class='center'>"+
                        "<div class='action-buttons'>"+
                        "<a class='green' onclick='editTime(this);'>"+
                        "<i class='ace-icon fa fa-pencil-square-o bigger-130' title='修改'></i>"+
                        "</a> <a class='red' onclick='deletePro(this);'>"+
                        "<i class='ace-icon fa fa-trash-o bigger-120' title='删除'></i>"+
                        "</a> </div>"+
                        "</td><td class='center'><div class='action-buttons'><a class='red' onclick='moveUp(this)'>"+
                        "<i class='ace-icon fa fa-arrow-up bigger-120' title='上移'></i></a>&nbsp;&nbsp;" +
                        "<a class='red' onclick='moveDown(this)'>" +
                        "<i class='ace-icon fa fa-arrow-down bigger-120' title='下移'></i></a></div></td></tr>");
                    time = getTime(data+" "+baseTime,"00:00:00");
                }
            }
        }, cancel: function () {
            return true;
        }
    });
}
//设备终端选择回调
function doSubmit(index, layero){
    var tree = layero.find("iframe")[0].contentWindow.$._tree;
    /* if ($.tree.notAllowParents(tree)) {*/
    var body = layer.getChildFrame('body', index);
    $("#treeId").val(body.find('#treeId').val());
    $("#treeName").val(body.find('#treeName').val());
    layer.close(index);
    /*}*/
}
//电台选择
function doCham() {
    var baseTime = "08:00:00";
    if($("#broaddate").val() == ""){
        layer.tips('请选择播出日期','#broaddate', {
            tips: [1, '#3595CC'],
            time: 4000
        });
        return false;
    }
    var data = $("#broaddate").val();

    //console.log("timeCham="+time);
    var _url = "/broad/proSinmanage/getdoCham";
    var _title = '电台选择';
    var _width = "1200";
    var _height = ($(window).height() - 50);
    layer.open({
        type: 2,
        maxmin: true,
        shade: 0.3,
        title: _title,
        fix: false,
        area: [_width + 'px', _height + 'px'],
        content: _url,
        shadeClose: true,
        btn: ['<i class="fa fa-chat"></i> 确认', '<i class="fa fa-close"></i> 关闭'],
        yes: function (index, layero) {
            layer.close(index);
            //获取子页面关闭前的回调函数获取到的值
            var res = $(layero).find("iframe")[0].contentWindow.callbackfile();
            //console.log(">>>",res)
            var times = getTime(data+" "+baseTime,res.data_time);
            $("#tbody").append("<tr><td class='center'>电台播音</td> <td class='center'>"+res.data_fileID+"</td>"+
                "<td class='center'>"+res.data_filename+"</td> <td class='center'>"+res.data_file+"</td>"+
                "<td class='center' name = '"+res.data_time+"'>"+times+"</td> <td class='center'>"+res.data_time+"</td>"+
                " <td class='center'>"+
                "<div class='action-buttons'>"+
                "<a class='green' onclick='editTime(this);'>"+
                "<i class='ace-icon fa fa-pencil-square-o bigger-130' title='修改'></i>"+
                "</a> <a class='red' onclick='deletePro(this);'>"+
                "<i class='ace-icon fa fa-trash-o bigger-120' title='删除'></i>"+
                "</a> </div>"+
                "</td><td class='center'><div class='action-buttons'><a class='red' onclick='moveUp(this)'>"+
                "<i class='ace-icon fa fa-arrow-up bigger-120' title='上移'></i></a>&nbsp;&nbsp;" +
                "<a class='red' onclick='moveDown(this)'>" +
                "<i class='ace-icon fa fa-arrow-down bigger-120' title='下移'></i></a></div></td></tr>");
            times = getTime(data+" "+baseTime,res.data_time);
        }, cancel: function () {
            return true;
        }
    });
}
function saves(scategory){
    if($("#tbody").children().length<=0){
        //小tips
        layer.tips('请添加节目','#saves', {
            tips: [1, '#3595CC'],
            time: 4000
        });

        return false;
    }
    if($("#broaddate").val()==""||$("#broaddate").val()==null){

        layer.tips('请选择播出日期','#broaddate', {
            tips: [1, '#3595CC'],
            time: 4000
        });
        return false;
    }
    var broaddate = $("#broaddate").val();
    console.log("播出日期>>>",broaddate)
    var trs = $("#tbody").find("tr");
    var prolist="";
    var onindex=-1,offindex=-1,stopindex=-1;
    for(var i=0;i<trs.length;i++){
        var type = trs[i].children[0].innerText;
        var fid = trs[i].children[1].innerText;
        var fn = trs[i].children[3].innerText;
        var begintime = trs[i].children[4].innerText;
        var broadtime = trs[i].children[5].innerText;
        prolist+="{'ptp':'"+type+"','fid':'"+fid+"','fN':'"+fn+"','bt':'"+begintime+"','broadtime':'"+broadtime+"'},";
        //console.log(prolist)
        if(fn=='app-on'&& onindex==-1){
            onindex=i;
        }else if(fn=='app-off'){
            offindex=i;
        }else{
            stopindex=i;
        }
    }
    if(onindex<0){
        layer.tips('请设置开启功放时间','#saves', {
            tips: [1, '#3595CC'],
            time: 4000
        });
        return false;
    }

    if(offindex<0){

        layer.tips('请设置关闭功放时间','#saves', {
            tips: [1, '#3595CC'],
            time: 4000
        });
        return false;
    }
    if(stopindex<0){
        layer.tips('请设置停止播放时间','#saves', {
            tips: [1, '#3595CC'],
            time: 2000
        });

        return false;
    }
    if(onindex!=0 ||(offindex-onindex)<=0 || (stopindex-offindex)!=1){

        layer.tips('请设置正确的节目顺序','#saves', {
            tips: [1, '#3595CC'],
            time: 4000
        });
        return false;
    }
    //获取选择终端
    //var nodes = zTree.getCheckedNodes();
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    var treeObj1 = $.fn.zTree.getZTreeObj("tree1");
    var nodes1 = treeObj1.getCheckedNodes(true);
    var nodes = treeObj.getCheckedNodes(true);
    if(nodes1.length>0){
        nodes = nodes1;
    }
    var terids = [];
    nodes.forEach(function(a,b){
        if(a.id.length==15){
            terids.push(Number(a.id));
        }
    })
    // terids = $("#treeName").val()
    // console.log("选择的终端>>>",terids)
    if(terids==""||terids==null||typeof(terids) == "undefined"){
        layer.tips('请选择播出终端','#saves', {
            tips: [1, '#3595CC'],
            time: 4000
        });

        return false;
    }

    var continuenum = $("#continuenum").val();
    console.log("连续天数>>>",continuenum)
    if(/^\d+$/.test(continuenum)) { //全为数字
        if(parseInt(continuenum)<1||parseInt(continuenum)>15){
            layer.tips('请输入1-15的数字','#saves', {
                tips: [1, '#3595CC'],
                time: 4000
            });
            return false;
        }
    }else{
        layer.tips('请输入1-15的数字','#saves', {
            tips: [1, '#3595CC'],
            time: 4000
        });
        return false;
    }
    prolist = "["+prolist.substring(0,prolist.length-1)+"]";
    console.log("表单数据>>>",JSON.stringify(prolist))
    $.ajax({
        url: ctx+'broad/proSinmanage/addProList',
        type: 'POST', //请求方法 GET or POST
        async: true, //是否使用异步请求的方式
        timeout: 3000, //请求超时的时间，以毫秒计
        data: {
            userId:userId,
            ProDate: broaddate,
            scategory:scategory,
            ProDay:continuenum,
            ProIMEI:JSON.stringify(terids),
            ProData:JSON.stringify(prolist)
        },
        dataType: 'json',
        success: function (data) {
            if(data.code=="200"){
                layer.msg("保存成功,正在刷新数据请稍后……", {
                    icon: 1,
                    time: 500,
                    shade: [0.1, '#8F8F8F']
                },function() {
                    if(scategory=="正常播出单"){
                        $.modal.openTab("节目播出单管理","/broad/proSinmanage");
                        location.reload();
                    }else{
                        $.modal.openTab("紧急播出单管理","/broad/proSinmanage/wproSinmanage");
                        location.reload();
                    }
                });
            }else {
                layer.msg("错误", {
                    icon: $.modal.icon("error"),
                    time: 1000,
                    shade: [0.1, '#8F8F8F']
                }
                // ,function() {
                //     $.modal.openTab("节目播出单管理","/broad/proSinmanage");
                //     location.reload();
                // }
                )
            }
        },
        error: function (res) {
            console.log(res)
            layer.msg("服务器错误", {
                icon: $.modal.icon("error"),
                time: 1000,
                shade: [0.1, '#8F8F8F']
            })
        }
    })
    // $.post("<%=basePath%>probroad/addProBroad.do",{broaddate:broaddate,prolist:prolist,terids:terids,continuenum:continuenum},function(data){
    //     if(data.result=="success"){
    //         window.location.href="<%=basePath%>probroad/listProBroad.do";
    //     }
    // });
}

function moveBy(obj) {
    console.log($(obj).parent().parent().parent().parent().get(this))
    console.log($(obj).parent().parent().parent())
}
/**
 * 时间重排测试
 */
function timeAuto(obj) {
    var objs = obj.parentNode.parentNode.parentNode.previousSibling.parentNode.childNodes;
    //console.log(objs.length)
    //console.log(objs)
    var baseTime = "08:00:00";
    var data = $("#broaddate").val();
    for (var i=2;i<objs.length;i++) {
        var obj = objs[i-1].childNodes[6];
        var obj2 = objs[i-1].childNodes[7].nextSibling.innerText;
        //console.log(obj2)
        var obj3 = objs[i].childNodes[6];
        var beginTime = $(obj).text();
        var fileLength = obj2;
        //console.log("开始时间："+beginTime+"文件时长："+fileLength)
        var dataTime = $(obj).attr("name");
        var time = getTimeAuto(data+" "+baseTime,beginTime,fileLength,dataTime);
        $(obj3).html(time);
        //console.log(">>>name",dataTime)
        //console.log(">>>",obj,obj2)
        //console.log(">>>",beginTime,fileLength)
    }
}
function getTimeAuto(intervaltime,beginTime,fileLength,dataTime){
    var basenum = 5;  //5秒钟延迟播放
    var seconds =0;
    if(beginTime!=null&&beginTime!=""){
        var H3 = parseInt(beginTime.split(":")[0]);
        var M3 = parseInt(beginTime.split(":")[1]);
        var S3 = parseInt(beginTime.split(":")[2]);
        //console.log("H3="+H3+"M3="+M3+"S3="+S3)
        seconds += H3 * 3600  + M3 * 60  + S3 ;
        //console.log("<<<1 前一个的播放开始时间>>>"+lasttime+"---"+seconds)
    }
    if(fileLength!=null &&fileLength !=""){
        var H = parseInt(fileLength.split(":")[0]);
        var M = parseInt(fileLength.split(":")[1]);
        var S = parseInt(fileLength.split(":")[2]);
        //console.log("H="+H+"M="+M+"S="+S)
        seconds += H * 3600  + M * 60  + S ;
        //console.log(">>>2 前一个的文件时长<<<"+timelenth+"---"+seconds)
    } //2017-11-11 80:00:00
    if(dataTime!=null &&dataTime!=""){
        var H = parseInt(dataTime.split(":")[0]);
        var M = parseInt(dataTime.split(":")[1]);
        var S = parseInt(dataTime.split(":")[2]);
        //console.log("H="+H+"M="+M+"S="+S)
        seconds += H * 3600  + M * 60  + S ;
        //console.log(">>>2 前一个的文件时长<<<"+timelenth+"---"+seconds)
    } //2017-11-11 80:00:00
    var Se = seconds-28800+basenum;
    //console.log(Se)
    var restData = addTime(intervaltime,Se);  //后面的是：前一个tr标签的播放开始时间+文件时长-08：00：00（28800s）+间隔时间
    // console.log(Se+">>>"+restData);


    var nule = restData.toString().split(" ")[1];
    if(nule!=null &&nule.length>0){
        var H,M,S;
        H = parseInt(nule.split(":")[0]);
        M = parseInt(nule.split(":")[1]);
        S = parseInt(nule.split(":")[2]);
        if(H<9){
            H = '0'+H;
        }
        if(M<9){
            M = '0'+M;
        }
        if(S<9){
            S = '0'+S;
        }
        nule = H+":"+M+":"+S;
    }
    return nule;
}