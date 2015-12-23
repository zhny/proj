<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="breadcrumb">
	<li>
		<i class="icon-home"></i>
		<a href="admin/index">首页</a> 
		<i class="icon-angle-right"></i>
	</li>
	<li><a href="javascript:void(0);">用户管理</a></li>
</ul>

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header" data-original-title>
			<h2><i class="halflings-icon user"></i><span class="break"></span>用户</h2>
			<div class="box-icon">
				<a data-trigger="modal" href="admin/usermanager/create/view" data-title="新增账户"  class="btn-minimize"><i class="halflings-icon plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			<form class="form-search form-inline" id="search-form">
			  <label>账户：</label>
		      <input type="text" class="input-medium search-query" name="phone">
		      <label>状态：</label>
		      <input type="text" class="input-medium search-query">
		      <button type="button" class="btn" id="search-btn">查询</button>
		    </form>
		</div>
		<div class="box-content">
	      <table id="grid-data" class="table table-condensed table-hover table-striped table-bordered" >
	         <thead>
	             <tr>
	                 <th data-column-id="phone">账户</th>
	                 <th data-column-id="roles" data-formatter="roles">角色</th>
	                 <th data-column-id="dateUpdate">更新时间</th>
	                 <th data-column-id="status" data-formatter="status">状态</th>
	                 <th data-column-id="commands" data-formatter="commands" data-sortable="false">操作</th>
	             </tr>
	         </thead>
	      </table>
		</div>
	</div>
</div>
<script type="text/javascript">
var grid = $("#grid-data").bootgrid({
	navigation:2,
    ajax: true,
    url: "admin/usermanager/page",
    post: function ()
    {
        return  $('#search-form').serializeObject();   
    },
    formatters: {
    	"roles":function(column,row){
    		var result="";
    		for(var i=0;i<row.roles.length;i++){
    			result+=row.roles[i].name+" ";
    		}
    		return result;
    	},
    	"status":function(column,row){
    		return row.status=="Y"?"有效":"冻结";
    	},
        "commands": function(column, row)
        {
            return "<button type=\"button\" class=\"btn btn-mini btn-primary command-edit\" data-row-id=\"" + row.id + "\"><i class=\"icon-edit\"></i></button> " + 
                "<button type=\"button\" class=\"btn btn-mini command-delete\" data-row-id=\"" + row.id + "\"><i class=\"icon-trash\"></i></button>";
        }
    }
}).on("loaded.rs.jquery.bootgrid", function()
{
    grid.find(".command-edit").on("click", function(e)
    {
    	var id=$(this).data('row-id');
    	var modal = $.scojs_modal({title:"修改账户",remote:'admin/usermanager/create/view?id='+id});
    	modal.show();
    	//$('#win-modal').load('admin/usermanager/create/view?id='+id);
    	//$('#win-modal').modal('show');
    }).end().find(".command-delete").on("click", function(e)
    {
    	var id=$(this).data('row-id');
        var confirm = $.scojs_confirm({
            content: "确认删除此数据?",
            action: function() {
         	  	$.post('admin/usermanager/del',{"ids":id},function(data){
         	  		grid.bootgrid('reload');
       			});
                this.destroy();
            }
          });
        confirm.show();
    });
});
$('#search-btn').click(function(){
	grid.bootgrid('reload');
});
</script>