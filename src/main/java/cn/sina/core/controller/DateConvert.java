package cn.sina.core.controller;


import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DateConvert {

	@RequestMapping(value = "/test/date.do")
	public String test(String username,Date birthday){
		System.out.println(username);
		System.out.println(birthday);
		return "test";
	}

	/*
	 * 局部日期格式转换器
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
	}*/

}
