package com.webtutorial.service;

import com.webtutorial.model.UserModel;

public interface IUserService {
	public abstract UserModel findByUserNamePasswordAndStatus(String userName, String password);
	UserModel findOne(Long id);
}
