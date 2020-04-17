package com.icss.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.UserBiz;
import com.icss.entity.User;
import com.icss.exception.InputEmptyException;
import com.icss.util.Log;

@WebServlet("/LoginSvl")
public class LoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			       HttpServletResponse response) throws ServletException, IOException {
		//使用Dispatcher对象从控制器转发到JSP页面
		request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		UserBiz biz = new UserBiz();
		try {
			User user = biz.login(uname, pwd);
			if(user != null) {
				Log.logger.info(user.getUname() + "登录成功!");
				Log.logger.info(user.getUname() + "角色是:" + user.getRole());
				//登录成功，转向书城主页
				//request.setAttribute("user",user );
				request.getSession().setAttribute("user",user );
				request.getRequestDispatcher("/MainSvl").forward(request, response);
			}else {
				Log.logger.info("用户登录失败");
				request.setAttribute("msg","用户名或密码输入错误，请重新输入...." );
				request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
			}
		} catch (InputEmptyException e) {	
			Log.logger.info("用户名密码为空，请重新输入...");
			request.setAttribute("msg","用户名或密码输入为空，请重新输入...." );
			request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);    //给开发人员看的日志
			Log.logger.info("网络繁忙，稍后再试....");  //给直接用户友好提示
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}
}
