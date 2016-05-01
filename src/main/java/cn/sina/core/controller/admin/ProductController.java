package cn.sina.core.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.common.page.Pagination;
import cn.sina.core.domain.product.Brand;
import cn.sina.core.domain.product.Color;
import cn.sina.core.domain.product.Feature;
import cn.sina.core.domain.product.Img;
import cn.sina.core.domain.product.Product;
import cn.sina.core.domain.product.Sku;
import cn.sina.core.domain.product.Type;
import cn.sina.core.query.product.BrandQuery;
import cn.sina.core.query.product.ColorQuery;
import cn.sina.core.query.product.FeatureQuery;
import cn.sina.core.query.product.ProductQuery;
import cn.sina.core.query.product.TypeQuery;
import cn.sina.core.service.freemarker.StaticPageService;
import cn.sina.core.service.product.BrandService;
import cn.sina.core.service.product.ColorService;
import cn.sina.core.service.product.FeatureService;
import cn.sina.core.service.product.ProductService;
import cn.sina.core.service.product.SkuService;
import cn.sina.core.service.product.TypeService;
import cn.sina.core.web.DateUtils;

/**
 * 商品控制器
 * @author Jaynnay
 *
 */
@Controller
public class ProductController {
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ColorService colorService;
	
	@Autowired
	private FeatureService featureService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private StaticPageService staticPageService;
	
	@Autowired
	private SkuService skuService;

	/**
	 * 加载商品列表页面
	 * 注意，这个url可能是从top.jsp中点进来的，也可能是从其他地方重定向过来的，比如添加一个商品后重定向到这
	 * 如果是从top.jsp里面点进来的，那么是没有搜索条件的，但是重定向过来的就有
	 * @return
	 */
	@RequestMapping(value = "/product/list.do")
	public String list(Integer brandId, Integer isShow, Integer pageNo ,String name, ModelMap model){
		
		//把所有的品牌查询出来，因为下拉选要用
		BrandQuery brandQuery = new BrandQuery();
		brandQuery.setFields("id,name");
		brandQuery.setIsDisplay(1);
		List<Brand> brands = brandService.getBrandList(brandQuery);
		model.addAttribute("brands", brands);
		
		//根据页面传入的条件查询商品
		ProductQuery productQuery = new ProductQuery();
		
		StringBuilder params = new StringBuilder();
		
		if(brandId != null){
			
			productQuery.setBrandId(brandId);
			
			params.append("&brandId=").append(brandId);
			
			model.addAttribute("brandId", brandId);
		}
		
		if(isShow != null){
			
			productQuery.setIsShow(isShow);
			
			params.append("&isShow=").append(isShow);
			
			model.addAttribute("isShow", isShow);
			
		}else{//如果为null，即从top中点进来的，默认为下架
			
			productQuery.setIsShow(0);
			
			params.append("&isShow=").append(0);
			
			model.addAttribute("isShow", 0);
		}
		
		if(StringUtils.isNotBlank(name)){
			
			productQuery.setName(name);
			
			productQuery.setNameLike(true);
			
			params.append("&name=").append(name);
			
			model.addAttribute("name", name);
		}
		
		productQuery.setPageNo(Pagination.cpn(pageNo));
		
		productQuery.setPageSize(5);
		
		productQuery.orderbyId(false);
		
		Pagination pagination =  productService.getProductListWithPage(productQuery);
		
		pagination.pageView("/product/list.do", params.toString());
		
		model.addAttribute("pagination", pagination);
		
		return "product/list";
	}
	
	/**
	 * 跳转到商品添加页面
	 * add页面需要的数据有：商品类型，商品品牌，材质，颜色
	 * @return
	 */
	@RequestMapping(value = "/product/toAdd.do")
	public String toAdd(ModelMap model){
		
		//1.商品类型
		TypeQuery typeQuery = new TypeQuery();
		typeQuery.setFields("id,name");
		typeQuery.setIsDisplay(1);//看数据库
		List<Type> types = typeService.getTypeList(typeQuery);
		model.addAttribute("types", types);
		
		//2.商品品牌
		BrandQuery brandQuery = new BrandQuery();
		brandQuery.setFields("id,name");
		brandQuery.setIsDisplay(1);
		List<Brand> brands = brandService.getBrandList(brandQuery);
		model.addAttribute("brands", brands);
		
		//3.材质
		FeatureQuery featureQuery = new FeatureQuery();
		featureQuery.setFields("id,name");
		featureQuery.setIsDel(1);
		List<Feature> features = featureService.getFeatureList(featureQuery);
		model.addAttribute("features", features);
		
		//4.颜色
		ColorQuery colorQuery = new ColorQuery();
		colorQuery.setFields("id,name");
		colorQuery.setParentId(0);//看ColorDao.xml中，是！=0
		List<Color> colors = colorService.getColorList(colorQuery);
		model.addAttribute("colors",colors);
		
		return "product/add";
	}
	
