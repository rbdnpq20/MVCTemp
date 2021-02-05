package com.mvc.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
				 + "		      order by id desc)n"
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
				String pubflag = rs.getString("pubflag");
				boolean pub = false;
				
				if (pubflag.equals("Y")) {
					pub = true;
				}

				Notice nt = new Notice(id, title, writerid, content, regDate, hit, files, pub);
				list.add(nt);
			}
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
				String pubflag = rs.getString("pubflag");
				boolean pub = false;
				
				if (pubflag.equals("Y")) {
					pub = true;
				}

				nt = new Notice(id, title, writerid, content, regDate, hit, files, pub);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nt;
	}
	
	public int getNoticeCount() {
		return getNoticeCount("title", "");
	}

	public int getNoticeCount(String field, String query) {
		int count = 0;
		String sql = "Select count(num.id) as count" 
				 + "  from (Select @rownum:=@rownum+1 as num , n.*" 
				 + "        from(select *"
				 + "               From notice"
				 + "			  where "+field+" like ?"  // %검색어% 
				 + "		      order by regdate desc)n"
				 + "        Where (@rownum:=0)=0) num " ;
		
		System.out.println(sql);
		List<Notice> list = new ArrayList<Notice>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, "%"+query+"%");
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int removeNoticeAll(int [] ids) { // list에서 체크해서 삭제
		String sql = "delete from notice where id=?";
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);

			for (int i=0 ; i <ids.length; i++) {
				int id = ids[i];
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setInt(1, id);
				psmt.executeUpdate();
				result++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result; 
	}
	
	public int insertNotice(Notice nt) { // 글쓰기
		 int rs = 0;
		 String sql = "insert into notice(title, writer_id, content, files, pubflag) "
		             + " values(?, ?, ?, ?, ?)" ;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, root, pw);
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, nt.getTitle());
				psmt.setString(2, nt.getWriterID());
				psmt.setString(3, nt.getContent());
				psmt.setString(4, nt.getFiles());
				String pub = "N";
				if(nt.getPub()==true) {
					pub = "Y";
				}
				psmt.setString(5, pub);
				rs = psmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return rs;
	}
	
	public int updateNotice(Notice nt) { // 글 수정
		return 0;
	}
	
	public int deleteNotice(int id) {
		String SQL = "Delete from notice where id= ? " ;
		int rs = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(SQL);
			psmt.setInt(1, id);
			rs = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs; 
	}
	
	public List<Notice> getNoticeNewList(Notice nt){
		return null;
	}

	public int deleteNoticeAll(int[] ids) {
		int result = 0;
		String params = "";
		
		for (int i = 0; i < ids.length; i++) {
			params += ids[i];
			if (i < ids.length - 1) {
				params += ",";
			}
		}
		
		String sql = "delete from notice where id in("+params+")";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			Statement st = con.createStatement();
			result = st.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result; 
	}
}
