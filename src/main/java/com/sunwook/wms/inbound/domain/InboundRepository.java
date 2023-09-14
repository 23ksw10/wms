package com.sunwook.wms.inbound.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface InboundRepository extends JpaRepository<Inbound, Long> {
}
