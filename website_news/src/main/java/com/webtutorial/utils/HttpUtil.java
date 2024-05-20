package com.webtutorial.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	private String value;
	
	public HttpUtil() {		
	}
	public HttpUtil(String value) {
		super();
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	//Luồng JSON là 1 chuỗi ObjectMapper().readValue dùng để đọc chuỗi JSON thành 1 object model
	public <T> T toModel(Class<T> isclass) {//biến isclass của Class<T> có thể tham chiếu đến mô hình của class là :NewsModel.class()
		try {
			//readValue ánh xạ chuỗi String key-value từ this.value thành Model isclass và trả về object isclass
			return new ObjectMapper().readValue(this.value, isclass);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//method có kiểu HttpUtil  of này  tức là nhận 1 cái luồng và lấy dữ liệu từ luồng và trả về đối tượng HttpUtil 
	//với dữ liệu vừa lấy từ luồng là 1 string.
	public static HttpUtil of(BufferedReader bufferedReader) {
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		try {
			while((line = bufferedReader.readLine())!= null) {
				stringBuilder.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new HttpUtil(stringBuilder.toString());	
	}
	
}
