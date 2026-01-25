package com.anand.auth_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 1️⃣ Read Authorization header
        String authHeader = request.getHeader("Authorization");

        // 2️⃣ Check Bearer token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            // 3️⃣ Extract token
            String token = authHeader.substring(7);

            // 4️⃣ Validate token
            if (jwtUtil.isTokenValid(token)) {

                // 5️⃣ Extract data from token
                String email = jwtUtil.extractEmail(token);
                String role  = jwtUtil.extractRole(token);

                log.info("JWT email: {}, role: {}", email, role);

                // 6️⃣ Only set auth if not already set
                if (email != null &&
                        SecurityContextHolder.getContext().getAuthentication() == null) {

                    // 7️⃣ Convert role → GrantedAuthority
                    GrantedAuthority authority =
                            new SimpleGrantedAuthority(role); // ADMIN

                    // 8️⃣ Create Authentication object
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    email,     // principal
                                    null,      // credentials (not needed)
                                    List.of(authority)
                            );

                    // 9️⃣ Store authentication in SecurityContext
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authentication);

                    log.info("SecurityContext set with authority: {}", authority);
                }
            }
        }

        // 🔟 Continue filter chain
        filterChain.doFilter(request, response);
    }
}
