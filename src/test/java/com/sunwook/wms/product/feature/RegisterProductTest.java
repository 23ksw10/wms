package com.sunwook.wms.product.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        private final ProductRepository productRepository;

        public RegisterProduct(final ProductRepository productRepository) {
            this.productRepository = productRepository;
        }


        public void request(final Request request) {
            // request에서 필요한 값들을 사용해 상품 도메인을 생성하고 저장한다
            Product product = request.toDomain();
            productRepository.save(product);
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

            public Request {
                Assert.hasText(name, "상품명은 필수입니다.");
                Assert.hasText(code, "상품코드는 필수입니다.");
                Assert.hasText(description, "상품설명은 필수입니다.");
                Assert.hasText(brand, "브랜드는 필수입니다.");
                Assert.hasText(maker, "제조사는 필수입니다.");
                Assert.hasText(origin, "원산지는 필수입니다.");
                Assert.notNull(category, "카테고리는 필수입니다.");
                Assert.notNull(temperatureZone, "온수대는 필수입니다.");
                Assert.notNull(weightInGrams, "무게는 필수입니다.");
                if (0 > weightInGrams) {
                    throw new IllegalArgumentException("무게는 0보다 작을 수 없습니다");
                }
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
            }

            public Product toDomain() {
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
                        new ProductSize(
                                widthInMillimeters,
                                heightInMillimeters,
                                lengthInMillimeters)
                );
            }


        }

    }

    public static class ProductSize {
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

    public static class Product {
        private final String name;
        private final String code;
        private final String description;
        private final String brand;
        private final String maker;
        private final String origin;
        private final Category category;
        private final TemperatureZone temperatureZone;
        private final Long weightInGrams;
        private final ProductSize productSize;
        private Long id;

        public Product(
                String name,
                String code,
                String description,
                String brand,
                String maker,
                String origin,
                Category category,
                TemperatureZone temperatureZone,
                Long weightInGrams,
                ProductSize productSize) {
            validateConstructor(name, code, description, brand, maker, origin, category, temperatureZone, weightInGrams, productSize);
            this.name = name;
            this.code = code;
            this.description = description;
            this.brand = brand;
            this.maker = maker;
            this.origin = origin;
            this.category = category;
            this.temperatureZone = temperatureZone;
            this.weightInGrams = weightInGrams;
            this.productSize = productSize;
        }

        private void validateConstructor(String name, String code, String description, String brand, String maker, String origin, Category category, TemperatureZone temperatureZone, Long weightInGrams, ProductSize productSize) {
            Assert.hasText(name, "상품명은 필수입니다.");
            Assert.hasText(code, "상품코드는 필수입니다.");
            Assert.hasText(description, "상품설명은 필수입니다.");
            Assert.hasText(brand, "브랜드는 필수입니다.");
            Assert.hasText(maker, "제조사는 필수입니다.");
            Assert.hasText(origin, "원산지는 필수입니다.");
            Assert.notNull(category, "카테고리는 필수입니다.");
            Assert.notNull(temperatureZone, "온수대는 필수입니다.");
            Assert.notNull(weightInGrams, "무게는 필수입니다.");
            Assert.notNull(productSize, "상품크기는 필수입니다");
        }

        public void assignId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }

    private static class ProductRepository {
        private final Map<Long, Product> products = new HashMap<>();
        private Long nextId = 1L;

        public void save(Product product) {
            //상품저장
            product.assignId(nextId++);
            products.put(product.getId(), product);
        }

        public List<Product> findAll() {
            return new ArrayList<>(products.values());
        }
    }
}
