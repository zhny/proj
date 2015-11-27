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
import cn.zhny.base.entity.User;
import cn.zhny.base.utils.Digests;
import cn.zhny.base.vo.EUITree;
import cn.zhny.base.vo.Jo;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.PagedList;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;

@Service
public class UserService extends AbstractService<User> {

	@Autowired
	private EbeanServer ebeanServer;

	@Autowired
	private RoleService roleService;

	@Override
	public Class<User> getClazz() {
		return User.class;
	}

	public User queryUserByPhone(String phone) {
		return ebeanServer.find(User.class).where().eq("phone", phone).ne("status", Constraint.StatusEnum.D).findUnique();
	}

	public List<Role> findUserRoles(Long userId) {
		User user = ebeanServer.find(User.class, userId);
		return user != null ? user.getRoles() : null;
	}

	public List<SqlRow> findUserPermission(Long userId, Constraint.PermissionTypeEnum ptype, Long parentId) {
		String sql="";
		if(userId.equals(-1L)){
			sql="select id,name,parent_id,url from permission where status='Y'" + (parentId == null ? " and parent_id is null " : "and parent_id=:parentId ") + "and permission_type=:type ";
		}else{
			sql="select distinct c.id,c.name,c.parent_id,c.url from user_roles a,role_permissions b,permission c " + "where a.role_id = b.role_id and b.permission_id=c.id and c.status='Y' " + (parentId == null ? "and c.parent_id is null " : "and c.parent_id=:parentId ") + "and c.permission_type=:type " + "and user_id=:userId";
		}
		SqlQuery sqlQuery = ebeanServer.createSqlQuery(sql);
		sqlQuery.setParameter("userId", userId).setParameter("type", ptype);
		if (parentId != null) {
			sqlQuery.setParameter("parentId", parentId);
		}
		return sqlQuery.findList();

	}

	public List<Permission> findRolePermission(Long roleId) {
		Role role = ebeanServer.find(Role.class, roleId);
		return role != null ? role.getPermissions() : null;
	}

	public PagedList<User> findPage(Integer cp, Integer pageSize) {
		if (cp == null)
			cp = 0;
		if (pageSize == null)
			pageSize = PAGE_SIZE;
		PagedList<User> page = ebeanServer.find(User.class).where().ne("status", Constraint.StatusEnum.D).orderBy("id desc").findPagedList(cp, pageSize);
		return page;
	}

	/**
	 * 获取角色权限树
	 * 
	 * @param roleId
	 * @return
	 */
	public List<EUITree> userRoleTree(Long userId) {
		Set<Long> rids = new HashSet<Long>();
		if (userId != null) {
			User user = this.get(userId);
			List<Role> userRoles = user.getRoles();
			for (Role r : userRoles) {
				rids.add(r.getId());
			}
		}
		List<Role> rtree = roleService.list();
		return travertEUITree(rtree, rids);
	}

	/**
	 * 将角色权限转换成EasyUI tree所需的树结构
	 * 
	 * @param ptree
	 * @param upids
	 * @return
	 */
	private List<EUITree> travertEUITree(List<Role> rtree, Set<Long> rids) {
		List<EUITree> tree = new ArrayList<EUITree>();
		for (Role r : rtree) {
			EUITree etree = new EUITree();
			etree.setId(r.getId());
			etree.setText(r.getName());
			etree.setChecked(rids.contains(r.getId()));
			tree.add(etree);
		}
		return tree;
	}

	public void createOrUpdateUserWithRoleIds(User user, String rids) {
		List<Role> roles = new ArrayList<Role>();
		if (!isNullOrEmpty(rids)) {
			String[] ridArrays = rids.split(";");
			for (String rid : ridArrays) {
				if (!isNullOrEmpty(rid)) {
					roles.add(roleService.get(Long.parseLong(rid)));
				}
			}
		}
		user.setRoles(roles);
		if (user.getId() == null) {
			user.setDateCreate(new Date());
			user.setDateUpdate(new Date());
			user.setPassword(Digests.md5(user.getPassword()));
			this.create(user);
		} else {
			user.setDateUpdate(new Date());
			this.update(user);
		}
	}

	public void freeze(User user) {
		user = this.get(user.getId());
		user.setStatus(user.getStatus().equals(Constraint.StatusEnum.Y) ? Constraint.StatusEnum.F : Constraint.StatusEnum.Y);
		this.update(user);
	}

	public User findByPhone(String phone) {
		return ebeanServer.find(User.class).where().eq("phone", phone).findUnique();
	}

	public Jo login(String name, String password) {
		User user = this.findByPhone(name);
		if (user != null) {
			if (Digests.md5(password).toLowerCase().equals(user.getPassword().toLowerCase())) {
				return new Jo(user);
			} else {
				return new Jo(Jo.CODE_FAIL, "密码错误");
			}
		} else {
			return new Jo(Jo.CODE_FAIL, "用户不存在");
		}
	}

	public void updatePassword(Long uid, String password) {
		ebeanServer.createSqlUpdate("update user set password=:password where id=:id").setParameter("id", uid).setParameter("password", Digests.md5(password)).execute();
	}
}
