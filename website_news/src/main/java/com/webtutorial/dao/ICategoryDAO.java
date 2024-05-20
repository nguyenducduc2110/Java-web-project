package com.webtutorial.dao;

import java.util.ArrayList;

import com.webtutorial.model.CategoryModel;



public interface ICategoryDAO {
	public abstract ArrayList<CategoryModel> findAll();
	public abstract CategoryModel findById(Long id);
}
