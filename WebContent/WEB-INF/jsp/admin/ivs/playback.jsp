<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="admin/index">首页</a>
        <i class="icon-angle-right"></i>
    </li>
    <li><a href="javascript:void(0);">视频回放</a></li>
</ul>
<div class="row-fluid sortable">
    <div class="box span3">
        <div class="box-header" data-original-title>
            <h2><i class="halflings-icon list"></i><span class="break"></span>监控点</h2>
            <div class="box-icon"></div>
        </div>
        <div class="box-content">
            <ul class="dashboard-list metro" id="cameras">
			</ul>
        </div>
        
        <div class="box-header" data-original-title>
            <h2><i class="halflings-icon list"></i><span class="break"></span>时间</h2>
            <div class="box-icon"></div>
        </div>
        <div class="box-content">
        	<div id="timepick"></div>
        	<div class="input-prepend input-append">
				<span class="add-on">从</span><input type="text" class="Wdate" name="timeStart" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
		    </div>
		    <div class="input-prepend input-append">
				<span class="add-on">到</span><input type="text" class="Wdate" name="timeEnd" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
		    </div>
		    <div>
			<button class="btn btn-primary" type="button" id="searchVideo">搜索录像</button>
			</div>
        </div>
    </div>
    <div class="box span9" >
        <div class="box-header" data-original-title>
            <h2><i class="halflings-icon user"></i><span class="break"></span>视频</h2>
            <div class="box-icon"></div>
        </div>
        <div class="box-content" id="ocxdiv">
        	
        </div>
    </div>
</div>
<script type="text/javascript">
var now=new Date();
var now_formart=now.format('yyyy-MM-dd');
$('input[name="timeEnd"]').val(now_formart+" 23:59:59");
$('input[name="timeStart"]').val(now_formart+" 00:00:00");
WdatePicker({eCont:'timepick',onpicked:function(dp){
	var timeStr=dp.cal.getDateStr('yyyy-MM-dd');
	$('input[name="timeEnd"]').val(timeStr+" 23:59:59");
	$('input[name="timeStart"]').val(timeStr+" 00:00:00");
}});
var ocxHeight=$(window).height()-200;
$('#ocxdiv').append('<object classid="clsid:61978326-6772-4595-9EC3-D23C5CD5E61F" id="ocxObject" width="100%" height="'+ocxHeight+'" name="ocx"></object>');
var ocx=document.getElementById('ocxObject');
$(function(){
	$.get('admin/ivs/param/allCamera',function(data){
		if(data){
			var cameras_ui=$('#cameras');
			for(var i=0;i<data.length;i++){
				var li='<li>';
				li+='<a href="javascript:void(0);">';
				li+='<i class="icon-facetime-video"></i>';
				li+='<strong>'+data[i].name+'</strong>';
				li+='</a>';	
				li+='<span class="todo-actions camera-play" data-indexCode="'+data[i].indexCode+'"><a href="javascript:void(0);"><i class="icon-play"></i></a></span>';
				li+='</li>';
				var li=$(li);
				$(li).find('.icon-play').click(SetPlayBackParam);
				cameras_ui.append(li);
			}
		}
	});
	
	
	function SetPlayBackParam(){
		var indexCode=$(this).parent().parent().attr('data-indexCode');
		var wndIndex=ocx.GetFocusWndIndex();
		$.get('admin/ivs/param/getPlaybackParam?indexCode='+indexCode+'&playWndIndex='+wndIndex,function(data){
			var ret=ocx.SetPlayBackParam(data);
			if(ret==0){
				
			}else{
				noty({"text":"搜索失败,"+ret,"layout":"top","type":"alert","closeButton":"true"});
			}
		});
	}
	
	$('#searchVideo').click(function(){
	      var startTime = $('input[name="timeStart"]').val();
	      var endTime = $('input[name="timeEnd"]').val();
	      if(startTime==''||endTime==''){
			noty({"text":"请选择录像时间段","layout":"top","type":"alert","closeButton":"true"});
	      }
	      var recType = 23;
	      var strXML = "<?xml version='1.0'?><Message><Time><Begin>"+startTime+"</Begin><End>"+endTime+"</End></Time><RecordType>"+recType+"</RecordType></Message>";
	      ocx.StartQueryRecord(strXML);
	});
});


</script>