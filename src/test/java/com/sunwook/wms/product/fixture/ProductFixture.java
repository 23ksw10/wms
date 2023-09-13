package com.sunwook.wms.product.fixture;

import com.sunwook.wms.product.domain.Category;
import com.sunwook.wms.product.domain.Product;
import com.sunwook.wms.product.domain.TemperatureZone;

public class ProductFixture {
    private String name = "name";
    private String code = "code";
    private String description = "description";
    private String brand = "brand";
    private String maker = "maker";
    private String origin = "origin";
    private Category category = Category.ELECTRONICS;
    private TemperatureZone temperatureZone = TemperatureZone.ROOM_TEMPERATURE;
    private Long weightInGrams = 1000L;
    private ProductSizeFixture productSize = ProductSizeFixture.aProductSize();

    public static ProductFixture aProduct() {
        return new ProductFixture();
    }

    public ProductFixture name(String name) {
        this.name = name;
        return this;
    }

    public ProductFixture code(String code) {
        this.code = code;
        return this;
    }

    public ProductFixture description(String description) {
        this.description = description;
        return this;
    }

    public ProductFixture brand(String brand) {
        this.brand = brand;
        return this;
    }

    public ProductFixture maker(String maker) {
        this.maker = maker;
        return this;
    }

    public ProductFixture origin(String origin) {
        this.origin = origin;
        return this;
    }

    public ProductFixture category(Category category) {
        this.category = category;
        return this;
    }

    public ProductFixture temperatureZone(TemperatureZone temperatureZone) {
        this.temperatureZone = temperatureZone;
        return this;
    }

    public ProductFixture weightInGrams(Long weightInGrams) {
        this.weightInGrams = weightInGrams;
        return this;
    }

    public ProductFixture productSize(ProductSizeFixture productSize) {
        this.productSize = productSize;
        return this;
    }

    public Product build() {
        return new Product(
                name,
                code,
                description,
                brand,
                maker,
                origin,
                category,
                temperatureZone,
                weightInGrams,
                productSize.build()
        );
    }
}