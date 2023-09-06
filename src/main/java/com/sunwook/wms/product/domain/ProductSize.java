package com.sunwook.wms.product.domain;

import org.springframework.util.Assert;

public class ProductSize {
    private final Long widthInMillimeters;
    private final Long heightInMillimeters;
    private final Long lengthInMillimeters;

    public ProductSize(
            Long widthInMillimeters,
            Long heightInMillimeters,
            Long lengthInMillimeters) {
        Assert.notNull(widthInMillimeters, "가로는 필수입니다");
        if (0 > widthInMillimeters) {
            throw new IllegalArgumentException("가로는 0보다 작을 수 없습니다");
        }
        Assert.notNull(heightInMillimeters, "높이는 필수입니다");
        if (0 > heightInMillimeters) {
            throw new IllegalArgumentException("높이는 0보다 작을 수 없습니다");
        }
        Assert.notNull(lengthInMillimeters, "길이는 필수입니다");
        if (0 > lengthInMillimeters) {
            throw new IllegalArgumentException("길이는 0보다 작을 수 없습니다");
        }
        this.widthInMillimeters = widthInMillimeters;
        this.heightInMillimeters = heightInMillimeters;
        this.lengthInMillimeters = lengthInMillimeters;
    }
}
