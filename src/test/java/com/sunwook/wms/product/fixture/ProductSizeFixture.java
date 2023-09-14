package com.sunwook.wms.product.fixture;

import com.sunwook.wms.product.domain.ProductSize;

public class ProductSizeFixture {
    private Long widthInMillimeters = 100L;
    private Long heightInMillimeters = 100L;
    private Long lengthInMillimeters = 100L;

    public static ProductSizeFixture aProductSize() {
        return new ProductSizeFixture();
    }

    public ProductSizeFixture widthInMillimeters(Long widthInMillimeters) {
        this.widthInMillimeters = widthInMillimeters;
        return this;
    }

    public ProductSizeFixture heightInMillimeters(Long heightInMillimeters) {
        this.heightInMillimeters = heightInMillimeters;
        return this;
    }

    public ProductSizeFixture lengthInMillimeters(Long lengthInMillimeters) {
        this.lengthInMillimeters = lengthInMillimeters;
        return this;
    }

    public ProductSize build() {
        return new ProductSize(
                widthInMillimeters,
                heightInMillimeters,
                lengthInMillimeters);
    }
}