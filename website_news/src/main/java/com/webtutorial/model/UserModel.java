package com.webtutorial.model;

import java.sql.Timestamp;

public class UserModel extends AbstractModel{	
	private String userName;
	private String fullName;
	private String password;
	private int status;
	private Long roleId;
//VD có 2 bảng user và role và trong bảng user có khoá ngoại là roleid -->Thì model trong java class user cũng phải có attribute là 1 object role.
	private RoleModel roleModel;
	public UserModel(Long id, Timestamp createdDate, Timestamp modifiedDate, String createdBy, String modifiedBy,
		String userName, String fullName, String password, int status, Long roleId, RoleModel roleModel) {
	super(id, createdDate, modifiedDate, createdBy, modifiedBy);
	this.userName = userName;
	this.fullName = fullName;
	this.password = password;
	this.status = status;
	this.roleId = roleId;
	this.roleModel = roleModel;
}

	public UserModel() {
		super();
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public RoleModel getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(RoleModel roleModel) {
		this.roleModel =  roleModel;
	}

	@Override
	public String toString() {
		return "UserModel [userName=" + userName + ", fullName=" + fullName + ", password=" + password + ", status="
				+ status + ", roleId=" + roleId + ", roleModel=" + this.roleModel + ", getId()=" + getId()
				+ ", getCreatedDate()=" + getCreatedDate() + ", getModifiedDate()=" + getModifiedDate()
				+ ", getCreatedBy()=" + getCreatedBy() + ", getModifiedBy()=" + getModifiedBy() + ", getPage()="
				+ getPage() + ", getMaxPageItem()=" + getMaxPageItem() + ", getTotalPage()=" + getTotalPage()
				+ ", getTotalItem()=" + getTotalItem() + ", getSortName()=" + getSortName() + ", getSortBy()="
				+ getSortBy() + ", toString()=" + super.toString() + "]";
	}

	

	
	
}
