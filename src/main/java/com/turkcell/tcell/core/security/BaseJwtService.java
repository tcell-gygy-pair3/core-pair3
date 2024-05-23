package com.turkcell.tcell.core.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BaseJwtService {

//

    private long EXPIRATION=600000;
    private String SECRET_KEY="cQis7hFG8pR/i4ZvnVUQrHQJ6oTm2wEcZa5F2r8IryZuqZX3OeEvhXQvVfwkhrxTyZEoUZhSTtetKQKDY2J6t6ArhgO7+0XRVFFM3dE2N2+FKTU3v4ulQBxj3r7s3me4FPXrjgOJPkfJdBFYfQFYl/m28zkS1xzj/V+ZljEJ5u1kZSNVb6ImMAmYqN5U0ULaVRZUXmRYDxRDLBZ/uanIuc7E0FJ0oICLHRaG8lXz0VdoSgpLJrKsYuJaGNVvGvI6odHJec9VQAOIn5Ay5WtmSTb0KLtL5I5MpKdm6P4UgcBfGfd7CY9JR2gNsHlAZH+t/LkwBA75os6UVAbQQu0RPjkJ54sRIlCalvCApuhX36g=";
    public String generateToken(String username, Map<String,Object> extraClaims)
    {
        String token = Jwts
                .builder() // token üretmek için
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis())) // token başlangıç süresi
                .expiration(new Date(System.currentTimeMillis()+ EXPIRATION)) // token bitiş süresi
                .claims(extraClaims)
                .signWith(getSigninKey())
                .compact();

        return token;
    }

    public Boolean validateToken(String token)
    {
        return getTokenClaims(token).getExpiration().after(new Date());
    }

    private Claims getTokenClaims(String token)
    {
        // parser ise çözümlemek decode için kullanılır
        return  Jwts.parser().verifyWith((SecretKey) getSigninKey()).build().parseSignedClaims(token).getPayload();

    }
    public String extractUsername(String token)
    {
        return getTokenClaims(token).getSubject();
    }
    public List<String> extractRoles(String token)
    {
        return getTokenClaims(token).get("roles",List.class);
    }
    private Key getSigninKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
