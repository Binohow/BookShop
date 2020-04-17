package com.icss.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.entity.Book;
import com.icss.util.Log;

/**
 * Servlet implementation class ShopcarSvl
 */
@WebServlet("/user/ShopcarSvl")
public class ShopcarSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopcarSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object user = request.getSession().getAttribute("user");
		if(user != null)
		{
			Object obj = request.getSession().getAttribute("shopcar");
			Map<String, Integer> shopcar = (Map<String, Integer>)obj;
			try {
				
				if(shopcar == null)
				{
					request.setAttribute("bkSize", -1);
					request.getRequestDispatcher("/WEB-INF/main/Shopcar.jsp").forward(request, response);
					
				}else 
				{
					Set<String> isbns = shopcar.keySet();
					List<Book> books = new BookBiz().getBooks(isbns);
					for(Book bk:books)
					{
						bk.setBuynum(shopcar.get(bk.getIsbn()));
					}
					request.setAttribute("books", books);
					request.setAttribute("bkSize", books.size());
					request.getRequestDispatcher("/WEB-INF/main/Shopcar.jsp").forward(request, response);
				}
				
			} catch (Exception e) {
				Log.logger.error(e.getMessage(),e);
				request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			}
		}else 
		{
			request.setAttribute("msg", "用户尚未登录，无法使用购物车...");
			request.getRequestDispatcher("/WEB-INF/main/login2.jsp");
		}
	}



}
