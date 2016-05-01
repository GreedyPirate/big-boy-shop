package cn.sina.core.web.interceptor;

import cn.sina.core.domain.user.Buyer;
import cn.sina.core.web.Constants;
import cn.sina.core.web.session.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SpringmvcInterceptor implements HandlerInterceptor{

	@Autowired
	private SessionProvider sessionProvider;
	
	private final String INTERCEPT_URL = "/buyer";
	
	//执行方法前
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean isLogin = false;
		Buyer buyer = (Buyer) sessionProvider.getAttribute(request, Constants.BUYER_SESSION);
		if(buyer != null){
			isLogin = true;
			request.setAttribute("isLogin", isLogin);
			request.setAttribute("username", buyer.getUsername());
		}

		/*//拦截以"/buyer"开头的请求，这类请求必须要求用户登录
		if(request.getRequestURI().contains(INTERCEPT_URL)){
			if(buyer == null){
				response.sendRedirect("/buyer/login.shtml?returnUrl=" + request.getParameter("returnUrl"));
				return false;
			}
		}*/
		return true;
	}
	
	//执行方法后
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	//页面渲染后
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
