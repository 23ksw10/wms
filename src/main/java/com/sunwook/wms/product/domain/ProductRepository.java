package com.sunwook.wms.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    default Product getBy(Long productId) {
        return findById(productId).orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다"));
    }
}
