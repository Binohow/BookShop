package com.icss.action.back;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.entity.Book;
import com.icss.util.Log;


/**
 * Servlet implementation class BookAddSvl
 */
@WebServlet("/back/BookAddSvl")
@MultipartConfig(maxFileSize = 2*1024*1024)
public class BookAddSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookAddSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/back/bookAdd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String isbn = request.getParameter("isbn");
		String bname = request.getParameter("bname");
		String author = request.getParameter("author");
		String press = request.getParameter("press");
		String pubdate = request.getParameter("pubdate");		
		Date dPubdate = null;
		try {
			if(pubdate != null && !pubdate.equals("")) {
				SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
				dPubdate = sd.parse(pubdate);	
			}			
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
		}
		String price = request.getParameter("price");
		double dPrice = 0;
		try {
			if(price != null && !price.equals("")) {
				dPrice = Double.parseDouble(price);	
			}			
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
		}	
		Book bk = new Book();
		bk.setIsbn(isbn);
		bk.setBname(bname);
		bk.setAuthor(author);
		bk.setPress(press);
		bk.setDiscount(1.0);
		bk.setInfo(bname);
		bk.setNum(0);
		bk.setPdate(dPubdate);
		bk.setPrice(dPrice);		
		try {			
			javax.servlet.http.Part part = request.getPart("pic");
			//获得图书写入磁盘的路径
			String path = request.getServletContext().getRealPath("/pic/products");
			String fname = path + "/" + part.getSubmittedFileName();
			part.write(fname);		
			bk.setPic("/pic/products/"+ part.getSubmittedFileName());
			request.setAttribute("book", bk);                       //当出错时，页面数据不丢失
			BookBiz biz = new BookBiz();
			biz.addBook(bk);
			request.setAttribute("msg", bname + "上传成功!");
			request.getRequestDispatcher("/WEB-INF/back/bookAdd.jsp").forward(request, response);
		}catch(java.lang.IllegalStateException e) {
			Log.logger.error(e.getMessage(),e);  
			request.setAttribute("msg", "上传文件超过了2M，请重新选择...");
			request.getRequestDispatcher("/WEB-INF/back/bookAdd.jsp").forward(request, response);
		} catch (SQLIntegrityConstraintViolationException e) {
			Log.logger.error(e.getMessage(),e);  
			request.setAttribute("msg", "isbn已存在，请重新输入...");
			request.getRequestDispatcher("/WEB-INF/back/bookAdd.jsp").forward(request, response);
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);    //给开发人员看的日志
			Log.logger.info("网络繁忙，稍后再试....");  //给直接用户友好提示
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}		
	}

}
