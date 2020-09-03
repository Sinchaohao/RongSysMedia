/**
 * Created by MI on 2019/3/20.
 */

function bonLoad() {
    init_bd_mbygroup();
    init_bds_mbygroup();
    init_bt_mbygroup();
    init_ptp_mbygroup();
    /*统计故障终端，按时间分布*/
    init_bd_terminlgroup();
    /*统计在线终端数，下线终端数，及百分比*/
    init_bd_terminalstate();
    init_bl_terminalstate();
    init_bip_terminalstate();
    //首页图表 三维广播分布可视化
    init_3d_bt();
    //首页图表 二维广播分布可视化
    init_ec_bt();
}
function init_bt_mbygroup() {
    var bt_mbygroup = echarts.init(document.getElementById('bt_mbygroup'));
    $.ajax({
        data :{"deptId":$("#deptId").val()},
        type: "POST",
        url: "/api/btime/bt",
        datatype: "JSON",
        success: function (data) {
            var btygroup_data = data.data;
            var x_data = new Array();
            var y_data = new Array();
            for (var i = 0; i < btygroup_data.length; i++) {
                if (btygroup_data[i].bcount == '') {
                    x_data.push('未知');
                } else {
                    y_data.push(parseInt(btygroup_data[i].bcount));
                    x_data.push(btygroup_data[i].broadDate);
                }
            }
            /*console.log(x_data);*/
            /*console.log(y_data);*/
            option = {
                color: ['#06edfc'],
                tooltip: {
                    trigger: 'axis'
                },
                toolbox: {
                    show: true,
                    feature: {
                        magicType: {show: true, type: ['line', 'bar']},
                    }
                },
                calculable: true,
                grid: {
                    left: '3%',
                    right: '3%',
                    top: '5%',
                    bottom: '0%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        data: x_data,
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value}'
                        }
                    }
                ],
                series: [
                    {
                        type: 'line',
                        data: y_data,
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    },
                ]
            };
            bt_mbygroup.setOption(option);
        }
    });
}

function init_bd_mbygroup() {
    var bd_mbygroup = echarts.init(document.getElementById('bd_mbygroup'));
    $.ajax({
        data :{"deptId":$("#deptId").val()},
        type: "POST",
        url: "/api/btime/bd",
        datatype: "JSON",
        success: function (data) {
            var bdygroup_data = data.data;
            var x_data = new Array();
            var y_data = new Array();
            for (var i = 0; i < bdygroup_data.length; i++) {
                if (bdygroup_data[i].bcount == '') {
                    x_data.push('未知');
                } else {
                    y_data.push(parseInt(bdygroup_data[i].bcount));
                    x_data.push(bdygroup_data[i].broadDate);
                }
            }
            /*console.log(x_data);*/
            /*console.log(y_data);*/
            bd_option = {
                color: ['#00fc1a'],
                tooltip: {
                    trigger: 'axis'
                },
                toolbox: {
                    show: true,
                    feature: {
                        magicType: {show: true, type: ['line', 'bar']},
                    }
                },
                calculable: true,
                grid: {
                    left: '3%',
                    right: '3%',
                    top: '5%',
                    bottom: '0%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        data: x_data
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value}'
                        }
                    }
                ],
                series: [
                    {
                        type: 'line',
                        data: y_data,
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    }
                ]
            };
            bd_mbygroup.setOption(bd_option);
        }
    });
}

function init_bds_mbygroup() {
    var bds_mbygroup = echarts.init(document.getElementById('bds_mbygroup'));
    $.ajax({
        data :{"deptId":$("#deptId").val()},
        type: "POST",
        url: "/api/btime/bds",
        datatype: "JSON",
        success: function (data) {
            var bdsygroup_data = data.data;
            var x_data = new Array();
            var y_data = new Array();
            for (var i = 0; i < bdsygroup_data.length; i++) {
                if (bdsygroup_data[i].marea == '') {
                    x_data.push('未知');
                } else {
                    x_data.push(bdsygroup_data[i].scategory);
                    y_data.push(parseInt(bdsygroup_data[i].bcount));
                }
            }
            bds_option = {
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: x_data
                },
                calculable: true,
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [
                            /*y_data*/
                            {value: y_data[0], name: x_data[0]},
                            {value: y_data[1], name: x_data[1]}
                        ]
                    }
                ]
            };
            bds_mbygroup.setOption(bds_option);
        }
    });
}

