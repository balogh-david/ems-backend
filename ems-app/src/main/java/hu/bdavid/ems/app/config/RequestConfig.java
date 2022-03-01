package hu.bdavid.ems.app.config;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import hu.bdavid.ems.app.rest.header.BaseHeader;

@RequiredArgsConstructor
@Configuration
public class RequestConfig {

    private final HttpServletRequest httpServletRequest;

    @Bean
    @RequestScope
    public BaseHeader createBaseHeader() {
        return BaseHeader.createBaseHeader(httpServletRequest);
    }
}
