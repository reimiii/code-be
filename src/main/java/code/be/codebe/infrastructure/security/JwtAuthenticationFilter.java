package code.be.codebe.infrastructure.security;

import code.be.codebe.application.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component @RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String username;

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    jwt = authHeader.substring(7);
    username = jwtService.extractUsername(jwt);

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (username != null && authentication == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

      if (jwtService.isTokenValid(jwt, userDetails)) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );

        WebAuthenticationDetailsSource detailsSource = new WebAuthenticationDetailsSource();
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        authToken.setDetails(detailsSource.buildDetails(request));
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
      }
    }

    filterChain.doFilter(request, response);
  }
}
