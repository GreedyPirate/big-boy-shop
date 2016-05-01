package cn.sina.core.service.product;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.common.page.Pagination;
import cn.sina.core.dao.product.ProductDao;
import cn.sina.core.domain.product.Img;
import cn.sina.core.domain.product.Product;
import cn.sina.core.domain.product.Sku;
import cn.sina.core.query.product.ImgQuery;
import cn.sina.core.query.product.ProductQuery;
/**
 * 商品事务层
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Resource
	ProductDao productDao;
	
	@Resource
	ImgService imgService;
	
	@Resource
	SkuService skuService;
	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addProduct(Product product) {
		
		//执行完保存，才会有productId
		Integer i = productDao.addProduct(product);
		
		Img img = product.getImg();
		img.setProductId(product.getId());
		img.setIsDef(1);
		imgService.addImg(img);
		
		//保存SKU
		Sku sku = new Sku();
		//商品ID
		sku.setProductId(product.getId());
		//运费
		sku.setDeliveFee(10.00);
		//售价
		sku.setSkuPrice(0.00);
		//市场价
		sku.setMarketPrice(0.00);
		//库存
		sku.setStockInventory(0);
		//购买限制
		sku.setSkuUpperLimit(0);
		//添加时间
		sku.setCreateTime(new Date());
		//是否最新
		sku.setLastStatus(1);
		//商品
		sku.setSkuType(1);
		//销量
		sku.setSales(0);
		for(String color : product.getColor().split(",")){
			//颜色ID
			sku.setColorId(Integer.parseInt(color));
			
			for(String size : product.getSize().split(",")){
				//尺码
				sku.setSize(size);
				//保存SKu
				skuService.addSku(sku);
			}
		}
		return i;
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public Product getProductByKey(Integer id) {
		ImgQuery imgQuery = new ImgQuery();
		imgQuery.setProductId(id);
		List<Img> imgList = imgService.getImgList(imgQuery);
		
		Product product= productDao.getProductByKey(id);
		if(imgList != null && imgList.size() > 0){
			product.setImg(imgList.get(0));
		}
		return product;
	}
	
	@Transactional(readOnly = true)
	public List<Product> getProductsByKeys(List<Integer> idList) {
		return productDao.getProductsByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id) {
		return productDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return productDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateProductByKey(Product product) {
		return productDao.updateProductByKey(product);
	}
	
	@Transactional(readOnly = true)
	public Pagination getProductListWithPage(ProductQuery productQuery) {
		Pagination p = new Pagination(productQuery.getPageNo(),productQuery.getPageSize(),productDao.getProductListCount(productQuery));
		List<Product> products = productDao.getProductListWithPage(productQuery);
		if(products != null && products.size() > 0){
			//每一个查询出来的商品，必须要有图片
			for (Product product : products) {
				ImgQuery imgQuery = new ImgQuery();
				imgQuery.setIsDef(1);
				imgQuery.setProductId(product.getId());
				List<Img> imgList = imgService.getImgList(imgQuery);
				//一个商品只有一个图片
				product.setImg(imgList.get(0));
			}
		}
		p.setList(products);
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<Product> getProductList(ProductQuery productQuery) {
		return productDao.getProductList(productQuery);
	}
}
