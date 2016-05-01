package cn.sina.core.controller;

import cn.sina.core.domain.product.Sku;
import cn.sina.core.domain.shop_cart.ShopCart;
import cn.sina.core.domain.shop_cart.ShopItem;
import cn.sina.core.domain.user.Addr;
import cn.sina.core.domain.user.Buyer;
import cn.sina.core.query.user.AddrQuery;
import cn.sina.core.service.product.SkuService;
import cn.sina.core.service.user.AddrService;
import cn.sina.core.web.Constants;
import cn.sina.core.web.session.SessionProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by Jaynnay on 2016/4/15.
 */
@Controller
public class CartController {

    @Autowired
    private SkuService skuService;

    @Autowired
    private SessionProvider sessionProvider;

    @Autowired
    private AddrService addrService;
    /**
     * 点击购买
     * 为什么没法在购物车中追加商品呢？因为每次都是new一个cookie，以前的被覆盖了，所以应该先判断有没有cookie
     */
    @RequestMapping(value = "/cart/buy.shtml")
    public String buy(Integer skuId, Integer amount, Integer buyLimit, Integer productId,
                      ModelMap model, HttpServletRequest request, HttpServletResponse response){

        ObjectMapper om = new ObjectMapper();

        //购物车也要分情况：cookie里有了就用cookie里的，没有就新建一个
        ShopCart shopCart = null;

        //获取request中所有cookie
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(Constants.CART_COOKIE)){
                    String cartJson = cookie.getValue();
                    try {
                        //把json转换成对象
                        shopCart = om.readValue(cartJson,ShopCart.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;//找到就不要遍历了
                }
            }
        }


        //如果cookie中没有，就新建一个购物车
        if(shopCart == null){
            shopCart = new ShopCart();
        }


        if(skuId!=null && amount!=null && amount>0 && buyLimit!=null && productId!=null){
            Sku skuInCart = new Sku();
            skuInCart.setId(skuId);
            skuInCart.setSkuUpperLimit(buyLimit);
            //购物条目
            ShopItem shopItem = new ShopItem();
            shopItem.setSku(skuInCart);
            shopItem.setAmount(amount);

            shopCart.setProductId(productId);
            shopCart.add(shopItem);

            //将购物车转成json对象
            om.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);//只转换不为null的字段
            StringWriter strWriter = new StringWriter();
            try {
                om.writeValue(strWriter,shopCart);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //放入cookie中
            Cookie cartCookie = new Cookie(Constants.CART_COOKIE,strWriter.toString());
            cartCookie.setMaxAge(60*60*24*7);
            //如果不设置，默认是/cart,导致别的访问获取不到Cooike
            cartCookie.setPath("/");
            response.addCookie(cartCookie);

        }

        //把购物车里所有购物项查出来
        List<ShopItem> shopItems = shopCart.getShopItems();
        for (ShopItem item:
                shopItems) {
            Sku sku =  skuService.getSkuByKey(item.getSku().getId());
            item.setSku(sku);
        }
        model.addAttribute("cart",shopCart);
        return "product/cart";
    }

    /**
     * 清空购物车
     * 1.用同名cookie覆盖
     * 2.将购物车中条目情况，即list的clear()方法
     * @return
     */
    @RequestMapping(value = "/cart/clear.shtml")
    public String clear(HttpServletResponse response){
        Cookie cookie = new Cookie(Constants.CART_COOKIE,null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/cart/buy.shtml";
    }

    @RequestMapping(value = "/cart/deleteItem.shtml")
    public String deleteItem(Integer skuId,HttpServletRequest request,HttpServletResponse response){
        ObjectMapper om = new ObjectMapper();
        ShopCart shopCart = null;
        //获取request中所有cookie
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(Constants.CART_COOKIE)){
                    String cartJson = cookie.getValue();
                    try {
                        shopCart = om.readValue(cartJson,ShopCart.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        if(shopCart != null){
            Sku sku = new Sku();
            sku.setId(skuId);
            ShopItem shopItem = new ShopItem();
            shopItem.setSku(sku);

            //删除sku
            shopCart.delete(shopItem);
            //再将购物车保存回cookie
            om.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);//只转换不为null的字段
            StringWriter strWriter = new StringWriter();
            try {
                om.writeValue(strWriter,shopCart);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //放入cookie中
            Cookie cartCookie = new Cookie(Constants.CART_COOKIE,strWriter.toString());
            cartCookie.setMaxAge(60*60*24*7);
            //如果不设置，默认是/cart,导致别的访问获取不到Cooike
            cartCookie.setPath("/");
            response.addCookie(cartCookie);
        }

        return "redirect:/cart/buy.shtml";
    }


    /**
     * 购物车结算
     */
    @RequestMapping(value = "/buyer/statement.shtml")
    public String statement(HttpServletRequest request,ModelMap model,HttpServletResponse response) {
        ObjectMapper om = new ObjectMapper();
        ShopCart shopCart = null;
        //获取request中所有cookie
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
                    break;//找到就不要遍历了
                }
            }
        }
        if (shopCart != null) {
            List<ShopItem> shopItems = shopCart.getShopItems();
            if (shopItems != null && shopItems.size() > 0) {
                //每一个购物项的数量 "应该小于" 该sku的库存
                int before = shopItems.size();
                for (ShopItem item : shopItems) {
                    Sku sku = skuService.getSkuByKey(item.getSku().getId());
                    if (item.getAmount() > sku.getStockInventory()) {
                        //如果超过库存限制，就删除此商品，并刷新购物车
                        shopCart.delete(item);
                    }
                }
                int after = shopItems.size();
                if (before > after) {//表示确实删除了某些购物项，购物车重新写入cookie，并刷新
                    //将购物车转成json对象
                    om.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
                    StringWriter strWriter = new StringWriter();
                    try {
                        om.writeValue(strWriter, shopCart);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Cookie cartCookie = new Cookie(Constants.CART_COOKIE, strWriter.toString());
                    cartCookie.setMaxAge(60 * 60 * 24 * 7);
                    cartCookie.setPath("/");
                    response.addCookie(cartCookie);
                    return "redirect:/cart/buy.shtml";
                } else {
                    //如果没超过限制，走正常购物流程
                    Buyer buyer = (Buyer) sessionProvider.getAttribute(request, Constants.BUYER_SESSION);
                    //加载收货地址
                    AddrQuery addrQuery = new AddrQuery();
                    addrQuery.setBuyerId(buyer.getUsername());
                    addrQuery.setIsDef(1);
                    List<Addr> addrList = addrService.getAddrList(addrQuery);
                    model.addAttribute("addr", addrList.get(0));

                    //查询出sku所有信息
                    for (ShopItem si : shopItems) {
                        Sku s = skuService.getSkuByKey(si.getSku().getId());
                        si.setSku(s);
                    }
                    model.addAttribute("cart", shopCart);
                    return "product/productOrder";
                }
            } else {
                return "redirect:/shopping/buyCart.shtml";
            }
        } else {
            return "redirect:/shopping/buyCart.shtml";
        }
    }


}
