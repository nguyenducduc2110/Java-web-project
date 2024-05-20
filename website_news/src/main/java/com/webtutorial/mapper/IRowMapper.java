package com.webtutorial.mapper;

import java.sql.ResultSet;

public interface IRowMapper<T> {
	public abstract T mapRow(ResultSet resultSet);//method ánh xạ row thành model
}
