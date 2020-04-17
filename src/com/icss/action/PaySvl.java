package com.icss.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.biz.CarBiz;
import com.icss.biz.UserBiz;
import com.icss.entity.Book;
import com.icss.entity.User;
import com.icss.util.Log;

/**
 * Servlet implementation class PaySvl
 */
@WebServlet("/user/PaySvl")
public class PaySvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaySvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBiz biz = new UserBiz();
		User user = (User)request.getSession().getAttribute("user");
		Map<String,Integer> shopcar = (Map<String,Integer>)request.getSession().getAttribute("shopcar");
		String allMoney = request.getParameter("allMoney");
		double dAllMoney = Double.parseDouble(allMoney);
	    try {
	    	BookBiz bookBiz = new BookBiz();
			List<Book> books = bookBiz.getBooks(shopcar.keySet());
			//设置购买数量
			for(Book bk : books) {
				bk.setBuynum(shopcar.get(bk.getIsbn()));
			}	
			biz.payMoney(user, dAllMoney, books);
			//跟新session中的用户余额
			user.setAccount(user.getAccount()-dAllMoney);
			request.setAttribute("allMoney", dAllMoney);
			//删除购物车
			CarBiz carBiz = new CarBiz();
			carBiz.clearCar(user.getUname());
		    shopcar = new HashMap<String,Integer>();
		    request.getSession().setAttribute("shopcar",shopcar);
		    
		    int count = (int)request.getSession().getAttribute("count");
			count = 0;
			request.getSession().setAttribute("count", count);
			
			request.getRequestDispatcher("/WEB-INF/main/payOK.jsp").forward(request, response);	
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);    //给开发人员看的日志
			Log.logger.info("网络繁忙，稍后再试....");  //给直接用户友好提示
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}

}
