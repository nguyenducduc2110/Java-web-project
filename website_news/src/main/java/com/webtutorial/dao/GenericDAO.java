package com.webtutorial.dao;

import java.util.ArrayList;

import com.webtutorial.mapper.IRowMapper;


public interface GenericDAO<T> {
	 ArrayList<T> queryList(String sql, IRowMapper<T> rowMapper, Object...parameters);
	
	 Long insert(String sql, Object...parameters);
	 void update(String sql, Object...parameters);
	
}
