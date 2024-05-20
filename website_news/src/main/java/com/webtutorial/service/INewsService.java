package com.webtutorial.service;

import java.util.ArrayList;

import com.webtutorial.model.NewsModel;
import com.webtutorial.paging.Pageable;

public interface INewsService {
	public abstract NewsModel findByCategoryId(Long id);
	NewsModel save(NewsModel newsModel);
	NewsModel update(NewsModel newsModel);
	void delete (Long[] id);
	 ArrayList<NewsModel> findAll(Pageable pageable);
	 NewsModel findOne(Long id);
}
