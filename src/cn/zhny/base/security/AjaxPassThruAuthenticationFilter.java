package cn.zhny.base.security;

import java.net.URLEncoder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zhny.base.entity.User;
import cn.zhny.base.service.UserService;
import cn.zhny.base.utils.JsonUtil;
import cn.zhny.base.utils.SessionObject;
import cn.zhny.base.vo.Jo;

public class AjaxPassThruAuthenticationFilter extends PassThruAuthenticationFilter {

	@Autowired
	UserService userService;

	/**
	 * 这个方法决定了是否能让用户登录
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		Subject subject = getSubject(request, response);

		// 如果 isAuthenticated 为 false 证明不是登录过的，同时 isRememberd 为true
		// 证明是没登陆直接通过记住我功能进来的
		if (!subject.isAuthenticated() && subject.isRemembered()) {
			// 获取session看看是不是空的
			Session session = subject.getSession(true);
			// 随便拿session的一个属性来看session当前是否是空的，我用userId，你们的项目可以自行发挥
			if (session.getAttribute(SessionObject.SESSION_KEY) == null) {
				// 如果是空的才初始化，否则每次都要初始化，项目得慢死
				// 这边根据前面的前提假设，拿到的是username
				String username = subject.getPrincipal().toString();
				// 在这个方法里面做初始化用户上下文的事情，比如通过查询数据库来设置session值，你们自己发挥
				User user = userService.get(Long.parseLong(username));
				
				UsernamePasswordToken token = new UsernamePasswordToken(user.getId().toString(),user.getPassword(),true);
	            SecurityUtils.getSubject().login(token);
				SessionObject so = new SessionObject();
				so.setUser(user);
				session.setAttribute(SessionObject.SESSION_KEY, so);
			}
		}

		// 这个方法本来只返回 subject.isAuthenticated() 现在我们加上 subject.isRemembered()
		// 让它同时也兼容remember这种情况
		return super.isAccessAllowed(request, response, mappedValue);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Subject subject = getSubject(request, response);
		if (subject.getPrincipal() == null) {
			if ("XMLHttpRequest".equalsIgnoreCase(httpRequest.getHeader("X-Requested-With"))) {
				httpResponse.setStatus(401);
				JsonUtil.toJson(new Jo(Jo.CODE_UNLOGIN, "登录超时，请重新登录"), httpResponse);
			} else {
				if (httpRequest.getRequestURI().indexOf("/admin") > 0) {
					saveRequestAndRedirectToLogin(request, response);
				} else {
					String url = URLEncoder.encode(httpRequest.getRequestURL() + "?" + httpRequest.getQueryString(), "utf-8");
					httpResponse.sendRedirect(httpRequest.getContextPath() + "/index/loginredirect?redirect=" + url);
				}
			}
			return false;
		} else {
			return super.onAccessDenied(request, response);
		}
	}
}
