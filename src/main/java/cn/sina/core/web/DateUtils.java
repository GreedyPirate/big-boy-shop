package cn.sina.core.web;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String dateFilename(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return dateFormat.format(new Date());
	}
}
