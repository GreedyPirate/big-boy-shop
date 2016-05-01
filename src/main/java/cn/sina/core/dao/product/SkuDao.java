package cn.sina.core.dao.product;

import java.util.List;

import cn.sina.core.domain.product.Sku;
import cn.sina.core.query.product.SkuQuery;


public interface SkuDao {

	/**
	 * 添加
	 * @param sku
	 */
	public Integer addSku(Sku sku);

	/**
	 * 根据主键查找
	 * @param skuQuery
	 */
	public Sku getSkuByKey(Integer id);

	/**
	 * 根据主键批量查找
	 * @param skuQuery
	 */
	public List<Sku> getSkusByKeys(List<Integer> idList);

	/**
	 * 根据主键删除
	 * @param skuQuery
	 */
	public Integer deleteByKey(Integer id);

	/**
	 * 根据主键批量删除
	 * @param skuQuery
	 */
	public Integer deleteByKeys(List<Integer> idList);

	/**
	 * 根据主键更新
	 * @param skuQuery
	 */
	public Integer updateSkuByKey(Sku sku);

	/**
	 * 分页查询
	 * @param skuQuery
	 */
	public List<Sku> getSkuListWithPage(SkuQuery skuQuery);

	/**
	 * 集合查询
	 * @param skuQuery
	 */
	public List<Sku> getSkuList(SkuQuery skuQuery);
	
	/**
	 * 总条数
	 * @param skuQuery
	 */
	public int getSkuListCount(SkuQuery skuQuery);
	
	/**
	 * 获取库存大于0的商品
	 * @param productId
	 * @return
	 */
	public List<Sku> getStock(Integer productId);

}
