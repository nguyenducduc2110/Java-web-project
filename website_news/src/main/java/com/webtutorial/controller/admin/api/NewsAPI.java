package com.webtutorial.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webtutorial.model.NewsModel;
import com.webtutorial.service.INewsService;
import com.webtutorial.utils.HttpUtil;
import com.webtutorial.utils.SessionUtil;




@WebServlet( urlPatterns = {"/api-admin-news"})
public class NewsAPI extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private INewsService newsService;
		
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long startTime = System.currentTimeMillis();

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		HttpUtil httpUtil = HttpUtil.of(req.getReader());
		System.out.println(httpUtil.getValue());
		NewsModel newsModel =(NewsModel) httpUtil.toModel(NewsModel.class);





		String admin = (String) SessionUtil.getInstance().getValue(req, "fullName");
		
		newsModel.setCreatedBy(admin);
		
		
		
		System.out.println(newsModel.toString());
		
		
		newsModel = newsService.save(newsModel);
		

		
		ObjectMapper objectMapper = new ObjectMapper();
		
		objectMapper.writeValue(resp.getOutputStream(), newsModel);
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		HttpUtil httpUtil = HttpUtil.of(req.getReader());
		NewsModel newsModel = httpUtil.toModel(NewsModel.class);
		String admin = (String) SessionUtil.getInstance().getValue(req, "fullName");
		System.out.println("update:"+admin);
		newsModel.setModifiedBy(admin);
		NewsModel newsModel1  = newsService.update(newsModel);	
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(resp.getOutputStream(), newsModel1);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		HttpUtil httpUtil = HttpUtil.of(req.getReader());
		Long[] listId = httpUtil.toModel(Long[].class);
		newsService.delete(listId);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(resp.getOutputStream(), "{ }");
	}
	
}
