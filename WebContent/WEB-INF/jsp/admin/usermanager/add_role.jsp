<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-body valibox" id="modal-body" style="width:auto;height:auto;max-height:100%">
	<form class="form-horizontal ">
		<fieldset>
		  <div class="control-group">
			<label class="control-label">角色名称</label>
			<div class="controls">
			  <input type="hidden" name="id" value="${role.id }" /> 
			  <input id="focusedInput" class="input-xlarge focused" type="text" name="name" value="${role.name }" datatype="*">
			</div>
		  </div>
		  <div class="control-group">
			<label class="control-label">角色描述</label>
			<div class="controls">
				<textarea rows="3" cols="200"  maxlength="2000" name="description">${role.description}</textarea>
			</div>
		  </div>
		  <div class="control-group">
			<label class="control-label">选择权限</label>
			<div class="controls">
				<ul id="pmtree"></ul>  
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
				var checkeds = $('#pmtree').tree('getChecked',['checked']);
				var partCheckeds = $('#pmtree').tree('getChecked',['indeterminate']);
				var ids='';
				for(var i in checkeds){
					if(checkeds[i].id){
						ids+=checkeds[i].id+";";
					}
				}
				for(var i in partCheckeds){
					if(partCheckeds[i].id){
						ids+=partCheckeds[i].id+";";
					}
				}
				$.ajax({
				   type: "POST",
				   url: "admin/role/create",
				   data: {
				   		"id":$('.valibox').find('input[name="id"]').val(),
				   		"name":$('.valibox').find('input[name="name"]').val(),
				   		"description":$('.valibox').find('[name="description"]').val(),
				   		"pids":ids
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
						var options = {"text":data.msg,"layout":"bottom","type":"alert","closeButton":"true"};
						noty(options);
					}
				});
			}
		});
	});
	
	$('#pmtree').tree({
		method:'get',
		animate:true,
		checkbox:true
	});
	
	$.get('admin/role/rptree',{"roleId":'${role.id}'},function(data){
		$('#pmtree').tree('loadData', data);
	});
</script>