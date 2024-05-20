package com.webtutorial.dao.impl;

import com.webtutorial.dao.GenericDAO;

//Đây là class cấp cao
public class DAOFactory {
	private DAOFactory() {}
//Factory Method Design Pattern
	public static final GenericDAO<?> getDAO(DAOType daoType) {
		switch (daoType) {
		case CategoryDAO:
			return new CategoryDAO();
//		case CommentDAO:
//			return new CommentDAO();
		case NewsDAO:
			return new NewsDAO();
//		case RoleDAO:
//			return new RoleDAO();
//		case UserDAO:
//			return new UserDAO();
		default:
			 throw new IllegalArgumentException("This bank type is unsupported");
		}
	}
}

