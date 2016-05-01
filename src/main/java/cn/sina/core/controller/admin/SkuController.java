package cn.sina.core.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sina.core.domain.product.Sku;
import cn.sina.core.query.product.SkuQuery;
import cn.sina.core.service.product.SkuService;
import cn.sina.core.web.ResponseUtils;

@Controller
public class SkuController {
	
	@Autowired
	private SkuService skuService;
	/**
	 * 加载商品的sku页面
	 * @param productId
	 * @param pno
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sku/list.do")
	public String skuList(Integer productId, String pno , ModelMap model){
		
		SkuQuery skuQuery = new SkuQuery();
		
		skuQuery.setProductId(productId);
		
		List<Sku> skuList = skuService.getSkuList(skuQuery);
		
		model.addAttribute("productId",productId);
		model.addAttribute("skuList",skuList);
		model.addAttribute("pno",pno);
		
		return "sku/list";
	}
	
	/**
	 * 保存对sku的修改
	 * @param sku
	 * @param response
	 */
	@RequestMapping(value = "/sku/add.do")
	public void skuUpdate(Sku sku, HttpServletResponse response){
		ResponseUtils.renderText(response, "保存成功");
		skuService.updateSkuByKey(sku);
	}

}
