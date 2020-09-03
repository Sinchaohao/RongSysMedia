function mil_load() {
    mil_data();
}
function mil_data() {
    var deptId=$("#deptId").val();
    $.ajax({
        url: "/api/milpannel/data",
        data :{"deptId":$("#deptId").val()},
        type: "POST",
        datatype: "JSON",
        success: function (data) {
            var mil_data = data.data;
            var time = new Array();
            var temp = new Array();
            var hum = new Array();
            var light = new Array();
            var pm25 = new Array();
            var co2 = new Array();
            var co = new Array();
            var so2 = new Array();
            // for (var i = 0; i < mil.length; i++) {
            //     if (temp_data[i].ambient_temp == 0) {
            //         x_data.push('未知');
            //     } else {
            //         y_data.push(temp_data[i].ambient_temp);
            //         time.push(temp_data[i].colltime);
            //     }
            // }
            for (i in mil_data){
                temp.push(mil_data[i].ambient_temp);
                hum.push(mil_data[i].ambient_hum);
                light.push(mil_data[i].ambient_light);
                pm25.push(mil_data[i].pm25);
                co2.push(mil_data[i].co2);
                co.push(mil_data[i].co);
                so2.push(mil_data[i].so2);
                time.push(mil_data[i].colltime);
            }

            var mil_temp = echarts.init(document.getElementById('mil-temp'));
            var mil_hum = echarts.init(document.getElementById('mil-hum'));
            var mil_light = echarts.init(document.getElementById('mil-light'));
            var mil_pm25 = echarts.init(document.getElementById('mil-pm25'));
            var mil_co2 = echarts.init(document.getElementById('mil-co2'));
            var mil_co = echarts.init(document.getElementById('mil-co'));
            var mil_so2 = echarts.init(document.getElementById('mil-so2'));


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
            mil_temp.setOption(temp_option);

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
            mil_hum.setOption(hum_option);

            light_option = {
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
                        data: light,
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    },
                ]
            };
            mil_light.setOption(light_option);

            pm25_option = {
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
                        data: pm25,
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    },
                ]
            };
            mil_pm25.setOption(pm25_option);

            co2_option = {
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
                        data: co2,
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    },
                ]
            };
            mil_co2.setOption(co2_option);

            co_option = {
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
                        data: co,
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    },
                ]
            };
            mil_co.setOption(co_option);

            so2_option = {
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
                        data: so2,
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    },
                ]
            };
            mil_so2.setOption(so2_option);
        }
    })
}
