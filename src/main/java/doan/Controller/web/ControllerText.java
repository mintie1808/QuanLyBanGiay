//test file

package doan.Controller.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class ControllerText
 */
public class ControllerText extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerText() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>" + "<head>" + "<title>SearchAccount</title>" + "</head>" + "</html>");
		out.println("<body>" + "<h1>ket qua tra dien thoai yeu cau cua bạn</h1>" + "</body>");
		out.println("<table border=1 cellPadding=1 cellSpacing=1>");
		String tenbao = request.getParameter("textthuebao");
		String newsql = "SELECT * FROM  Product__tb";
		if (tenbao != null && tenbao.length() != 0) {
			newsql = newsql + " where ProductName like '%" + tenbao + "%'";
		}
		String conStr = "jdbc:sqlserver://LAPTOP-MGRO6IPK\\SQLEXPRESS:1433;databaseName=Sql_web_ban__quan_ao;encrypt=true;trustServerCertificate=true;";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Connection con = null;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(conStr, "trongtruong", "truong123456");
			stmt = con.createStatement();
			rs = stmt.executeQuery(newsql);
			out.println("<tr>" + "<th>so thu tu </th>" + "<th>ProductName</th>" + "<th>Price</th>" + "<th>Color</th>"
					+ "</tr>\n ");
			if (rs != null) {
				for (int i = 1; rs.next();) {
					out.println("<tr>" + "<td>" + i + "</td>" + "<td>" + rs.getString(2) + "</td>" + "<td>"
							+ rs.getString(3) + "</td>" + "<td>" + rs.getString(4) + "</td>" + "</tr>");
				}
				out.println("</table>");
				rs.close();
				stmt.close();
				con.close();
			}
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
