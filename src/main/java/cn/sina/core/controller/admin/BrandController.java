package cn.sina.core.controller.admin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.common.page.Pagination;
import cn.sina.core.domain.product.Brand;
import cn.sina.core.service.product.BrandService;

/**
 * 品牌控制器
 * @author Jaynnay
 *
 */
@Controller
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	
	@RequestMapping(value = "/brand/list.do")
	public String list(String name,Integer isDisplay,Integer pageNo, ModelMap model){
		
		StringBuilder params = new StringBuilder();
		
		//封装brand对象
		Brand brand = new Brand();
		
		if(StringUtils.isNotBlank(name)){
			brand.setName(name);
			params.append("name=").append(name);
		}
		
		if(isDisplay!=null){
			brand.setIsDisplay(isDisplay);
			params.append("&isDisplay=").append(isDisplay);
		}else{
			//默认为1
			brand.setIsDisplay(1);
			params.append("&isDisplay=").append(1);
		}
		
		brand.setPageSize(5);
		
		//如果pageNo==null或小于1，重置为1
		brand.setPageNo(Pagination.cpn(pageNo));
		
		Pagination pagination = brandService.getBrandWithPage(brand);
		
		//分页
		pagination.pageView("/brand/list.do", params.toString());
		
		//放到request中
		model.addAttribute("pagination", pagination);
		
		//回显
		model.addAttribute("isDisplay", isDisplay);
		model.addAttribute("name",name);
		
		return "brand/list";
	}
	
	/**
	 * 跳转至添加页面
	 * @return
	 */
	@RequestMapping(value = "/brand/toAdd.do")
	public String toAdd(){
		
		return "brand/add";
	}
	
	/**
	 * 添加品牌
	 * @return
	 */
	@RequestMapping(value = "/brand/add.do")
	public String add(Brand brand){
		
		brandService.addBrand(brand);
		
		return "redirect:/brand/list.do";
	}
	
	/**
	 * 删除品牌
	 * @param id
	 * @param name
	 * @param isDisplay
	 * @return
	 */
	@RequestMapping(value = "/brand/delete.do")
	public String delete(Integer id, String name, Integer isDisplay, ModelMap model){
		
		brandService.deleteBrandByKey(id);
		
		if(StringUtils.isNotBlank(name)){
			model.addAttribute("name", name);
		}
		
		if(isDisplay != null){
			model.addAttribute("isDisplay", isDisplay);
		}
		
		return "redirect:/brand/list.do";
	}
	
	/**
	 * 批量删除品牌
	 * @param ids 每个商品的checkbox的name都是ids
	 * @param name
	 * @param isDisplay
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/brand/deleteMore.do")
	public String deleteMore(Integer[] ids, String name, Integer isDisplay, ModelMap model){
		
		brandService.deleteBrandsByIds(ids);
		
		if(StringUtils.isNotBlank(name)){
			model.addAttribute("name", name);
		}
		
		if(isDisplay != null){
			model.addAttribute("isDisplay", isDisplay);
		}
		
		return "redirect:/brand/list.do";
	}
	
	/**
	 * 跳转到品牌修改页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/brand/toEdit.do")
	public String toEdit(Integer id, ModelMap model){
		
		Brand brand = brandService.getBrandById(id);
		
		model.addAttribute("brand", brand);
		
		return "brand/edit";
	}
	
	/**
	 * 提交品牌修改
	 * @param brand
	 * @return
	 */
	@RequestMapping(value = "/brand/edit.do")
	public String edit(Brand brand){
		
		brandService.updateBrand(brand);
		
		return "redirect:/brand/list.do";
	}
}
