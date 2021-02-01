package com.mvc.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jdbc.app.entity.Notice;

public class NoticeService {
	String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String root = "root";
	String pw = "dkssud";
	
	public List<Notice> getNoticeList(){
		
		return getNoticeList(1, "title", "");
	}
	
	public List<Notice> getNoticeList(int page){
		
		return getNoticeList(page, "title", "");
	}
	
	public List<Notice> getNoticeList(int page, String field, String query){
		int start = 1 + (page - 1) * 10; // 1, 11, 21, 31
		int end = page * 10; // 10, 20, 30, 40

		String sql = "Select *" 
				 + "from (Select @rownum:=@rownum+1 as num , n.*" 
				 + "        from(select *"
				 + "               From notice"
				 + "			  where "+field+" like ?"  // %검색어% 
				 + "		      order by regdate desc)n"
				 + "        Where (@rownum:=0)=0) num " 
				 + "Where num.num between ? and ? ";
		
		System.out.println(sql);
		List<Notice> list = new ArrayList<Notice>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			// Statement st = con.createStatement();
			psmt.setString(1, "%"+query+"%");
			psmt.setInt(2, start);
			psmt.setInt(3, end);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writerid = rs.getString("writer_id");
				Date regDate = rs.getDate("regdate");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");

				Notice nt = new Notice(id, title, writerid, content, regDate, hit, files);
				list.add(nt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Notice> getList1(int page) { // 글 조회
		int start = 1 + (page - 1) * 10; // 1, 11, 21, 31
		int end = page * 10; // 10, 20, 30, 40

		String sql = "Select *" 
				+ "from (Select @rownum:=@rownum+1 as num , n.*" 
				+ "        from(select *"
				+ "               From notice" 
				+ "		      order by regdate desc)n"
				+ "        Where (@rownum:=0)=0) num " 
				+ " Where num.num between ? and ? ";

		List<Notice> list = new ArrayList<Notice>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			// Statement st = con.createStatement();
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writerid = rs.getString("writer_id");
				Date regDate = rs.getDate("regdate");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");

				Notice nt = new Notice(id, title, writerid, content, regDate, hit, files);
				list.add(nt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Notice> getNoticeList11() {
		List<Notice> list = new ArrayList();
		String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String root = "root";
		String pw = "dkssud";

		String sql = "select * from notice";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writerid = rs.getString("writer_id");
				Date regDate = rs.getDate("regdate");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");

				Notice nt = new Notice(id, title, writerid, content, regDate, hit, files);
				list.add(nt);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Notice getNoticeView() {

		Notice list = new Notice();
		String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String root = "root";
		String pw = "dkssud";

		String sql = "select * from notice";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();

			rs.next();
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String writerid = rs.getString("writer_id");
			Date regDate = rs.getDate("regdate");
			String content = rs.getString("content");
			int hit = rs.getInt("hit");
			String files = rs.getString("files");

			Notice nt = new Notice(id, title, writerid, content, regDate, hit, files);
			list = nt;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Notice count() {

		Notice list = new Notice();
		String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String root = "root";
		String pw = "dkssud";

		String sql = "select count(id) from notice";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();

			rs.next();
			int count = rs.getInt("count(id)");
			Notice nt = new Notice();
			nt.setHit(count);
			list = nt;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Notice getNoticeDetail(int id) {
		String sql = "Select * from notice where id=? ";
		Notice nt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			// Statement st = con.createStatement();
			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
				String title = rs.getString("title");
				String writerid = rs.getString("writer_id");
				Date regDate = rs.getTimestamp("regdate");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");

				nt = new Notice(id, title, writerid, content, regDate, hit, files);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nt;
	}
}
