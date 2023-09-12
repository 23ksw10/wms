package com.sunwook.wms.inbound.domain;

import com.sunwook.wms.product.domain.Product;
import org.springframework.util.Assert;

public class InboundItem {
    private final Product product;
    private final Long quantity;
    private final Long unitPrice;
    private final String description;

    public InboundItem(
            Product product,
            Long quantity,
            Long unitPrice,
            String description) {
        validateConstructor(product, quantity, unitPrice, description);
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.description = description;
    }

    private void validateConstructor(
            Product product,
            Long quantity,
            Long unitPrice,
            String description) {
        Assert.notNull(product, "상품은 필수입니다");
        Assert.notNull(quantity, "수량은 필수입니다");
        if (quantity < 1) {
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다");
        }
        Assert.notNull(unitPrice, "단가는 필수입니다");
        if (unitPrice < 0) {
            throw new IllegalArgumentException("가격은 0원 이상이어야 합니다");
        }
        Assert.hasText(description, "제품 설명은 필수입니다");
    }
}
