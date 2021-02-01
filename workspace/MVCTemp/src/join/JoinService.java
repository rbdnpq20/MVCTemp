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
		
		int start = 1 + (page - 1) * 2 ;
		int end = page * 2 ;
		
		List<Join> list = new ArrayList();
		String sql = "select l.LOCNAME as LOCATION, s.REGDATE as DATE, u.NAME, s.ENEMY, s.CONTENT, s.seq "
					+ "from USER u, SCORE s, LOCATION l "
					+ "where l.id = s.locid and u.id = s.hiter and "+ field +" like ?"
					+ "and s.seq between ? and ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, "%"+query+"%");
			psmt.setInt(2, start);
			psmt.setInt(3, end);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
			String location = rs.getString("LOCATION");
			Date date = rs.getDate("DATE");
			String name = rs.getString("NAME");
			String enemy = rs.getString("ENEMY");
			String content = rs.getString("CONTENT");
			int seq = rs.getInt("seq");
			
			Join sc = new Join();
			sc.setLocation(location);
			sc.setDate(date);
			sc.setName(name);
			sc.setEnemy(enemy);
			sc.setContent(content);
			sc.setSeq(seq);
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
			
			String locid = rs.getString("locid");
			String enemy = rs.getString("enemy");
			String regdate = rs.getString("regdate");
			String hiter = rs.getString("hiter");
			String content = rs.getString("content");
			String memo = rs.getString("memo");
			seq = rs.getInt("seq");
			
			sc.setLocid(locid);
			sc.setEnemy(enemy);
			sc.setRegdate(regdate);
			sc.setHiter(hiter);
			sc.setContent(content);
			sc.setMemo(memo);
			sc.setSeq(seq);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sc;
	}

}
