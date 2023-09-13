package com.sunwook.wms.inbound.feature;

import com.sunwook.wms.inbound.domain.InboundRepository;
import com.sunwook.wms.product.domain.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.sunwook.wms.product.fixture.ProductFixture.aProduct;
import static org.mockito.Mockito.*;

public class RegisterInboundTest {
    private RegisterInbound registerInbound;
    private ProductRepository productRepository;
    private InboundRepository inboundRepository;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        inboundRepository = new InboundRepository();
        registerInbound = new RegisterInbound(productRepository, inboundRepository);
    }

    @Test
    @DisplayName("입고를 등록한다.")
    void registerInbound() {
        when(productRepository.findById(anyLong()))
                .thenReturn(Optional.of(aProduct().build()));

        final LocalDateTime orderRequestedAt = LocalDateTime.now();
        final LocalDateTime estimatedArrivalAt = LocalDateTime.now().plusDays(1);
        final Long productId = 1L;
        final Long quantity = 1L;
        final Long unitPrice = 1500L;
        final RegisterInbound.Request.Item inboundItem = new RegisterInbound.Request.Item(
                productId,
                quantity,
                unitPrice,
                "description"
        );
        final List<RegisterInbound.Request.Item> inboundItems = List.of(inboundItem);
        RegisterInbound.Request request = new RegisterInbound.Request(
                "title",
                "description",
                orderRequestedAt,
                estimatedArrivalAt,
                inboundItems
        );
        registerInbound.request(request);
    }

}
