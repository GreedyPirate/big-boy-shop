package cn.sina.core.controller;

import cn.sina.core.domain.order.Order;
import cn.sina.core.domain.product.Sku;
import cn.sina.core.domain.shop_cart.ShopCart;
import cn.sina.core.domain.shop_cart.ShopItem;
import cn.sina.core.domain.user.Buyer;
import cn.sina.core.service.order.OrderService;
import cn.sina.core.service.product.SkuService;
import cn.sina.core.web.Constants;
import cn.sina.core.web.session.SessionProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jaynnay on 2016/4/19.
 */
@Controller
public class FrontOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SessionProvider sessionProvider;

    @Autowired
    private SkuService skuService;

    /**
     * 提交订单
     * @return
     */
    @RequestMapping(value = "/buyer/confirmOrder.shtml")
    public String confirmOrder(Order order, HttpServletRequest request, HttpServletResponse response, ModelMap model){
        //取出购物车，因为购物车和订单中信息共用
        ObjectMapper om = new ObjectMapper();
        ShopCart shopCart = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Constants.CART_COOKIE)) {
                    String cartJson = cookie.getValue();
                    try {
                        shopCart = om.readValue(cartJson, ShopCart.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        //填充购物车中的数据
        List<ShopItem> shopItems = shopCart.getShopItems();
        for (ShopItem shopItem : shopItems){
            Sku sku = skuService.getSkuByKey(shopItem.getSku().getId());
            shopItem.setSku(sku);
        }

        //设置订单所属用户
        Buyer buyer = (Buyer) sessionProvider.getAttribute(request, Constants.BUYER_SESSION);
        order.setBuyerId(buyer.getUsername());
        Integer orderId = orderService.addOrder(order,shopCart);

        //提交订单后应该清除购物车
        Cookie shopCartCookie = new Cookie(Constants.CART_COOKIE,null);
        shopCartCookie.setMaxAge(0);
        shopCartCookie.setPath("/");
        response.addCookie(shopCartCookie);


        Order o = orderService.getOrderByKey(orderId);
        model.addAttribute("order",o);
        return "product/confirmOrder";
    }
}
