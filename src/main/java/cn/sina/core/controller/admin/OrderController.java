package cn.sina.core.controller.admin;

import cn.sina.core.domain.order.Detail;
import cn.sina.core.domain.order.Order;
import cn.sina.core.domain.user.Addr;
import cn.sina.core.query.order.DetailQuery;
import cn.sina.core.query.order.OrderQuery;
import cn.sina.core.query.user.AddrQuery;
import cn.sina.core.service.order.DetailService;
import cn.sina.core.service.order.OrderService;
import cn.sina.core.service.user.AddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Jaynnay on 2016/4/20.
 * 订单后台控制器
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DetailService detailService;

    @Autowired
    private AddrService addrService;
    /**
     * 加载左侧菜单数据，根据两个参数确定
     * @param isPaiy
     * @param state
     * @param model
     * @return
     */
    @RequestMapping(value = "/order/list.do")
    public String list(Integer isPaiy, Integer state, ModelMap model){
        OrderQuery orderQuery = new OrderQuery();
        if(isPaiy!=null){
            orderQuery.setIsPaiy(isPaiy);
        }
        if(state != null){
            orderQuery.setState(state);
        }

        List<Order> orders = orderService.getOrderList(orderQuery);

        model.addAttribute("orders",orders);
        return "order/list";
    }

    @RequestMapping(value = "/order/view.do")
    public String view(Integer id, ModelMap model){

        Order order = orderService.getOrderByKey(id);
        model.addAttribute("order",order);

        DetailQuery detailQuery = new DetailQuery();
        detailQuery.setOrderId(id);
        List<Detail> details = detailService.getDetailList(detailQuery);
        model.addAttribute("details",details);

        AddrQuery addrQuery = new AddrQuery();
        addrQuery.setBuyerId(order.getBuyerId());
        addrQuery.setIsDef(1);
        List<Addr> addrs = addrService.getAddrList(addrQuery);
        model.addAttribute("addr",addrs.get(0));

        return "order/view";
    }
}
