package cn.zhny.admin.usermanager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhny.base.action.AbstractAction;
import cn.zhny.base.entity.Constraint;
import cn.zhny.base.entity.User;
import cn.zhny.base.service.UserService;
import cn.zhny.base.utils.Digests;
import cn.zhny.base.vo.BSPage;
import cn.zhny.base.vo.Jo;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Expression;
import com.avaje.ebean.PagedList;

@Controller
@RequestMapping("admin/usermanager")
@Transactional
public class UserManagerAction extends AbstractAction{
	
	@Autowired
    private HttpServletRequest request;
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping("")
	public String index(){
		return "admin/usermanager/user";
	}
	
	
	@ResponseBody
	@RequestMapping("page")
	public BSPage page(Integer current,Integer rowCount){
		Expression expr=Expr.ne("status", Constraint.StatusEnum.D);
		if(checkParam("phone")){
			expr=Expr.and(expr, Expr.ilike("phone", getParam("phone")+"%"));
		}
		if(checkParam("status")){
			expr=Expr.and(expr, Expr.eq("status", getParam("status")));
		}
		PagedList<User> users=userService.findPage(current, rowCount, expr);
		return new BSPage(users,rowCount);
	}
	
	@RequestMapping("create/view")
	public String createView(Long id,Model model){
		if(id!=null){
			model.addAttribute("user", userService.get(id));
		}
		model.addAttribute("rtree", userService.userRoleTree(id));
		return "admin/usermanager/add_user";
	}
	
	@ResponseBody
	@RequestMapping("create")
	public Jo create(User user,String rids,String surePassword){
		if(user.getId()==null&&(surePassword==null||user.getPassword()==null||!surePassword.equals(user.getPassword()))){
			return new Jo(Jo.CODE_FAIL,"两次密码输入不一致");
		}
		User _user=userService.findByPhone(user.getPhone());
		if(_user!=null){
			return new Jo(Jo.CODE_FAIL,"用户已存在");
		}
		userService.createOrUpdateUserWithRoleIds(user,rids);
		return new Jo();
	}
	
	@ResponseBody
	@RequestMapping("del")
	public Jo del(String ids){
		userService.softDeleteBatchObj(ids);
		return new Jo();
	}
	
	@ResponseBody
	@RequestMapping("freeze")
	public String freeze(User user){
		userService.freeze(user);
		return "success";
	}
	
	@RequestMapping("passwordupdate")
	public String passwordupdate(){
		return "admin/usermanager/passwordupdate";
	}
	
	@ResponseBody
	@RequestMapping("updateMyPassword")
	public Jo updateMyPassword(String oldPassword,String newPassword){
		if(oldPassword.equals(newPassword)){
			return new Jo(Jo.CODE_FAIL, "新密码不能和原密码相同！");
		}
		long uid= getCurrentUser().getId();
		User user = userService.get(uid);
		oldPassword = Digests.md5(oldPassword);
		newPassword = Digests.md5(newPassword);
		if(user.getPassword().equals(oldPassword)){
			user.setPassword(newPassword);
			userService.update(user);
			return new Jo(Jo.CODE_SUCCESS, "修改成功！");
			//修改成功
		}else{
			//原密码不正确
			return new Jo(Jo.CODE_FAIL, "原密码错误！");
		}
	}
	
}
