package score;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/scorelist")
public class ScoreListController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String field = req.getParameter("field");
		String query = req.getParameter("query");
		
		ScoreService ss = new ScoreService();

		if (query == null || field == null) {
			field = "locid";
			query = "";
		}
		System.out.println(field);
		System.out.println(query);
		
		List<Score> list = ss.ScoreList(field, query);
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("ScoreList.jsp").forward(req, resp);
	}
}
