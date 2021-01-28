package temp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/templist")
public class TempListController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TempService ts = new TempService();
		List<Temp> list = ts.select();
		
		req.setAttribute("templist", list);
		req.getRequestDispatcher("/TempList.jsp").forward(req, resp);
	}
}
