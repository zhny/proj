package cn.zhny.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhny.base.entity.Constraint;
import cn.zhny.base.entity.Permission;

import com.avaje.ebean.EbeanServer;

@Service
public class PermissionService extends AbstractService<Permission>{

	@Autowired
	private EbeanServer ebeanServer;
	
	@Override
	public Class<Permission> getClazz() {
		return Permission.class;
	}
	
	
	public List<Permission> tree(){
		List<Permission> tops=ebeanServer.find(Permission.class).where()
														  .isNull("parent.id").ne("status", Constraint.StatusEnum.D)
														  .setUseCache(true)
														  .setReadOnly(true).findList();
		treeChildren(tops);
		return tops;
	}
	
	public void treeChildren(List<Permission> list){
		if(list.size()>0){
			for(Permission permission:list){
				List<Permission> children=ebeanServer.find(Permission.class).where()
																	  .ne("status", Constraint.StatusEnum.D)
																	  .eq("parent.id", permission.getId())
																	  .setUseCache(true)
																	  .setReadOnly(true).findList();
				permission.setChildren(children);
				treeChildren(children);
			}
		}
	}
	
	public List<Permission> nextLevelPermission(Long parentId,Constraint.PermissionTypeEnum type){
		List<Permission> children=ebeanServer.find(Permission.class).where()
				  .ne("status", Constraint.StatusEnum.D)
				  .eq("parent.id", parentId)
				  .eq("permissionType", type)
				  .setUseCache(true)
				  .setReadOnly(true).findList();
		return children;
	}
	
}
