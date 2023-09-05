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
        RegisterProduct.Request request = new RegisterProduct.Request(
                "name",
                "code",
                "description",
                "brand",
                "maker",
                "origin",
                Category.ELECTRONICS,

                );
    }

    public enum Category {

        ELECTRONICS("전자기기");

        private final String description;

        Category(final String description) {
            this.description = description;
        }
    }

    public static class RegisterProduct {
        public void request() {

        }

        public record Request() {

        }
    }
}
