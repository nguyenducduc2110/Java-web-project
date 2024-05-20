package com.webtutorial.model;

import java.sql.Timestamp;

public class CommentModel extends AbstractModel{
	private String content;
	private Long userId;
	private Long newId;
	public CommentModel(Long id, Timestamp createdDate, Timestamp modifiedDate, String createdBy, String modifiedBy,
			String content, Long userId, Long newId) {
		super(id, createdDate, modifiedDate, createdBy, modifiedBy);
		this.content = content;
		this.userId = userId;
		this.newId = newId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getNewId() {
		return newId;
	}
	public void setNewId(Long newId) {
		this.newId = newId;
	}
	
}
