package com.webtutorial.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	public static SessionUtil getInstance() {	
		return SessionUtilHelper.INSTANCE;
	}
	private static class SessionUtilHelper {
		private static final SessionUtil INSTANCE = new SessionUtil();
	}
	//đặt value cho session
	public void putValue(HttpServletRequest request, String key, Object value) {
		HttpSession session = request.getSession();
		// Set session to expire after 30 minutes of inactivity
		session.setMaxInactiveInterval(90 * 60); // Thời gian tính bằng giây, nên 30 phút = 30 * 60 giây
		System.out.println("Session là:"+ session.getId());//Mỗi session sẽ có một id để phân biệt giữa các client khi có nhiều client.
		session.setAttribute(key, value);//Lấy session từ http request và setAtribut cho session
	}
	public String getSessionID(HttpServletRequest request) {
		return request.getSession().getId();
	}
	public Object getValue(HttpServletRequest request, String key) {
		//request.getSession() trả object quản lý phiên:HttpSession session = request.getSession(); 
		return request.getSession().getAttribute(key);
	}
	public void removeValue(HttpServletRequest request, String key) {
		
		request.getSession().removeAttribute(key);
		//4256E3A1564E82A2D58F1E6DF6355E8F
	}
}
