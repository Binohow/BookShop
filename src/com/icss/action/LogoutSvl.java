package com.icss.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutSvl
 */
@WebServlet("/user/LogoutSvl")
public class LogoutSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, 
			    HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().invalidate();  //清除session中的数据
	    //使用重定向转向主页
		String basePath = request.getContextPath() + "/";
		response.sendRedirect(basePath + "MainSvl");
	}

	

}
