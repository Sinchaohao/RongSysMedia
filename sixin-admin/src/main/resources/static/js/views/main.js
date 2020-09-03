// 等待页面DOM加载完后自动加载
function main_onload() {
    //系统注意弹窗注意
    info_pop();
    // 广播加载
    init_broad();
    //村务加载
    init_village();
    //物联网和山洪数据统计
    init_iotdatacount();
    init_rivisdatacount();
    init_riotdevcount();


    // 月登陆排名
    init_ranklogin();
    // 月操作排名
    init_rankoper();
    // 登陆地点排名
    init_rankloc();
    // 登陆地点的柱状图
    init_all_loc();
    //最近创建用户
    init_recentCreat();

    //系统访问记录
    init_sys_loginlog();
    //系统操作记录
    init_sys_operlog();
    //系统监控
    init_sys_mon();

    // //每15秒刷新系统监控数据
    // setInterval(init_sys_mon, 15000);
    // //每30秒刷新登陆数据数据
    // setInterval(init_sys_loginlog, 30000);
}
// 系统说明
function info_pop() {
    $('#pop').html('<div class="modal-dialog">' +
        '<div class="modal-content animated flipInY">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
        '<h4 class="modal-title">网站声明</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<p>' +
        '您现在访问的是《融媒体综合数据平台的后台管理系统》，此网站正处于开发阶段，所有功能均处于测试期。' +
        '所有用户操作已进行行为记录，勿使用擅自进行任何增删改操作，若对系统的造成不良影响则会追究到个人，谢谢配合。'+
        '</p>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-primary" data-dismiss="modal">知道了</button>' +
        '</div>' +
        '</div>' +
        '</div>')
}
// 广播信息
function init_broad() {
    $.ajax({
        type: "POST",
        url: "/api/bcount/bindex",
        dataType: "json",
        success: function(data){
            $("#fls_cb_time").html(data.time);
            $("#count_dev").html(data.data.dev);
            $("#count_run").html(data.data.run);
            $("#count_stop").html(data.data.stop);
        }
    });
}
// 物联网信息
function init_iotdatacount() {
    $.ajax({
        type: "GET",
        url: "/api/iot/countall",
        dataType: "json",
        success: function(data){
            $("#iotcount").html(data.data);
        }
    });
}
// 山洪信息
function init_rivisdatacount() {
    $.ajax({
        type: "GET",
        url: "/api/rivervis/countall",
        dataType: "json",
        success: function(data){
            $("#riverviscount").html(data.data);
        }
    });
}
// 山洪预警信息
function init_riotdevcount() {
    $.ajax({
        type: "GET",
        url: "/api/iot/devicecount",
        dataType: "json",
        success: function(data){
            $("#iotdevcount").html(data.data);
        }
    });
}
// 村务初始化
function init_village() {
    $.ajax({
        type:"GET",
        url:"/api/count/p_m",
        dataType:"json",
        success:function (data) {
            $("#fls_cv_time").html(data.time);
            $("#count_ms").html(data.data[0].msum);
            $("#count_mm").html(data.data[0].mman);
            $("#count_mw").html(data.data[0].mwoman);
            $("#count_ps").html(data.data[0].psum);
            $("#count_p_m").html(data.data[0].pman);
            $("#count_pw").html(data.data[0].pwoman);
        }
    });
}

