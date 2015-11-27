package cn.zhny.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhny.base.entity.Constraint;
import cn.zhny.base.entity.Permission;
import cn.zhny.base.entity.Role;
import cn.zhny.base.vo.EUITree;

import com.avaje.ebean.EbeanServer;

@Service
public class RoleService extends AbstractService<Role>{

	
	@Autowired
	PermissionService permissionService;
	
	@Autowired
	EbeanServer ebeanServer;
	
	@Override
	public Class<Role> getClazz() {
		return Role.class;
	}
	
	/**
	 * 获取角色权限树
	 * @param roleId
	 * @return
	 */
	public List<EUITree> rolePermissionTree(Long roleId){
		Set<Long> pids=new HashSet<Long>();
		if(roleId!=null){
			Role role=ebeanServer.find(Role.class,roleId);
			List<Permission> rolePermissions=role.getPermissions();
			for(Permission p:rolePermissions){
				pids.add(p.getId());
			}
		}
		List<Permission> ptree=permissionService.tree();
		return travertEUITree(ptree,pids);
	}

	/**
	 * 将角色权限转换成EasyUI tree所需的树结构
	 * @param ptree
	 * @param upids
	 * @return
	 */
	private List<EUITree> travertEUITree(List<Permission> ptree,Set<Long> upids){
		List<EUITree> tree=new ArrayList<EUITree>();
		for(Permission p:ptree){
			EUITree etree=new EUITree();
			etree.setId(p.getId());
			etree.setText(p.getName());
			etree.setChecked(upids.contains(p.getId()));
			if(p.getChildren()!=null&&p.getChildren().size()>0){
				etree.setChecked(false);
				etree.setChildren(travertEUITree(p.getChildren(),upids));
			}
			tree.add(etree);
		}
		
		return tree;
	}

	public void createRoleWithPermissionIds(Role role, String pids) {
		List<Permission> permissions=new ArrayList<Permission>();
		if(!isNullOrEmpty(pids)){
			String[] pidArrays=pids.split(";");
			for(String pid:pidArrays){
				if(!isNullOrEmpty(pid)){
					permissions.add(permissionService.get(Long.parseLong(pid)));
				}
			}
		}
		role.setPermissions(permissions);
		if(role.getId()==null){
			role.setDateCreate(new Date());
			role.setDateUpdate(new Date());
			this.create(role);
		}else{
			role.setDateUpdate(new Date());
			this.update(role);
		}
	}

	public void freeze(Role role) {
		role=this.get(role.getId());
		role.setStatus(role.getStatus().equals(Constraint.StatusEnum.Y)?Constraint.StatusEnum.F:Constraint.StatusEnum.Y);
		this.update(role);
		
	}
}
