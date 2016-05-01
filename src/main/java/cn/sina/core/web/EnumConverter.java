package cn.sina.core.web;

import org.springframework.core.convert.converter.Converter;

import cn.sina.core.domain.user.Buyer.Gender;

public class EnumConverter implements Converter<String, Gender>{

	@Override
	public Gender convert(String source) {
		String value = source.trim();
	    if ("".equals(value)) {
	      return null;
	    }
		return Gender.getValue(source);
	}

}
