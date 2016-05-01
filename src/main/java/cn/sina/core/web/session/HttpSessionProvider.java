package cn.sina.core.web.session;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpSessionProvider implements SessionProvider {

	@Override
	public void setAttribute(HttpServletRequest request, String key,
			Serializable value) {
		HttpSession session = request.getSession();
		if(session != null){
			session.setAttribute(key, value);
		}
	}

	@Override
	public Serializable getAttribute(HttpServletRequest request, String key) {
		HttpSession session = request.getSession();
		if(session != null){
			return (Serializable) session.getAttribute(key);
		}
		return null;
	}

	@Override
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session != null){
			session.invalidate();
		}
	}

	@Override
	public String getSessionID(HttpServletRequest request) {
		return request.getRequestedSessionId();
	}

}
