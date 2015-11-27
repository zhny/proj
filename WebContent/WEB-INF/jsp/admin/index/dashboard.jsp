<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="admin/index">首页</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="javascript:void(0);">仪表盘</a></li>
</ul>

<div class="row-fluid  ">
	<div class="span2">
		<div id="wdchart_yb" style="height: 300px;"></div>
	</div>
	<div class="span2">
		<div id="sdchart_yb" style="height: 300px;"></div>
	</div>
	<div class="span2">
		<div id="fxchart_yb" style="height: 300px;"></div>
	</div>
	<div class="span2">
		<div id="fschart_yb" style="height: 300px;"></div>
	</div>
	<div class="span2">
		<div id="qychart_yb" style="height: 300px;"></div>
	</div>
	<div class="span2">
		<div id="phpmchart_yb" style="height: 300px;"></div>
	</div>
</div>
<div class="row-fluid  ">
	<div class="span3">
	</div>
	<div class="span3">
	</div>
	<div class="span3">
	</div>
	<div class="span3">
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
		/**温度折线图开始**/
		var wdchart = echarts.init(document.getElementById('wdchart'));
		var wdchart_option = {
			title : {
				text : '当前温度变化',
				x: 'center',
				subtext : '每分钟采集一次'
			},
			tooltip : {
				trigger : 'axis'
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
				x: 'center',
				subtext : '每分钟采集一次'
			},
			tooltip : {
				trigger : 'axis'
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
		
		
		/**温度仪表盘开始**/
		var wdchart_yb = echarts.init(document.getElementById('wdchart_yb'));
		wdchart_yb_option = {
			tooltip : {
				formatter : "{a} <br/>{c} °C"
			},
			series : [ {
				axisLine : {
					show : true,
					lineStyle : {
						color : [ [ 0.3, '#48b' ], [ 0.6, '#228b22' ],
								[ 1, '#ff4500' ] ],
						width : 12
					}
				},
				radius:[0, '100%'],
				min : -50,
				max : 100,
				name : '当前温度',
				type : 'gauge',
				detail : {
					formatter : '{value} °C'
				},
				data : [ {
					value : dxs.d1s[dxs.d1s.length - 1],
					name : '当前温度'
				} ]
			} ]
		};
		wdchart_yb.setOption(wdchart_yb_option);
		/**温度仪表盘结束**/

		/**湿度仪表盘开始**/
		var sdchart_yb = echarts.init(document.getElementById('sdchart_yb'));
		sdchart_yb_option = {
			tooltip : {
				formatter : "{a} <br/>{c} %"
			},
			series : [ {
				axisLine : {
					show : true,
					lineStyle : {
						color : [ [ 0.3, '#48b' ], [ 0.8, '#228b22' ],
								[ 1, '#ff4500' ] ],
						width : 12
					}
				},
				radius:[0, '100%'],
				min : 0,
				max : 100,
				name : '当前湿度',
				type : 'gauge',
				detail : {
					formatter : '{value} %'
				},
				data : [ {
					value : dxs.d2s[dxs.d2s.length - 1],
					name : '当前湿度'
				} ]
			} ]
		};
		sdchart_yb.setOption(sdchart_yb_option);
		/**湿度仪表盘结束**/
		
		/**风向仪表盘开始**/
		var fxchart_yb = echarts.init(document.getElementById('fxchart_yb'));
		fxchart_yb_option = {
			tooltip : {
				formatter : "风向角度 <br/>{c}° "
			},
			series : [ {
				axisLine : {
					show : true,
					lineStyle : {
						color : [ [ 0.25, '#48b' ], [ 0.5, '#228b22' ],
								[ 0.75, '#ff4500' ],[ 1, '#aa4500' ] ],
						width : 12
					}
				},
				radius:[0, '100%'],
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
				name : '当前风向',
				type : 'gauge',
				detail : {
					formatter : ' '
				},
				data : [ {
					value : dxs.d3s[dxs.d3s.length - 1],
					name : '当前风向'
				} ]
			} ]
		};
		fxchart_yb.setOption(fxchart_yb_option);
		/**风向仪表盘结束**/
		
		/**风速仪表盘开始**/
		var fschart_yb = echarts.init(document.getElementById('fschart_yb'));
		fschart_yb_option = {
			tooltip : {
				formatter : "风速<br/>${value}m/s"
			},
			series : [ {
				axisLine : {
					show : true,
					lineStyle : {
						color : [ [ 0.25, '#228b22' ], [ 0.5, '#DDB822' ],
								[ 1, '#ff4500' ] ],
						width : 12
					}
				},
				radius:[0, '100%'],
				axisTick:{
					splitNumber: 4
				},
				min : 0,
				max : 40,
				name : 'm/s',
				type : 'gauge',
				detail : {
					formatter : '{value} m/s'
				},
				data : [ {
					value : dxs.d4s[dxs.d4s.length - 1],
					name : '当前风速'
				} ]
			} ]
		};
		fschart_yb.setOption(fschart_yb_option);
		/**风速仪表盘结束**/
		
		/**气压仪表盘开始**/
		var qychart_yb = echarts.init(document.getElementById('qychart_yb'));
		qychart_yb_option = {
			tooltip : {
				formatter : "{a} <br/>{c} hPa"
			},
			series : [ {
				axisLine : {
					show : true,
					lineStyle : {
						color : [ [ 0.3, '#48b' ], [ 0.6, '#228b22' ],
								[ 1, '#ff4500' ] ],
						width : 12
					}
				},
				radius:[0, '100%'],
				splitNumber:5,
				min : 0,
				max : 3000,
				name : '当前气压',
				type : 'gauge',
				detail : {
					formatter : '{value} hPa'
				},
				data : [ {
					value : dxs.d5s[dxs.d5s.length - 1],
					name : '当前气压'
				} ]
			} ]
		};
		qychart_yb.setOption(qychart_yb_option);
		/**气压仪表盘结束**/
		
		/**PM2.5及水PH值仪表盘开始**/
		var phpmchart_yb = echarts.init(document.getElementById('phpmchart_yb'));
		phpmchart_yb_option = {
			tooltip : {
				formatter : "{a} <br/>{c} "
			},
			series : [ 
				{
					axisLine : {
						show : true,
						lineStyle : {
							color : [ [ 0.07, '#00FF33' ], [ 0.15, '#66CC66' ],[ 0.23, '#6699CC' ], [ 0.3, '#6666CC' ],
									[ 0.5, '#663366' ],[1,'#330033'] ],
							width : 12
						}
					},
					axisLabel: {
		                formatter:function(v){
		                    switch (v + '') {
		                        case '250' : return 'PM2.5';
		                        default:return v;
		                    }
		                }
		            },
					pointer: {
		                width:2
		            },
					radius:[0, '100%'],
					min : 0,
					max : 500,
					splitNumber:2,
					startAngle:165,
		            endAngle:15,
					name : '当前PM2.5',
					type : 'gauge',
					detail : {
						show: false
					},
					data : [ {
						value : dxs.d13s[dxs.d13s.length - 1]
					} ]
				},{
					axisLine : {
						show : true,
						lineStyle : {
							color : [ 
								[ 0.14, '#952138' ], [ 0.28, '#952138' ],[ 0.43, '#DE9D01' ],
								[ 0.57, '#948508' ], [ 0.71, '#163A20' ],[ 0.86, '#221F32' ],[ 1, '#281028' ]
							],
							width : 12
						}
					},
					axisTick: {            // 坐标轴小标记
		                show: false
		            },
					axisLabel: {
		                formatter:function(v){
		                    switch (v + '') {
		                        case '6' : return 'H';
		                        case '8' : return 'P';
		                        default:return v;
		                    }
		                }
		            },
					pointer: {
		                width:2
		            },
					radius:[0, '100%'],
					min : 0,
					max : 14,
					splitNumber:7,
					startAngle:345,
		            endAngle:195,
					name : '当前水PH',
					type : 'gauge',
					detail : {
						show: false
					},
					data : [ {
						value : dxs.d14s[dxs.d14s.length - 1]
					} ]
				},
			]
		};
		phpmchart_yb.setOption(phpmchart_yb_option);
		/**PM2.5及水PH值仪表盘结束**/
		
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
						wdchart_yb_option.series[0].data[0].value = dxs.d1s[dxs.d1s.length - 1];
						wdchart_yb.setOption(wdchart_yb_option,true);
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
						sdchart_yb_option.series[0].data[0].value = dxs.d2s[dxs.d2s.length - 1];
						sdchart_yb.setOption(sdchart_yb_option,true);
					}
					if (dxs.d3s && dxs.d3s.length > 0) {
						fxchart_yb_option.series[0].data[0].value = dxs.d3s[dxs.d3s.length - 1];
						fxchart_yb.setOption(fxchart_yb_option,true);
					}
					if (dxs.d4s && dxs.d4s.length > 0) {
						fschart_yb_option.series[0].data[0].value = dxs.d4s[dxs.d4s.length - 1];
						fschart_yb.setOption(fschart_yb_option,true);
					}
					if (dxs.d5s && dxs.d5s.length > 0) {
						qychart_yb_option.series[0].data[0].value = dxs.d5s[dxs.d5s.length - 1];
						qychart_yb.setOption(qychart_yb_option,true);
					}
					
					if (dxs.d13s && dxs.d13s.length > 0) {
						phpmchart_yb_option.series[0].data[0].value = dxs.d13s[dxs.d14s.length - 1];
					}
					if (dxs.d14s && dxs.d14s.length > 0) {
						phpmchart_yb_option.series[1].data[0].value = dxs.d14s[dxs.d14s.length - 1];
					}
					phpmchart_yb.setOption(phpmchart_yb_option,true);
				}
			});
			// 动态数据接口 addData
		}, 2000);
	}
</script>