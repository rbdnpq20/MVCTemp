package com.mvc.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.app.entity.Notice;
import com.mvc.web.service.NoticeService;

@WebServlet("/list") //4
public class NoticeListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoticeService ns = new NoticeService();
		

		String field = req.getParameter("field");
		String query = req.getParameter("query");
		String page_ = req.getParameter("p");
		int page = 1;
		
		if (field==null || query==null || field.equals("")) {
			field ="title";
			query ="";
		}
		
		if (page_ != null) {
			page = Integer.parseInt(req.getParameter("p"));
		}
		
		System.out.println("field : "+ field);
		System.out.println("query : "+ query);
		
		int count = 0 ;
		count = ns.getNoticeCount(field, query);
		System.out.println(count);
				
		List<Notice> list = ns.getNoticeList(page, field, query); //1
		
		req.setAttribute("list", list); // list 보내기
		req.setAttribute("count", count); // 조회 된 목록 갯수
		req.getRequestDispatcher("WEB-INF/view/notice/NoticeList.jsp").forward(req, resp); //3
		}
	}