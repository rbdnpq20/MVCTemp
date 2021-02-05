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
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String openid[] = req.getParameterValues("open");
		String deleteid[] = req.getParameterValues("delete");		
		String cmd = req.getParameter("cmd");
		
		switch(cmd) {
		case "보기":
			for (String s : openid) {
				System.out.println("open : "+s);
			}
			break;
			
		case "삭제" :
			for (String s : deleteid) {
				System.out.println("del : "+s);
			}
			
			int ids[] = new int[deleteid.length];
			
			for (int i=0 ; i<ids.length ; i++) {
				ids[i] = Integer.parseInt(deleteid[i]);
			}
			JoinService js = new JoinService();
			int result = js.deleteJoinAll(ids);
			break;
			}
	
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