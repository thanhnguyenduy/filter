package bean;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;

/**
 * Filter checks if LoginBean has loginIn property set to true. If it is not set
 * then request is being redirected to the login.xhml page.
 * 
 * @author itcuties
 *
 */
public class LoginFilter implements Filter {

	/**
	 * Checks if user is logged in. If not it redirects to the login.xhtml page.
	 */
	// public void doFilter(ServletRequest request, ServletResponse response,
	// FilterChain chain) throws IOException, ServletException {
	// // Get the loginBean from session attribute
	// HttpServletRequest req = (HttpServletRequest)request;
	// LoginBean loginBean =
	// (LoginBean)((HttpServletRequest)request).getSession().getAttribute("loginBean");
	//
	// // For the first application request there is no loginBean in the session
	// so user needs to log in
	// // For other requests loginBean is present but we need to check if user
	// has logged in successfully
	// if (loginBean == null || !loginBean.isLoggedIn()) {
	// String contextPath = req.getContextPath();
	// ((HttpServletResponse)response).sendRedirect(contextPath +
	// "/login.xhtml");
	// }else if(req.getRequestURI().contains("login")){
	// String contextPath = ((HttpServletRequest)request).getContextPath();
	// ((HttpServletResponse)response).sendRedirect(contextPath +
	// "/views/welcome.xhtml");
	// }else
	// {
	// chain.doFilter(request, response);
	// }
	//
	//
	// }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Get the loginBean from session attribute
		HttpServletRequest req1 = (HttpServletRequest) request;
		HttpServletResponse res1 = (HttpServletResponse) response;
		LoginBean loginBean = (LoginBean) ((HttpServletRequest) request).getSession().getAttribute("loginBean");

		String sessionUser = (String) req1.getSession().getAttribute("username");
		String curentPath = req1.getRequestURL().toString();

		// For the first application request there is no loginBean in the
		// session so user needs to log in
		// For other requests loginBean is present but we need to check if user
		// has logged in successfully
		if (sessionUser != null) {
			if (curentPath.contains("login")) {
				res1.sendRedirect(req1.getContextPath() + "/views/welcome.xhtml");
			} else {
				chain.doFilter(request, response);
			}
		} else {
			
			if (curentPath.contains("views")) {
				res1.sendRedirect(req1.getContextPath() + "/login.xhtml");
				
			} else {
				chain.doFilter(request, response);
			}
		} 

	}

	// public void doFilter(ServletRequest request, ServletResponse response,
	// FilterChain chain)
	// throws IOException, ServletException {
	// LoginBean loginBean =
	// (LoginBean)((HttpServletRequest)request).getSession().getAttribute("loginBean");
	// try {
	//
	// HttpServletRequest reqt = (HttpServletRequest) request;
	// HttpServletResponse resp = (HttpServletResponse) response;
	// HttpSession session = reqt.getSession(false);
	//
	// String reqURI = reqt.getRequestURI();
	// if (session != null && session.getAttribute("loginBean") != null) {
	// if (reqURI.indexOf("/login") >= 0 ) {
	// resp.sendRedirect(reqt.getContextPath() + "/views/welcome.xhtml");
	// } else {
	// chain.doFilter(request, response);
	// }
	// } else
	// if (reqURI.indexOf("/login") >= 0 || reqURI.indexOf("/public/") >= 0
	// || reqURI.contains("javax.faces.resource")) {
	// chain.doFilter(request, response);
	// } else {
	// resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
	// }
	//
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// }
	// }

	public void init(FilterConfig config) throws ServletException {
		// Nothing to do here!
	}

	public void destroy() {
		// Nothing to do here!
	}

}