package com.medilabo.microservice_frontend.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.medilabo.microservice_frontend.dto.RapportDTO;

@FeignClient(name = "rapport", url = "http://localhost:8084")
public interface RapportProxy {

    @GetMapping("/report/{id}")
    RapportDTO getReport(@PathVariable("id") Long id);

}
