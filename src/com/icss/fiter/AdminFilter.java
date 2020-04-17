package com.icss.fiter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.icss.cons.IRole;
import com.icss.entity.User;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/back/*")
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		Object user = req.getSession().getAttribute("user");
		if(user != null) {
			//�ж��Ѿ���¼�û������
			User loginUser = (User)user;
			if(loginUser.getRole() == IRole.ADMIN) {
				chain.doFilter(request, response);  //���·�����Դ
			}else {
				request.setAttribute("msg","���Ȩ�޲����������µ�¼...");
				req.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
			}		
		}else {
			//��δ��¼��ת����¼ҳ
			request.setAttribute("msg","����������Դ����Ҫ��ǰ��¼...");
			req.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);			
		}		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
