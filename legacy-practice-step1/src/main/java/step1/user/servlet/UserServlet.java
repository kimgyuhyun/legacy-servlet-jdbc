package step1.user.servlet;

import step1.user.dao.UserDao;
import step1.user.dto.UserDto;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;



@WebServlet("/user/*")
public class UserServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest  request, HttpServletResponse response)
		throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String path = request.getPathInfo();
		if ("/form".equals(path)) {
			request.getRequestDispatcher("/WEB-INF/user/user-form.jsp").forward(request, response);
			return;
		}
		
		response.sendError(HttpServletResponse.SC_NOT_FOUND);

		
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		String path = request.getPathInfo();
        
        try {
        	UserDao userDao = new UserDao(getServletContext());
        	
        	if ("/create".equals(path)) {
        		 String name = request.getParameter("name");
                 String ageStr = request.getParameter("age");
                 String birthDateStr = request.getParameter("birthDate");
                 String address = request.getParameter("address");
                 UserDto dto = new UserDto();
                 dto.setName(name);
                 dto.setAge(Integer.parseInt(ageStr));
                 dto.setBirthDate(Date.valueOf(birthDateStr));
                 dto.setAddress(address);
        		
                 userDao.insert(dto);
                 
                 response.sendRedirect(request.getContextPath() + "/user/form");
                 return;
        	}
        	
        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
        	throw new ServletException(e);
        }

	}
	
	
	
}
