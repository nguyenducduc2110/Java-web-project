package com.webtutorial.service;

import java.util.ArrayList;

import com.webtutorial.model.CategoryModel;

public interface ICategoryService  {
	public abstract ArrayList<CategoryModel> findAll();
	public abstract CategoryModel findById(Long id);

}
