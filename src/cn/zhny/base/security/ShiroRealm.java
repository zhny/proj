package cn.zhny.base.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.zhny.base.entity.Permission;
import cn.zhny.base.entity.Role;
import cn.zhny.base.entity.User;
import cn.zhny.base.service.UserService;
import cn.zhny.base.utils.Collections3;

@Component
public class ShiroRealm extends AuthorizingRealm{

	@Autowired
	UserService userService;
	
	public ShiroRealm() {
		setName("ShiroRealm");
//		setCredentialsMatcher(new Sha256CredentialsMatcher());
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection princ) {
		Long userId = (Long) princ.fromRealm(getName()).iterator().next();
		if(userId!=null){
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<String> userPermissionNames = new ArrayList<String>();
			List<Role> userRoles=userService.findUserRoles(userId);
			List<String> userRoleNames=Collections3.extractToList(userRoles, "name");
			if(userId.equals(-1L)){				//管理员
				userPermissionNames.add("*");
			}else{
				for (Role role: userRoles) {
					List<Permission> rolePermissions=role.getPermissions();
					for (Permission permission :rolePermissions) {
						if(permission.getUrl()!=null&&!permission.getUrl().equals("")){
							info.addStringPermission(permission.getId().toString());
							info.addStringPermission("/"+permission.getUrl());
						}
					}
				}
			}
			info.addStringPermissions(userPermissionNames);
			info.addRoles(userRoleNames);
			return info;
		}
		return null;
	}
	

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token=(UsernamePasswordToken)authcToken;
		User user=userService.get(Long.parseLong(token.getUsername()));
		if(user!=null){
			return new SimpleAuthenticationInfo(user.getId(), user.getPassword(),this.getName());
		}
		return null;
	}

}
