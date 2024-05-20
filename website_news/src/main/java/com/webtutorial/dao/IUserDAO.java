package com.webtutorial.dao;

import com.webtutorial.model.UserModel;

public interface IUserDAO {
	public abstract UserModel findByUserNamePasswordAndStatus(String userName, String password);
	UserModel findOne(Long id);
}
