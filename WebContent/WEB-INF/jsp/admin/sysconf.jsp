<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-body valibox" id="modal-body" style="width:auto;height:auto;max-height:100%">
	<form class="form-horizontal " id="sysconf_form">
		<fieldset>
		  <c:forEach var="i" items="${confs}" varStatus="x">
		  <div class="control-group">
			<label class="control-label">${i.name }</label>
			<div class="controls">
			  <input type="hidden" name="id" value="${i.id }" /> 
			  <input type="hidden" name="code" value="${i.code }" /> 
			  <input id="focusedInput" class="input-xlarge focused" type="text" name="value" value="${i.value }" datatype="*">
			</div>
		  </div>
		  </c:forEach>
		</fieldset>
	</form>
</div>
<div class="modal-footer">
	<a href="#" class="btn" data-dismiss="modal">关闭</a>
	<a href="javascript:void(0);" class="btn btn-primary" id="save_btn">保存</a>
</div>

<script type="text/javascript">
	
	$.Tipmsg.r = "";
	var validator = $(".valibox").Validform({
		tiptype : 4
	});
	$(function() {
		$('#save_btn').click(function() {
			var postData=[];
			$('#sysconf_form').find('.controls').each(function(){
				var id=$(this).find('input[name="id"]').val();
				var code=$(this).find('input[name="code"]').val();
				var value=$(this).find('input[name="value"]').val();
				postData.push({"id":id,"code":code,"value":value});
			});
			if (validator.check()) {
				$.ajax({
				   type: "POST",
				   url: "admin/sysconf/update",
				   contentType : 'application/json;charset=utf-8',
				   data: JSON.stringify(postData),
				   success : function(data) {
						if (data.code == 0) {
							noty({"text":"保存成功","layout":"bottom","type":"alert","closeButton":"true"});
							$('.modal').trigger('close');
							$("#grid-data").bootgrid('reload');
						} else {
							noty({"text":data.msg,"layout":"bottom","type":"alert","closeButton":"true"});
						}
					},
					error : function(data) {
						noty({"text":data.msg,"layout":"bottom","type":"alert","closeButton":"true"});
					}
				});
			}
		});
	});
</script>