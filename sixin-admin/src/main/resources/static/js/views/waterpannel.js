function water_load() {
    imei_install_count();
    water_data();
}
function imei_install_count() {
    var deptId=$("#deptid").val();
    var imei_installcount = echarts.init(document.getElementById('imei-installcount'));
    $.ajax({
        url: "/api/waterpannel/installcount",
        data: {"deptId": $("#deptid").val()},
        type: "POST",
        datatype: "JSON",
        success: function (data) {
            var installcount_data=data.data;
            var installtime =new Array();
            var number = new Array();
            for(i in installcount_data){
                installtime.push(installcount_data[i].startdate);
                number.push(installcount_data[i].id);
            }

            installcount_option = {
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
                        data: installtime,
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
                        data: number,
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    },
                ]
            };
            imei_installcount.setOption(installcount_option);
        }
    })
}

function water_data() {
    var deptId=$("#deptid").val();
    $.ajax({
        url: "/api/waterpannel/data",
        data: {"deptId": $("#deptid").val()},
        type: "POST",
        datatype: "JSON",
        success: function (data) {
            var water_data=data.data;
            var time =new Array();
            var vdata = new Array();
            for(i in water_data){
                time.push(water_data[i].colldate);
                vdata.push(water_data[i].vdata);
            }
            var data_bycolltime = echarts.init(document.getElementById('water-data'));
            databytime_option = {
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
                        data: time,
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
                        data: vdata,
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    },
                ]
            };
            data_bycolltime.setOption(databytime_option);
        }
    })
}