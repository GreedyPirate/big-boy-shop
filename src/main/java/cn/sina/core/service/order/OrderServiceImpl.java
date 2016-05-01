package cn.sina.core.service.order;


import cn.itcast.common.page.Pagination;
import cn.sina.core.dao.order.OrderDao;
import cn.sina.core.domain.order.Detail;
import cn.sina.core.domain.order.Order;
import cn.sina.core.domain.shop_cart.ShopCart;
import cn.sina.core.domain.shop_cart.ShopItem;
import cn.sina.core.query.order.OrderQuery;
import cn.sina.core.web.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * 订单
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrderDao orderDao;

	@Autowired
	private DetailService detailService;
	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addOrder(Order order, ShopCart shopCart) {

		order.setPayableFee(shopCart.getTotalPrice());//总价 = 商品总价 + 运费
		order.setTotalPrice(shopCart.getTotalProductPrice());//商品总价
		order.setDeliverFee(shopCart.getPostage());//运费
		order.setOid(DateUtils.dateFilename());//订单号

		if(order.getPaymentWay() == 0){
			//支付方式为到付时，支付状态为到付
			order.setIsPaiy(0);
		}else{
			//其他都是待付款装填
			order.setIsPaiy(1);
		}
		order.setState(0);//订单状态：提交订单
		order.setCreateDate(new Date());//创建时间
		//保存订单
		Integer rows = orderDao.addOrder(order);

		//同时保存订单中的条目:对应购物车中的条目
		List<ShopItem> shopItems = shopCart.getShopItems();

		//保存每一个条目
		for (ShopItem item : shopItems){
			Detail detail = new Detail();
			detail.setOrderId(order.getId());//mybaits-SelectKey
			detail.setAmount(item.getAmount());
			detail.setColor(item.getSku().getColor().getName());
			detail.setProductName(item.getSku().getProduct().getName());
			detail.setProductNo(item.getSku().getProduct().getNo());
			detail.setSize(item.getSku().getSize());
			detail.setSkuPrice(item.getSku().getSkuPrice());

			detailService.addDetail(detail);
		}

		return order.getId();
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public Order getOrderByKey(Integer id) {
		return orderDao.getOrderByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Order> getOrdersByKeys(List<Integer> idList) {
		return orderDao.getOrdersByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id) {
		return orderDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return orderDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateOrderByKey(Order order) {
		return orderDao.updateOrderByKey(order);
	}
	
	@Transactional(readOnly = true)
	public Pagination getOrderListWithPage(OrderQuery orderQuery) {
		Pagination p = new Pagination(orderQuery.getPageNo(),orderQuery.getPageSize(),orderDao.getOrderListCount(orderQuery));
		p.setList(orderDao.getOrderListWithPage(orderQuery));
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<Order> getOrderList(OrderQuery orderQuery) {
		return orderDao.getOrderList(orderQuery);
	}
}
