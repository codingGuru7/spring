/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cdac.egov.wamis.security.jwt;

import static com.cdac.egov.wamis.security.jwt.SecurityConstants.SECRET;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ambarishn
 */
public class JwtFacade {
//    private Claims claims;

    private Optional<Claims> claims = Optional.empty();

    public JwtFacade() {
    }

    public JwtFacade(HttpServletRequest request) {
        if (request != null && request.getHeader(SecurityConstants.HEADER_STRING) != null) {
            String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
            this.claims = Optional.of(Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody());
        }
    }

    public Optional<Claims> getClaims() {
        return claims;
    }

    public Optional<String> getSubject() {
        if (claims.isPresent()) {
            return Optional.ofNullable(claims.get().getSubject());
        } else {
            return Optional.empty();
        }
    }

    public Optional<Date> getExpiration() {
        return Optional.ofNullable(claims.get().getExpiration());
    }
    
    public Optional<String> getUserId() {
        return Optional.ofNullable(claims.get().getId());
    }

}
