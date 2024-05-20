package com.webtutorial.dao;

import java.util.ArrayList;

import com.webtutorial.model.NewsModel;
import com.webtutorial.paging.Pageable;

public interface INewsDAO {
	public abstract NewsModel findByCategoryId(Long id);
	Long save(NewsModel newsModel);
	void update(NewsModel newsModel);
	NewsModel findOne(Long id);
	void delete(Long id);
	ArrayList<NewsModel> findAll(Pageable pageable);
	
}
