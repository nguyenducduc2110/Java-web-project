package com.webtutorial.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.webtutorial.dao.impl.AbstractDAO;
import com.webtutorial.model.UserModel;

public class FormUtil {

	@SuppressWarnings("unchecked")
	public static <T> T toModel(Class<T> clazz, HttpServletRequest request) {//biến isclass của Class<T> có thể tham chiếu đến mô hình của class là :NewsModel.class()
		T object = null;
		try {
			object = clazz.getDeclaredConstructor().newInstance();//tạo 1 instance của mô hình mà clazz tham chiếu đến 
			//populate TT readValue
			BeanUtils.populate(object, request.getParameterMap());//key cùng tên vs thuộc tính của object thì nó gán value của key thuộc tính cùng tên đó.
			//Làm thế này thì ko cần: request.getParameter("maxPageItem")
		} catch (InstantiationException|InvocationTargetException | NoSuchMethodException e) {
			System.out.println(e.getMessage());
		} catch (IllegalAccessException e) {
			System.out.println(e.getMessage());

		}	
		return object;
	}
}
