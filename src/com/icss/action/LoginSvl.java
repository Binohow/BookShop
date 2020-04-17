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
		//ʹ��Dispatcher����ӿ�����ת����JSPҳ��
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
				Log.logger.info(user.getUname() + "��¼�ɹ�!");
				Log.logger.info(user.getUname() + "��ɫ��:" + user.getRole());
				//��¼�ɹ���ת�������ҳ
				//request.setAttribute("user",user );
				request.getSession().setAttribute("user",user );
				request.getRequestDispatcher("/MainSvl").forward(request, response);
			}else {
				Log.logger.info("�û���¼ʧ��");
				request.setAttribute("msg","�û��������������������������...." );
				request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
			}
		} catch (InputEmptyException e) {	
			Log.logger.info("�û�������Ϊ�գ�����������...");
			request.setAttribute("msg","�û�������������Ϊ�գ�����������...." );
			request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
		} catch (Exception e) {
			Log.logger.error(e.getMessage(),e);    //��������Ա������־
			Log.logger.info("���緱æ���Ժ�����....");  //��ֱ���û��Ѻ���ʾ
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}
}
