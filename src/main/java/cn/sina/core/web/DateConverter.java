package cn.sina.core.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;
/**
 * 全局日期格式转换器
 * @author Jaynnay
 * @date 2016-1-20 下午4:50:54
 */
public class DateConverter implements WebBindingInitializer{

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
	}
	
}
