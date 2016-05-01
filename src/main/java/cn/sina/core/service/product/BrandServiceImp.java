package cn.sina.core.service.product;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.common.page.Pagination;
import cn.sina.core.dao.product.BrandDao;
import cn.sina.core.domain.product.Brand;
import cn.sina.core.query.product.BrandQuery;

@Service
@Transactional(readOnly=true,isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
public class BrandServiceImp implements BrandService{
	
	@Autowired
	private BrandDao brandDao;

	@Override
	@Transactional(readOnly=true)
	public Pagination getBrandWithPage(Brand brand) {
		/**
		 * Pagination用于分页的一个类，构造方法3个参数的解释
		 * 1.页号
		 * 2.每页大小 
		 * 3.总记录数
		 */
		int totalRow = brandDao.getBrandCount(brand);
		Pagination pagination = new Pagination(brand.getPageNo(),brand.getPageSize(),totalRow);
		
		//把品牌集合放到Pagination对象里面去
		pagination.setList(brandDao.getBrandWithPage(brand));
		
		return pagination;
	}

	/**
	 * 添加品牌:其实我应该判断下这个品牌是否已存在
	 */
	@Override
	@Transactional(readOnly=false)
	public void addBrand(Brand brand) {
		brandDao.addBrand(brand);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteBrandByKey(Integer id) {
		brandDao.deleteBrandByKey(id);
		
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteBrandsByIds(Integer[] ids) {
		brandDao.deleteBrandsByIds(ids);
	}

	@Override
	@Transactional(readOnly=true)
	public Brand getBrandById(Integer id) {
		return brandDao.getBrandById(id);
	}

	@Override
	@Transactional(readOnly=false)
	public void updateBrand(Brand brand) {
		brandDao.updateBrand(brand);
	}

	@Override
	public List<Brand> getBrandList(BrandQuery brandQuery) {
		return brandDao.getBrandList(brandQuery);
	}

}
