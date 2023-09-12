package com.sunwook.wms.inbound.domain;

import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

public class Inbound {
    private final String title;
    private final String description;
    private final LocalDateTime orderRequestedAt;
    private final LocalDateTime estimatedArrivalAt;
    private final List<InboundItem> inboundItems;
    private Long id;

    public Inbound
            (String title,
             String description,
             LocalDateTime orderRequestedAt,
             LocalDateTime estimatedArrivalAt,
             List<InboundItem> inboundItems) {
        validateConstructor(
                title,
                description,
                orderRequestedAt,
                estimatedArrivalAt,
                inboundItems);
        this.title = title;
        this.description = description;
        this.orderRequestedAt = orderRequestedAt;
        this.estimatedArrivalAt = estimatedArrivalAt;
        this.inboundItems = inboundItems;
    }

    private void validateConstructor(
            String title,
            String description,
            LocalDateTime orderRequestedAt,
            LocalDateTime estimatedArrivalAt,
            List<InboundItem> inboundItems) {
        Assert.hasText(title, "입고 제목은 필수입니다");
        Assert.hasText(description, "입고 설명은 필수입니다");
        Assert.notNull(orderRequestedAt, "입고 요청일은 필수입니다");
        Assert.notNull(estimatedArrivalAt, "입고 예정은 필수입니다");
        Assert.notEmpty(inboundItems, "입고 품목은 필수입니다");
    }

    public void assignId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
