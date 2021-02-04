package com.mvc.web.controller.notice;

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

@WebServlet("/notice/delete") //4
public class NoticeListDelete extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoticeService ns = new NoticeService();
		int id = Integer.parseInt(req.getParameter("id"));
		
		int id_num = ns.deleteNotice(id);
		
		req.setAttribute("id", id_num);
		req.getRequestDispatcher("/WEB-INF/view/notice/NoticeDelete.jsp").forward(req, resp); //3
		}
	}