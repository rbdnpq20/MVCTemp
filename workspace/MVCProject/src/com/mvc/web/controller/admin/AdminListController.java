package com.mvc.web.controller.admin;

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

@WebServlet("/admin/list") //4
public class AdminListController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	String c[] = req.getParameterValues("c");
	int ids[] = new int[c.length];
	
	for (String i : c) {
		System.out.println("선택된 값 : "+ i);	
	}
	
	for (int i=0; i<c.length ; i++) {
		ids[i] = Integer.parseInt(c[i]);
	}
	
	NoticeService ns = new NoticeService();
	int result = ns.removeNoticeAll(ids);
	
	
	resp.sendRedirect("list");
}	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoticeService ns = new NoticeService();

		//파라미터 받기
		String field_ = req.getParameter("field");
		String query_ = req.getParameter("query");
		String page_ = req.getParameter("p");
		
		// 첫 페이지일때 (매개변수 초기화)
		int page = 1;
		String field = "title";
		String query = "";
		
		// 뭐라도 값이 들어오면 값 대체
		if (field_ != null && !(field_.equals(""))) {
			field = field_;
		}
		
		if (query_ != null && !(query_.equals(""))) {
			query = query_;
				
		}
		
		if (page_ != null) {
			page = Integer.parseInt(req.getParameter("p"));
		}
		
		System.out.println("page : "+ page);
		System.out.println("field : "+ field);
		System.out.println("query : "+ query);
		
		int count = 0 ;
		count = ns.getNoticeCount(field, query);
		System.out.println(count);
				
		List<Notice> list = ns.getNoticeList(page, field, query); //1
		
		req.setAttribute("list", list); // list 보내기
		req.setAttribute("count", count); // 조회 된 목록 갯수
		req.getRequestDispatcher("/WEB-INF/view/admin/AdminList.jsp").forward(req, resp); //3
		}
	}