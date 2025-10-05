package com.medilabo.microservice_frontend.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.medilabo.microservice_frontend.config.FeignConfig;
import com.medilabo.microservice_frontend.dto.RapportDTO;

@FeignClient(name = "rapport", url = "http://microservice-gateway:8090", configuration = FeignConfig.class)
public interface RapportProxy {

    @GetMapping("/api/report/{id}")
    RapportDTO getReport(@PathVariable("id") Long id);

}
