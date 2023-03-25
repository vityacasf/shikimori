package com.example.gateway.config.security.filter;


import com.example.gateway.config.security.jwt.JwtProvider;
import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
  public static final String TOKEN_NAME = "TOKEN";
  private final JwtProvider jwtProvider;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {
    final String token = getTokenFromRequest(request);

    if (jwtProvider.validateToken(token)) {
      final Claims claims = jwtProvider.getTokenClaims(token);

      final var oAuth2User = new DefaultOAuth2User(Collections.emptyList(), claims, "sub");

      Collection<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority("ROLE_" + claims.get("roleName")));

      final OAuth2AuthenticationToken auth = new OAuth2AuthenticationToken(
          oAuth2User, authorities, "application");
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
    filterChain.doFilter(request, response);
  }

  private String getTokenFromRequest(final HttpServletRequest request) {
    return Optional.of(request)
        .map(HttpServletRequest::getCookies)
        .map(Arrays::asList)
        .filter(CollectionUtils::isNotEmpty)
        .stream()
        .flatMap(List::stream)
        .filter(cookie -> TOKEN_NAME.equals(cookie.getName()))
        .map(Cookie::getValue)
        .findFirst()
        .orElse("");
  }
}
