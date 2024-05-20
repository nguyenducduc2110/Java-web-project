package com.webtutorial.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.webtutorial.model.RoleModel;
import com.webtutorial.model.UserModel;

public class UserMapper implements IRowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		try {
			UserModel user = new UserModel();
			user.setId(resultSet.getLong("id"));
			user.setUserName(resultSet.getString("name"));
			user.setFullName(resultSet.getString("fullname"));
			user.setPassword(resultSet.getString("password"));
			user.setRoleId(resultSet.getLong("roleid"));
			user.setStatus(resultSet.getInt("status"));
			user.setCreatedDate(resultSet.getTimestamp("createddate"));
			user.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			user.setCreatedBy(resultSet.getString("createdby"));
			user.setModifiedBy(resultSet.getString("modifiedby"));
			//Đoạn này join bảng role và user thành 1 bảng kết hợp cả 2 bảng.
			//Lý do trong class UserModel có attribute roleId rồi mà vẫn phải truyền 1 object role vào làm attribute là vì khi câu lệnh select có join nó trộn 2 bảng vào thì nó thừa ra cả roleId và cả 1 object role.
			//Nếu câu select không có inner join thì đoạn setRole id bị sai nên try cacth
			try {
				RoleModel roleModel = new RoleModel();
				roleModel.setCode(resultSet.getString("code"));
				roleModel.setName(resultSet.getString(12));
				roleModel.setId(resultSet.getLong("id"));
				user.setRoleModel(roleModel);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
