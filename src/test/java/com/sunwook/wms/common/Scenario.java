package com.sunwook.wms.common;

import com.sunwook.wms.inbound.feature.api.RegisterInboundApi;
import com.sunwook.wms.product.feature.api.RegisterProductApi;

public class Scenario {

    public static RegisterProductApi registerProduct() {
        return new RegisterProductApi();
    }

    public RegisterInboundApi registerInbound() {
        return new RegisterInboundApi();
    }
}
