package com.icss.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.CarBiz;
import com.icss.entity.Car;
import com.icss.biz.UserBiz;
import com.icss.entity.User;
import com.icss.exception.InputEmptyException;
import com.icss.util.Log;

@WebServlet("/LoginSvl2")
public class LoginSvl2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSvl2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			       HttpServletResponse response) throws ServletException, IOException {
		//使用Dispatcher对象从控制器转发到JSP页面
		request.getRequestDispatcher("/WEB-INF/main/login2.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		UserBiz biz = new UserBiz();
		PrintWriter out = response.getWriter();
		CarBiz carBiz = new CarBiz();
		try {
			User user = biz.login(uname, pwd);
			if(user != null) {
				Log.logger.info(user.getUname() + "登录成功!");
				Log.logger.info(user.getUname() + "角色是:" + user.getRole());
				//登录成功，转向书城主页
				request.getSession().setAttribute("user",user );
				Map<String,Integer>  shopcar = new HashMap<String,Integer>();
				List<Car> car = carBiz.getCar(user.getUname());  
				for(Car item : car)
				{
				  shopcar.put(item.getIsbn(), item.getCount());
				}
				int count = car.size();
			    request.getSession().setAttribute("count", count);
				request.getSession().setAttribute("shopcar",shopcar );
				out.print("1");    
			}else {
				Log.logger.info("用户登录失败");
				request.setAttribute("msg","用户名或密码输入错误，请重新输入...." );
				out.print("2");
			}
		} catch (InputEmptyException e) {	
			Log.logger.info("用户名密码为空，请重新输入...");
			request.setAttribute("msg","用户名或密码输入为空，请重新输入...." );
			out.print("0");
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);    //给开发人员看的日志
			Log.logger.info("网络繁忙，稍后再试....");  //给直接用户友好提示
			out.print("-1");
		}
		out.flush();
		out.close();
	}
}