function init_ptp_mbygroup() {
    var ptp_mbygroup = echarts.init(document.getElementById('ptp_mbygroup'));
    $.ajax({
        type: "POST",
        url: "/api/bp/ptp",
        data :{"deptId":$("#deptId").val()},
        datatype: "JSON",
        success: function (data) {
            var ptpygroup_data = data.data;
            var x_data = new Array();
            var y_data = new Array();
            var sum = 0;
            for (var i = 0; i < ptpygroup_data.length; i++) {
                if (ptpygroup_data[i].ptp == '') {
                    x_data.push('未知');
                } else {
                    x_data.push(ptpygroup_data[i].ptp);
                    y_data.push(parseInt(ptpygroup_data[i].count));
                    sum = sum > y_data[i] ? sum : y_data[i];
                }
                sum /= 5;
            }
            /*console.log(sum);*/
            ptp_option = {
                color: ['#6761c1'],
                grid: {
                    left: '3%',
                    right: '3%',
                    top: '5%',
                    bottom: '0%',
                    containLabel: true
                },
                tooltip: {
                    trigger: 'axis'
                },
                toolbox: {
                    show: true
                },
                polar: [
                    {
                        indicator: [
                            {text: x_data[0], max: sum + y_data[0]},
                            {text: x_data[1], max: sum + y_data[1]},
                            {text: x_data[2], max: sum + y_data[2]},
                            {text: x_data[3], max: sum + y_data[3]},
                            {text: x_data[4], max: sum + y_data[4]}
                        ]
                    }
                ],
                calculable: true,
                series: [
                    {
                        name: '操作类型',
                        type: 'radar',
                        data: [
                            {
                                name: '操作记录',
                                value: y_data
                            }
                        ]
                    }
                ]
            };
            ptp_mbygroup.setOption(ptp_option);
        }
    });
}

function init_bd_terminlgroup() {
    var bd_terminlgroup = echarts.init(document.getElementById('bd_terminlgroup'));
    $.ajax({
        type: "POST",
        url: "/api/terminal/bt",
        data :{"deptId":$("#deptId").val()},
        datatype: "JSON",
        success: function (data) {
            var bd_data = data.data;
            var x_data = new Array;
            var y_data = new Array;
            for (i in bd_data) {
                if (bd_data[i] == null) {
                    x_data.push('未知');
                } else {
                    x_data.push(bd_data[i].tmid);
                    y_data.push(bd_data[i].time);
                }
            }
            option = {
                grid: {
                    top: '5%',
                    left: '0%',
                    right: '3%',
                    bottom: 80
                },
                toolbox: {},
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        animation: false,
                        label: {
                            backgroundColor: '#505765'
                        }
                    }
                },
                color: ['#1265fc'],
                legend: {
                    data: ['流量', '降雨量'],
                    x: 'left'
                },
                dataZoom: [
                    {
                        show: true,
                        realtime: true,
                        start: 65,
                        end: 85
                    },
                    {
                        type: 'inside',
                        realtime: true,
                        start: 65,
                        end: 85
                    }
                ],
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        axisLine: {onZero: false},
                        data: y_data.map(function (str) {
                            return str.replace(' ', '\n')
                        })
                    }
                ],
                yAxis: [
                    {
                        name: '',
                        type: 'value',
                        max: 80
                    },
                    {
                        name: '',
                        nameLocation: 'start',
                        max: 80,
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '终端维护量',
                        type: 'line',
                        yAxisIndex: 1,
                        animation: false,
                        areaStyle: {},
                        lineStyle: {
                            width: 1
                        },
                        markArea: {
                            silent: true,
                            data: [[{
                                xAxis: '2009/9/10\n7:00'
                            }, {
                                xAxis: '2009/9/20\n7:00'
                            }]]
                        },
                        data: x_data
                    }
                ]
            };

            bd_terminlgroup.setOption(option);
        }
    });

}

