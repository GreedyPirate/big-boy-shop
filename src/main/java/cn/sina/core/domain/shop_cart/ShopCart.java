package cn.sina.core.domain.shop_cart;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaynnay on 2016/4/15.
 */
public class ShopCart {
    private List<ShopItem> shopItems = new ArrayList<ShopItem>();
    private Integer productId;//要添加的商品id

    /**
     * 添加商品，应该判断这个item是否应该在购物车了，如果已经有了，数量加1即可
     * 注意sku购买限制的判断
     * contains和equeals都要重写euqals方法
     * 注意重写equals方法，如果两个ShopItem的skuid一样就认为相等
     * @param shopItem
     */
    public void add(ShopItem shopItem){
       if(shopItems.contains(shopItem)){
          //把该条目的数量+1
           for(ShopItem item:shopItems){
               if (item.equals(shopItem)){
                   //总的购买数 = 购物车原有的 + 用户提交的(不一定是1件)
                   int amount = item.getAmount() + shopItem.getAmount();
                   //如果小于该sku的购买限制
                   if(amount < item.getSku().getSkuUpperLimit()){
                       item.setAmount(amount);
                   }else{
                       item.setAmount(item.getSku().getSkuUpperLimit());
                   }
               }
           }
       }else{
            shopItems.add(shopItem);
       }
    }

    public void delete(ShopItem shopItem){
        shopItems.remove(shopItem);
    }

    /**
     * 共多少个商品
     * @return
     */
    @JsonIgnore
    public int getTotalProductAmount(){
        int total = 0;
        for(ShopItem item : shopItems){
            total += item.getAmount();
        }
        return total;
    }

    /**
     * 商品总价
     * @return
     */
    @JsonIgnore
    public double getTotalProductPrice(){
        double total = 0;
        for(ShopItem item : shopItems){
            total += item.getSku().getSkuPrice() * item.getAmount();
        }
        return total;
    }

    /**
     * 运费：大于50包邮
     * @return
     */
    @JsonIgnore
    public double getPostage(){
        double postage = 0;
        if(getTotalProductPrice() < 50){
            postage = 10;
        }
        return postage;
    }

    /**
     * 订单总价 = 商品总价 + 运费
     * @return
     */
    @JsonIgnore
    public double getTotalPrice(){
        return getTotalProductPrice() + getPostage();
    }












    public List<ShopItem> getShopItems() {
        return shopItems;
    }

    public void setShopItems(List<ShopItem> shopItems) {
        this.shopItems = shopItems;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

}
