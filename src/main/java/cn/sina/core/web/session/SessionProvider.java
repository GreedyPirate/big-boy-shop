package cn.sina.core.web.session;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public interface SessionProvider {
	public void setAttribute(HttpServletRequest request,String key,Serializable value);
	
	public Serializable getAttribute(HttpServletRequest request,String key);
	
	public void logout(HttpServletRequest request);
	
	public String getSessionID(HttpServletRequest request);
}
