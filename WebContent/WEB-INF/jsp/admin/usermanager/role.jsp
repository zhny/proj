<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="breadcrumb">
	<li>
		<i class="icon-home"></i>
		<a href="admin/index">首页</a> 
		<i class="icon-angle-right"></i>
	</li>
	<li><a href="javascript:void(0);">角色管理</a></li>
</ul>

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header" data-original-title>
			<h2><i class="halflings-icon user"></i><span class="break"></span>角色</h2>
			<div class="box-icon">
				<a data-trigger="modal" href="admin/role/create/view" data-title="添加角色"  class="btn-minimize"><i class="halflings-icon plus"></i></a>
			</div>
		</div>
		<div class="box-content">
			
			<form class="form-search form-inline" id="search-form">
			  <label>角色名称：</label>
		      <input type="text" class="input-medium search-query" name="name">
		      <label>角色状态：</label>
      		  <select name="status">
               	 	<option value="">请选择</option>
               	 	<option value="Y">有效</option>
               	 	<option value="F">冻结</option>
              </select>
		      <button type="button" class="btn" id="search-btn">查询</button>
		    </form>
		</div>
		<div class="box-content">
	      <table id="grid-data" class="table table-condensed table-hover table-striped table-bordered" >
	         <thead>
	             <tr>
	                 <th data-column-id="name">角色名称</th>
	                 <th data-column-id="description">描述</th>
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
    url: "admin/role/page",
    post: function ()
    {
        return  $('#search-form').serializeObject();   
    },
    formatters: {
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
    	var modal = $.scojs_modal({title:"编辑角色",remote:'admin/role/create/view?id='+id});
    	modal.show();
    }).end().find(".command-delete").on("click", function(e)
    {
    	var id=$(this).data('row-id');
        var confirm = $.scojs_confirm({
            content: "确认删除此数据?",
            action: function() {
         	  	$.post('admin/role/del',{"ids":id},function(data){
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