package com.webtutorial.dao.impl;

import java.util.ArrayList;

import com.webtutorial.dao.IUserDAO;
import com.webtutorial.mapper.NewsMapper;
import com.webtutorial.mapper.UserMapper;
import com.webtutorial.model.NewsModel;
import com.webtutorial.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{
	
	@Override
	public UserModel findByUserNamePasswordAndStatus(String userName, String password) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u");
		sql.append(" INNER JOIN role AS r ON r.id = u.roleid");
		sql.append(" WHERE u.name = ? AND u.password = ?");
		ArrayList<UserModel> users = queryList(sql.toString(), new UserMapper(), userName, password);
		return users.isEmpty() ? null : users.get(0);
	}
	@Override
	public UserModel findOne(Long id) {
		ArrayList<UserModel	> arrayList = super.queryList("select * from user where id = ?;", new UserMapper(), id);
		return arrayList.isEmpty() ? null: arrayList.get(0);
	}
}
