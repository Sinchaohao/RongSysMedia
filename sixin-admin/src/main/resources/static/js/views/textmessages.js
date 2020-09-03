// 等待页面DOM加载完后自动加载
function onload() {

    // 广播加载
    init_bt_tel_freq();

}



function init_bt_tel_freq() {
    var bt_tel_freq = echarts.init(document.getElementById('tel_freq'));
    $.ajax({
        type: "GET",
        url: "/api/counttel/freq",
        datatype: "JSON",
        success: function (data) {

            var pre = data.data;
            var x_data = new Array();
            var y_data = new Array();

           for (i in pre){
               x_data.push(pre[i].textnum);
               y_data.push(pre[i].num);
           }

            tel_freq_option = {
                color: ['#db4e2b'],
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '0%',
                    right: '0%',
                    bottom: '0%',
                    top: '2%',
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
                        name:'频率',
                        type:'bar',
                        barWidth: '60%',
                        data: y_data
                    }
                ]
            };

            bt_tel_freq.setOption(tel_freq_option);
        }
    });
}

