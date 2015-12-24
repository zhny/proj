package cn.zhny.admin.usermanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhny.base.action.AbstractAction;
import cn.zhny.base.entity.Permission;
import cn.zhny.base.service.PermissionService;
import cn.zhny.base.vo.EUIPage;
import cn.zhny.base.vo.Jo;

/**
 * 权限管理
 * @author zhny
 *
 */
@Controller
@RequestMapping("admin/permission")
@Transactional(rollbackFor=Exception.class)
public class PermissionManagerAction extends AbstractAction{
	
	@Autowired
	PermissionService permissionService;
	
	@RequestMapping("")
	public String index(){
		return "admin/usermanager/permission";
	}
	
	/**
	 * 权限列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("list")
	public EUIPage list(){
		List<Permission> result=permissionService.tree();
		return new EUIPage((long) result.size(),result);
	}
	
	/**
	 * 权限创建
	 * @return
	 */
	@ResponseBody
	@RequestMapping("create")
	public Permission create(Permission obj){
		return permissionService.create(obj);
	}
	
	/**
	 * 权限更新
	 * @return
	 */
	@ResponseBody
	@RequestMapping("update")
	public Permission update(Permission obj){
		return permissionService.update(obj);
	}
	
	
	/**
	 * 权限删除
	 * @return
	 */
	@ResponseBody
	@RequestMapping("del")
	public Jo del(String id){
		permissionService.softDeleteBatchObj(id);
		return new Jo();
	}
	
}
