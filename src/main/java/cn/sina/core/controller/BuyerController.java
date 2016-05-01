package cn.sina.core.controller;

import cn.sina.core.common.md5.MD5Encrypt;
import cn.sina.core.domain.country.City;
import cn.sina.core.domain.country.Province;
import cn.sina.core.domain.country.Town;
import cn.sina.core.domain.user.Buyer;
import cn.sina.core.domain.user.Buyer.Gender;
import cn.sina.core.query.country.CityQuery;
import cn.sina.core.query.country.TownQuery;
import cn.sina.core.service.country.CityService;
import cn.sina.core.service.country.ProvinceService;
import cn.sina.core.service.country.TownService;
import cn.sina.core.service.user.BuyerService;
import cn.sina.core.web.Constants;
import cn.sina.core.web.ResponseUtils;
import cn.sina.core.web.session.SessionProvider;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class BuyerController {

	@Autowired
	private BuyerService buyerService;
	
	@Autowired
	private SessionProvider sessionProvider;
	
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	
	@Autowired
	private MD5Encrypt md5Encrypt;
	
	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private TownService townService;
	/**
	 * 跳转到登陆页面
	 * @return
	 */
	@RequestMapping(value = "/buyer/login.shtml",method = RequestMethod.GET)
	public String login(){
		return "buyer/login";
	}
	
	/**
	 * 用户登录表单提交
	 * @return
	 */
	@RequestMapping(value = "/buyer/login.shtml",method = RequestMethod.POST)
	public String login(String username,String password,String captcha,String returnUrl,HttpServletRequest request,ModelMap model){
		//判断验证码
		if(imageCaptchaService.validateResponseForID(sessionProvider.getSessionID(request), captcha)){
			Buyer buyer = buyerService.getBuyerByKey(username);//sql中根据username查
			if(buyer!=null && buyer.getPassword().equals(md5Encrypt.encrypt(password))){
				//如果验证码，用户名密码都正确，放到session中
				sessionProvider.setAttribute(request, Constants.BUYER_SESSION, buyer);
				if(StringUtils.isNotBlank(returnUrl)){
					return "redirect:" + returnUrl;
				}else{
					//个人中心
					return "redirect:/buyer/index.shtml" ;
				}
			}else{
				model.addAttribute("username", username);
				model.addAttribute("password", password);
				model.addAttribute("msg", "用户名不存在或密码错误");
				return "buyer/login";
			}
		}else{
			model.addAttribute("msg", "验证码错误");
			model.addAttribute("username", username);
			model.addAttribute("password", password);
			return "buyer/login";
		}
	}
	
	/**
	 * 退出登陆
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/shop/logout.shtml")
	public String logout(HttpServletRequest request){
		sessionProvider.logout(request);
		return "redirect:/product/display/list.shtml";
	}
	
	/**
	 * 跳转到注册页面
	 * @return
	 */
	@RequestMapping(value = "/buyer/regist.shtml",method = RequestMethod.GET)
	public String regist(){
		return "buyer/regist";
	}
	
	/**
	 * 用户注册表单提交
	 * @return
	 */
	@RequestMapping(value = "/buyer/regist.shtml",method = RequestMethod.POST)
	public String regist(Buyer buyer,String sex ,String returnUrl,HttpServletRequest request){
		if("male".equals(sex)){
			buyer.setGender(Gender.MAN);
		}else if("female".equals(sex)){
			buyer.setGender(Gender.WOMAN);
		}else{
			buyer.setGender(Gender.SECRECY);
		}
		//md5加密
		String pwd = buyer.getPassword();
		pwd = md5Encrypt.encrypt(pwd);
		buyer.setPassword(pwd);
		//注册时间
		buyer.setRegisterTime(new Date());
		//未被删除
		buyer.setIsDel(1);
		buyerService.addBuyer(buyer);
		//放入session中，注册就登陆了
		sessionProvider.setAttribute(request, Constants.BUYER_SESSION, buyer);
		if(StringUtils.isNotBlank(returnUrl)){
			return "redirect:" + returnUrl;
		}else{
			return "redirect:/product/display/list.shtml";
		}
	}
	/**
	 * 跳转到用户个人中心
	 * @return
	 */
	@RequestMapping(value = "/buyer/index.shtml")
	public String index(){
		return "buyer/index";
	}
	/**
	 * 加载用户个人资料
	 * @return
	 */
	@RequestMapping(value = "/buyer/profile.shtml")
	public String profile(HttpServletRequest request,ModelMap model){
		//从session中取出user
		Buyer buyerInSession = (Buyer) sessionProvider.getAttribute(request, Constants.BUYER_SESSION);
		Buyer buyer = buyerService.getBuyerByKey(buyerInSession.getUsername());
		//model到页面
		model.addAttribute("buyer", buyer);
		
		//定位用户的地址，省市县
		//省应该查出所有
		List<Province> allProvinces = provinceService.getProvinceList(null);
		model.addAttribute("provinces", allProvinces);
		//根据省查询市
		CityQuery cityQuery = new CityQuery();
		cityQuery.setProvince(buyer.getProvince());
		List<City> getCitiesByProvince = cityService.getCityList(cityQuery);
		model.addAttribute("citys", getCitiesByProvince);
		//根据市查询县
		TownQuery townQuery = new TownQuery();
		townQuery.setCity(buyer.getCity());
		List<Town> getTownsByCity = townService.getTownList(townQuery);
		model.addAttribute("towns", getTownsByCity);
		
		return "buyer/profile";
	}

	//选择省后加载市
	@RequestMapping(value = "/buyer/city.shtml")
	public void city(String provinceCode,HttpServletResponse response){
		CityQuery cityQuery = new CityQuery();
		cityQuery.setProvince(provinceCode);
		List<City> cities = cityService.getCityList(cityQuery);
		//转化为json对象
		JSONObject jo = new JSONObject();
		jo.put("cities", cities);
		/*
			转换后json
			{
				"cities":[
						{"id":16,"name":"太原市"},
						{"id":17,"name":"大同市"}
						]
			}
		 */
		ResponseUtils.renderJson(response, jo.toString());
	}
	//加载市后加载县
	@RequestMapping(value = "/buyer/town.shtml")
	public void town(String cityCode,HttpServletResponse response){
		TownQuery townQuery = new TownQuery();
		townQuery.setCity(cityCode);
		List<Town> townList = townService.getTownList(townQuery);
		//转成json
		JSONObject jo = new JSONObject();
		jo.put("towns", townList);
		ResponseUtils.renderJson(response,jo.toString());
	}

	/**
	 * 保存个人资料的修改
	 * @return
     */
	@RequestMapping(value = "/buyer/editProfile.shtml")
	public String editProfile(Buyer buyer,String sex){
		if("male".equals(sex)){
			buyer.setGender(Gender.MAN);
		}else{
			buyer.setGender(Gender.WOMAN);
		}
		buyerService.updateBuyerByKey(buyer);
		return "redirect:/buyer/profile.shtml";
	}

	/**
	 * 加载用户收货地址
	 * @return
	 */
	@RequestMapping(value = "/buyer/deliver_address.shtml")
	public String deliver_address(){
		return "buyer/deliver_address";
	}
	
}
