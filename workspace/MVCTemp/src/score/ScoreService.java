package score;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScoreService {
	String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String root = "root";
	String pw = "dkssud";
	
	public List<Score> ScoreList(String field, String query) {
		List<Score> list = new ArrayList();
		String sql = "Select * from score where "+field+" like ?"; // 검색어
		System.out.println(sql);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, "%"+query+"%");
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
			String locid = rs.getString("locid");
			String enemy = rs.getString("enemy");
			String regdate = rs.getString("regdate");
			String hiter = rs.getString("hiter");
			String content = rs.getString("content");
			String memo = rs.getString("memo");
			
			Score sc = new Score();
			sc.setLocid(locid);
			sc.setEnemy(enemy);
			sc.setRegdate(regdate);
			sc.setHiter(hiter);
			sc.setContent(content);
			sc.setMemo(memo);
			
			list.add(sc);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
