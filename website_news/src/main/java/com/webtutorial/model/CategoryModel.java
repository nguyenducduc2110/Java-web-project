package com.webtutorial.model;

import java.sql.Timestamp;

public class CategoryModel extends AbstractModel{
	private String code;
	private String name;
	public CategoryModel(Long id, Timestamp createdDate, Timestamp modifiedDate, String createdBy, String modifiedBy,
			String code, String name) {
		super(id, createdDate, modifiedDate, createdBy, modifiedBy);
		this.code = code;
		this.name = name;
	}
	public CategoryModel() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CategoryModel [code=" + code + ", name=" + name + ", " + super.toString() + "]";
	}

	
	
}
