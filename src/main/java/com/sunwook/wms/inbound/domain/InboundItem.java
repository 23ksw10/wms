package com.sunwook.wms.inbound.domain;

import com.sunwook.wms.product.domain.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Entity
@Table(name = "inbound_item")
@Comment("입고 상품")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InboundItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("입고 상품 아이디")
    @Column(name = "inbound_item_id")
    private Long id;
    @Comment("상품")
    @JoinColumn(name = "product_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @Comment("수량")
    @Column(name = "quantity", nullable = false)
    private Long quantity;
    @Comment("단가")
    @Column(name = "unit_price", nullable = false)
    private Long unitPrice;
    @Comment("제품 설명")
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inbound_id", nullable = false)
    @Comment("입고 아이디")
    private Inbound inbound;

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

    public void assignInbound(Inbound inbound) {
        Assert.notNull(inbound, "입고는 필수입니다");
        this.inbound = inbound;
    }
}
