package cn.zhny.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhny.base.action.AbstractAction;
import cn.zhny.base.entity.Constraint;
import cn.zhny.base.entity.User;
import cn.zhny.base.service.PermissionService;
import cn.zhny.base.service.UserService;
import cn.zhny.base.utils.RandomValidateCode;
import cn.zhny.base.utils.SessionObject;
import cn.zhny.base.vo.Jo;

import com.avaje.ebean.SqlRow;

@Controller
@RequestMapping("/admin")
@Transactional
public class IndexAction extends AbstractAction{
	
	@Autowired
    private HttpServletRequest request;
	
	@Autowired
	PermissionService permissionService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("")
	public String _(){
		return "admin/login";
	}
	
	/**
	 * 验证码
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("random")
	public void random(HttpServletResponse response){
		response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		RandomValidateCode randomValidateCode = new RandomValidateCode();
		try {
		    randomValidateCode.getRandcode(request, response);//输出图片方法
		    response.flushBuffer();  
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Jo login(String name,String password,String rememberme){
		Jo result= userService.login(name, password);
		if(result.getCode()==Jo.CODE_SUCCESS){
			User user=(User)result.getData();
			UsernamePasswordToken token = new UsernamePasswordToken(user.getId().toString(),user.getPassword(),"y".equals(rememberme));
	        try {
	            SecurityUtils.getSubject().login(token);
	        } catch (Exception e) {
	        	return new Jo(Jo.CODE_FAIL,"登录失败，系统异常");
	        }
			SessionObject so=new SessionObject();
			so.setUser(user);
			request.getSession().setAttribute(SessionObject.SESSION_KEY, so);
			return new Jo();
		}else{
			return result;
		}
		
	}
	
	@RequestMapping(value="logout")
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout(); 
		}
		return "admin/login";
	}
	
	@RequestMapping("index")
	public String index(Model model){
		List<SqlRow> tree=userService.findUserPermission(getCurrentUser().getId(),Constraint.PermissionTypeEnum.MENU,null);
		model.addAttribute("tree",tree);
		return "admin/index";
	}
	
	@RequestMapping("index/dashboard")
	public String index_dashboard(){
		return "admin/index/dashboard";
	}
	
	@ResponseBody
	@RequestMapping("nextLevelPermission")
	public Jo nextLevelPermission(Long parentId,Constraint.PermissionTypeEnum type){
		return new Jo(userService.findUserPermission(getCurrentUser().getId(),type,parentId)); 
	}
	
	@RequestMapping("validname")
	@ResponseBody
	public Map<String, Object> validname(String param) {
		User user = userService.findByPhone(param);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			result.put("status", "n");
			result.put("info", "该账户已被注册");
		} else {
			result.put("status", "y");
		}
		return result;
	}
	
}
