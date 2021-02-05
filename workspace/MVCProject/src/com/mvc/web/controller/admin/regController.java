package com.mvc.web.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.app.entity.Notice;
import com.mvc.web.service.NoticeService;

@WebServlet("/admin/board/notice/reg")  
public class regController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	req.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String open = req.getParameter("open");
		boolean pub = Boolean.parseBoolean(open);
		
		Notice nt = new Notice();
		nt.setTitle(title);
		nt.setContent(content);
		nt.setWriterID("유규종");
		nt.setFiles("");
		nt.setPub(pub);
		int result = 0 ;
		
		NoticeService ns = new NoticeService();
		result = ns.insertNotice(nt);
		resp.sendRedirect("list");
		
		PrintWriter out = resp.getWriter();
		out.printf("title :"+ title + " ");
		out.printf("content :"+ content + " ");
		out.printf("open :"+ open + " ");
	}
}
