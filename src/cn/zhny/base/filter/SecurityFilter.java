package cn.zhny.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import cn.zhny.base.utils.JsonUtil;
import cn.zhny.base.vo.Jo;

/**
 * 最简单的权限过滤器
 * 
 * @author hxw
 *
 */
public class SecurityFilter implements Filter {

	// 不需要过滤的资源
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
//		HttpSession httpSession = httpReq.getSession();
//		String url = httpReq.getRequestURI();
//		String path = httpReq.getContextPath();
		try {
//			if (isFilter(url, path)) {
//				if (httpSession.getAttribute(SessionObject.SESSION_KEY) == null) {
//					// 到登录页面
//					httpRes.sendRedirect(path);
//				} else {
//					chain.doFilter(request, response);
//				}
//			} else {
//				chain.doFilter(request, response);
//			}
			
			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			if (httpRes != null) {
				Jo jo = new Jo(Jo.CODE_FAIL, "系统异常");
				JsonUtil.toJson(jo, httpRes);
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
