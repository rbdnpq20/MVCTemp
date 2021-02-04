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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ids_[] = req.getParameterValues("c");
		int ids[] = new int[ids_.length];
		
		for (int i = 0; i<ids_.length; i++) {
			ids[i] = Integer.parseInt(ids_[i]);
		}
		for (String i : ids_) {
			System.out.println("선택된 값 : "+ i);	
		}
		
		JoinService js = new JoinService();
		js.removeJoinAll(ids);
		
		resp.sendRedirect("joinlist");
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String field = req.getParameter("field");
		String query = req.getParameter("query");
		String page_ = req.getParameter("p");
		
		int page = 1 ;

		JoinService ss = new JoinService();
		
		if (query == null || field == null || field.equals("")) {
			field = "locname";
			query = "";
		}
		
		if (page_ != null) {
			page = Integer.parseInt(req.getParameter("p"));
		}
		
		int count = ss.JoinCount(field, query);
		List<Join> list = ss.JoinList(field, query, page);
		
		req.setAttribute("count", count);
		req.setAttribute("list", list);
		req.getRequestDispatcher("JoinList.jsp").forward(req, resp);
	}
}