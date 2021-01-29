package join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JoinService {
	String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String root = "root";
	String pw = "dkssud";
	
	public List<Join> JoinList(String field, String query) {
		List<Join> list = new ArrayList();
		String sql = "select l.LOCNAME as LOCATION, s.REGDATE as DATE, u.NAME, s.ENEMY, s.CONTENT "
					+ "from USER u, SCORE s, LOCATION l "
					+ "where l.id = s.locid and u.id = s.hiter and "+ field +" like ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, "%"+query+"%");
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
			String location = rs.getString("LOCATION");
			String date = rs.getString("DATE");
			String name = rs.getString("NAME");
			String enemy = rs.getString("ENEMY");
			String content = rs.getString("CONTENT");
			
			Join sc = new Join();
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

}
