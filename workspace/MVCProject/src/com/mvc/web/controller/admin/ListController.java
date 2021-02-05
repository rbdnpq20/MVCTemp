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

@WebServlet("/admin/board/notice/list") //4
public class ListController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	resp.setContentType("text/html;charset=UTF-8");
	
	String[] openIds = req.getParameterValues("open");
	String[] delIds = req.getParameterValues("del");
	String cmd = req.getParameter("cmd");
	System.out.println("cmd : "+ cmd);
	
	switch(cmd) {
		case "보기":
			for (String s : openIds) {
				System.out.println("open : "+s);
			}
			break;
			
		case "삭제" :
			for (String s : delIds) {
				System.out.println("del : "+s);
			}
			int [] ids = new int[delIds.length];
			
			for (int i=0 ; i<ids.length ; i++) {
				ids[i] = Integer.parseInt(delIds[i]);
			}
			NoticeService ns = new NoticeService();
			int result = ns.deleteNoticeAll(ids);
			break;
	}
	
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
		req.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(req, resp); //3
		}
	}