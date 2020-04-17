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
			//判断已经登录用户的身份
			User loginUser = (User)user;
			if(loginUser.getRole() == IRole.ADMIN) {
				chain.doFilter(request, response);  //向下访问资源
			}else {
				request.setAttribute("msg","你的权限不够，请重新登录...");
				req.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
			}		
		}else {
			//尚未登录，转到登录页
			request.setAttribute("msg","访问受限资源，需要提前登录...");
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
