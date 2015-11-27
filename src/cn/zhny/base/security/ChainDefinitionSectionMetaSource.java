package cn.zhny.base.security;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zhny.base.entity.Permission;
import cn.zhny.base.service.PermissionService;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {

	public static final String PREMISSION_STRING = "perms[\"{0}\"]";

	private String filterChainDefinitions;

	@Autowired
	private PermissionService permissionService;

	public String getFilterChainDefinitions() {
		return filterChainDefinitions;
	}

	@Resource
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		String fiter = "";// 改正后的url配置
		List<Permission> list = permissionService.list();
		for (Iterator<Permission> it = list.iterator(); it.hasNext();) {
			Permission permission = it.next();
			if (!StringUtils.isEmpty(permission.getUrl())) {
				fiter += "/"+ permission.getUrl()+ " = authc, "+ MessageFormat.format(PREMISSION_STRING,permission.getId()) + "\n";
			}
		}
		// 追加beans.xml中已经有的过滤
		this.filterChainDefinitions = filterChainDefinitions + fiter;
	}

	public Section getObject() {
		Ini ini = new Ini();
		ini.load(filterChainDefinitions);
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		return section;
	}

	public Class<?> getObjectType() {
		return this.getClass();
	}

	public boolean isSingleton() {
		return false;
	}
}
