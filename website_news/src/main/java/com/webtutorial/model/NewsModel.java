package com.webtutorial.model;

import java.sql.Timestamp;

public class NewsModel extends AbstractModel{
	private String title;
	private String thumbnail;
	private String shortDescription;
	private String content;
	private Long categoryId;
	public NewsModel(Long id, Timestamp createdDate, Timestamp modifiedDate, String createdBy, String modifiedBy,
			String title, String thumbnail, String shortDescription, String content, Long categoryId) {
		super(id, createdDate, modifiedDate, createdBy, modifiedBy);
		this.title = title;
		this.thumbnail = thumbnail;
		this.shortDescription = shortDescription;
		this.content = content;
		this.categoryId = categoryId;
	}
	public NewsModel() {
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	@Override
	public String toString() {
		return "NewsModel [title=" + title + ", thumbnail=" + thumbnail + ", shortDescription=" + shortDescription
				+ ", content=" + content + ", categoryId=" + categoryId + ", getId()=" + getId() + ", getCreatedDate()="
				+ getCreatedDate() + ", getModifiedDate()=" + getModifiedDate() + ", getCreatedBy()=" + getCreatedBy()
				+ ", getModifiedBy()=" + getModifiedBy() + "]";
	}
	
	
}
