package join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import score.Score;

public class JoinService {
	String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String root = "root";
	String pw = "dkssud";
	
	public List<Join> JoinList(String field, String query, int page) {
		int start = 1 + (page - 1) * 4 ;
		int end = page * 4 ;
		
		List<Join> list = new ArrayList();
		String sql = "Select * " 
				+	"from (Select @rownum:=@rownum+1 as num , n.* "
				+	"from (select s.seq, l.locname, u.name, s.regdate, s.enemy, s.content from " 
				+	"(select id, name " 
				+	"from user)u, " 
				+	"(select locid, hiter, regdate, enemy, seq, content "
				+	"from score)s, " 
				+	"(select id, locname " 
				+	"from location)l " 
				+	"where l.id = s.locid and u.id = s.hiter and "+field+" like ? )n "
				+	"Where (@rownum:=0)=0) num "
			    +   "where num.num between ? and ?" ;
		
//		String sql = "select * from "
//		 + "(select s.seq, l.locname, u.name, s.regdate, s.enemy, s.content from "
//		 + 		"(select id, name "
//		 +			"from user)u, "
//		 +		"(select locid, hiter, regdate, enemy, seq, content "
//		 +			"from score)s, "
//		 +		"(select id, locname " 
//		 +			"from location)l "
//		 +	"where l.id = s.locid and u.id = s.hiter)result "
//		 +	"where +"+field+" like ? and result.seq between ? and ? ";
		
		System.out.println(sql);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, "%"+query+"%");
			psmt.setInt(2, start);
			psmt.setInt(3, end);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
			int seq = rs.getInt("seq");
			String location = rs.getString("locname");
			Date date = rs.getDate("regdate");
			String name = rs.getString("name");
			String enemy = rs.getString("enemy");
			String content = rs.getString("content");

			
			Join sc = new Join();
			sc.setSeq(seq);
			sc.setLocation(location);
			sc.setDate(date);
			sc.setName(name);
			sc.setEnemy(enemy);
			sc.setContent(content);
			list.add(sc);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Score JoinDetail(int seq) {
		String sql = "Select * from score where seq=? ";
		Score sc = new Score();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, seq);
			ResultSet rs = psmt.executeQuery();

			rs.next();
			seq = rs.getInt("seq");
			String locid = rs.getString("locid");
			String enemy = rs.getString("enemy");
			String regdate = rs.getString("regdate");
			String hiter = rs.getString("hiter");
			String content = rs.getString("content");
			String memo = rs.getString("memo");

			sc.setSeq(seq);
			sc.setLocid(locid);
			sc.setEnemy(enemy);
			sc.setRegdate(regdate);
			sc.setHiter(hiter);
			sc.setContent(content);
			sc.setMemo(memo);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sc;
	}
	
	public int JoinCount(String field, String query) {
		String sql = "Select count(num.seq) as count " 
			+	"from (Select @rownum:=@rownum+1 as num , n.* "
			+	"from (select s.seq, l.locname, u.name, s.regdate, s.enemy, s.content from " 
			+	"(select id, name " 
			+	"from user)u, " 
			+	"(select locid, hiter, regdate, enemy, seq, content "
			+	"from score)s, " 
			+	"(select id, locname " 
			+	"from location)l " 
			+	"where l.id = s.locid and u.id = s.hiter and "+field+" like ?)n "
			+	"Where (@rownum:=0)=0) num" ;
		

//   이거는 rows넘 안써서 만든거
//		String sql ="select count(seq) from "
//				 + "(select s.seq, l.locname, u.name, s.regdate, s.enemy, s.content from "
//				 + 		"(select id, name "
//				 +			"from user)u, "
//				 +		"(select locid, hiter, regdate, enemy, seq, content "
//				 +			"from score)s, "
//				 +		"(select id, locname " 
//				 +			"from location)l "
//				 +	"where l.id = s.locid and u.id = s.hiter)result "
//				 +	"where +"+field+" like ?";
		
		int count = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, "%"+query+"%");
			ResultSet rs = psmt.executeQuery();

			rs.next();
			count = rs.getInt("count");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}