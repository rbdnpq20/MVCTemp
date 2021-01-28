package user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userlist")
public class UserListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserService us = new UserService();
		List<User> list = us.select();
		
		req.setAttribute("userlist", list);
		req.getRequestDispatcher("/UserList.jsp").forward(req, resp);
	}

}
