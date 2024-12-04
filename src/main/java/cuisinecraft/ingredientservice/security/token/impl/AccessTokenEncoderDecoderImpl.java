package cuisinecraft.ingredientservice.security.token.impl;


import cuisinecraft.ingredientservice.security.token.AccessToken;
import cuisinecraft.ingredientservice.security.token.AccessTokenDecoder;
import cuisinecraft.ingredientservice.security.token.AccessTokenEncoder;
import cuisinecraft.ingredientservice.security.token.exception.InvalidAccessTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccessTokenEncoderDecoderImpl implements AccessTokenEncoder, AccessTokenDecoder {
    private final Key key;

    public AccessTokenEncoderDecoderImpl(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String encode(AccessToken accessToken) {
        Map<String, Object> claimsMap = new HashMap<>();

        // Assuming getRole() returns a single Role enum
        if (accessToken.getRole() != null) {
            claimsMap.put("role", accessToken.getRole());
        }

        if (accessToken.getUserid() != null) {
            claimsMap.put("userid", accessToken.getUserid());
        }

        Instant now=Instant.now();
        System.out.println("Current Instant: " + now);

        return Jwts.builder()
                .setSubject(accessToken.getSubject())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .addClaims(claimsMap)
                .signWith(key)
                .compact();

    }

    @Override
    public AccessToken decode(String accessTokenEncoded) {
        try {
            Jwt<?, Claims> jwt = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(accessTokenEncoded);
            Claims claims = jwt.getBody();
            System.out.println("Current Instant: " + claims);

            String role = String.valueOf(claims.get("role", String.class));
            Long userid = claims.get("userid", Long.class);

            return new AccessTokenImpl(claims.getSubject(), userid,role);
        } catch (JwtException e) {
            throw new InvalidAccessTokenException(e.getMessage());
        }
    }
}
