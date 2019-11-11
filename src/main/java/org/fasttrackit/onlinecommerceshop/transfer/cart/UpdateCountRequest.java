package org.fasttrackit.onlinecommerceshop.transfer.cart;

import javax.validation.constraints.NotNull;

public class UpdateCountRequest {
    @NotNull
    private long productId;
    @NotNull
    private int count;

    public long getProductId() {
        return this.productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "UpdateCountRequest{" +
                "productId=" + productId +
                ", count=" + count +
                '}';
    }
}
