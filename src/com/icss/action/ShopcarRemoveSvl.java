package com.icss.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.dao.CarDao;
import com.icss.entity.Car;

/**
 * Servlet implementation class ShopcarRemoveSvl
 */
@WebServlet("/user/ShopcarRemoveSvl")
public class ShopcarRemoveSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopcarRemoveSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		Object obj = request.getSession().getAttribute("shopcar");
		Map<String, Integer> shopcar = (Map<String, Integer>)obj;
		shopcar.remove(isbn);
		int count = shopcar.size();
		request.getSession().setAttribute("count", count);
		request.getRequestDispatcher("/user/ShopcarSvl").forward(request, response);
	}

}