function init_bd_terminalstate() {
    var bd_terminalstate = echarts.init(document.getElementById('bd_terminalstate'));
    $.ajax({
        type: "POST",
        url: "/api/bcount/bindex",
        data :{"aid":$("#deptId").val()},
        datatype: "JSON",
        success: function (data) {
            var bd_device = data.data.dev;
            var bd_run = data.data.run;
            var bd_stop = data.data.stop;
            option = {
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['终端总数', '在线终端数', '离线终端数']
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [
                            {value: bd_device, name: '终端总数'},
                            {value: bd_run, name: '在线终端数'},
                            {value: bd_stop, name: '离线终端数'}
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };

            bd_terminalstate.setOption(option);
        }

    });

}

function init_bl_terminalstate() {
    var bl_mygroup = echarts.init(document.getElementById('bl_mygroup'));
    $.ajax({
        type: "POST",
        url: "/api/bp/bl",
        data :{"deptId":$("#deptId").val()},
        datatype: "JSON",
        success: function (data) {
            var bd_data = data.data;
            var x_data = new Array;
            var y_data = new Array;
            for (i in bd_data) {
                if (bd_data[i] == null) {
                    x_data.push('未知');
                } else {
                    x_data.push(bd_data[i].count);
                    y_data.push(bd_data[i].lenth);
                }
            }
            init_bl_option = {
                tooltip: {
                    formatter: "{a}: <br/>{b} min : {c}"
                },
                color: ['#c161ae'],
                angleAxis: {},
                radiusAxis: {
                    type: 'category',
                    data: y_data
                },
                polar: {},
                series: [{
                    name: '数目',
                    type: 'bar',
                    data: x_data,
                    coordinateSystem: 'polar'
                }]
            };

            bl_mygroup.setOption(init_bl_option);
        }
    });
}

function init_bip_terminalstate() {
    var bip_mygroup = echarts.init(document.getElementById('bip_mygroup'));
    $.ajax({
        type: "POST",
        url: "/api/bp/bip",
        data :{"deptId":$("#deptId").val()},
        datatype: "JSON",
        success: function (data) {
            var bd_data = data.data;
            var x_data = new Array;
            var y_data = new Array;
            for (i in bd_data) {
                if (bd_data[i] == null) {
                    x_data.push('未知');
                } else {
                    x_data.push(bd_data[i].count);
                    y_data.push(bd_data[i].isPublic);
                }
            }
            init_bip_option = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter: function (params) {
                        var tar = params[1];
                        return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'category',
                    splitLine: {show: false},
                    data: y_data
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '辅助',
                        type: 'bar',
                        stack: '总量',
                        itemStyle: {
                            normal: {
                                barBorderColor: 'rgba(0,0,0,0)',
                                color: 'rgba(0,0,0,0)'
                            },
                            emphasis: {
                                barBorderColor: 'rgba(0,0,0,0)',
                                color: 'rgba(0,0,0,0)'
                            }
                        },
                        data: [0, x_data[2], 0]
                    },
                    {
                        name: '是否公共',
                        type: 'bar',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'inside'
                            }
                        },
                        data: x_data
                    }
                ]
            };

            bip_mygroup.setOption(init_bip_option);
        }
    });
}

