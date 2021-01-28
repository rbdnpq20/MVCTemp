package temp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tempdetail")
public class TempDetail extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String date = req.getParameter("date");
		
		TempService ts = new TempService();
		Temp te = ts.detail(date);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(te.date);
		
		req.setAttribute("tempdetail", te);
		req.getRequestDispatcher("/TempDetail.jsp").forward(req, resp);;
		
	}

}
