package com.lazo.saos.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import static com.lazo.saos.security.SecurityConfiguration.APIKEY_NAME;

/**
 * Created by Lazo on 2023-12-16
 */

@Component
@RequiredArgsConstructor
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    @Value("${API_KEY}")
    private String apiKey;

    private Optional<Authentication> extract(HttpServletRequest request) {
        var providedKey = request.getHeader(APIKEY_NAME);
        if (providedKey == null || !providedKey.equals(apiKey))
            return Optional.empty();

        return Optional.of(new ApiKeyAuth(providedKey, AuthorityUtils.NO_AUTHORITIES));
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        this.extract(request)
                .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
        filterChain.doFilter(request, response);
    }
}