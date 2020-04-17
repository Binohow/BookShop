package com.icss.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.UserBiz;

/**
 * Servlet implementation class UnameValidSvl
 */
@WebServlet("/UnameValidSvl")
public class UnameValidSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnameValidSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request,
			               HttpServletResponse response) throws ServletException, IOException {
	
		String uname = request.getParameter("uname");
		PrintWriter out = response.getWriter();   	//回应文本信息		
		if(uname == null || uname.equals("")) {
			out.print("0");                    		//用户名输入为空
		}else {
			UserBiz biz = new UserBiz();
			try {
				boolean bRet = biz.validUname(uname);	
				if(bRet) {
					out.print("1");      			//用户名已经存在
				}else {
					out.print("2");     			//用户不存在 
				}
			} catch (Exception e) {
				out.print("-1");     				//用户不存在 
			}			
		}
		out.flush();
		out.close();		
	}	
}
