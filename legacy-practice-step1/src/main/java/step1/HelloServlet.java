package step1;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


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
		
		String dbStatusLine; // DB 성공/실패 문구를 담을 변수 선언
		try { // DB 연결 시도
			Properties props = new Properties(); // 설정 파일 키/값 담을 객체 생성
			try (InputStream  in = getServletContext().getResourceAsStream("/WEB-INF/db.properties")) {
				if (in == null) {
					throw new IllegalStateException(
							"ServletContext 에서 /WEB-INF/db.properties 를 찾을 수 없습니다.");
				}
				props.load(in); // db.properties에 연결된 바이트 스트림 통로에서 .properties 형식 텍스트를 읽어서
				// props 내부에 채워 넣음
			}
			
			String url = props.getProperty("jdbc.url");
			String user = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
			
			if (url == null || user == null || password == null) {
				throw new IllegalStateException(
						"jdbc.url, jdbc.username, jdbc,password 키가 db.properties 에 모두 있어야 합니다.");
			}
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try (Connection conn = DriverManager.getConnection(url, user, password)) {
				dbStatusLine = "DB 연결 성공 (getCatalog=" + conn.getCatalog() + ")";
			}
		} catch (Exception e) {
			dbStatusLine = "DB 연결 실패: " + e.getClass().getName() + " / " + e.getMessage();
		}
		
		
		out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'><title>Hello</title></head><body>");
        out.println("<h1>Hello Servlet</h1>");
        out.println("<p>컨텍스트 경로 다음에 <code>/hello</code> 로 매핑됨.</p>");
        out.println("<p>" + escapeHtml(dbStatusLine) + "</p>");
        out.println("</body></html>");
        out.flush();
		
	}
	
	private static String escapeHtml(String s) {
		if (s == null) {
			return "";
		}
		return s.replace("&", "&amp;")
				.replace("<", "&lt;")
				.replace(">", "&gt;")
				.replace("\"", "&quot;");
	}

}
