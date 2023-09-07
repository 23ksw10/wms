package com.sunwook.wms.product.feature;

import com.sunwook.wms.common.ApiTest;
import com.sunwook.wms.product.domain.ProductRepository;
import com.sunwook.wms.product.feature.api.RegisterProductApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegisterProductTest extends ApiTest {

    @Autowired
    private ProductRepository productRepository;


    @Test
    @DisplayName("상품을 생성한다.")
    void registerProduct() {
        // given
        new RegisterProductApi().request();

        //then
        assertThat(productRepository.findAll()).hasSize(1);

    }

}
