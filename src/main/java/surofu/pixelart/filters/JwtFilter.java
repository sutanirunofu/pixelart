package surofu.pixelart.filters;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import surofu.pixelart.user.UserService;
import surofu.pixelart.utils.JwtUtils;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String accessToken = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            accessToken = authHeader.substring(7);

            try {
                username = jwtUtils.getUsername(accessToken);
            } catch (ExpiredJwtException e) {
                log.debug("Jwt has been expired");
            } catch (SignatureException e) {
                log.debug("Jwt bad signature");
            } catch (Exception e) {
                log.debug("Jwt could not be parsed: {}", e.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    jwtUtils.getRoles(accessToken).stream().map(SimpleGrantedAuthority::new).toList()
            );

            SecurityContextHolder.getContext().setAuthentication(token);

            try {
                UserDetails userDetails = userService.loadUserByUsername(username);
                accessToken = jwtUtils.generateAccessToken(userDetails);
                response.addHeader("Authorization", "Bearer " + accessToken);
            } catch (UsernameNotFoundException e) {
                log.debug("Username not found: {}", e.getMessage());
            }
        }

        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        filterChain.doFilter(request, response);
    }
}
