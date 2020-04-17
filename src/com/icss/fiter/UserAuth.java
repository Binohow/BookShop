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

/**
 * Servlet Filter implementation class UserAuth
 */
@WebFilter("/user/*")
public class UserAuth implements Filter {

    /**
     * Default constructor. 
     */
    public UserAuth() {
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
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req =(HttpServletRequest) request;
		Object user = req.getSession().getAttribute("user");
		// pass the request along the filter chain
		if(user!=null)
		{
			chain.doFilter(request, response);
		}else {
			request.setAttribute("msg", "访问受限资源，需要提前登录...");
			request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
