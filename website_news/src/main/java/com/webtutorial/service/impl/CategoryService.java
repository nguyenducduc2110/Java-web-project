package com.webtutorial.service.impl;

import java.util.ArrayList;

import javax.inject.Inject;

import com.webtutorial.dao.ICategoryDAO;
import com.webtutorial.model.CategoryModel;
import com.webtutorial.service.ICategoryService;

public class CategoryService  implements ICategoryService{
//	private IDAO<?> categoryDAO;
//	@SuppressWarnings("unchecked")
//	public ArrayList<CategoryModel> findAll(){
//		categoryDAO = DAOFactory.getDAO(DAOType.CategoryDAO);
//		return (ArrayList<CategoryModel>) categoryDAO.findAll();
//	}
	@Inject // tiêm instance IDAO vào thì nó sẽ gọi method ở class đã implements IDAO
	private ICategoryDAO iCategoryDAO;

	@Override
	public ArrayList<CategoryModel> findAll() {
		return iCategoryDAO.findAll();
	}
	
	@Override
	public CategoryModel findById(Long id) {
		return iCategoryDAO.findById(id);
	}
	
}
