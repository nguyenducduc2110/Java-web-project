package com.webtutorial.dao.impl;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.webtutorial.dao.INewsDAO;
import com.webtutorial.mapper.NewsMapper;
import com.webtutorial.model.NewsModel;
import com.webtutorial.paging.Pageable;

public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO{
	
	@Override
	public NewsModel findByCategoryId(Long id) {	
		return super.queryList("select * from news where = ?", new NewsMapper(), id).get(0);
	}

	@Override
	public Long save(NewsModel newsModel) {
		String sql = "insert into news(title, thumbnail, shortdescription, content, categoryid,"
				+ "createddate,  modifieddate, createdby, modifiedby) values(?,?,?,?,?,?,?,?,?);";
		return insert(sql, newsModel.getTitle(), newsModel.getThumbnail(),newsModel.getShortDescription(), newsModel.getContent(), newsModel.getCategoryId(), newsModel.getCreatedDate(), newsModel.getModifiedDate(), newsModel.getCreatedBy(), newsModel.getModifiedBy());
	}
	@Override
	public void update(NewsModel updateNew) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,");
		sql.append(" shortdescription = ?, content = ?, categoryid = ?,");
		sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?");
		update(sql.toString(), updateNew.getTitle(), updateNew.getThumbnail(), updateNew.getShortDescription(),
				updateNew.getContent(), updateNew.getCategoryId(), updateNew.getCreatedDate(), 
				updateNew.getCreatedBy(), updateNew.getModifiedDate(), 
				updateNew.getModifiedBy(), updateNew.getId());
	}
	@Override
	public NewsModel findOne(Long id) {
		ArrayList<NewsModel> arrayList = super.queryList("select * from news where id = ?;", new NewsMapper(), id);
		return arrayList.isEmpty() ? null: arrayList.get(0);
	}

	@Override
	public void delete(Long id) {
		super.update("delete from news where id = ?;", id);
	}

	@Override
	public ArrayList<NewsModel> findAll(Pageable pageable) {
		StringBuilder sql = new StringBuilder("select * from news");
		if( pageable.getSorter() != null && StringUtils.isNotBlank(pageable.getSorter().getSortBy()) && StringUtils.isNotBlank(pageable.getSorter().getSortBy())) {
			sql.append(" order by "+pageable.getSorter().getSortName()+" "+pageable.getSorter().getSortBy()+"");
		}
		if(pageable.getOffset()!= null && pageable.getLimit()!= null) {
			sql.append(" limit "+pageable.getOffset()+", "+pageable.getLimit()+" ");
		}	
		return super.queryList(sql.toString(), new NewsMapper());
	}
	
	
	
}
