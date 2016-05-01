package cn.sina.core.domain.shop_cart;

import cn.sina.core.domain.product.Sku;

/**
 * Created by Jaynnay on 2016/4/15.
 */
public class ShopItem {
    private Sku sku;
    private int amount = 1;

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopItem shopItem = (ShopItem) o;

        return sku.getId().equals(shopItem.sku.getId());

    }

    @Override
    public int hashCode() {
        int result = sku.hashCode();
        result = 31 * result + amount;
        return result;
    }
}
