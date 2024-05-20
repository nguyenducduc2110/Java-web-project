package com.webtutorial.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.webtutorial.model.CategoryModel;

public class CategoryMapper implements IRowMapper<CategoryModel>{

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		try {
				CategoryModel categoryModel = new CategoryModel();		
				categoryModel.setId(resultSet.getLong("id"));
				categoryModel.setCode(resultSet.getString("code"));
				categoryModel.setName(resultSet.getString("name"));
				categoryModel.setCreatedDate(resultSet.getTimestamp("createddate"));
				categoryModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
				categoryModel.setCreatedBy(resultSet.getString("createdby"));
				categoryModel.setModifiedBy(resultSet.getString("modifiedby"));
				return categoryModel;
			
		} catch (SQLException e) {
			return null;
		}
	}
}
