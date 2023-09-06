package com.sunwook.wms.product.feature;

import com.sunwook.wms.product.domain.Category;
import com.sunwook.wms.product.domain.ProductRepository;
import com.sunwook.wms.product.domain.TemperatureZone;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegisterProductTest {
    @LocalServerPort
    private int port;

    private RegisterProduct registerProduct;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        if (RestAssured.UNDEFINED_PORT == RestAssured.port) {
            RestAssured.port = port;
        }
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

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/products")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        //then
        assertThat(productRepository.findAll()).hasSize(1);

    }

}
