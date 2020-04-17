package com.icss.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.entity.Book;
import com.icss.util.Log;

/**
 * Servlet implementation class BookSearch
 */
@WebServlet("/BookSearchSvl")
public class BookSearchSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSearchSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/main/search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bname = request.getParameter("bname");
		BookBiz biz = new BookBiz();
		try {
			List<Book> bk = biz.searchBook(bname);
			request.setAttribute("book", bk);
			request.getRequestDispatcher("/WEB-INF/main/searched.jsp").forward(request, response);	
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);    //给开发人员看的日志
			Log.logger.info("网络繁忙，稍后再试....");  //给直接用户友好提示
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}		
	}

}