// 系统监控
function init_sys_mon() {
    $.ajax({
        type:"GET",
        url:"/api/sys_mon/mi",
        dataType:"json",
        success:function (data) {
            $("#mon_flush_time").html('<span>'+ data.time + ' 更新' +
                '</span>');

            $("#sys_mon_cpu").html('<table class="table table-hover">\n' +
                '<thead>' +
                '<tr>' +
                '<th>CPU</th>' +
                '<th>参考值</th>' +
                '</tr>' +
                '</thead>' +
                '<tbody>' +
                '<tr>' +
                '</td>' +
                '<td>核心</td>' +
                '<td id="" class="text-warning">' + data.data.cpu.cpuNum + " 核心" +
                '</td>' +
                '</tr>' +
                '<tr>' +
                '</td>' +
                '<td>用户</td>' +
                '<td id="" class="text-navy">' + data.data.cpu.used + "%" +
                '</td>' +
                '</tr>' +
                '<tr>' +
                '</td>' +
                '<td>系统</td>' +
                '<td id="" class="text-warning">' + data.data.cpu.sys + "%" +
                '</td>' +
                '</tr>' +
                '<tr>' +
                '</td>' +
                '<td>空闲</td>' +
                '<td id="" class="text-navy">' + data.data.cpu.free + "%" +
                '</td>' +
                '</tr>' +
                '</tbody>' +
                '</table>'
            );

            $("#sys_mon_line").html(' <div>' +
                ' <div>' +
                '<span>' + "内存" +
                '</span>' +
                ' <small class="pull-right">' + data.data.mem.usage + "%" +
                ' </small>' +
                ' </div>' +
                '<div class="progress progress-mini">' +
                '<div style="width:' +  data.data.mem.usage + "%" +
                '" class="progress-bar"></div>' +
                '</div>' +
                '<br>' +
                ' <div>' +
                '<span>' + "JVM" +
                '</span>' +
                ' <small class="pull-right">' + data.data.jvm.usage + "%" +
                ' </small>' +
                ' </div>' +
                '<div class="progress progress-mini">' +
                '<div style="width:' + data.data.jvm.usage + "%" +
                '" class="progress-bar"></div>' +
                '</div>' +
                '<br>' +
                ' <div>' +
                '<span>' + "CPU" +
                '</span>' +
                ' <small class="pull-right">' + data.data.cpu.used + "%" +
                ' </small>' +
                ' </div>' +
                '<div class="progress progress-mini">' +
                '<div style="width:' + data.data.cpu.used + "%" +
                '" class="progress-bar"></div>' +
                '</div>' +
                '</div>'
            );

            var disk_data = data.data.sysFiles;
            var diskinfo = '';
            for(diskname in disk_data){
                diskinfo += '<tr>' +
                    '<td>' + disk_data[diskname].dirName  +
                    '</td>' +
                    '<td>' + disk_data[diskname].usage + '%' +
                    '</td>' +
                    '</tr>';
            }

            $("#sys_mon_disk").html('<table class="table table-hover margin bottom">\n' +
                '<thead>' +
                '<tr>' +
                '<th>磁盘</th>' +
                '<th>已用</th>' +
                '</tr>' +
                '</thead>' +
                '<tbody>' + diskinfo +
                '</tbody>' +
                '</table>')

        }
    })
}
// 系统登陆记录
function init_sys_loginlog() {
    $.ajax({
        type: "GET",
        url: "/api/sys_log/l_log",
        dataType: "json",
        success: function (data) {
            $("#llog_flush_time").html('<span>' + data.time + ' 更新' +
                '</span>');

            var l_log = data.data;
            var log_info = '';
            for (log in l_log){
                log_info += '<tr>' +
                    '<td>' + l_log[log].user_name +
                    '</td>' +
                    '<td>' + l_log[log].dept_name +
                    '</td>' +
                    '<td>' + l_log[log].role_name +
                    '</td>' +
                    '<td>' + l_log[log].ipaddr +
                    '</td>' +
                    '<td>' + l_log[log].login_location +
                    '</td>' +
                    '<td>' + l_log[log].loginTime +
                    '</td>' +
                    '<td>' + l_log[log].msg +
                    '</td>' +
                    '<td>' + l_log[log].times +
                    '</td>' +
                    '<td>' + l_log[log].browser +
                    '</td>' +
                    '</tr>';
            }

            $("#sys_login_log").html('<table class="table table-hover">' +
                '<thead>' +
                '<tr>' +
                '<th>姓名</th>' +
                '<th>部门</th>' +
                '<th>级别</th>' +
                '<th>IP</th>' +
                '<th>地点</th>' +
                '<th>时间</th>' +
                '<th>状态</th>' +
                '<th>月次数</th>' +
                '<th>浏览器</th>' +
                '</tr>' +
                '</thead>' +
                '<tbody>' + log_info +
                '</tbody>' +
                '</table>');
        }
    })

}
// 系统操作记录
function init_sys_operlog() {
    $.ajax({
        type: "GET",
        url: "/api/sys_log/O_log",
        dataType: "json",
        success: function (data) {
            var O_log = data.data;
            var log_info = '';
            for (log in O_log){
                log_info += '<tr>' +
                    '<td>' + O_log[log].user +
                    '</td>' +
                    '<td>' + O_log[log].title +
                    '</td>' +
                    '<td>' + O_log[log].type +
                    '</td>' +
                    '<td>' + O_log[log].url +
                    '</td>' +
                    '<td>' + O_log[log].res +
                    '</td>' +
                    '<td>' + O_log[log].Time +
                    '</td>' +
                    '<td>' + O_log[log].ip +
                    '</td>' +
                    '<td>' + O_log[log].loc +
                    '</td>' +
                    '<td>' + O_log[log].times +
                    '</td>' +
                    '</tr>';
            }

            $("#sys_oper_log").html('<table class="table table-hover">' +
                '<thead>' +
                '<tr>' +
                '<th>姓名</th>' +
                '<th>模块</th>' +
                '<th>操作</th>' +
                '<th>调用服务</th>' +
                '<th>结果</th>' +
                '<th>时间</th>' +
                '<th>IP</th>' +
                '<th>地点</th>' +
                '<th>月次数</th>' +
                '</tr>' +
                '</thead>' +
                '<tbody>' + log_info +
                '</tbody>' +
                '</table>');
        }
    })

}

