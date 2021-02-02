package join;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/joinlist")
public class JoinListController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String field = req.getParameter("field");
		String query = req.getParameter("query");
		String page_ = req.getParameter("p");
		
		int page = 1 ;

		JoinService ss = new JoinService();
		
		if (query == null || field == null) {
			field = "l.LOCNAME";
			query = "";
		}
		
		if (page_ != null) {
			page = Integer.parseInt(req.getParameter("p"));
		}
		
		int count = ss.JoinCount();
		
		List<Join> list = ss.JoinList(field, query, page);
		
		req.setAttribute("count", count);
		req.setAttribute("list", list);
		req.getRequestDispatcher("JoinList.jsp").forward(req, resp);
	}
}