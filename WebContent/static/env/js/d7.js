function renderChart(rTimeStart,rTimeEnd,rTimes,dxs){
	var _chart = echarts.init(document.getElementById('_chart'));
	var _chart_option = {
		    title : {
		        text: '每日累积雨量图',
		        x: 'center',
		        subtext: rTimeStart+' ~ '+rTimeEnd
		    },
		    tooltip : {
		    	trigger: 'axis',
		    	formatter: function(params) {
		                return params[0].name + '<br/>'+ params[0].seriesName + ' : ' + -params[0].value + ' (mm)';
		        }
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    dataZoom : {
		        show : true,
		        realtime : true,
		        start : 0,
		        end : 100
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            axisLine: {onZero: false},
		            data : rTimes
		        }
		    ],
		    yAxis : [
		        {
		            name : '当日累积降雨量(mm)',
		            type : 'value',
		            axisLabel : {
		                formatter: function(v){
		                    return - v;
		                }
		            }
		        }
		    ],
		    series : [
		        {
		            name:'当日累积降雨量',
		            type:'line',
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		            data: (function(){
		                var oriData = dxs;
		                var len = oriData.length;
		                while(len--) {
		                    oriData[len] *= -1;
		                }
		                return oriData;
		            })()
		        }
		    ]
		};
	_chart.setOption(_chart_option);
}