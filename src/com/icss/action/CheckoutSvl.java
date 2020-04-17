package com.icss.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.biz.CarBiz;
import com.icss.entity.Book;
import com.icss.entity.Car;
import com.icss.entity.User;
import com.icss.util.Log;

/**
 * Servlet implementation class CheckoutSvl
 */
@WebServlet("/user/CheckoutSvl")
public class CheckoutSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收结算信息，购买数量存于session中
		Object obj = request.getSession().getAttribute("shopcar");
		Map<String,Integer> shopcar = (Map<String,Integer>)obj;
		Set<String> isbns =  shopcar.keySet();
		for(String isbn: isbns)
		{
			String buynum = request.getParameter("bk"+isbn);
			try {
				int iBuynum = Integer.parseInt(buynum);	
				shopcar.put(isbn, iBuynum);
			} catch (Exception e) {
			Log.logger.info(isbn + "图书购买数量输入错误!");
			}
			
		}
		//存入数据库，先删后存入
		User user = (User)request.getSession().getAttribute("user");
		CarBiz carBiz = new CarBiz();
		try {
			//删除
			carBiz.clearCar(user.getUname());
			//插入
			carBiz.insertCar(shopcar,user.getUname());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		BookBiz biz = new BookBiz();
		List<Book> books = new ArrayList<Book>();
		try {
			books=biz.getBooks(isbns);
			for(Book bk:books)
			{
				bk.setBuynum(shopcar.get(bk.getIsbn()));
			}
			request.setAttribute("books", books);
			double allMoney=0;
			for(Book bk:books)
			{
				allMoney += bk.getPrice()*bk.getBuynum();
			}
			request.setAttribute("allMoney", allMoney);
			request.getRequestDispatcher("/WEB-INF/main/checkout.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
