package com.webtutorial.dao.impl;

import java.util.ArrayList;

import com.webtutorial.dao.ICategoryDAO;
import com.webtutorial.mapper.CategoryMapper;
import com.webtutorial.model.CategoryModel;


public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO{
	@Override
	 public ArrayList<CategoryModel> findAll(){
		 return super.queryList("select * from category", new CategoryMapper());// truyền object new CategoryMapper() chỉ để dùng method của class CategoryMapper
	 }
	@Override
	public CategoryModel findById(Long id) {
		ArrayList<CategoryModel>  arrayList = super.queryList("select * from category where id = ?", new CategoryMapper(), id);
		return arrayList.isEmpty() ? null : arrayList.get(0);
	}
	
//	@Override
//	public ArrayList<CategoryModel> findAll() {
//		//Từ 1 đến 2 bị lặp code vì các method insert, selectbyId cùng phải (connect), (xong viết câu lệnh query), và (ánh xạ từ row thành model)
//	     ===>Vì thế hãy viết method chung có 2 tham số nhận câu query(thêm1paramter là biến số động) và tham số kiểu RowMapper(để nhận 1row rồi mapping thành model và add vào list)             
//		//1
//		connection = AbstractDAO.getConnection();
//		Long id = null;
//		String name = "";
//		String code = "";
//		java.sql.Timestamp createddate = null;
//		java.sql.Timestamp modifieddate = null;
//		String createdby = "";
//		String modifiedby = "";
//		CategoryModel categoryModel = null;
//		ArrayList<CategoryModel> categoryArrayList = new ArrayList<>();
//		try {
//			//chỉ có câu query là khác nên làm tham số
//			PreparedStatement preparableStatement = connection.prepareStatement("select * from category");
//			ResultSet resultset = preparableStatement.executeQuery();		
//			while (resultset.next()) {
//		        //3 Từ 3->4 lặp việc ánh xạ từ 1hàng trong bảng vừa query đc thành model=>Mỗi Model nên có 1 class có method về việc ánh xạ từ table thành model  
//				id = resultset.getLong("id");//=>Các class có chung 1 method nên có 1 interface chứa method đó.
//				code = resultset.getString("code");
//				name = resultset.getString("name");
//				createddate = resultset.getTimestamp("createddate");
//				modifieddate = resultset.getTimestamp("modifieddate");
//				createdby = resultset.getString("createdby");
//				modifiedby = resultset.getString("modifiedby");
//				categoryModel = new CategoryModel(id, createddate, modifieddate, createdby, modifiedby, code, name);
//				//4
//		//2
//				categoryArrayList.add(categoryModel);
//			}
//			AbstractDAO.closeConnection(connection);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return categoryArrayList;
//	}

}
