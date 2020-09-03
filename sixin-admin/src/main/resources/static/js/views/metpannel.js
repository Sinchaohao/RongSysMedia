function met_load() {
    met_data();
}
function met_data() {
    var deptId=$("#deptid").val();
    $.ajax({
        url: "/api/metpannel/data",
        data: {"deptId": $("#deptid").val()},
        type: "POST",
        datatype: "JSON",
        success: function (data) {
            var met_data = data.data;
            var time = new Array();
            var atmo_pressure = new Array();
            var wind_speed = new Array();
            for (i in met_data) {
                atmo_pressure.push(met_data[i].atmo_pressure);
                wind_speed.push(met_data[i].wind_speed);
                time.push(met_data[i].colltime);
            }

            var met_atmo_pressure = echarts.init(document.getElementById('met-atmo_pressure'));
            var met_wind_speed = echarts.init(document.getElementById('met-wind_speed'));
            atmo_pressure_option = {
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
                        data: atmo_pressure,
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    },
                ]
            };
            met_atmo_pressure.setOption(atmo_pressure_option);

            wind_speed_option = {
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
                        data: wind_speed,
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    },
                ]
            };
            met_wind_speed.setOption(wind_speed_option);
        }
    })
}