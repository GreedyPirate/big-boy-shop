package cn.sina.core.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 页面跳转
 * @author Jaynnay
 *
 */
@Controller
@RequestMapping(value = "/control")
public class FrameController {
	
	/**
	 * 加载商品模块主页面
	 * @return
	 */
	@RequestMapping(value = "/frame/product_main")
	public String product_main(){
		return "frame/product_main";
	}

	/**
	 * 加载商品模块左侧页面
	 * @return
	 */
	@RequestMapping(value = "/frame/product_left")
	public String product_left(){
		return "frame/product_left";
	}
	
	/**
	 * 加载订单模块主页面
	 * @return
	 */
	@RequestMapping(value = "/frame/order_main")
	public String order_main(){
		return "frame/order_main";
	}

	/**
	 * 加载订单模块左侧页面
	 * @return
	 */
	@RequestMapping(value = "/frame/order_left")
	public String order_left(){
		return "frame/order_left";
	}
}

