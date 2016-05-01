package cn.sina.core.controller;

import cn.itcast.common.page.Pagination;
import cn.sina.core.domain.product.*;
import cn.sina.core.query.product.BrandQuery;
import cn.sina.core.query.product.FeatureQuery;
import cn.sina.core.query.product.ProductQuery;
import cn.sina.core.query.product.TypeQuery;
import cn.sina.core.service.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台商品控制器
 * @author Jaynnay
 *
 */
@Controller
public class FrontProductController {
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FeatureService featureService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	SkuService skuService;

	@RequestMapping(value = "/product/display/list.shtml")
	public String index(Integer pageNo,Integer brandId,String brandName,Integer typeId,String typeName,ModelMap model){
		
		ProductQuery productQuery = new ProductQuery();
		
		StringBuilder params = new StringBuilder();
		
		productQuery.setPageNo(Pagination.cpn(pageNo));
		
		productQuery.setPageSize(8);
		
		//设置Id倒排
		productQuery.orderbyId(false);
		
		//隐藏已选条件
		boolean flag = false;
		//条件Map窗口
		Map<String,String> query = new LinkedHashMap<String,String>();
		
		
		//1.商品类型
		if(null != typeId){
			productQuery.setTypeId(typeId);
			flag = true;
			//显示在页面
			model.addAttribute("typeId", typeId);
			model.addAttribute("typeName", typeName);
			
			query.put("类型", typeName);
			
			params.append("&").append("typeId=").append(typeId).append("&typeName=").append(typeName);
		}else{
			TypeQuery typeQuery = new TypeQuery();
			typeQuery.setFields("id,name");
			typeQuery.setIsDisplay(1);//看数据库
			List<Type> types = typeService.getTypeList(typeQuery);
			model.addAttribute("types", types);
		}
		
		//2.商品品牌
		if(null != brandId){
			productQuery.setBrandId(brandId);
			flag = true;
			//显示在页面
			model.addAttribute("brandId", brandId);
			model.addAttribute("brandName", brandName);
			
			query.put("品牌", brandName);
			
			params.append("&").append("brandId=").append(brandId).append("&brandName=").append(brandName);
		}else{
			BrandQuery brandQuery = new BrandQuery();
			brandQuery.setFields("id,name");
			brandQuery.setIsDisplay(1);
			List<Brand> brands = brandService.getBrandList(brandQuery);
			model.addAttribute("brands", brands);
		}
		
		//3.材质
		FeatureQuery featureQuery = new FeatureQuery();
		featureQuery.setFields("id,name");
		featureQuery.setIsDel(1);
		List<Feature> features = featureService.getFeatureList(featureQuery);
		model.addAttribute("features", features);
		
		model.addAttribute("flag", flag);
		//条件
		model.addAttribute("query", query);
		
		Pagination pagination =  productService.getProductListWithPage(productQuery);
		
		pagination.pageView("/product/display/list.shtml", params.toString());
		
		model.addAttribute("pagination", pagination);
		
		return "product/product";
	}
	
	@RequestMapping(value = "/product/detail.shtml")
			public String productDetail(ModelMap model,Integer id){
				//通过id加载商品
				Product product = productService.getProductByKey(id);
				model.addAttribute("product",product);

				//加载商品库存大于0的sku
				List<Sku> skuList = skuService.getStock(id);
				model.addAttribute("skus",skuList);

				//把所有sku中不重复的颜色找出来
				List<Color> colors = new ArrayList<Color>();
				if(skuList !=null && skuList.size() > 0){
					for (Sku sku : skuList) {
						Color color = sku.getColor();
						if(!colors.contains(color)){
							colors.add(color);
						}
					}
		}
		model.addAttribute("colors",colors);
		return "product/productDetail";
	}
}