function init_3d_bt() {

    var pre_data;
    var area_group = new Array();
    var data_group = new Array();
    var xyz_data = new Array();
    var ec_3d_bt= echarts.init(document.getElementById('3d_bt'));

    $.ajax({
        type: "GET",
        url: "/api/device/sumterm",
        dataType: "json",
        success: function (data_bt) {
            pre_data = data_bt.data;
            var dp_temp = ['down','req','run','sum'];
            //确定area_group
            for ( area_name in pre_data){
                area_group.push(pre_data[area_name].aname);
            }
            //确定xyz_data
            for( pg_name in pre_data){
                var ag = area_group.indexOf(pre_data[pg_name].aname);
                var temp_data = pre_data[pg_name];
                delete temp_data["aname"];
                var temp_keys = Object.keys(temp_data);
                for(i=0;i<temp_keys.length;i++){
                    var this_key = temp_keys[i];
                    var pg = dp_temp.indexOf(temp_keys[i]);
                    var z_value = temp_data[this_key];
                    xyz_data.push([pg,ag,z_value])
                }
            }

            // 英文 - 中文替换
            for ( item in dp_temp){
                if(dp_temp[item].indexOf('sum') >= 0 ){
                    dp_temp[item] = dp_temp[item].replace('sum','设备总数');
                }else if(dp_temp[item].indexOf('run') >= 0 ){
                    dp_temp[item] = dp_temp[item].replace('run','运行数');
                }else if (dp_temp[item].indexOf('down') >= 0 ) {
                    dp_temp[item] = dp_temp[item].replace('down','停止数');
                }else if(dp_temp[item].indexOf('req') >= 0){
                    dp_temp[item] = dp_temp[item].replace('req','维修数');
                }
                data_group = dp_temp;
            }

            //确定3D_EC 坐标系与数据
            var a_cg =area_group;
            var data_cg = data_group;
            var data = xyz_data;
            ec_3d_bt_option = {
                tooltip: {
                    axisPointer :{
                        label:{
                            show: true
                        }
                    }
                },
                visualMap: {
                    max: 260,
                    inRange: {
                        color: ['#00868B','#00CD00', '#00FA9A', '#00CED1','#00F5FF','#00FFFF']
                    }
                },
                xAxis3D: {
                    type: 'category',
                    data: a_cg
                },
                yAxis3D: {
                    type: 'category',
                    data: data_cg
                },
                zAxis3D: {
                    type: 'value'
                },
                grid3D: {
                    boxWidth: 300,
                    boxDepth: 80,
                    axisLabel: {
                        interval: 0
                    },
                    viewControl: {
                        // projection: 'orthographic'
                        autoRotate: true
                    },
                    light: {
                        main: {
                            intensity: 1.2,
                            shadow: true
                        },
                        ambient: {
                            intensity: 0.3
                        }
                    }
                },
                series: [{
                    type: 'bar3D',
                    data: data.map(function (item) {
                        return {
                            value: [item[1], item[0], item[2]],
                        }
                    }),
                    shading: 'lambert',

                    label: {
                        textStyle: {
                            fontSize: 12,
                            borderWidth: 1
                        }
                    },

                    emphasis: {
                        label: {
                            textStyle: {
                                fontSize: 20,
                                color: '#900'
                            }
                        },
                        itemStyle: {
                            color: '#900'
                        }
                    }
                }]
            };
            ec_3d_bt.setOption(ec_3d_bt_option);
        }
    })

}

function init_ec_bt() {
    var pre_data;
    var area_group = new Array();
    var ec_bt_sum = echarts.init(document.getElementById('bt_sum'));

    $.ajax({
        type: "GET",
        url: "/api/device/sumterm",
        dataType: "json",
        success: function (data_bt) {
            pre_data = data_bt.data;
            //确定area_group
            for ( area_name in pre_data){
                area_group.push(pre_data[area_name].aname);
            }

            // 二维图表 广播地域分布可视化
            var x_data = area_group;
            var y_data = new Array();

            for (y in pre_data){
                y_data.push(pre_data[y].sum);
            }
            ec_bt_sum_option = {
                color: ['#9B30FF'],
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'cross'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '0%',
                    right: '0%',
                    top: '5%',
                    bottom: '0%',
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
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'终端数量',
                        type:'bar',
                        barWidth: '60%',
                        data:y_data
                    }
                ]
            };
            ec_bt_sum.setOption(ec_bt_sum_option);

        }
    })
}

