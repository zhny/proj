<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-body valibox" id="modal-body" style="width:auto;height:auto;max-height:100%">
	<form class="form-horizontal ">
		<fieldset>
		  <div class="control-group">
			<label class="control-label">账户名</label>
			<div class="controls">
			  <input type="hidden" name="id" value="${user.id }" /> 
			  <c:if test="${user.phone==null}">
				<input id="focusedInput" class="input-xlarge focused" type="text" name="phone" value="${user.phone }" datatype="*6-15" errormsg="帐号字符在6~15位之间！" ajaxurl="admin/validname">
			  </c:if> 
			  <c:if test="${user.phone!=null}">
				<span class="input-xlarge uneditable-input">${user.phone}</span>
			  </c:if>
			</div>
		  </div>
		  
		  <c:if test="${user.password==null}">
		  <div class="control-group">
			<label class="control-label">密码</label>
			<div class="controls">
				<input id="focusedInput" class="input-xlarge focused" type="password" name="password"  datatype="*6-15" errormsg="密码范围在6~15位之间！" >
			</div>
		  </div>
		  <div class="control-group">
			<label class="control-label">确认密码</label>
			<div class="controls">
				<input id="focusedInput" class="input-xlarge focused" type="password" name="surePassword" datatype="*" recheck="password" errormsg="您两次输入的账号密码不一致！">
			</div>
		  </div>
		  </c:if>	
		  	  
		  <div class="control-group">
			<label class="control-label">选择角色</label>
			<div class="controls">
			  <c:forEach items="${rtree }" var="i">
				  <label class="checkbox inline">
					<input type="checkbox" name="roles" value="${i.id}" <c:if test="${i.checked==true}">checked="checked"</c:if> >${i.text}
				  </label>
			  </c:forEach>
			</div>
		  </div>
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
			if (validator.check()) {
				var checkeds = $('.valibox').find('input:checked');
				var ids = '';
				checkeds.each(function() {
					ids += $(this).val() + ";";
				});
				$.ajax({
					type : "POST",
					url : "admin/usermanager/create",
					data : {
						"id" : $('.valibox').find('input[name="id"]').val(),
						"phone" : $('.valibox').find('input[name="phone"]').val(),
						"password" : $('.valibox').find('input[name="password"]').val(),
						"surePassword" : $('.valibox').find('[name="surePassword"]').val(),
						"rids" : ids
					},
					success : function(data) {
						if (data.code == 0) {
							var options = {"text":"保存成功","layout":"bottom","type":"alert","closeButton":"true"};
							noty(options);
							$('.modal').trigger('close');
							$("#grid-data").bootgrid('reload');
						} else {
							var options = {"text":data.msg,"layout":"bottom","type":"alert","closeButton":"true"};
							noty(options);
						}
					},
					error : function() {
						var options = {"text":"处理失败,请重试","layout":"bottom","type":"alert","closeButton":"true"};
						noty(options);
					}
				});
			}
		});
	});
</script>