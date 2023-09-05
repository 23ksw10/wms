package com.sunwook.wms.product.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegisterProductTest {

    private RegisterProduct registerProduct;

    @BeforeEach
    void setUp() {
        registerProduct = new RegisterProduct;
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
//        assertThat(productRepository.findAll()).hasSize(1);

    }

    public enum Category {

        ELECTRONICS("전자기기");

        private final String description;

        Category(final String description) {
            this.description = description;
        }
    }

    public enum TemperatureZone {

        ROOM_TEMPERATURE("상온");

        private final String description;

        TemperatureZone(final String description) {
            this.description = description;
        }
    }

    public static class RegisterProduct {
        public void request(Request request) {

        }

        public record Request(
                String name,
                String code,
                String description,
                String brand,
                String maker,
                String origin,
                Category category,
                TemperatureZone temperatureZone,
                Long weightInGrams,
                Long widthInMillimeters,
                Long heightInMillimeters,
                Long lengthInMillimeters) {

        }
    }
}
