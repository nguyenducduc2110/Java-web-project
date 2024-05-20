package com.webtutorial.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webtutorial.model.UserModel;
import com.webtutorial.service.IUserService;
import com.webtutorial.utils.FormUtil;
import com.webtutorial.utils.SessionUtil;


@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap","/thoat"})
public class HomeController extends HttpServlet {
	
	@Inject			
	private IUserService userService;
	
	private static final long serialVersionUID = 1L;	
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
        String action = request.getParameter("action");
        if(action!=null && action.equals("login")) {
        	String alert = request.getParameter("alert");
			String message = request.getParameter("message");
			if (message != null && alert != null) {
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}
			
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if(cookie.getName().equalsIgnoreCase("userName")) {
					request.setAttribute("UserName", cookie.getValue());
				}else if(cookie.getName().equalsIgnoreCase("password")) {
					request.setAttribute("password", cookie.getValue());
				}
			}
			
        	RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/login.jsp");
    		requestDispatcher.forward(request, response);
        }else if(action!=null && action.equalsIgnoreCase("logout")) {
        	SessionUtil.getInstance().removeValue(request, "Id");
        	SessionUtil.getInstance().removeValue(request, "fullName");
			SessionUtil.getInstance().removeValue(request, "RoleCode");
        	response.sendRedirect(request.getContextPath()+"/trang-chu");
        }else {



         






    		
        	
        	if(request.getRequestURI().endsWith("/dang-nhap")) {
        		response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login");
        	}else {
        		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/web/home.jsp");
        		requestDispatcher.forward(request, response);
        	}
 
        }		
    }

    @Override
    
    
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	 String action = request.getParameter("action");
         if(action != null && action.equals("login")) {
        	 UserModel userModel = FormUtil.toModel(UserModel.class, request);
         	System.out.println(userModel.toString());
         	
         	
         		userModel = userService.findByUserNamePasswordAndStatus(userModel.getUserName(), userModel.getPassword());
         	if(userModel != null) {
         		
         		SessionUtil.getInstance().putValue(request, "fullName", userModel.getFullName());
         		SessionUtil.getInstance().putValue(request, "Id", userModel.getId());
         		SessionUtil.getInstance().putValue(request, "RoleCode", userModel.getRoleModel().getCode());
         		System.out.println("Vừa set cho session:"+SessionUtil.getInstance().getValue(request, "Id"));
         		System.out.println("Vừa set cho session:"+SessionUtil.getInstance().getValue(request, "fullName"));
         		System.out.println("Vừa set cho session:"+SessionUtil.getInstance().getValue(request, "RoleCode"));
         		








         		if(userModel.getRoleModel().getCode().equalsIgnoreCase("USER")) {
             		response.sendRedirect(request.getContextPath()+"/trang-chu");
                 	System.out.println(userModel.toString());
         		}else if(userModel.getRoleModel().getCode().equalsIgnoreCase("ADMIN")) {
             		response.sendRedirect(request.getContextPath()+"/admin-home"); 	
                 	System.out.println(userModel.toString());
         		}
         	}else {
         		
         		response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&alert=danger&message=userName_password_invalid");
         	}
         }else {
  
         }

    }
    
} 