// 登陆排名
function init_ranklogin() {
    $.ajax({
        type: "GET",
        url: "/api/sys_log/CountLoLogDescMonth",
        datatype: "JSON",
        success: function (data) {
            var predata = data.data;
            var log_info = '';
            for (i in predata){
                log_info += '<tr>' +
                    '<td>' + predata[i].user +
                    '</td>' +
                    '<td>' + predata[i].cn +
                    '</td>' +
                    '</tr>';
            }
            $("#rank_login").html('<table class="table table-hover">' +
                '<thead>' +
                '<tr>' +
                '<th>姓名</th>' +
                '<th>登陆排名/30天</th>' +
                '</tr>' +
                '</thead>' +
                '<tbody>' + log_info +
                '</tbody>' +
                '</table>');
        }
    })

}

// 操作排名
function init_rankoper() {
    $.ajax({
        type: "GET",
        url: "/api/sys_log/CountOperLogMonth",
        datatype: "JSON",
        success: function (data) {
            var predata = data.data;
            var log_info = '';
            for (i in predata){
                log_info += '<tr>' +
                    '<td>' + predata[i].user +
                    '</td>' +
                    '<td>' + predata[i].cn +
                    '</td>' +
                    '</tr>';
            }
            $("#rank_oper").html('<table class="table table-hover">' +
                '<thead>' +
                '<tr>' +
                '<th>姓名</th>' +
                '<th>操作排名/30天</th>' +
                '</tr>' +
                '</thead>' +
                '<tbody>' + log_info +
                '</tbody>' +
                '</table>');
        }
    })

}

// 登陆地点排名
function init_rankloc() {
    $.ajax({
        type: "GET",
        url: "/api/sys_log/CountHotLocal",
        datatype: "JSON",
        success: function (data) {
            var predata = data.data;
            var log_info = '';
            for (i in predata){
                log_info += '<tr>' +
                    '<td>' + predata[i].name +
                    '</td>' +
                    '<td>' + predata[i].value +
                    '</td>' +
                    '</tr>';
            }
            $("#rank_loc").html('<table class="table table-hover">' +
                '<thead>' +
                '<tr>' +
                '<th>热门登陆地点</th>' +
                '<th>次数</th>' +
                '</tr>' +
                '</thead>' +
                '<tbody>' + log_info +
                '</tbody>' +
                '</table>');
        }
    })

}

// 所有地点的柱状图
function init_all_loc() {
    var allloc = echarts.init(document.getElementById('all_loc'));
    $.ajax({
        type: "GET",
        url: "/api/sys_log/CountLocal",
        datatype: "JSON",
        success: function (data) {
            var predata = data.data;
            var x_data = new Array;
            var y_data = new Array;
            for (i in predata) {
                x_data.push(predata[i].name);
                y_data.push(predata[i].value);
            }
            option = {
                color: ['#676a6c'],
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '-1%',
                    right: '1%',
                    bottom: '1%',
                    top: '5%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'category',
                        data : x_data,
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis : [
                    {
                        show: false,
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'访问量',
                        type:'bar',
                        barWidth: '60%',
                        data:y_data
                    }
                ]
            };
            allloc.setOption(option);
        }
    })

}


// 最近创建的用户
function init_recentCreat() {
    $.ajax({
        type: "GET",
        url: "/api/sys_log/recentCreatUser",
        datatype: "JSON",
        success: function (data) {
            var predata = data.data;
            var log_info = '';
            for (i in predata){
                log_info += '<tr>' +
                    '<td>' + predata[i].ct +
                    '</td>' +
                    '<td>' + predata[i].un +
                    '</td>' +
                    '</tr>';
            }
            $("#recent_reg").html('<table class="table table-hover">' +
                '<thead>' +
                '<tr>' +
                '<th>最近新增用户</th>' +
                '<th>姓名</th>' +
                '</tr>' +
                '</thead>' +
                '<tbody>' + log_info +
                '</tbody>' +
                '</table>');
        }
    })

}

