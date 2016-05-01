package cn.sina.core.service.product;

import java.util.List;

import cn.itcast.common.page.Pagination;
import cn.sina.core.domain.product.Brand;
import cn.sina.core.query.product.BrandQuery;

public interface BrandService {
	/**
	 * 通过条件查询品牌(带分页)
	 * @param brand
	 * @return
	 */
	public Pagination getBrandWithPage(Brand brand);
	
	/**
	 * 添加品牌
	 * @param brand
	 */
	public void addBrand(Brand brand);
	
	/**
	 * 删除单个品牌
	 * @param id
	 */
	public void deleteBrandByKey(Integer id);
	
	/**
	 * 批量删除品牌
	 * @param id
	 */
	public void deleteBrandsByIds(Integer[] ids);
	
	/**
	 * 根据主键id查询品牌
	 * @param id
	 * @return
	 */
	public Brand getBrandById(Integer id);
	
	/**
	 * 根据主键id更新品牌
	 * @param brand
	 */
	public void updateBrand(Brand brand);
	
	
	/**
	 * 查询品牌集合，主要是参数类型是BrandQuery
	 * @param brandQuery
	 * @return
	 */
	public List<Brand> getBrandList(BrandQuery brandQuery);
}
