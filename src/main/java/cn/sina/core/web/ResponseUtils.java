package cn.sina.core.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * 服务器向页面发送数据的工具类
 * 注意这种设计方式，提高了复用性
 * @author Jaynnay
 *
 */
public class ResponseUtils {
	/**
	 * 发送数据
	 * @param response
	 * @param contentType
	 * @param data
	 */
	public static void render(HttpServletResponse response , String contentType, String data){
		response.setContentType(contentType);
		try {
			response.getWriter().write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送json格式数据
	 * @param response
	 * @param data
	 */
	public static void renderJson(HttpServletResponse response , String data){
		render(response,"application/json?charset=UTF-8",data);
	}
	
	/**
	 * 发送xml格式数据
	 * @param response
	 * @param data
	 */
	public static void renderXml(HttpServletResponse response , String data){
		render(response,"text/xml?charset=UTF-8",data);
	}
	
	/**
	 * 发送文本格式数据
	 * @param response
	 * @param data
	 */
	public static void renderText(HttpServletResponse response , String data){
		render(response,"text/plain?charset=UTF-8",data);
	}
}
