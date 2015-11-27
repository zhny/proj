package cn.zhny.admin.usermanager;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhny.base.action.AbstractAction;
import cn.zhny.base.entity.Constraint;
import cn.zhny.base.entity.Role;
import cn.zhny.base.service.RoleService;
import cn.zhny.base.vo.BSPage;
import cn.zhny.base.vo.EUITree;
import cn.zhny.base.vo.Jo;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Expression;
import com.avaje.ebean.PagedList;

@Controller
@RequestMapping("admin/role")
@Transactional
public class RoleManagerAction extends AbstractAction{
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping("")
	public String index(){
		return "admin/usermanager/role";
	}
	
	@RequestMapping("view/{id}")
	public String view(@PathVariable Long id,Model model){
		model.addAttribute("role", roleService.get(id));
		return "admin/usermanager/view_role";
	}
	
	
	@ResponseBody
	@RequestMapping("page")
	public BSPage page(Integer current,Integer rowCount){
		Expression expr=Expr.ne("status", Constraint.StatusEnum.D);
		if(checkParam("name")){
			expr=Expr.and(expr, Expr.icontains("name", getParam("name").toString()));
		}
		if(checkParam("dateUpdateStart")){
			expr=Expr.and(expr, Expr.ge("dateUpdate", getParam("dateUpdateStart")));
		}
		if(checkParam("dateUpdateEnd")){
			expr=Expr.and(expr, Expr.le("dateUpdate", getParam("dateUpdateEnd")));
		}
		if(checkParam("status")){
			expr=Expr.and(expr, Expr.eq("status", getParam("status")));
		}
		
		PagedList<Role> roles=roleService.findPage(current, rowCount, expr);
		return new BSPage(roles,rowCount);
	}
	
	@RequestMapping("create/view")
	public String createView(Long id,Model model){
		if(id!=null){
			model.addAttribute("role", roleService.get(id));
		}
		return "admin/usermanager/add_role";
	}
	
	@ResponseBody
	@RequestMapping("rptree")
	public List<EUITree> euiRolePermissionTree(Long roleId){
		return roleService.rolePermissionTree(roleId);
	}
	
	@ResponseBody
	@RequestMapping("create")
	public Jo create(Role role,String pids){
		roleService.createRoleWithPermissionIds(role,pids);
		return new Jo();
	}
	
	@ResponseBody
	@RequestMapping("del")
	public Jo del(String ids){
		roleService.softDeleteBatchObj(ids);
		return new Jo();
	}
	
	@ResponseBody
	@RequestMapping("freeze")
	public Jo freeze(Role role){
		roleService.freeze(role);
		return new Jo();
	}
}
