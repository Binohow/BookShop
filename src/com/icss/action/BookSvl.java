package com.icss.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.entity.Book;
import com.icss.util.Log;

/**
 * Servlet implementation class BookSvl
 */
@WebServlet("/BookSvl")
public class BookSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		BookBiz biz = new BookBiz();
		try {
			Book bk = biz.getBookInfo(isbn);
			request.setAttribute("book", bk);
			request.getRequestDispatcher("/WEB-INF/main/book.jsp").forward(request, response);	
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);    //给开发人员看的日志
			Log.logger.info("网络繁忙，稍后再试....");  //给直接用户友好提示
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}		
	}

	

}
