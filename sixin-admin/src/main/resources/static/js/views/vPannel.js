function v_onload() {

    //首页图表 村镇村民人口地域分布可视化
    init_ec_mbygroup();
    //首页图表 村镇党员地域分布可视化
    init_ec_pbygroup();
    //首页图表 三维村镇人口可视化
    init_3d_v_pm();
}


function init_ec_mbygroup() {
    $.ajax({
        url: "/api/count/m",
        type:"POST",
        data :{"aid":$("#deptId").val()},
        dataType: "json",
        success: function (data) {
            var mbygroup_data = data.data;
            var x_data = new Array();
            var y_data = new Array();
            for( x in mbygroup_data){
                if(mbygroup_data[x].marea == ''){
                    x_data.push('未知');
                }else {
                    x_data.push(mbygroup_data[x].marea);
                }
            }
            for (y in mbygroup_data){
                y_data.push(mbygroup_data[y].msum);
            }
            var ec_mbygroup = echarts.init(document.getElementById('ec_mbygroup'));
            ec_mbygroup_option = {
                color: ['#66fccc'],
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
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
                        name:'人口数量',
                        type:'bar',
                        barWidth: '60%',
                        data:y_data
                    }
                ]
            };
            ec_mbygroup.setOption(ec_mbygroup_option);
        }
    })
}

function sort_ec_mbygroup() {
    $.ajax({
        url: "/api/count/mSort",
        type:"POST",
        data :{"aid":$("#deptId").val()},
        dataType: "json",
        success: function (data) {
            var mbygroup_data = data.data;
            var x_data = new Array();
            var y_data = new Array();
            for( x in mbygroup_data){
                if(mbygroup_data[x].marea == ''){
                    x_data.push('未知');
                }else {
                    x_data.push(mbygroup_data[x].marea);
                }
            }
            for (y in mbygroup_data){
                y_data.push(mbygroup_data[y].msum);
            }
            var ec_mbygroup = echarts.init(document.getElementById('ec_mbygroup'));
            ec_mbygroup_option = {
                color: ['#66fccc'],
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
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
                        name:'人口数量',
                        type:'bar',
                        barWidth: '60%',
                        data:y_data
                    }
                ]
            };
            ec_mbygroup.setOption(ec_mbygroup_option);
        }
    })
}

function init_ec_pbygroup() {
    $.ajax({
        url: "/api/count/pm",
        type:"POST",
        data:{"deptId":$("#deptId").val()},
        dataType: "json",
        success: function (data) {
            var mbygroup_data = data.data;
            var x_data = new Array();
            var y_data = new Array();
            for( x in mbygroup_data){
                if(mbygroup_data[x].parea == ''){
                    x_data.push('未知');
                }else {
                    x_data.push(mbygroup_data[x].parea);
                }
            }
            for (y in mbygroup_data){
                y_data.push(mbygroup_data[y].psum);
            }

            var ec_pbygroup = echarts.init(document.getElementById('ec_pbygroup'));
            ec_pbygroup_option = {
                color: ['#ff3399'],
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
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
                        name:'人口数量',
                        type:'bar',
                        barWidth: '60%',
                        data:y_data
                    }
                ]
            };
            ec_pbygroup.setOption(ec_pbygroup_option);

        }
    })
}

function sort_ec_pbygroup() {
    $.ajax({
        url: "/api/count/pmSort",
        type:"POST",
        data:{"aid":$("#deptId").val()},
        dataType: "json",
        success: function (data) {
            var mbygroup_data = data.data;
            var x_data = new Array();
            var y_data = new Array();
            for( x in mbygroup_data){
                if(mbygroup_data[x].parea == ''){
                    x_data.push('未知');
                }else {
                    x_data.push(mbygroup_data[x].parea);
                }
            }
            for (y in mbygroup_data){
                y_data.push(mbygroup_data[y].psum);
            }

            var ec_pbygroup = echarts.init(document.getElementById('ec_pbygroup'));
            ec_pbygroup_option = {
                color: ['#ff3399'],
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
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
                        name:'人口数量',
                        type:'bar',
                        barWidth: '60%',
                        data:y_data
                    }
                ]
            };
            ec_pbygroup.setOption(ec_pbygroup_option);

        }
    })
}

