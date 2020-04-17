package com.icss.action;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.UserBiz;
import com.icss.cons.IRole;
import com.icss.entity.User;
import com.icss.util.Log;

/**
 * Servlet implementation class RegistSvl
 */
@WebServlet("/RegistSvl")
public class RegistSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/main/regist2.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		UserBiz biz = new UserBiz();
		User user = new User();
		user.setUname(uname);
		user.setRole(IRole.COMMON_USER);  //ע��ֻ������ͨ�û�
		user.setPwd(pwd);
		user.setAccount(0);
		user.setAddress("ɽ��ʡԭƽ��");
		user.setPhonenum("17693417794");
		try {
			biz.regist(user);
			Log.logger.info(user.getUname() + "ע��ɹ�");
			//ע��ɹ���ת����¼ҳ��
			request.setAttribute("msg", "ע��ɹ������¼...");
			request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
		} catch (SQLIntegrityConstraintViolationException e) {
			request.setAttribute("msg", "�û����Ѵ��ڣ�����������...");
			request.getRequestDispatcher("/WEB-INF/main/regist.jsp").forward(request, response);
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}

}
