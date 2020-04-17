package com.icss.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShopcarAddSvl
 */
@WebServlet("/user/ShopcarAddSvl")
public class ShopcarAddSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopcarAddSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		String isbn = request.getParameter("isbn");
		if(isbn == null)
		{
			throw new ServletException("isbn不能为空，无法加入购物车...");
		}
		Object user = request.getSession().getAttribute("user");
		if(user != null)
		{
			Object obj = request.getSession().getAttribute("shopcar");
			Map<String, Integer> shopmap = (Map<String, Integer>)obj;
			//判断isbn该ISBN是否在shopmap里面
			if(shopmap.containsKey(isbn))
			{
				shopmap.put(isbn, shopmap.get(isbn)+1);
			}else
			{
				 shopmap.put(isbn, 1);
			}
			int count = shopmap.size();
			request.getSession().setAttribute("count", count);
			
		    request.getRequestDispatcher("/user/ShopcarSvl").forward(request, response);
		}else 
		{
			request.setAttribute("msg", "用户尚未登录，无法使用购物车...");
			request.getRequestDispatcher("/WEB-INF/main/login2.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