function init_3d_v_pm() {
    var pre_data;
    var area_group = new Array();
    var people_group = new Array();
    var xyz_data = new Array();
    var ec_3d_v_pm = echarts.init(document.getElementById('3d_v_pm'));

    $.ajax({
        type: "GET",
        url: "/api/count/g_cPM",
        dataType: "json",
        success: function (data_pm) {
            pre_data = data_pm.data;
            var pg_temp = Object.keys(pre_data[0]);
            pg_temp.splice(arrIndex(pg_temp,'area'),1);
            //重新确定人员分组的顺序(暂时写死）
            pg_temp = ['pwoman','pman','psum','mwoman','mman','msum'];


            //确定area_group
            for ( area_name in pre_data){
                area_group.push(pre_data[area_name].area);
            }
            //确定xyz_data
            for( pg_name in pre_data){
                var ag = area_group.indexOf(pre_data[pg_name].area);
                var temp_data = pre_data[pg_name];
                delete temp_data["area"];
                var temp_keys = Object.keys(temp_data);
                for(i=0;i<temp_keys.length;i++){
                    var this_key = temp_keys[i];
                    var pg = pg_temp.indexOf(temp_keys[i]);
                    var z_value = temp_data[this_key];
                    xyz_data.push([pg,ag,z_value])
                }
            }
            //确定people_group
            // 英文 - 中文替换
            for ( item in pg_temp){
                if(pg_temp[item].indexOf('p') == 0 ){
                    pg_temp[item] = pg_temp[item].replace('p','党员');
                    if(pg_temp[item].indexOf('sum') >= 0){
                        pg_temp[item] = pg_temp[item].replace('sum','总数');
                    }else if(pg_temp[item].indexOf('man') >= 0 && pg_temp[item].indexOf('wo') < 0){
                        pg_temp[item] = pg_temp[item].replace('man','男性');
                    }else if(pg_temp[item].indexOf('woman') >= 0 && pg_temp[item].indexOf('wo') >= 0){
                        pg_temp[item] = pg_temp[item].replace('woman','女性');
                    }
                }
                if(pg_temp[item].indexOf('m') == 0 ){
                    pg_temp[item] = pg_temp[item].replace('m','村民');
                    if(pg_temp[item].indexOf('sum') >= 0){
                        pg_temp[item] = pg_temp[item].replace('sum','总数');
                    }else if(pg_temp[item].indexOf('man') && pg_temp[item].indexOf('wo') < 0){
                        pg_temp[item] = pg_temp[item].replace('man','男性');
                    }else if(pg_temp[item].indexOf('woman') >= 0 && pg_temp[item].indexOf('wo') >= 0){
                        pg_temp[item] = pg_temp[item].replace('woman','女性');
                    }
                }
                people_group = pg_temp;
            }
            //确定3D_EC 坐标系与数据
            var g_cg =area_group;
            var p_cg = people_group;
            var data = xyz_data;
            ec_3d_v_pm_option = {
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
                        color: ['#CD3333','#FF3030', '#FF7F50', '#FFB90F','#FFF68F','#FFFF00']
                    }
                },
                xAxis3D: {
                    type: 'category',
                    data: g_cg
                },
                yAxis3D: {
                    type: 'category',
                    data: p_cg
                },
                zAxis3D: {
                    type: 'value'
                },
                grid3D: {
                    boxWidth: 200,
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
            ec_3d_v_pm.setOption(ec_3d_v_pm_option);

        }
    });
}

//找到包含value的值 在arr数组中的下标
function arrIndex(arr, value) {
    var i = arr.length;
    while (i--) {
        if (arr[i] === value) {
            return i;
        }
    }
    return false;
}