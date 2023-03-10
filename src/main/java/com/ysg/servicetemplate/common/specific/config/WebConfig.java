package com.ysg.servicetemplate.common.specific.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebConfig {
    @Component
    public class RequestWrapperFilter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest httpServletRequest,
                HttpServletResponse httpServletResponse,
                FilterChain filterChain) throws ServletException, IOException {
            filterChain.doFilter(new ContentCachingRequestWrapper(httpServletRequest), httpServletResponse);
        }
    }
}
