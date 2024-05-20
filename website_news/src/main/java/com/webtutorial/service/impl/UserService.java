package com.webtutorial.service.impl;

import javax.inject.Inject;

import com.webtutorial.dao.IUserDAO;
import com.webtutorial.model.UserModel;
import com.webtutorial.service.IUserService;

public class UserService implements IUserService{
	@Inject
	private IUserDAO userDAO;
	@Override
	public UserModel findByUserNamePasswordAndStatus(String userName, String password) {
		return userDAO.findByUserNamePasswordAndStatus(userName, password);
	}
	@Override
	public UserModel findOne(Long id) {
		return userDAO.findOne(id);
	}
	
	
}