	/**
	 * 商品添加 注意img
	 * @param product
	 * @param img
	 * @return
	 */
	@RequestMapping(value = "/product/add.do")
	public String add(Product product , Img img){
		
		product.setImg(img);
		product.setNo(DateUtils.dateFilename());
		product.setCreateTime(new Date());
		
		productService.addProduct(product);
		
		return "redirect:/product/list.do";
	}
	
	/**
	 * 商品上架功能
	 * @return
	 */
	@RequestMapping(value = "/product/isShow.do")
	public String isShow(Integer[] ids,Integer pageNo , String name , Integer brandId , Integer isShow , ModelMap model){
		
		Product product = new Product();
		product.setIsShow(1);
		
		if(ids != null && ids.length > 0){
			for (Integer id : ids) {
				product.setId(id);
				productService.updateProductByKey(product);
				//使用freemarker生成静态页面
				Map<String,Object> root = new HashMap<String, Object>();
				
				//通过id加载商品
				Product p = productService.getProductByKey(id);
				root.put("product", p);
				
				//加载商品库存大于0的sku
				List<Sku> skuList = skuService.getStock(id);
				root.put("skus", skuList);
				
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
				root.put("colors", colors);
				//freemarker的Service
				staticPageService.productDetail(root, id);
			}
		}
		
		if(pageNo != null){
			model.addAttribute("pageNo",pageNo);
		}
		if(name != null){
			model.addAttribute("name",name);
		}
		if(brandId != null){
			model.addAttribute("brandId",brandId);
		}
		if(isShow != null){
			model.addAttribute("isShow",isShow);
		}
		
		return "redirect:/product/list.do";
	}
	
	@RequestMapping(value = "/product/delete.do")
	public String delete(Integer[] ids,Integer pageNo , String name , Integer brandId , Integer isShow , ModelMap model){
		
		if(ids != null && ids.length > 0){
			for (Integer id : ids) {
				productService.deleteByKey(id);
			}
		}
		
		if(pageNo != null){
			model.addAttribute("pageNo",pageNo);
		}
		if(name != null){
			model.addAttribute("name",name);
		}
		if(brandId != null){
			model.addAttribute("brandId",brandId);
		}
		if(isShow != null){
			model.addAttribute("isShow",isShow);
		}
		
		return "redirect:/product/list.do";
	}
	
	@RequestMapping(value = "/product/toEdit.shtml")
	public String toEdit(Integer id, ModelMap model){
		Product product = productService.getProductByKey(id);
		model.addAttribute("prodcut",product);
		
		//1.商品类型
		TypeQuery typeQuery = new TypeQuery();
		typeQuery.setFields("id,name");
		typeQuery.setIsDisplay(1);//看数据库
		List<Type> types = typeService.getTypeList(typeQuery);
		model.addAttribute("types", types);
		
		//2.商品品牌
		BrandQuery brandQuery = new BrandQuery();
		brandQuery.setFields("id,name");
		brandQuery.setIsDisplay(1);
		List<Brand> brands = brandService.getBrandList(brandQuery);
		model.addAttribute("brands", brands);
		
		//3.材质
		FeatureQuery featureQuery = new FeatureQuery();
		featureQuery.setFields("id,name");
		featureQuery.setIsDel(1);
		List<Feature> features = featureService.getFeatureList(featureQuery);
		model.addAttribute("features", features);
		
		//4.颜色
		ColorQuery colorQuery = new ColorQuery();
		colorQuery.setFields("id,name");
		colorQuery.setParentId(0);//看ColorDao.xml中，是！=0
		List<Color> colors = colorService.getColorList(colorQuery);
		model.addAttribute("colors",colors);
		return "product/edit";
	}
}
