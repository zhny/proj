function renderChart(rTimeStart,rTimeEnd,dxy){
	var _chart = echarts.init(document.getElementById('_chart'));
	var _chart_option = {
	   title : {
	       text: '水PH变化',
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
	            return data + '<br/>PH : '+ params.value[1] + ''; 
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
	    	   splitNumber:14,
	    	   min:0,
	    	   max:14,
	           type : 'value',
	           axisLabel : {
	               formatter: '{value}'
	           }
	       }
	   ],
	   series : [
	       {
	           name:'检测站1',
	           type:'bar',
	           showAllSymbol: true,
	           data: dxy
	       }
	   ]
	};
	_chart.setOption(_chart_option);
}