<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-body valibox" id="modal-body" style="width:auto;height:auto;max-height:100%">
	<div class="row-fluid  ">
		<div class="span12">
			<div id="_chart" style="height:320px">
			</div>
		</div>
	</div>	
</div>
<div class="modal-footer">
	<a href="#" class="btn" data-dismiss="modal">关闭</a>
</div>
<script src="static/env/js/${dx}.js"></script>
<script type="text/javascript">
$.ajax({
    type: "POST",
    url: "admin/environment/getData/${dx}?${query}",
    success: function(data){
    	if(!data.rTimes){
    		renderChart('${rTimeStart}','${rTimeEnd}',data);
    	}else{
    		rTimes=data.rTimes;
    		dxs=data.dxs;
    		renderChart('${rTimeStart}','${rTimeEnd}',rTimes,dxs);
    	}
   }
});
</script>