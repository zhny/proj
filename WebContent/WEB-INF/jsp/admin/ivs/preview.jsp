<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="admin/index">首页</a>
        <i class="icon-angle-right"></i>
    </li>
    <li><a href="javascript:void(0);">视频预览</a></li>
</ul>
<div class="row-fluid sortable">
    <div class="box span2">
        <div class="box-header" data-original-title>
            <h2><i class="halflings-icon list"></i><span class="break"></span>监控点</h2>
            <div class="box-icon"></div>
        </div>
        <div class="box-content">
            <div>
           		 <ul class="dashboard-list metro" id="cameras">
					
				</ul>
            </div>
        </div>
    </div>
    <div class="box span8" >
        <div class="box-header" data-original-title>
            <h2><i class="halflings-icon user"></i><span class="break"></span>预览</h2>
            <div class="box-icon"></div>
        </div>
        <div class="box-content" id="ocxdiv">
        	
        </div>
    </div>
    <div class="box span2" style="width:160px;display:block;">
        <div class="box-header" data-original-title>
            <h2><i class="halflings-icon list"></i><span class="break"></span>云台控制</h2>
            <div class="box-icon"></div>
        </div>
        <div class="box-content" style="padding: 2px;">
            <div>
                <a href="javascript:void(0);"><i class="glyphicons-icon left_arrow ocx-ctrl" data-type="25"></i></a>
            	<a href="javascript:void(0);"><i class="glyphicons-icon up_arrow ocx-ctrl" data-type="21"></i></a>
            	<a href="javascript:void(0);"><i class="glyphicons-icon right_arrow ocx-ctrl" data-type="26"></i></a>
            </div>
            <div>
            	<a href="javascript:void(0);"><i class="glyphicons-icon left_arrow ocx-ctrl" data-type="23"></i></a>
            	<a href="javascript:void(0);"><i class="glyphicons-icon retweet_2 ocx-ctrl" data-type="29"></i></a>
            	<a href="javascript:void(0);"><i class="glyphicons-icon right_arrow ocx-ctrl" data-type="24"></i></a>
            </div>
            <div>
            	<a href="javascript:void(0);"><i class="glyphicons-icon left_arrow ocx-ctrl" data-type="27"></i></a>
            	<a href="javascript:void(0);"><i class="glyphicons-icon down_arrow ocx-ctrl" data-type="22"></i></a>
            	<a href="javascript:void(0);"><i class="glyphicons-icon right_arrow ocx-ctrl" data-type="28"></i></a>
            </div>
            <div>
            	<a href="javascript:void(0);" title="焦距变大"><i class="glyphicons-icon zoom_in ocx-ctrl" data-type="11"></i></a>
            	<a href="javascript:void(0);" title="焦距变小"><i class="glyphicons-icon zoom_out ocx-ctrl" data-type="12"></i></a>
            </div>
            <div>
            	<a href="javascript:void(0);" title="焦点前调"><i class="glyphicons-icon show_big_thumbnails ocx-ctrl" data-type="13"></i></a>
            	<a href="javascript:void(0);" title="焦点后调"><i class="glyphicons-icon show_thumbnails ocx-ctrl" data-type="14"></i></a>
            </div>
            <div>
            	<a href="javascript:void(0);" title="光圈变大"><i class="glyphicons-icon circle_plus ocx-ctrl" data-type="15"></i></a>
            	<a href="javascript:void(0);" title="光圈变小"><i class="glyphicons-icon circle_minus ocx-ctrl" data-type="16"></i></a>
            </div>
            <div class="slider sliderMin green" title="云台速度"></div>
			<div class="field_notice hide"><span class="must ocx-ctrl-speed">3</span></div>
        </div>
    </div>
</div>
<script type="text/javascript">
$(".sliderMin").slider({
	range: "min",
	value: 2,
	min: 0,
	max: 7,
	slide: function( event, ui ) {
		$( ".ocx-ctrl-speed" ).html(ui.value );
	}
});
var ocxHeight=$(window).height()-160;
$('#ocxdiv').append('<object classid="clsid:D5E14042-7BF6-4E24-8B01-2F453E8154D7" id="ocxObject" width="100%" height="'+ocxHeight+'" name="OcxObject"></object>');
var ocx=document.getElementById('ocxObject');
ocx.SetOcxMode(0);

var isTurn=false;			//正在旋转
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
				$(li).find('.icon-play').click(cameraPlay);
				cameras_ui.append(li);
			}
		}
	});
	
	$('.ocx-ctrl').click(function(){
		var speed=parseInt($(".ocx-ctrl-speed" ).html(),10);
		var type=parseInt($(this).attr('data-type'),10);
		if(type==29){
			ocx.StartTask_PTZ(!isTurn?type:(-type),speed);
			isTurn=!isTurn;
		}else{
			ocx.StartTask_PTZ(type,speed);
			setTimeout(function(){ocx.StartTask_PTZ(-type,speed);},500);
		}
	});
	
	function cameraPlay(){
		var indexCode=$(this).parent().parent().attr('data-indexCode');
		$.get('admin/ivs/param/prewviewParam?indexCode='+indexCode,function(data){
			var ret=ocx.StartTask_Preview_FreeWnd(data);
			if(ret==0){
				
			}else{
				noty({"text":"预览失败,"+ret,"layout":"top","type":"alert","closeButton":"true"});
			}
		});
	}
});


</script>