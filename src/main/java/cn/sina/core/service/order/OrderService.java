package cn.sina.core.service.order;

import cn.itcast.common.page.Pagination;
import cn.sina.core.domain.order.Order;
import cn.sina.core.domain.shop_cart.ShopCart;
import cn.sina.core.query.order.OrderQuery;

import java.util.List;

public interface OrderService {
	/**
	 * 基本插入
	 * 
	 * @return
	 */
	public Integer addOrder(Order order, ShopCart shopCart);

	/**
	 * 根据主键查询
	 */
	public Order getOrderByKey(Integer id);

	/**
	 * 根据主键批量查询
	 */
	public List<Order> getOrdersByKeys(List<Integer> idList);

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id);

	/**
	 * 根据主键批量删除
	 * 
	 * @return
	 */
	public Integer deleteByKeys(List<Integer> idList);

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateOrderByKey(Order order);

	/**
	 * 根据条件查询分页查询
	 * 
	 * @param orderQuery
	 *            查询条件
	 * @return
	 */
	public Pagination getOrderListWithPage(OrderQuery orderQuery);

	/**
	 * 根据条件查询
	 * 
	 * @param orderQuery
	 *            查询条件
	 * @return
	 */
	public List<Order> getOrderList(OrderQuery orderQuery);
}
