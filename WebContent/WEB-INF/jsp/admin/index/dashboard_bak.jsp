<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="admin/index">首页</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="javascript:void(0);">仪表盘</a></li>
</ul>

<div class="row-fluid  ">
	<div class="span12">
		<div id="chart_yb" style="height: 300px;"></div>
	</div>
</div>
<div class="row-fluid">
	<div class="span12 widget">
		<div id="wdchart" style="height: 320px;"></div>
	</div>
</div>
<div class="row-fluid">
	<div class="span12 widget">
		<div id="sdchart" style="height: 320px;"></div>
	</div>
</div>

<script type="text/javascript">
	var rTimes = [];
	var lastId;
	var dxs = [];
	$.ajax({
		type : "POST",
		url : "admin/environment/getData",
		data : "len=20",
		success : function(data) {
			rTimes = data.rTimes;
			lastId = data.lastId;
			dxs = data;
			renderChart();
		}
	});
	function renderChart() {
		/**仪表盘开始**/
		var chart_yb=echarts.init(document.getElementById('chart_yb'));
		chart_yb_option={
			tooltip : {
		        formatter: "{b} <br/>{c} {a}"
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    series : [
				{
					center : ['20%', '50%'],
					axisLine : {
						show : true,
						lineStyle : {
							color : [ [ 0.3, '#48b' ], [ 0.6, '#228b22' ],
									[ 1, '#ff4500' ] ],
							width : 15
						}
					},
					min : -50,
					max : 100,
					name : '°C',
					type : 'gauge',
					detail : {
						formatter : '{value} °C'
					},
					data : [ {
						value : dxs.d1s[dxs.d1s.length - 1],
						name : '当前温度'
					} ]
				},{
					center : ['40%', '50%'],
					axisLine : {
						show : true,
						lineStyle : {
							color : [ [ 0.3, '#48b' ], [ 0.8, '#228b22' ],
									[ 1, '#ff4500' ] ],
							width : 15
						}
					},
					min : 0,
					max : 100,
					name : '%',
					type : 'gauge',
					detail : {
						formatter : '{value} %'
					},
					data : [ {
						value : dxs.d2s[dxs.d2s.length - 1],
						name : '当前湿度'
					} ]
				},{
					center : ['60%', '50%'],
					axisLine : {
						show : true,
						lineStyle : {
							color : [ [ 0.25, '#48b' ], [ 0.5, '#228b22' ],
									[ 0.75, '#ff4500' ],[ 1, '#aa4500' ] ],
							width : 12
						}
					},
					axisLabel : {
		                formatter: function(v){
		                	switch (v+''){
		                        case '0': return '北';
		                        case '90': return '东';
		                        case '180': return '南';
		                        case '270': return '西';
		                        case '360':
		                        default: return '';
		                    }
		                }
		            },
		            pointer:{
		            	length : '80%',
		                width : 12,
		                color : 'auto'
		            },
					splitNumber:4,
					startAngle:90,
					endAngle:-270,
					min : 0,
					max : 360,
					name : '°',
					type : 'gauge',
					detail : {
						formatter : ' '
					},
					data : [ {
						value : dxs.d3s[dxs.d3s.length - 1],
						name : '当前风向'
					} ]
				},{
					center : ['80%', '50%'],
					axisLine : {
						show : true,
						lineStyle : {
							color : [ [ 0.25, '#228b22' ], [ 0.5, '#DDB822' ],
									[ 1, '#ff4500' ] ],
							width : 12
						}
					},
					axisTick:{
						splitNumber: 4
					},
					min : 0,
					max : 40,
					name : 'M/S',
					type : 'gauge',
					detail : {
						formatter : '{value} M/S'
					},
					data : [ {
						value : dxs.d4s[dxs.d4s.length - 1],
						name : '当前风速'
					} ]
				}
				
		    ]
		}
		chart_yb.setOption(chart_yb_option);
		/**仪表盘结束**/
		
		/**温度折线图开始**/
		var wdchart = echarts.init(document.getElementById('wdchart'));
		var wdchart_option = {
			title : {
				text : '当前温度变化',
				subtext : '每分钟采集一次'
			},
			tooltip : {
				formatter: "{b} <br/>{c} °C"
			},
			legend : {
				data : [ '检测站1' ]
			},
			toolbox : {
				show : true,
				feature : {
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					},
					restore : {show: true},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				boundaryGap : true,
				data : rTimes
			} ],
			yAxis : [ {
				type : 'value',
				axisLabel : {
					formatter : '{value} °C'
				}
			} ],
			series : [ {
				name : '检测站1',
				type : 'line',
				data : dxs.d1s
			} ]
		};
		wdchart.setOption(wdchart_option);
		/**温度折线图结束**/
		
		/**湿度折线图开始**/
		var sdchart = echarts.init(document.getElementById('sdchart'));
		var sdchart_option = {
			title : {
				text : '当前湿度变化',
				subtext : '每分钟采集一次'
			},
			tooltip : {
				formatter: "{b} <br/>{c} %"
			},
			legend : {
				data : [ '检测站1' ]
			},
			toolbox : {
				show : true,
				feature : {
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					},
					restore : {show: true},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				boundaryGap : true,
				data : rTimes
			} ],
			yAxis : [ {
				type : 'value',
				axisLabel : {
					formatter : '{value} %'
				}
			} ],
			series : [ {
				name : '检测站1',
				type : 'bar',
				data : dxs.d2s
			} ]
		};
		sdchart.setOption(sdchart_option);
		/**湿度折线图结束**/

		if (typeof (timeTicket) != 'undefined') {
			clearInterval(timeTicket);
		}
		timeTicket = setInterval(function() {
			$.post('admin/environment/getData',{'lastId' : lastId},function(data) {
				if (data) {
					lastId = data.lastId;
					rTimes = data.rTimes;
					dxs = data;
					if (dxs.d1s && dxs.d1s.length > 0) {
						wdchart.addData([ 
	                      [ 
	                        0, // 系列索引
							dxs.d1s, // 新增数据
							false, // 新增数据是否从队列头部插入
							false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
							rTimes // 坐标轴标签
						  ] 
						]);
						chart_yb_option.series[0].data[0].value = dxs.d1s[dxs.d1s.length - 1];
					}

					if (dxs.d2s && dxs.d2s.length > 0) {
						sdchart.addData([ 
		                  [ 
		                    0, // 系列索引
							dxs.d2s, // 新增数据
							false, // 新增数据是否从队列头部插入
							false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
							rTimes // 坐标轴标签
						  ] 
		                ]);
						chart_yb_option.series[1].data[0].value = dxs.d2s[dxs.d2s.length - 1];
					}
					if (dxs.d3s && dxs.d3s.length > 0) {
						chart_yb_option.series[2].data[0].value = dxs.d3s[dxs.d3s.length - 1];
					}
					if (dxs.d4s && dxs.d4s.length > 0) {
						chart_yb_option.series[3].data[0].value = dxs.d4s[dxs.d4s.length - 1];
					}
					chart_yb.setOption(chart_yb_option,true);
				}
			});
			// 动态数据接口 addData
		}, 30000);
	}
</script>