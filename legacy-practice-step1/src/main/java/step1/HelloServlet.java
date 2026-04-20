package step1;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest  request, HttpServletResponse  response)
			throws ServletException, IOException {
		// HttpServleyRequest: URL, 쿼리스트링, 헤더, 쿠키, 파라미터, 세션 등이 여기로 들어옴
		// HttpServleyResponse: 상태 코드, 헤더, 응답 본문(body)쓰기 등 나가는 응답
		

		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter(); // getWriter: 문자(텍스트/HTML)로 응답 본문을 쓸 떄 쓰는 출력 스트림
		out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'><title>Hello</title></head><body>");
        out.println("<h1>Hello Servlet</h1>");
        out.println("<p>컨텍스트 경로 다음에 <code>/hello</code> 로 매핑됨.</p>");
        out.println("</body></html>");
        out.flush();
		
	}

}