function sort_3d_bt() {

    var pre_data;
    var area_group = new Array();
    var data_group = new Array();
    var xyz_data = new Array();
    var ec_3d_bt= echarts.init(document.getElementById('3d_bt'));

    $.ajax({
        type: "GET",
        url: "/api/device/sumtermSort",
        dataType: "json",
        success: function (data_bt) {
            pre_data = data_bt.data;
            var dp_temp = ['down','req','run','sum'];
            //确定area_group
            for ( area_name in pre_data){
                area_group.push(pre_data[area_name].aname);
            }
            //确定xyz_data
            for( pg_name in pre_data){
                var ag = area_group.indexOf(pre_data[pg_name].aname);
                var temp_data = pre_data[pg_name];
                delete temp_data["aname"];
                var temp_keys = Object.keys(temp_data);
                for(i=0;i<temp_keys.length;i++){
                    var this_key = temp_keys[i];
                    var pg = dp_temp.indexOf(temp_keys[i]);
                    var z_value = temp_data[this_key];
                    xyz_data.push([pg,ag,z_value])
                }
            }

            // 英文 - 中文替换
            for ( item in dp_temp){
                if(dp_temp[item].indexOf('sum') >= 0 ){
                    dp_temp[item] = dp_temp[item].replace('sum','设备总数');
                }else if(dp_temp[item].indexOf('run') >= 0 ){
                    dp_temp[item] = dp_temp[item].replace('run','运行数');
                }else if (dp_temp[item].indexOf('down') >= 0 ) {
                    dp_temp[item] = dp_temp[item].replace('down','停止数');
                }else if(dp_temp[item].indexOf('req') >= 0){
                    dp_temp[item] = dp_temp[item].replace('req','维修数');
                }
                data_group = dp_temp;
            }

            //确定3D_EC 坐标系与数据
            var a_cg =area_group;
            var data_cg = data_group;
            var data = xyz_data;
            ec_3d_bt_option = {
                tooltip: {
                    axisPointer :{
                        label:{
                            show: true
                        }
                    }
                },
                visualMap: {
                    max: 260,
                    inRange: {
                        color: ['#00868B','#00CD00', '#00FA9A', '#00CED1','#00F5FF','#00FFFF']
                    }
                },
                xAxis3D: {
                    type: 'category',
                    data: a_cg
                },
                yAxis3D: {
                    type: 'category',
                    data: data_cg
                },
                zAxis3D: {
                    type: 'value'
                },
                grid3D: {
                    boxWidth: 300,
                    boxDepth: 80,
                    axisLabel: {
                        interval: 0
                    },
                    viewControl: {
                        // projection: 'orthographic'
                        autoRotate: true
                    },
                    light: {
                        main: {
                            intensity: 1.2,
                            shadow: true
                        },
                        ambient: {
                            intensity: 0.3
                        }
                    }
                },
                series: [{
                    type: 'bar3D',
                    data: data.map(function (item) {
                        return {
                            value: [item[1], item[0], item[2]],
                        }
                    }),
                    shading: 'lambert',

                    label: {
                        textStyle: {
                            fontSize: 12,
                            borderWidth: 1
                        }
                    },

                    emphasis: {
                        label: {
                            textStyle: {
                                fontSize: 20,
                                color: '#900'
                            }
                        },
                        itemStyle: {
                            color: '#900'
                        }
                    }
                }]
            };
            ec_3d_bt.setOption(ec_3d_bt_option);
        }
    })

}

function sort_ec_bt() {
    var pre_data;
    var area_group = new Array();
    var ec_bt_sum = echarts.init(document.getElementById('bt_sum'));

    $.ajax({
        type: "GET",
        url: "/api/device/sumtermSort",
        dataType: "json",
        success: function (data_bt) {
            pre_data = data_bt.data;
            //确定area_group
            for ( area_name in pre_data){
                area_group.push(pre_data[area_name].aname);
            }

            // 二维图表 广播地域分布可视化
            var x_data = area_group;
            var y_data = new Array();

            for (y in pre_data){
                y_data.push(pre_data[y].sum);
            }
            ec_bt_sum_option = {
                color: ['#9B30FF'],
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'cross'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '0%',
                    right: '0%',
                    top: '5%',
                    bottom: '0%',
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
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'终端数量',
                        type:'bar',
                        barWidth: '60%',
                        data:y_data
                    }
                ]
            };
            ec_bt_sum.setOption(ec_bt_sum_option);

        }
    })
}