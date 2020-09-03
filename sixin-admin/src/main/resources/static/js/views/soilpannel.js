function soil_load() {
    soil_data();
}
function soil_data() {
    var deptId=$("#deptid").val();
    $.ajax({
        url: "/api/soilpannel/data",
        data: {"deptId": $("#deptid").val()},
        type: "POST",
        datatype: "JSON",
        success: function (data) {
            var soil_data = data.data;
            var time = new Array();
            var temp = new Array();
            var hum = new Array();
            for (i in soil_data) {
                temp.push(soil_data[i].soil_temp);
                hum.push(soil_data[i].soil_temp);
                time.push(soil_data[i].colltime);
            }

            var soil_temp = echarts.init(document.getElementById('soil-temp'));
            var soil_hum = echarts.init(document.getElementById('soil-hum'));
            temp_option = {
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
                        data: temp,
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    },
                ]
            };
            soil_temp.setOption(temp_option);

            hum_option = {
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
                        data: hum,
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    },
                ]
            };
            soil_hum.setOption(hum_option);
        }
    })
}