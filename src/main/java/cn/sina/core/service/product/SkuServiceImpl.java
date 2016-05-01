package cn.sina.core.service.product;

import cn.itcast.common.page.Pagination;
import cn.sina.core.dao.product.ColorDao;
import cn.sina.core.dao.product.SkuDao;
import cn.sina.core.domain.product.Color;
import cn.sina.core.domain.product.Product;
import cn.sina.core.domain.product.Sku;
import cn.sina.core.query.product.SkuQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
/**
 * 最小销售单元事务层
 */
@Service
@Transactional
public class SkuServiceImpl implements SkuService {

	@Resource
	private SkuDao skuDao;
	
	@Resource
	private ColorDao colorDao;

	@Resource
	private ProductService productService;

	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addSku(Sku sku) {
		return skuDao.addSku(sku);
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public Sku getSkuByKey(Integer id) {
		Sku sku = skuDao.getSkuByKey(id);
		Color color = colorDao.getColorByKey(sku.getColorId());
		Product product = productService.getProductByKey(sku.getProductId());
		sku.setProduct(product);
		sku.setColor(color);
		return sku;
	}
	
	@Transactional(readOnly = true)
	public List<Sku> getSkusByKeys(List<Integer> idList) {
		return skuDao.getSkusByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id) {
		return skuDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return skuDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateSkuByKey(Sku sku) {
		return skuDao.updateSkuByKey(sku);
	}
	
	@Transactional(readOnly = true)
	public Pagination getSkuListWithPage(SkuQuery skuQuery) {
		Pagination p = new Pagination(skuQuery.getPageNo(),skuQuery.getPageSize(),skuDao.getSkuListCount(skuQuery));
		p.setList(skuDao.getSkuListWithPage(skuQuery));
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<Sku> getSkuList(SkuQuery skuQuery) {
		List<Sku> skuList = skuDao.getSkuList(skuQuery);
		//同时加载颜色
		if(skuList != null && skuList.size() > 0){
			for (Sku sku : skuList) {
				Color color = colorDao.getColorByKey(sku.getColorId());
				sku.setColor(color);
			}
		}
		return skuList;
	}
	
	/**
	 * 获取库存大于0的商品
	 * @param productId
	 * @return
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Sku> getStock(Integer productId) {
		List<Sku> skuList = skuDao.getStock(productId);
		//同时加载颜色
		if(skuList != null && skuList.size() > 0){
			for (Sku sku : skuList) {
				Color color = colorDao.getColorByKey(sku.getColorId());
				sku.setColor(color);
			}
		}
		return skuList;
	}
}
