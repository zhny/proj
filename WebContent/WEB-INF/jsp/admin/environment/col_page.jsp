<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="breadcrumb">
	<li>
		<i class="icon-home"></i>
		<a href="admin/index">首页</a> 
		<i class="icon-angle-right"></i>
	</li>
	<li><a href="javascript:void(0);">气象环境</a></li>
</ul>

<div class="row-fluid sortable">
	<div class="box span6">
		<div class="box-header" data-original-title>
			<h2><i class="halflings-icon list"></i><span class="break"></span>气象环境</h2>
			<div class="box-icon">
				<a data-href="admin/environment/export" href="javascript:void(0);" data-title="导出数据" id="export_data" class="btn-minimize"><i class="halflings-icon download-alt"></i>导出数据</a>
			</div>
		</div>
		<div class="box-content">
			<form class="form-horizontal form-search form-inline" id="search-form">
				<fieldset>
				  <div class="control-group">
					<label class="control-label">采集时间段：</label>
					<div class="controls">
					  <input type="text" class="" name="rTimeStart" value="${param.rTimeStart}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">~<input type="text" class="" name="rTimeEnd" value="${param.rTimeEnd}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
					</div>
				  </div>
				  <div class="control-group">
					<label class="control-label">数据项：</label>
					<div class="controls">
					  <select name="col">
				      	<option value="">全部</option>
				      	<option value="d1">温度</option>
				      	<option value="d2">湿度</option>
				      	<option value="d3">风向</option>
				      	<option value="d4">风速</option>
				      	<option value="d5">气压</option>
				      	<option value="d6">总辐射</option>
				      	<option value="d7">雨量</option>
				      	<option value="d8">土壤温度</option>
				      	<option value="d12">日照时数</option>
				      	<option value="d13">PM2.5</option>
				      	<option value="d14">水PH值</option>
				      </select>
					</div>
				  </div>
				  <div class="form-actions">
					 <button type="button" class="btn btn-primary" id="search-btn">查询</button>
					 <a class="btn data-link" href="javascript:void(0);" data-href="admin/environment">返回</a>
				  </div>
				</fieldset>
			</form>
		</div>
		<div class="box-content">
	      <table id="grid-data" class="table table-condensed table-hover table-striped table-bordered" >
	         <thead>
	             <tr>
	                 <th data-column-id="rTime" data-width="140">采集时间</th>
	                 <th data-column-id="station_sName" data-formatter="station_sName">站点</th>
	                 <th data-column-id="${param.col }">${param.colName}</th>
	             </tr>
	         </thead>
	      </table>
		</div>
	</div>
	<div class="box span6">
		<div class="box-header" data-original-title>
			<h2><i class="halflings-icon picture"></i><span class="break"></span>统计图表</h2>
			<div class="box-icon">
			</div>
		</div>
		<div class="box-content">
			<div id="_chart" style="height:420px">
			</div>
		</div>
	</div>
</div>
<script src="static/env/js/${param.col}.js"></script>
<script type="text/javascript">
var col='${param.col}';
$('#search-form').find('[name="col"]').val(col);

$('#export_data').click(function(){
	var href=$(this).attr("data-href")+"?"+$('#search-form').serialize();
	location.href=href;
});

var grid = $("#grid-data").bootgrid({
	sorting:false,
	navigation:2,
    ajax: true,
    url: "admin/environment/page",
    post: function ()
    {
    	var param= $('#search-form').serializeObject();   
        param.colName=$('#search-form').find('[name="col"]').find("option:selected").text();
        return param;  
    },
    formatters: {
    	"station_sName":function(column,row){
    		return row.station.sName;
    	},
    }
});
var notUseXy=['d6','d7','d12'];
var isXy=!($.inArray(col, notUseXy)>=0);
var param= $('#search-form').serialize();
param+='&isXy='+isXy;
var rTimeStart='${param.rTimeStart}';
var rTimeEnd='${param.rTimeEnd}'

var query=''
$.ajax({
    type: "POST",
    url: "admin/environment/getData/${param.col}?"+param,
    success: function(data){
    	if(!data.rTimes){
    		renderChart(rTimeStart,rTimeEnd,data);
    	}else{
    		rTimes=data.rTimes;
    		dxs=data.dxs;
    		renderChart(rTimeStart,rTimeEnd,rTimes,dxs);
    	}
   }
});

$('#search-btn').click(function(){
	var param= $('#search-form').serializeObject();   
    param.colName=$('#search-form').find('[name="col"]').find("option:selected").text();
	$('#content').load('admin/environment',param);
});
</script>