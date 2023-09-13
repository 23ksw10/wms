package com.sunwook.wms.inbound.feature;

import com.sunwook.wms.inbound.domain.Inbound;
import com.sunwook.wms.inbound.domain.InboundItem;
import com.sunwook.wms.inbound.domain.InboundRepository;
import com.sunwook.wms.product.domain.ProductRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
class RegisterInbound {
    private final ProductRepository productRepository;
    private final InboundRepository inboundRepository;


    @PostMapping("/inbounds")
    @ResponseStatus(HttpStatus.CREATED)
    public void request(@RequestBody @Valid Request request) {
        // TODO 요청을 도메인으로 변경해서 도메인을 저장한다.
        Inbound inbound = createInbound(request);
        inboundRepository.save(inbound);
    }

    private Inbound createInbound(Request request) {
        return new Inbound(
                request.title,
                request.description,
                request.orderRequestedAt,
                request.estimatedArrivalAt,
                mapToInboundItems(request)
        );
    }

    private List<InboundItem> mapToInboundItems(Request request) {
        return request.inboundItems.stream()
                .map(this::newInboundItems)
                .toList();
    }

    private InboundItem newInboundItems(Request.Item item) {
        return new InboundItem(
                productRepository.getBy(item.productId),
                item.quantity,
                item.unitPrice,
                item.description
        );
    }

    public record Request(
            @NotBlank(message = "입고 제목은 필수입니다.")
            String title,
            @NotBlank(message = "입고 설명은 필수입니다.")
            String description,
            @NotNull(message = "입고 요청일은 필수입니다.")
            LocalDateTime orderRequestedAt,
            @NotNull(message = "입고 예정일은 필수입니다.")
            LocalDateTime estimatedArrivalAt,
            @NotEmpty(message = "입고 품목은 필수입니다.")
            List<Request.Item> inboundItems) {
        public Request {
            Assert.hasText(title, "입고 제목은 필수입니다.");
            Assert.hasText(description, "입고 설명은 필수입니다.");
            Assert.notNull(orderRequestedAt, "입고 요청일은 필수입니다.");
            Assert.notNull(estimatedArrivalAt, "입고 예정일은 필수입니다.");
            Assert.notEmpty(inboundItems, "입고 품목은 필수입니다.");
        }

        public record Item(
                @NotNull(message = "상품 번호는 필수입니다.")
                Long productId,
                @NotNull(message = "수량은 필수입니다.")
                Long quantity,
                @NotNull(message = "단가는 필수입니다.")
                @Min(value = 0, message = "단가는 0원 이상이어야 합니다.")
                Long unitPrice,
                @NotBlank(message = "상품 설명은 필수입니다.")
                String description) {
            public Item {
                Assert.notNull(productId, "상품 번호는 필수입니다.");
                Assert.notNull(quantity, "수량은 필수입니다.");
                if (quantity < 1) {
                    throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
                }
                Assert.notNull(unitPrice, "단가는 필수입니다.");
                if (unitPrice < 0) {
                    throw new IllegalArgumentException("단가는 0원 이상이어야 합니다.");
                }
                Assert.notNull(description, "상품 설명은 필수입니다.");

            }
        }
    }
}
