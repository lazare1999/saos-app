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
import java.util.Arrays;
import java.util.Objects;
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

        var apikeyAsParam = request.getParameterValues(APIKEY_NAME);
        if (Objects.equals(apikeyAsParam, null) || Objects.equals(apikeyAsParam.length, 0))
            return Optional.empty();


        var providedKey = Arrays.stream(apikeyAsParam)
                .filter(pk -> apiKey.equals(pk))
                .findAny()
                .orElse(null);

        if (providedKey == null)
            return Optional.empty();

        var ans = new ApiKeyAuth(providedKey, AuthorityUtils.NO_AUTHORITIES);

        return Optional.of(ans);
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        this.extract(request)
                .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
        filterChain.doFilter(request, response);
    }
}