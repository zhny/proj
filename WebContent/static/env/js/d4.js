function renderChart(rTimeStart,rTimeEnd,dxy){
	var _chart = echarts.init(document.getElementById('_chart'));
	var _chart_option = {
	   title : {
	       text: '风速变化',
	       x: 'center',
	       subtext: rTimeStart+' ~ '+rTimeEnd
	   },
	   tooltip : {
	        trigger: 'item',
	        formatter : function (params) {
	            var date = new Date(params.value[0]);
	            data = date.getFullYear() + '-'
	                   + (date.getMonth() + 1) + '-'
	                   + date.getDate() + ' '
	                   + date.getHours() + ':'
	                   + date.getMinutes();
	            return data + '<br/>风速:'+ params.value[1] + 'M/S'; 
	        }
	   },
	   dataZoom: {
	        show: true
	   },
	   grid: {
	        y2: 80
	   },
	   toolbox: {
	       show : true,
	       feature : {
	    	   dataZoom : {show: true},
	           restore : {show: true},
	           magicType : {show: true, type: ['line', 'bar']},
	           saveAsImage : {show: true}
	       }
	   },
	   calculable : true,
	   xAxis : [
	       {
	           type : 'time'
	       }
	   ],
	   yAxis : [
	       {
	           type : 'value',
	           axisLabel : {
	               formatter: '{value} M/S'
	           }
	       }
	   ],
	   series : [
	       {
	           name:'检测站1',
	           type:'line',
	           showAllSymbol: true,
	           data: dxy
	       }
	   ]
	};
	_chart.setOption(_chart_option);
}