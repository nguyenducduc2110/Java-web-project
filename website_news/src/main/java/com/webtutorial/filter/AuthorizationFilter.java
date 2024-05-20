package com.webtutorial.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webtutorial.constant.SystemContant;
import com.webtutorial.utils.SessionUtil;



public class AuthorizationFilter implements Filter{	
	private ServletContext conetxt;
	@Override

	public void init(FilterConfig filterConfig) throws ServletException {
		this.conetxt = filterConfig.getServletContext();
	}

	@Override
	
	public void doFilter(ServletRequest servletRequest, ServletResponse  servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		String url = httpServletRequest.getRequestURI();
		String roleCode = (String) SessionUtil.getInstance().getValue(httpServletRequest, "RoleCode");
		
		System.out.println("Đường dẫn cuối của url"+url);
		if(roleCode == null &&  url.endsWith("/admin-home")) {
     		httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/dang-nhap?action=login&alert=danger&message=no_login");
		}else if(roleCode == null && url.endsWith("/admin-news")) {
         		httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/dang-nhap?action=login&alert=danger&message=not_login");
		}else if(roleCode != null && url.endsWith("/admin-news")) {
			if(roleCode.equalsIgnoreCase(SystemContant.ADMIN)) {
				chain.doFilter(servletRequest, servletResponse);
			}else {
	        	SessionUtil.getInstance().removeValue(httpServletRequest, "Id");
				SessionUtil.getInstance().removeValue(httpServletRequest, "fullName");
				SessionUtil.getInstance().removeValue(httpServletRequest, "RoleCode");
         		httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/dang-nhap?action=login&alert=danger&message=not_login");
			}
		}else if(roleCode != null && url.endsWith("/admin-home")) {
			if(roleCode.equalsIgnoreCase(SystemContant.ADMIN)) {
				chain.doFilter(servletRequest, servletResponse);
			}else {
				SessionUtil.getInstance().removeValue(httpServletRequest, "fullName");
				SessionUtil.getInstance().removeValue(httpServletRequest, "RoleCode");
         		httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/dang-nhap?action=login&alert=danger&message=not_login");
			}
		}else if(roleCode != null && (url.endsWith("/trang-chu") || url.endsWith("/dang-nhap") || url.endsWith("/thoat"))) {
			if(roleCode.equalsIgnoreCase(SystemContant.USER)) {
				chain.doFilter(servletRequest, servletResponse);
			}else if(roleCode.equalsIgnoreCase(SystemContant.ADMIN) && url.endsWith("/trang-chu")){
	        	SessionUtil.getInstance().removeValue(httpServletRequest, "Id");
				SessionUtil.getInstance().removeValue(httpServletRequest, "fullName");
				SessionUtil.getInstance().removeValue(httpServletRequest, "RoleCode");         		
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/trang-chu");
			}else if(roleCode.equalsIgnoreCase(SystemContant.ADMIN) && url.endsWith("/thoat")) {
	        	SessionUtil.getInstance().removeValue(httpServletRequest, "Id");
				SessionUtil.getInstance().removeValue(httpServletRequest, "fullName");
				SessionUtil.getInstance().removeValue(httpServletRequest, "RoleCode");         		
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/trang-chu");
			}else {
				
				SessionUtil.getInstance().removeValue(httpServletRequest, "fullName");
	        	SessionUtil.getInstance().removeValue(httpServletRequest, "Id");
				SessionUtil.getInstance().removeValue(httpServletRequest, "RoleCode");         		
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/dang-nhap?action=login");
        	}
		}else {
			chain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {
		
		
	}

}
