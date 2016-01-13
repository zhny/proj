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
	<div class="box span12">
		<div class="box-header" data-original-title>
			<h2><i class="halflings-icon user"></i><span class="break"></span>气象环境</h2>
			<div class="box-icon">
				<a  href="admin/environment/export" data-title="导出数据"  class="btn-minimize"><i class="halflings-icon download-alt"></i>导出数据</a>
			</div>
		</div>
		<div class="box-content">
			<form class="form-search form-inline" id="search-form">
			  <label>采集时间段：</label>
		      <input type="text" class="input-medium search-query" name="rTimeStart" value="${param.rTimeStart}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">~<input type="text" class="input-medium search-query" name="rTimeEnd" value="${param.rTimeEnd}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
		      <label>数据项：</label>
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
		      <button type="button" class="btn" id="search-btn">查询</button>
		    </form>
		</div>
		<div class="box-content">
	      <table id="grid-data" class="table table-condensed table-hover table-striped table-bordered" >
	         <thead>
	             <tr>
	                 <th data-column-id="rTime" data-width="140">采集时间</th>
	                 <th data-column-id="station_sName" data-formatter="station_sName"  data-width="100">站点</th>
	                 <th data-column-id="d1">温度</th>
	                 <th data-column-id="d2">湿度</th>
	                 <th data-column-id="d3">风向</th>
	                 <th data-column-id="d4">风速</th>
	                 <th data-column-id="d5">气压</th>
	                 <th data-column-id="d6" data-width="70">总辐射</th>
	                 <th data-column-id="d7">雨量</th>
	                 <th data-column-id="d8" data-width="75">土壤温度</th>
	                 <th data-column-id="d12" data-width="75">日照时数</th>
	                 <th data-column-id="d13" data-width="75">PM2.5</th>
	                 <th data-column-id="d14" data-width="75">水PH值</th>
	             </tr>
	         </thead>
	      </table>
		</div>
	</div>
</div>
<script type="text/javascript">
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
$('#search-btn').click(function(){
	 var param= $('#search-form').serializeObject();   
     param.colName=$('#search-form').find('[name="col"]').find("option:selected").text();
	$('#content').load('admin/environment',param);
});
$('th[data-column-id="d1"]').click(function(){
	var modal = $.scojs_modal({width:"800",title:"温度变化",remote:'admin/environment/view/d1?isXy=true&'+$('#search-form').serialize()});
	modal.show();
});
$('th[data-column-id="d2"]').click(function(){
	var modal = $.scojs_modal({width:"800",title:"湿度变化",remote:'admin/environment/view/d2?isXy=true&'+$('#search-form').serialize()});
	modal.show();
});
$('th[data-column-id="d3"]').click(function(){
	var modal = $.scojs_modal({width:"800",title:"风向变化",remote:'admin/environment/view/d3?isXy=true&'+$('#search-form').serialize()});
	modal.show();
});
$('th[data-column-id="d4"]').click(function(){
	var modal = $.scojs_modal({width:"800",title:"风速变化",remote:'admin/environment/view/d4?isXy=true&'+$('#search-form').serialize()});
	modal.show();
});
$('th[data-column-id="d5"]').click(function(){
	var modal = $.scojs_modal({width:"800",title:"气压变化",remote:'admin/environment/view/d5?isXy=true&'+$('#search-form').serialize()});
	modal.show();
});
$('th[data-column-id="d6"]').click(function(){
	var modal = $.scojs_modal({width:"800",title:"总辐射变化",remote:'admin/environment/view/d6?'+$('#search-form').serialize()});
	modal.show();
});
$('th[data-column-id="d7"]').click(function(){
	var modal = $.scojs_modal({width:"800",title:"降雨量变化",remote:'admin/environment/view/d7?'+$('#search-form').serialize()});
	modal.show();
});
$('th[data-column-id="d8"]').click(function(){
	var modal = $.scojs_modal({width:"800",title:"土壤变化",remote:'admin/environment/view/d8?isXy=true&'+$('#search-form').serialize()});
	modal.show();
});
$('th[data-column-id="d12"]').click(function(){
	var modal = $.scojs_modal({width:"800",title:"日照时数变化",remote:'admin/environment/view/d12?'+$('#search-form').serialize()});
	modal.show();
});
$('th[data-column-id="d13"]').click(function(){
	var modal = $.scojs_modal({width:"800",title:"PM2.5变化",remote:'admin/environment/view/d13?isXy=true&'+$('#search-form').serialize()});
	modal.show();
});
$('th[data-column-id="d14"]').click(function(){
	var modal = $.scojs_modal({width:"800",title:"PH变化",remote:'admin/environment/view/d14?isXy=true&'+$('#search-form').serialize()});
	modal.show();
});
</script>