package surofu.pixelart.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    @Value("${surofu.pixelart.app.jwt.secret}")
    private String secret;

    @Value("${surofu.pixelart.app.jwt.lifetime}")
    private Duration accessLifetime;

    @Value("${surofu.pixelart.app.jwt.refresh-lifetime}")
    private Duration refreshLifetime;

    public String generateAccessToken(UserDetails userDetails) {
        return this.generateTokenWithExpiresDate(userDetails, this.accessLifetime);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return this.generateTokenWithExpiresDate(userDetails, this.refreshLifetime);
    }

    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        List<?> rawRoles = getAllClaimsFromToken(token).get("roles", List.class);
        return rawRoles.stream()
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .collect(Collectors.toList());
    }

    private Claims getAllClaimsFromToken(String token) {

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String generateTokenWithExpiresDate(UserDetails userDetails, Duration expires) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        claims.put("roles", roles);
        claims.put("sub", userDetails.getUsername());

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + expires.toMillis());

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiresAt)
                .signWith(key)
                .compact();
    }
}
