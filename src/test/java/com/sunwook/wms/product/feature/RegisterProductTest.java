package com.sunwook.wms.product.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterProductTest {

    private RegisterProduct registerProduct;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        registerProduct = new RegisterProduct(productRepository);
    }

    @Test
    @DisplayName("상품을 생성한다.")
    void registerProduct() {
        // given
        final Long weightInGrams = 1000L;

        final Long widthInMillimeters = 100L;
        final Long heightInMillimeters = 100L;
        final Long lengthInMillimeters = 100L;

        RegisterProduct.Request request = new RegisterProduct.Request(
                "name",
                "code",
                "description",
                "brand",
                "maker",
                "origin",
                Category.ELECTRONICS,
                TemperatureZone.ROOM_TEMPERATURE,
                weightInGrams, // gram
                widthInMillimeters, // 너비
                heightInMillimeters, //높이
                lengthInMillimeters// 길이
        );
        //when
        registerProduct.request(request);

        //then
        assertThat(productRepository.findAll()).hasSize(1);

    }

}
