package com.sunwook.wms.product.domain;

public enum Category {

    ELECTRONICS("전자기기");

    private final String description;

    Category(final String description) {
        this.description = description;
    }
}
