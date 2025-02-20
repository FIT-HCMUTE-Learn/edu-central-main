package com.landingis.api.client;

import com.landingis.api.config.FeignClientConfig;
import com.landingis.api.constant.FeignConstant;
import com.landingis.api.dto.AuthenticationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "authenticationClient",
        url = FeignConstant.EXTENSION_SERVICE_URL,
        configuration = FeignClientConfig.class
)
public interface AuthenticationClient {

    @GetMapping("/api/auth/token")
    AuthenticationDto generateToken();
}
