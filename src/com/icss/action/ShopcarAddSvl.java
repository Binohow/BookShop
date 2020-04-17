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
			throw new ServletException("isbn����Ϊ�գ��޷����빺�ﳵ...");
		}
		Object user = request.getSession().getAttribute("user");
		if(user != null)
		{
			Object obj = request.getSession().getAttribute("shopcar");
			Map<String, Integer> shopmap = (Map<String, Integer>)obj;
			//�ж�isbn��ISBN�Ƿ���shopmap����
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
			request.setAttribute("msg", "�û���δ��¼���޷�ʹ�ù��ﳵ...");
			request.getRequestDispatcher("/WEB-INF/main/login2.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
