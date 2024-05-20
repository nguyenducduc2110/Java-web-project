package com.webtutorial.controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webtutorial.constant.SystemContant;
import com.webtutorial.model.NewsModel;
import com.webtutorial.paging.PageRequest;
import com.webtutorial.paging.Pageable;
import com.webtutorial.service.ICategoryService;
import com.webtutorial.service.INewsService;
import com.webtutorial.sort.Sorter;
import com.webtutorial.utils.FormUtil;
import com.webtutorial.utils.MessageUtil;



@WebServlet(urlPatterns = {"/admin-news"})
public class NewsController extends HttpServlet{

	
	/**
	 * 
	 */
	@Inject
	private INewsService newsService;
	@Inject
	private ICategoryService categoryService;
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
        
		










		

		String typeAction = request.getParameter("type");
		NewsModel newsModel = FormUtil.toModel(NewsModel.class, request);
		String view = "";
		
	if(typeAction.equalsIgnoreCase(SystemContant.LIST)) {
		
		
		
		Pageable pageable = new PageRequest(newsModel.getPage(), newsModel.getMaxPageItem(), 
											new Sorter(newsModel.getSortName(), newsModel.getSortBy()));	
		ArrayList<NewsModel> arrayListModelOffset = newsService.findAll(pageable);
		Pageable pageable2 = new PageRequest(null, null, null);
		ArrayList<NewsModel> arrayListALL = newsService.findAll(pageable2);
		newsModel.setTotalItem(arrayListALL.size());

		newsModel.setTotalPage((int)Math.ceil((double)newsModel.getTotalItem() / newsModel.getMaxPageItem()));
		
		request.setAttribute("list", arrayListModelOffset);
		request.setAttribute(SystemContant.MODEL, newsModel);
		
		view = "/views/admin/news/list.jsp";		
	}else if(typeAction.equalsIgnoreCase(SystemContant.EDIT)) {
		
		String id = request.getParameter("id");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");;
		if(id != null) {
			newsModel = newsService.findOne(Long.parseLong(id));
			request.setAttribute("newsModel", newsModel);
			
		}else {
			
		}
		MessageUtil.showMessage(request);
		request.setAttribute("categories", categoryService.findAll());
		view = "/views/admin/news/edit.jsp";
	}
	
	
	RequestDispatcher requestDispatcher = request.getRequestDispatcher(view);
	requestDispatcher.forward(request, response);
		






    }
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	    }
}
