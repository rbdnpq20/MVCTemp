package score;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/scoreinsert")
public class ScoreInsert extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.getRequestDispatcher("Scoreinsert.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = 0;
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String locid = req.getParameter("locid");
		String enemy = req.getParameter("enemy");
		String hiter = req.getParameter("hiter");
		String content = req.getParameter("content");
		String memo = req.getParameter("memo");
		
		Score sc = new Score();
		sc.setLocid(locid);
		sc.setEnemy(enemy);
		sc.setHiter(hiter);
		sc.setContent(content);
		sc.setMemo(memo);
		
		ScoreService scs = new ScoreService();
		result = scs.insertScore(sc);
		
		resp.sendRedirect("joinlist");
		}
	}
