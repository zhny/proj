function renderChart(rTimeStart,rTimeEnd,dxy){
	var _chart = echarts.init(document.getElementById('_chart'));
	var _chart_option = {
	    title : {
	        text: '风向变化',
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
	            return data + '<br/>风向角度:'+ params.value[1]+"°"; 
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
	            saveAsImage : {show: true}
	        }
	    },
	    xAxis : [
	        {
	            type : 'time',
	            scale:true
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            scale:true,
	            min:0,
	            max:360,
	            axisLabel : {
	                formatter: function(value){
	                	if((value>=0&&value<90)||value==360){
	                		return "北";
	                	}else if(value>=90&&value<180){
	                		return "东";
	                	}else if(value>=180&&value<270){
	                		return "南";
	                	}else{
	                		return "西";
	                	}
	                }
	            },
	            splitNumber:4,
	        	splitArea:{
	        		show:true  
	        	} 
	        }
	    ],
	    series : [
	        {
	            name:'监测站1',
	            type:'scatter',
	            showAllSymbol: true,
		        symbolSize:4,
	            data: dxy
	        }
	    ]
	};
		                    
	_chart.setOption(_chart_option);
}