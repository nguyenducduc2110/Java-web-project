package com.webtutorial.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.inject.Inject;

import com.webtutorial.dao.INewsDAO;
import com.webtutorial.model.NewsModel;
import com.webtutorial.paging.Pageable;
import com.webtutorial.service.INewsService;
public class NewsService implements INewsService{

	@Inject
	private INewsDAO newsDAO;
	
	@Override
	public NewsModel findByCategoryId(Long id) {
		return newsDAO.findByCategoryId(id);
	}
	
	//Sau khi save thì trả về object đã save để hiện bài báovừa save
	@Override
	public NewsModel save(NewsModel newsModel) {
		newsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		Long id = newsDAO.save(newsModel);
		return newsDAO.findOne(id);
		
	}

	@Override
	public NewsModel update(NewsModel newsModel) {
	    Long id = newsModel.getId();
	    NewsModel newsModelOld = newsDAO.findOne(id);
	    newsModelOld.setModifiedDate(new Timestamp(System.currentTimeMillis()));

	    if (newsModel.getTitle() != null) {
	        newsModelOld.setTitle(newsModel.getTitle());
	    }
	    if (newsModel.getThumbnail() != null) {
	        newsModelOld.setThumbnail(newsModel.getThumbnail());
	    }
	    if (newsModel.getShortDescription() != null) {
	        newsModelOld.setShortDescription(newsModel.getShortDescription());
	    }
	    if (newsModel.getCategoryId() != null) {
	        newsModelOld.setCategoryId(newsModel.getCategoryId());
	    }
	    if (newsModel.getModifiedDate() != null) {
	        newsModelOld.setModifiedDate(newsModel.getModifiedDate());
	    }
	    if (newsModel.getModifiedBy() != null) {
	        newsModelOld.setModifiedBy(newsModel.getModifiedBy());
	    }

	    //set người thay đổi sẽ lấy tên tài khoản
	    newsDAO.update(newsModelOld);
	    return newsDAO.findOne(id);
	}


	@Override
	public void delete(Long[] id) {//Nhận vào 1 mảng Vì:Xoá 1 or nhiều bài viết
		for (Long idNews : id) {
			//Delete bài viết thì phải delete cả comment trong bài viết.
			//1.delete comment (khoa ngoai new_id)->Xoá ngọn xong đến gốc(Còn insert thì gốc xong đến ngọn)
			//2.delete news
			newsDAO.delete(idNews);
		}
	}

	@Override
	public ArrayList<NewsModel> findAll(Pageable pageable) {
		return newsDAO.findAll(pageable);
	}

	@Override
	public NewsModel findOne(Long id) {
		return newsDAO.findOne(id);
	}
	
}
