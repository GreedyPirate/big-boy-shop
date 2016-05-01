package cn.sina.core.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 后台页面跳转
 * @author Jaynnay
 * @date 2016-1-20 下午10:42:52
 */
@Controller
@RequestMapping(value = "/control")
public class CenterController {

	/**
	 * @Author: 杨杰
	 * @Description: 后台首页
	 * @CreateDate: 2016-1-20
	 * @Params: 
	 * @Return: String
	 */
	@RequestMapping(value = "/index.do")
	public String index(){
		return "index";
	}
	/**
	 * @Author: 杨杰
	 * @Description: 后台顶部页面
	 * @CreateDate: 2016-1-20
	 * @Params: 
	 * @Return: String
	 */
	@RequestMapping(value = "/top.do")
	public String top(){
		return "top";
	}
	/**
	 * @Author: 杨杰
	 * @Description: 后台主体页面
	 * @CreateDate: 2016-1-20
	 * @Params: 
	 * @Return: String
	 */
	@RequestMapping(value = "/main.do")
	public String main(){
		return "main";
	}
	/**
	 * @Author: 杨杰
	 * @Description: 主体页面中的左部页面
	 * @CreateDate: 2016-1-20
	 * @Params: 
	 * @Return: String
	 */
	@RequestMapping(value = "/left.do")
	public String left(){
		return "left";
	}
	/**
	 * @Author: 杨杰
	 * @Description: 主体页面中的右部页面
	 * @CreateDate: 2016-1-20
	 * @Params: 
	 * @Return: String
	 */
	@RequestMapping(value = "/right.do")
	public String right(){
		return "right";
	}
}
