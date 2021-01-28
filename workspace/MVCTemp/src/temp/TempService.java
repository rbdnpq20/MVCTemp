package temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TempService {
	
	String url = "jdbc:mysql://localhost:3306/home_work?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String root = "root";
	String pw = "dkssud";
	
	public List<Temp> select() {
		List<Temp> list = new ArrayList();
		String sql = "select * from temp";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
			String date = rs.getString("date");
			int temp = rs.getInt("temp");
			int locid = rs.getInt("locid");
			String writer = rs.getString("writer");
			
			Temp te = new Temp();
			te.setDate(date);
			te.setTemp(temp);
			te.setLocid(locid);
			te.setWriter(writer);
			list.add(te);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Temp detail(String date) {
		Temp te = new Temp();
		String sql = "select * from temp where date=?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, root, pw);
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, date);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
			date = rs.getString("date");
			int temp = rs.getInt("temp");
			int locid = rs.getInt("locid");
			String writer = rs.getString("writer");
		
			te.setDate(date);
			te.setTemp(temp);
			te.setLocid(locid);
			te.setWriter(writer);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return te;
	}
}
